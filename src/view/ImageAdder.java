package view;

import model.exchange.Offer;
import model.product.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ImageAdder {
    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    private ImageAdder() {

    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/
    public static JLabel getImageLabel(String imagePath, int width, int height) {
        ImageIcon imageIcon = new ImageIcon(imagePath);
        JLabel image = new JLabel();
        image.setIcon(getScaledImage(imageIcon, width, height));
        return image;
    }

    public static ImageIcon getScaledImage(ImageIcon imageIcon, int w, int h) {
        // NOTE: Es código adaptado de StackOverFlow: https://stackoverflow.com/a/18335435
        Image image = imageIcon.getImage();
        Image newImg = image.getScaledInstance(w, h, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }

    public static JPanel getOfferImagePanel(Offer offer, int width, int height) {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        ArrayList<SecondHandProduct> originProducts = offer.getOriginProducts();
        ArrayList<SecondHandProduct> destinationProducts = offer.getDestinationProducts();

        panel.add(getImageLabel(originProducts.getFirst().getPhoto(), width / 2, height / 2));
        panel.add(getImageLabel(".\\resources\\arrow_left.png", width / 2, height / 2));
        panel.add(getImageLabel(".\\resources\\arrow_right.png", width / 2, height / 2));
        panel.add(getImageLabel(destinationProducts.get(1).getPhoto(), width / 2, height / 2));

        return panel;
    }

    public static JPanel getPackImagePanel(Pack pack, int width, int height) {
        JPanel panel = new JPanel(new BorderLayout());

        StoreProduct product = pack.getProducts().getFirst();
        JLabel imageLabel = getImageLabel(product.getPhoto(), width, height);
        panel.add(imageLabel, BorderLayout.NORTH);

        JLabel number = new JLabel("+" + (pack.getProducts().size() - 1));
        panel.add(number, BorderLayout.EAST);

        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        return panel;
    }
}