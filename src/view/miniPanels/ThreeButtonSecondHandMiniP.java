package view.miniPanels;

import model.product.SecondHandProduct;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

import static main.Main.brownColour;
import static view.ImageAdder.getImageLabel;
import static view.ImageAdder.getScaledImage;

/**
 * The type Three button second hand mini p.
 * @author Ana O.R.
 * @version 1.0
 */
public class ThreeButtonSecondHandMiniP extends AbstractMiniP {
    private final JButton firstButton;
    private final JButton secondButton;
    private final JButton thirdButton;
    private final SecondHandProduct secondHandProduct;
    private final JLabel productImage;
    private final JTextPane productInfo;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Three button second hand mini p.
     * @param product          the product
     * @param index            the index
     * @param firstButtonName  the first button name
     * @param firstIconPath    the first icon path
     * @param secondButtonName the second button name
     * @param secondIconPath   the second icon path
     * @param thirdButtonName  the third button name
     * @param thirdIconPath    the third icon path
     * @throws BadLocationException the bad location exception
     */
    public ThreeButtonSecondHandMiniP(SecondHandProduct product, int index, String firstButtonName,
                                      String firstIconPath, String secondButtonName, String secondIconPath,
                                      String thirdButtonName, String thirdIconPath) throws BadLocationException {
        this.secondHandProduct = product;
        int width = 350;
        int height = 60;
        this.setLayout(new FlowLayout());

        firstButton = new JButton(firstButtonName);
        firstButton.setPreferredSize(new Dimension(firstButtonName.length() * 15, height));
        if (firstIconPath != null) {
            firstButton.setIcon(getScaledImage(new ImageIcon(firstIconPath), height / 4, height / 4));
        }

        secondButton = new JButton(secondButtonName);
        secondButton.setPreferredSize(new Dimension(secondButtonName.length() * 15, height));
        if (secondIconPath != null) {
            secondButton.setIcon(getScaledImage(new ImageIcon(secondIconPath), height / 4, height / 4));
        }

        thirdButton = new JButton(thirdButtonName);
        thirdButton.setPreferredSize(new Dimension(thirdButtonName.length() * 15, height));
        if (thirdIconPath != null) {
            thirdButton.setIcon(getScaledImage(new ImageIcon(thirdIconPath), height / 4, height / 4));
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
        this.add(firstButton);
        if (!product.isAvailable()) {
            this.add(secondButton);
        } else {
            productInfo.setPreferredSize(new Dimension(width + (thirdButtonName.length() * 15), height));
        }
        this.add(thirdButton);

        this.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, brownColour));
    }

    /**
     * It gets the first button
     * @return the first button
     */
    public JButton getFirstButton() {
        return firstButton;
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
     * It gets the second button
     * @return the second button
     */
    public JButton getSecondButton() {
        return secondButton;
    }

    /**
     * It gets the second hand product
     * @return the second hand product
     */
    public SecondHandProduct getSecondHandProduct() {
        return secondHandProduct;
    }

    /**
     * It gets the third button
     * @return the third button
     */
    public JButton getThirdButton() {
        return thirdButton;
    }

}