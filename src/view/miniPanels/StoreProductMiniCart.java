package view.miniPanels;

import model.discount.DiscountType;
import model.discount.ProductFixedPercentage;
import model.order.Cart;
import model.product.StoreProduct;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static main.Main.brownColour;
import static view.ImageAdder.getImageLabel;
import static view.ImageAdder.getScaledImage;

public class StoreProductMiniCart extends MiniPanel {
    private static final long serialVersionUID = 1L;
    private final JButton deleteFromCart = new JButton("Delete from Cart");
    private final StoreProduct storeProduct;
    private final JLabel productImage;
    private final JTextPane productInfo;
    private JSpinner unitSpinner;
    private Cart cart = null;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public StoreProductMiniCart(StoreProduct product, int index) throws BadLocationException {
        this.storeProduct = product;
        int width = 350;
        int height = 60;
        this.setLayout(new FlowLayout());

        deleteFromCart.setPreferredSize(new Dimension(125, height));
        deleteFromCart.setIcon(getScaledImage(new ImageIcon(".\\resources\\app\\cart.png"), height / 4, height / 4));

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

//        if (product.getStock() == 0) {
//            StyleConstants.setForeground(attributes, Color.RED);
//            StyleConstants.setItalic(attributes, true);
//            deleteFromCart.setEnabled(false);
//        }

        int initialValue;
        if (cart != null) {
            initialValue = cart.getSp().get(product);
        } else {
            initialValue = 1;
        }
        SpinnerModel model = new SpinnerNumberModel(initialValue, 1, 99, 1);
        unitSpinner = new JSpinner(model);

        unitSpinner.setPreferredSize(new Dimension(50, 30));

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
        this.add(new JLabel("Units:"));
        this.add(unitSpinner);
        this.add(deleteFromCart);

        this.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, brownColour));
    }

    /**
     * @return the deleteFromCart
     */
    public JButton getDeleteFromCart() {
        return deleteFromCart;
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

    public JSpinner getUnitSpinner() {
        return unitSpinner;
    }

    public void setCart(Cart newCart) {
        this.cart = newCart;
    }

    /**
     * It makes it possible to assign a controller to this panel's components
     * @param c the desired controller
     */
    public void setController(ActionListener c) {
        deleteFromCart.addActionListener(c);
    }
}