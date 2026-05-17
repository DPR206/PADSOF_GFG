package view;

import model.exchange.Offer;
import model.product.Pack;
import model.product.SecondHandProduct;

import javax.swing.*;
import java.awt.*;

/**
 * The type Image adder.
 * @author Ana O.R.
 * @version 1.0
 */
public class ImageAdder {
    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * It gets the image label
     * @param imagePath the image path
     * @param width     the width
     * @param height    the height
     * @return the image label
     */
    /*----------------------------------------------------- MISC -----------------------------------------------------*/
    public static JLabel getImageLabel(String imagePath, int width, int height) {
        ImageIcon imageIcon = new ImageIcon(imagePath);
        JLabel image = new JLabel();
        image.setIcon(getScaledImage(imageIcon, width, height));
        return image;
    }

    /**
     * It gets the scaled image
     * @param imageIcon the image icon
     * @param w         the w
     * @param h         the h
     * @return the scaled image
     */
    public static ImageIcon getScaledImage(ImageIcon imageIcon, int w, int h) {
        Image image = imageIcon.getImage();
        Image newImg = image.getScaledInstance(w, h, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }

    /**
     * It gets the offer image panel
     * @param offer  the offer
     * @param width  the width
     * @param height the height
     * @return the offer image panel
     */
    public static JPanel getOfferImagePanel(Offer offer, int width, int height) {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        SecondHandProduct originProduct = offer.getOriginProducts().getFirst();
        SecondHandProduct destinationProduct = offer.getDestinationProducts().getFirst();

        panel.add(getImageLabel(originProduct.getPhoto(), width / 2, height / 2));
        panel.add(getImageLabel(".\\resources\\app\\arrow_left.png", width / 2, height / 2));
        panel.add(getImageLabel(".\\resources\\app\\arrow_right.png", width / 2, height / 2));
        panel.add(getImageLabel(destinationProduct.getPhoto(), width / 2, height / 2));

        return panel;
    }

    /**
     * It gets the pack image panel
     * @param pack   the pack
     * @param width  the width
     * @param height the height
     * @return the pack image panel
     */
    public static JPanel getPackImagePanel(Pack pack, int width, int height) {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel imageLabel = getImageLabel(pack.getPhoto(), width - 16, height - 16);
        panel.add(imageLabel, BorderLayout.NORTH);

        JLabel number = new JLabel("+" + (pack.getProducts().size() - 1));
        panel.add(number, BorderLayout.EAST);

        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        return panel;
    }
}