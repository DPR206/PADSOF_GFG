package view.miniPanels;

import static main.Main.brownColour;
import static view.ImageAdder.getImageLabel;
import static view.ImageAdder.getScaledImage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import model.product.StoreProduct;

public class StoreProductMiniEdit extends JPanel{
	private final JButton addToCart = new JButton("Modificar");
    private final StoreProduct storeProduct;
    private final JLabel productImage;
    private final JTextPane productInfo;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public StoreProductMiniEdit(StoreProduct product, int index) throws BadLocationException {
        this.storeProduct = product;
        int width = 350;
        int height = 60;
        this.setLayout(new FlowLayout());

        addToCart.setPreferredSize(new Dimension(125, height));
        addToCart.setIcon(getScaledImage(new ImageIcon(".\\resources\\app\\cart.png"), height / 4, height / 4));

        productImage = getImageLabel(product.getPhoto(), height, height); // DUE: Revisar dimensiones
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
        addToCart.addActionListener(c);
    }
}