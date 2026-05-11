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

//                    if (Store.getInstance().getEmployeeList().isEmpty()) {
//                        Manager.getInstance().addEmployee("emp123", "alice", Permission.STORE, Permission.ORDER);
//                        Manager.getInstance().addEmployee("emp456", "bob", Permission.ORDER);
//                        Manager.getInstance().addEmployee("emp789", "carol", Permission.EXCHANGE);
//                    } Ya están metidos

//                    ((RegisteredClient) Store.getInstance().getUsers().get("taha")).getWallet().getProducts().get(0)
//                                                                                   .valuate(5,
//                                                                                           ConservationStatus.SLIGHTLY_USED);
//                    ((RegisteredClient) Store.getInstance().getUsers().get("taha")).getWallet().getProducts().get(1)
//                                                                                   .valuate(7.6,
//                                                                                           ConservationStatus.PERFECT);
//                    ((RegisteredClient) Store.getInstance().getUsers().get("taha")).getWallet().getProducts().get(2)
//                                                                                   .valuate(1.23,
//                                                                                           ConservationStatus.VERY_GOOD);
//
//                    ((RegisteredClient) Store.getInstance().getUsers().get("martin")).getWallet().getProducts().get(0)
//                                                                                     .valuate(2,
//                                                                                             ConservationStatus.DAMAGED);

                    new App().setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}