package view.miniPanels;

import model.product.SecondHandProduct;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

import static main.Main.brownColour;
import static view.ImageAdder.getImageLabel;
import static view.ImageAdder.getScaledImage;

/**
 * The type Second hand mini p.
 * @author Ana O.R.
 * @version 1.0
 */
public class SecondHandMiniP extends AbstractMiniP {
    private final JButton button;
    private final SecondHandProduct secondHandProduct;
    private final JLabel productImage;
    private final JTextPane productInfo;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Second hand mini p.
     * @param product    the product
     * @param index      the index
     * @param buttonName the button name
     * @param iconPath   the icon path
     * @throws BadLocationException the bad location exception
     */
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

    /**
     * It gets the button
     * @return the button
     */
    public JButton getButton() {
        return button;
    }

    /**
     * It gets the product image
     * @return the product image
     */
    public JLabel getProductImage() {
        return productImage;
    }

    /**
     * It gets the product info
     * @return the product info
     */
    public JTextPane getProductInfo() {
        return productInfo;
    }

    /**
     * It gets the second hand product
     * @return the second hand product
     */
    public SecondHandProduct getSecondHandProduct() {
        return secondHandProduct;
    }
}