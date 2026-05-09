package main;

import model.store.Store;
import view.App;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

public class Main {
    public static Color backgroundColour = new Color(246, 243, 238);
    public static Color brownColour = new Color(84, 69, 65);

    /*----------------------------------------------------- MISC -----------------------------------------------------*/
    public static void main(String[] args) {
        /*try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } Da problemas*/

        UIManager.put("Panel.background", new ColorUIResource(246, 243, 238));
        UIManager.put("Menu.selectionBackground", backgroundColour);
        UIManager.put("FormattedTextField.background", backgroundColour);
        UIManager.put("EditorPane.background", backgroundColour);
        UIManager.put("TextPane.background", backgroundColour);
        UIManager.put("Button.background", brownColour);
        UIManager.put("Button.foreground", new Color(255, 255, 255));

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    /* Load store */
                    Store.getInstance().loadStore("data", "statics");

                    new App().setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}