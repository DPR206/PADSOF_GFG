package main;

import model.store.Store;
import view.App;

import javax.swing.*;
import java.awt.*;

public class Main {
    /*----------------------------------------------------- MISC -----------------------------------------------------*/
    public static void main(String[] args) {
        //UIManager.put("Panel.background", new Color(246, 243, 238)); // Beige background as default
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    /* Load store */
                    Store.getInstance().loadStore("data", "statics");
                    /*new Figurine(5.35, "Hamster de arcilla", "Cutie", ".\\resources\\hamster.jpg", 1, "5cm x 2.5cm",
                            "Handmade", "Arcilla");
                    new Figurine(11.8, "Funko Pop Yoda", "Funko", ".\\resources\\yoda.jpg", 15, "10cm x 5cm", "Funko",
                            "Vinilo");
                    new Game(13, "Monopoly", "Juego de Monopoly duh", ".\\resources\\monopoly.jpg", 30, 2, "3-99",
                            GameStyle.DICE);
                    new Game(5.75, "Polilla Tramposa", "Muy chuli y tal", ".\\resources\\polilla.jpg", 56, 4, "+6",
                            GameStyle.CARDS);*/
                    new App().setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}