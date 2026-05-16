#!/usr/bin/env python3
import os
import re
from pathlib import Path
from collections import defaultdict

# Raíz del proyecto
src_dir = Path("C:\\eclipse\\workspace\\PADSOF_GFG\\src")

# Diccionario para organizar por paquete
classes_without_javadoc = defaultdict(list)

# Patrón para detectar declaraciones de clase
# Captura: public/private/abstract/etc. + class/interface/enum + nombre
class_pattern = re.compile(
    r'^\s*(public|private|protected|static|final|abstract|sealed|strictfp)?\s*(public|private|protected|static|final|abstract|sealed|strictfp)?\s*(class|interface|enum|record)\s+(\w+)',
    re.MULTILINE
)

def extract_package_from_path(file_path):
    """Extrae el nombre del paquete de la ruta del archivo"""
    relative_path = file_path.relative_to(src_dir)
    # Convierte ruta a paquete (por ejemplo: model/user/User.java -> model.user)
    parts = relative_path.parts[:-1]  # Excluye el nombre del archivo
    if parts:
        return '.'.join(parts)
    return 'default'

def has_javadoc_before(content, match_start):
    """Verifica si hay Javadoc inmediatamente antes de la clase"""
    # Busca hacia atrás desde el inicio del match
    # Permite espacios en blanco y newlines
    search_start = max(0, match_start - 1000)  # Busca en los últimos 1000 caracteres
    
    # Extrae el texto antes del match
    text_before = content[search_start:match_start]
    
    # Busca /** más reciente
    javadoc_match = None
    for match in re.finditer(r'/\*\*', text_before):
        javadoc_match = match
    
    if not javadoc_match:
        return False
    
    # Verifica que el */ esté entre el /** y la clase
    javadoc_start = search_start + javadoc_match.start()
    javadoc_end_match = re.search(r'\*/', content[javadoc_start:match_start])
    
    if not javadoc_end_match:
        return False
    
    # Verifica que no haya otra clase entre el /** y esta clase
    between_text = content[javadoc_start:match_start]
    # Si hay otro "class" (que no sea parte del comentario), entonces no es Javadoc para esta clase
    lines_between = between_text.split('\n')
    
    # El Javadoc debe estar inmediatamente antes (máximo unas líneas en blanco)
    # Cuenta líneas en blanco entre */ y class
    class_line_text = content[match_start:match_start+100]
    
    # Busca el */ más cercano al inicio de la clase
    close_comment_search = content[max(0, match_start-500):match_start]
    close_matches = list(re.finditer(r'\*/', close_comment_search))
    
    if not close_matches:
        return False
    
    last_close = close_matches[-1]
    close_pos = max(0, match_start-500) + last_close.end()
    
    # Verifica que entre */ y class solo hay espacios en blanco
    between = content[close_pos:match_start]
    if re.match(r'^\s*$', between):
        # Busca si hay un /** antes del */
        check_text = content[max(0, close_pos-2000):close_pos]
        if '/**' in check_text:
            return True
    
    return False

# Recorre todos los archivos Java
for java_file in sorted(src_dir.rglob('*.java')):
    try:
        with open(java_file, 'r', encoding='utf-8', errors='ignore') as f:
            content = f.read()
        
        # Extrae el paquete
        package_name = extract_package_from_path(java_file)
        
        # Busca todas las clases en el archivo
        for match in class_pattern.finditer(content):
            class_name = match.group(4)
            match_start = match.start()
            
            # Verifica si tiene Javadoc
            if not has_javadoc_before(content, match_start):
                line_number = content[:match_start].count('\n') + 1
                classes_without_javadoc[package_name].append({
                    'name': class_name,
                    'line': line_number,
                    'file': java_file.name
                })
    
    except Exception as e:
        print(f"Error procesando {java_file}: {e}")

# Imprime los resultados
print("\n" + "="*80)
print("CLASES SIN DOCUMENTACIÓN JAVADOC")
print("="*80 + "\n")

total_count = 0
for package_name in sorted(classes_without_javadoc.keys()):
    classes = classes_without_javadoc[package_name]
    if classes:
        print(f"\n📦 Paquete: {package_name}")
        print("-" * 80)
        for cls in sorted(classes, key=lambda x: x['name']):
            print(f"  • {cls['name']:40} (línea {cls['line']:4}) - {cls['file']}")
            total_count += 1

print("\n" + "="*80)
print(f"Total de clases sin Javadoc: {total_count}")
print("="*80)
