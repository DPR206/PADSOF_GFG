package view;

import javax.swing.*;
import java.awt.*;

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
}