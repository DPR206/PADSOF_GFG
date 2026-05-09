package main;

import model.product.Pack;
import model.product.StoreProduct;
import model.store.Store;
import view.App;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.util.ArrayList;

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

                    ArrayList<StoreProduct> productList = new ArrayList<>();
                    productList.add(Store.getInstance().getStoreProductList().get(1));
                    productList.add(Store.getInstance().getStoreProductList().get(2));
                    productList.add(Store.getInstance().getStoreProductList().get(4));
                    new Pack(12, productList, ".\\resources\\app\\image_not_found.jpg");
                    productList = new ArrayList<>();
                    productList.add(Store.getInstance().getStoreProductList().get(3));
                    productList.add(Store.getInstance().getStoreProductList().get(5));
                    productList.add(Store.getInstance().getStoreProductList().get(4));
                    new Pack(11, productList, ".\\resources\\app\\image_not_found.jpg");
                    productList = new ArrayList<>();
                    productList.add(Store.getInstance().getStoreProductList().get(2));
                    productList.add(Store.getInstance().getStoreProductList().get(0));
                    productList.add(Store.getInstance().getStoreProductList().get(3));
                    new Pack(13, productList, ".\\resources\\app\\image_not_found.jpg");
                    productList = new ArrayList<>();
                    productList.add(Store.getInstance().getStoreProductList().get(0));
                    productList.add(Store.getInstance().getStoreProductList().get(1));
                    productList.add(Store.getInstance().getStoreProductList().get(2));
                    new Pack(13, productList, ".\\resources\\app\\image_not_found.jpg");

                    new App().setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}