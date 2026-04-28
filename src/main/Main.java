package main;

import view.App;

import javax.swing.*;
import java.awt.*;

public class Main {
    /*----------------------------------------------------- MISC -----------------------------------------------------*/
    public static void main(String[] args) {
        //UIManager.put("Panel.background", new Color(246, 243, 238)); // Beige background as default
        UIManager.put("Panel.background", Color.RED);
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        UIManager.getLookAndFeelDefaults().put("defaultFont", new Font("Comic Sans MS", Font.BOLD, 14));

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new App().setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}