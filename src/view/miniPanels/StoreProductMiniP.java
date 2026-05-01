package view.miniPanels;

import model.product.StoreProduct;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static main.Main.brownColour;
import static view.ImageAdder.getImageLabel;
import static view.ImageAdder.getScaledImage;

public class StoreProductMiniP extends JPanel {
    private final JButton addToCart = new JButton("Add to Cart");
    private final StoreProduct storeProduct;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public StoreProductMiniP(StoreProduct product, int index) throws BadLocationException {
        this.storeProduct = product;
        int width = 350;
        int height = 60;
        this.setLayout(new FlowLayout());

        addToCart.setPreferredSize(new Dimension(125, height));
        addToCart.setIcon(getScaledImage(new ImageIcon(".\\resources\\cart.png"), height / 4, height / 4));

        JLabel productImage = getImageLabel(product.getPhoto(), height, height); // DUE: Revisar dimensiones
        JTextPane productInfo = new JTextPane();
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
        doc.insertString(doc.getLength(), // DUE Añadir descuento
                ("Price: " + String.format("%.2f", product.getPrice()) + " €\n"), attributes);

        if (product.getStock() == 0) {
            StyleConstants.setForeground(attributes, Color.RED);
            StyleConstants.setItalic(attributes, true);
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
        this.add(addToCart);

        this.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, brownColour));
    }

    public StoreProduct getStoreProduct() {
        return storeProduct;
    }

    /**
     * It makes it possible to assign a controller to this panel's components
     * @param c the desired controller
     */
    public void setController(ActionListener c) {
        addToCart.addActionListener(c);
    }

}