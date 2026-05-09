package main;

import model.product.ProductType;
import model.product.SecondHandProduct;
import model.store.Store;
import model.user.Employee;
import model.user.RegisteredClient;
import model.user.StorePermission;
import view.App;
import view.RegisteredMainP;

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

                    RegisteredClient rc = new RegisteredClient("taha", "10282634M", "password", true);
                    rc.addProductWallet(new SecondHandProduct("1", "2", ".\\resources\\hamster.jpg",
                            ProductType.FIGURINE, rc));
                    rc.addProductWallet(new SecondHandProduct("2", "2", ".\\resources\\hamster.jpg",
                            ProductType.FIGURINE, rc));
                    rc.addProductWallet(new SecondHandProduct("3", "2", ".\\resources\\hamster.jpg",
                            ProductType.FIGURINE, rc));
                    rc.addProductWallet(new SecondHandProduct("4", "2", ".\\resources\\hamster.jpg",
                            ProductType.FIGURINE, rc));
                    rc.addProductWallet(new SecondHandProduct("5", "2", ".\\resources\\hamster.jpg",
                            ProductType.FIGURINE, rc));
                    rc.addProductWallet(new SecondHandProduct("6", "2", ".\\resources\\hamster.jpg",
                            ProductType.FIGURINE, rc));
                    rc.addProductWallet(new SecondHandProduct("7", "2", ".\\resources\\hamster.jpg",
                            ProductType.FIGURINE, rc));
                    rc.addProductWallet(new SecondHandProduct("8", "2", ".\\resources\\hamster.jpg",
                            ProductType.FIGURINE, rc));
                    rc.addProductWallet(new SecondHandProduct("9", "2", ".\\resources\\hamster.jpg",
                            ProductType.FIGURINE, rc));
                    rc.addProductWallet(new SecondHandProduct("10", "2", ".\\resources\\hamster.jpg",
                            ProductType.FIGURINE, rc));
                    rc.addProductWallet(new SecondHandProduct("11", "2", ".\\resources\\hamster.jpg",
                            ProductType.FIGURINE, rc));

                    rc = new RegisteredClient("martin", "10282634M", "password", true);
                    rc.addProductWallet(new SecondHandProduct("1", "2", ".\\resources\\virus.jpg",
                            ProductType.FIGURINE, rc));
                    rc.addProductWallet(new SecondHandProduct("2", "2", ".\\resources\\virus.jpg",
                            ProductType.FIGURINE, rc));
                    rc.addProductWallet(new SecondHandProduct("3", "2", ".\\resources\\virus.jpg",
                            ProductType.FIGURINE, rc));
                    rc.addProductWallet(new SecondHandProduct("4", "2", ".\\resources\\virus.jpg",
                            ProductType.FIGURINE, rc));
                    rc.addProductWallet(new SecondHandProduct("5", "2", ".\\resources\\virus.jpg",
                            ProductType.FIGURINE, rc));
                    rc.addProductWallet(new SecondHandProduct("6", "2", ".\\resources\\virus.jpg",
                            ProductType.FIGURINE, rc));
                    rc.addProductWallet(new SecondHandProduct("7", "2", ".\\resources\\virus.jpg",
                            ProductType.FIGURINE, rc));
                    rc.addProductWallet(new SecondHandProduct("8", "2", ".\\resources\\virus.jpg",
                            ProductType.FIGURINE, rc));
                    rc.addProductWallet(new SecondHandProduct("9", "2", ".\\resources\\virus.jpg",
                            ProductType.FIGURINE, rc));
                    rc.addProductWallet(new SecondHandProduct("10", "2", ".\\resources\\virus.jpg",
                            ProductType.FIGURINE, rc));
                    rc.addProductWallet(new SecondHandProduct("11", "2", ".\\resources\\virus.jpg",
                            ProductType.FIGURINE, rc));
                    StorePermission sp = new StorePermission();
                    Employee emp = new Employee("pwd", "emp1", false);
                    new App().setVisible(true);
                    Store.getInstance().getEmployees().put(emp.getId(), emp);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}