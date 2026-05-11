package view.miniPanels;

import model.discount.DiscountType;
import model.discount.ProductFixedPercentage;
import model.product.StoreProduct;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static main.Main.brownColour;
import static view.ImageAdder.getImageLabel;
import static view.ImageAdder.getScaledImage;

public class StoreProductMiniP extends MiniPanel {
    private static final long serialVersionUID = 1L;
    private final JButton button;
    private final StoreProduct storeProduct;
    private final JLabel productImage;
    private final JTextPane productInfo;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public StoreProductMiniP(StoreProduct product, int index, String buttonName, String iconPath)
            throws BadLocationException {
        this.storeProduct = product;
        int width = 350;
        int height = 60;
        this.setLayout(new FlowLayout());

        button = new JButton(buttonName);
        button.setPreferredSize(new Dimension(125, height));
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
        doc.insertString(doc.getLength(), ("Price: " + String.format("%.2f", product.getPrice()) + " €"), attributes);

        if (product.getDiscount() != null && product.getDiscount().getType() == DiscountType.FIXED_PERCENTAGE) {
            StyleConstants.setForeground(attributes, Color.RED);
            StyleConstants.setItalic(attributes, true);

            doc.insertString(doc.getLength(),
                    "- " + ((ProductFixedPercentage) product.getDiscount()).getPercentage() + "%", attributes);

            StyleConstants.setForeground(attributes, Color.BLACK);
            StyleConstants.setItalic(attributes, false);
        } else {
            doc.insertString(doc.getLength(), "\n", attributes);
        }

        if (product.getStock() == 0) {
            StyleConstants.setForeground(attributes, Color.RED);
            StyleConstants.setItalic(attributes, true);
            button.setEnabled(false);
        }
        doc.insertString(doc.getLength(), ("Stock: " + product.getStock()), attributes);

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
        return button;
    }

    public JLabel getProductImage() {
        return productImage;
    }

    public JTextPane getProductInfo() {
        return productInfo;
    }

    public StoreProduct getStoreProduct() {
        return storeProduct;
    }

    /**
     * It makes it possible to assign a controller to this panel's components
     * @param c the desired controller
     */
    public void setController(ActionListener c) {
        button.addActionListener(c);
    }
}