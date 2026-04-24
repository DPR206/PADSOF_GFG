package graphicInterface;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Panel2 extends JPanel {

    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        // Llamamos al constructor para que se ejecute la lógica
        new Panel2();
    }

    // 2. El constructor: Define qué hace tu panel/ventana
    public Panel2() {
        // crear ventana
        JFrame ventana = new JFrame("Mi GUI");
        
        // obtener contenedor, asignar layout
        Container contenedor = ventana.getContentPane();
        contenedor.setLayout(new FlowLayout());
        
        // crear pantalla1
        JPanel pantalla1 = new JPanel();
        JButton boton = new JButton("siguiente");
        pantalla1.add(boton);
        pantalla1.setVisible(true);
        
        // crear pantalla 2
        JPanel pantalla2 = new JPanel();
        JLabel etiqueta = new JLabel("segunda pantalla");
        pantalla2.add(etiqueta);
        pantalla2.setVisible(false);
        
        // asociar accion al botón
        boton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pantalla1.setVisible(false);
                pantalla2.setVisible(true);
                // Actualización necesaria para FlowLayout
                contenedor.revalidate(); 
                contenedor.repaint();    
            }
        });
        
        // añadir pantallas al contenedor
        contenedor.add(pantalla1);
        contenedor.add(pantalla2);
        
        // mostrar ventana
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(300, 200);
        ventana.setVisible(true);
    }
}
