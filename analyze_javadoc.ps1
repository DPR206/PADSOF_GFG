$srcDir = "C:\eclipse\workspace\PADSOF_GFG\src"
$classesWithoutJavadoc = @{}

# Obtiene todos los archivos .java
$javaFiles = Get-ChildItem -Path $srcDir -Filter "*.java" -Recurse

foreach ($file in $javaFiles) {
    $content = Get-Content -Path $file.FullName -Raw -Encoding UTF8
    
    # Calcula el paquete basado en la ruta
    $relativePath = $file.FullName.Substring($srcDir.Length + 1)
    $pathParts = $relativePath -split '\\'
    $packageName = ($pathParts[0..($pathParts.Length-2)] -join '.').Replace('\', '.')
    if (-not $packageName) { $packageName = "default" }
    
    # Busca declaraciones de clase
    $classMatches = [regex]::Matches($content, '(?m)^\s*(public\s+)?(abstract\s+)?(private\s+)?(static\s+)?(final\s+)?(sealed\s+)?(class|interface|enum|record)\s+(\w+)')
    
    foreach ($match in $classMatches) {
        $className = $match.Groups[8].Value
        $classStartPos = $match.Index
        
        # Verifica si hay Javadoc antes
        $searchStart = [Math]::Max(0, $classStartPos - 1000)
        $textBefore = $content.Substring($searchStart, $classStartPos - $searchStart)
        
        # Busca /** más reciente
        $javadocMatch = $null
        $lastJavadocStart = $textBefore.LastIndexOf('/**')
        
        if ($lastJavadocStart -ge 0) {
            # Verifica que el */ esté antes del class
            $javadocText = $textBefore.Substring($lastJavadocStart)
            if ($javadocText -contains '*/') {
                # Hay Javadoc
                $hasJavadoc = $true
            } else {
                $hasJavadoc = $false
            }
        } else {
            $hasJavadoc = $false
        }
        
        if (-not $hasJavadoc) {
            $lineNumber = ($content.Substring(0, $classStartPos) -split "`n").Count
            
            if (-not $classesWithoutJavadoc[$packageName]) {
                $classesWithoutJavadoc[$packageName] = @()
            }
            
            $classesWithoutJavadoc[$packageName] += @{
                Name = $className
                Line = $lineNumber
                File = $file.Name
            }
        }
    }
}

# Ordena y muestra los resultados
Write-Host ""
Write-Host ("=" * 80)
Write-Host "CLASES SIN DOCUMENTACION JAVADOC"
Write-Host ("=" * 80)
Write-Host ""

$totalCount = 0
foreach ($package in ($classesWithoutJavadoc.Keys | Sort-Object)) {
    $classes = $classesWithoutJavadoc[$package]
    if ($classes) {
        Write-Host ""
        Write-Host "Paquete: $package"
        Write-Host ("-" * 80)
        
        foreach ($cls in ($classes | Sort-Object Name)) {
            Write-Host ("  * {0,-40} (linea {1,4}) - {2}" -f $cls.Name, $cls.Line, $cls.File)
            $totalCount++
        }
    }
}

Write-Host ""
Write-Host ("=" * 80)
Write-Host "Total de clases sin Javadoc: $totalCount"
Write-Host ("=" * 80)
