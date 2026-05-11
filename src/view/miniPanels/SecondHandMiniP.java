package view.miniPanels;

import model.product.SecondHandProduct;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

import static main.Main.brownColour;
import static view.ImageAdder.getImageLabel;
import static view.ImageAdder.getScaledImage;

public class SecondHandMiniP extends MiniPanel {
    private final JButton button;
    private final SecondHandProduct secondHandProduct;
    private final JLabel productImage;
    private final JTextPane productInfo;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public SecondHandMiniP(SecondHandProduct product, int index, String buttonName, String iconPath)
            throws BadLocationException {
        this.secondHandProduct = product;
        int width = 350;
        int height = 60;
        this.setLayout(new FlowLayout());

        button = new JButton(buttonName);
        button.setPreferredSize(new Dimension(buttonName.length() * 15, height));
        if (iconPath != null) {
            button.setIcon(getScaledImage(new ImageIcon(iconPath), height / 4, height / 4));
        }

        productImage = getImageLabel(product.getPhoto(), height, height);
        productInfo = new JTextPane();
        productInfo.setEditable(false);
        productInfo.setFocusable(false);

        SimpleAttributeSet attributes = new SimpleAttributeSet();
        StyleConstants.setAlignment(attributes, StyleConstants.ALIGN_LEFT);
        StyleConstants.setBold(attributes, true);
        productInfo.setCharacterAttributes(attributes, true);
        productInfo.setText(product.getName() + "\n");

        attributes = new SimpleAttributeSet();
        StyleConstants.setAlignment(attributes, StyleConstants.ALIGN_LEFT);

        Document doc = productInfo.getStyledDocument();
        if (product.getPrice() != 0) {
            doc.insertString(doc.getLength(), ("Valuation: " + String.format("%.2f", product.getPrice()) + " €\n"),
                    attributes);
        } else if (product.isPaidValuation()) {
            StyleConstants.setItalic(attributes, true);
            doc.insertString(doc.getLength(), ("Valuation pending\n"), attributes);
        } else {
            StyleConstants.setItalic(attributes, true);
            doc.insertString(doc.getLength(), ("No valuation\n"), attributes);
        }

        productInfo.setPreferredSize(new Dimension(width, height));

        JTextPane indexNum = new JTextPane();
        indexNum.setEditable(false);
        indexNum.setFocusable(false);

        SimpleAttributeSet attributes2 = new SimpleAttributeSet();
        StyleConstants.setBold(attributes2, true);
        indexNum.setCharacterAttributes(attributes2, true);
        indexNum.setText("\n" + index + ".");
        indexNum.setPreferredSize(new Dimension(25, height));

        this.add(indexNum);
        this.add(productImage);
        this.add(productInfo);
        this.add(button);

        this.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, brownColour));
    }

    public JButton getButton() {
        System.out.println("Returning button...");
        return button;
    }

    public JLabel getProductImage() {
        return productImage;
    }

    public JTextPane getProductInfo() {
        return productInfo;
    }

    public SecondHandProduct getSecondHandProduct() {
        return secondHandProduct;
    }
}