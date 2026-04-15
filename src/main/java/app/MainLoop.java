package app;

import user.UnregisteredClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * It implements the app's main loop
 * @author Ana O.R.
 * @version 1.0
 */
public class MainLoop extends Loop {
    /** This loop's instance */
    private static MainLoop INSTANCE;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * The Main loop's constructor
     */
    MainLoop() {

    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * It gets the Main loop's instance
     * @return the main loop's instance
     */
    protected static MainLoop getInstance() {
        if (MainLoop.INSTANCE == null) {
            MainLoop.INSTANCE = new MainLoop();
        }
        return INSTANCE;
    }

    /**
     * The apps main loop
     * @throws IOException              the io exception
     * @throws IllegalArgumentException the illegal argument exception
     * @throws NullPointerException     the null pointer exception
     */
    protected void main() throws IOException, IllegalArgumentException, NullPointerException {
        prueba();
    }

    public void prueba() {
        // crear ventana
        JFrame ventana = new JFrame("Mi GUI");

        // obtener contenedor, asignar layout
        Container contenedor = ventana.getContentPane();
        contenedor.setLayout(new FlowLayout());

        // crear componentes
        JLabel etiqueta = new JLabel("What do you wish to do?");
        JButton boton1 = new JButton("Browse as unregistered client");
        JButton boton2 = new JButton("Log in");

        // asociar acciones a componentes
        boton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentUser = new UnregisteredClient(true);
                try {
                    loopSelector();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // asociar acciones a componentes
        boton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    logger();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // añadir componentes al contenedor
        contenedor.add(etiqueta);
        contenedor.add(boton1);
        contenedor.add(boton2);

        // mostrar ventana
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(2000, 2400);
        ventana.setVisible(true);
    }
}