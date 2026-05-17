package view.miniPanels;

import model.discount.DiscountType;
import model.discount.ProductFixedPercentage;
import model.order.Cart;
import model.product.Pack;
import model.product.StoreProduct;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

import static main.Main.brownColour;
import static view.ImageAdder.getPackImagePanel;
import static view.ImageAdder.getScaledImage;

/**
 * The type Pack cart mini p.
 * @author Ana O.R.
 * @version 1.0
 */
public class PackCartMiniP extends AbstractMiniP {
    private final JButton deleteFromCart = new JButton("Delete from Cart");
    private final JButton applyChanges = new JButton("Apply Changes");
    private final Pack p;
    private final JTextPane packInfo;
    private final JPanel packImage;
    private final JSpinner unitSpinner;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Pack mini p.
     * @param p          the p
     * @param index      the index
     * @param buttonName the button name
     * @param iconPath   the icon path
     * @param cart       the cart
     * @throws BadLocationException the bad location exception
     */
    public PackCartMiniP(Pack p, int index, String buttonName, String iconPath, Cart cart) throws BadLocationException {
        super();

        this.p = p;
        int width = 350;
        int height = 80;
        this.setLayout(new FlowLayout());

        deleteFromCart.setPreferredSize(new Dimension(buttonName.length() * 15, height));
        deleteFromCart.setCursor(new Cursor(Cursor.HAND_CURSOR));
        if (iconPath != null) {
            deleteFromCart.setIcon(getScaledImage(new ImageIcon(iconPath), height / 4, height / 4));
        }

        this.packImage = getPackImagePanel(p, height, height);
        this.packInfo = new JTextPane();
        this.packInfo.setEditable(false);
        this.packInfo.setFocusable(false);

        SimpleAttributeSet attributes = new SimpleAttributeSet();
        StyleConstants.setAlignment(attributes, StyleConstants.ALIGN_LEFT);
        StyleConstants.setBold(attributes, true);
        packInfo.setCharacterAttributes(attributes, true);
        packInfo.setText("PACK " + p.getId() + "\n");

        attributes = new SimpleAttributeSet();
        StyleConstants.setAlignment(attributes, StyleConstants.ALIGN_LEFT);

        Document doc = packInfo.getStyledDocument();
        doc.insertString(doc.getLength(), ("Price: " + String.format("%.2f", this.p.getPrice()) + " €"), attributes);

        if (p.getDiscount() != null && p.getDiscount().getType() == DiscountType.FIXED_PERCENTAGE) {
            StyleConstants.setForeground(attributes, Color.RED);
            StyleConstants.setItalic(attributes, true);

            doc.insertString(doc.getLength(),
                    " - " + ((ProductFixedPercentage) p.getDiscount()).getPercentage() + "%\n", attributes);

            StyleConstants.setForeground(attributes, Color.BLACK);
            StyleConstants.setItalic(attributes, false);
        } else {
            doc.insertString(doc.getLength(), "\n", attributes);
        }

        packInfo.setPreferredSize(new Dimension(width + 16, height));

        int initialValue;
        if (cart != null) {
            doc.insertString(doc.getLength(), ("Uds: " + cart.getPacksHashMap().get(p) + "\n"), attributes);
            initialValue = cart.getPacksHashMap().get(p);
        } else {
            initialValue = 1;
        }
        SpinnerModel model = new SpinnerNumberModel(initialValue, 1, 99, 1);
        unitSpinner = new JSpinner(model);

        unitSpinner.setPreferredSize(new Dimension(50, 30));

        packInfo.setPreferredSize(new Dimension(width + 16, height));

        for (StoreProduct product : p.getProducts()) {
            if (product.getStock() == 0) {
                StyleConstants.setForeground(attributes, Color.RED);
                StyleConstants.setItalic(attributes, true);
                doc.insertString(doc.getLength(), ("Last one!"), attributes);
                unitSpinner.setEnabled(false);
                applyChanges.setEnabled(false);
            }
        }

        JTextPane indexNum = new JTextPane();
        indexNum.setEditable(false);
        indexNum.setFocusable(false);

        SimpleAttributeSet attributes2 = new SimpleAttributeSet();
        StyleConstants.setBold(attributes2, true);
        indexNum.setCharacterAttributes(attributes2, true);
        indexNum.setText("\n" + index + ".");
        indexNum.setPreferredSize(new Dimension(25, height));

        this.add(indexNum);
        this.add(packImage);
        this.add(packInfo);
        this.add(new JLabel("Units:"));
        this.add(unitSpinner);
        this.add(applyChanges);
        this.add(deleteFromCart);

        this.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, brownColour));
    }

    /**
     * It gets the apply changes
     * @return the apply changes
     */
    public JButton getApplyChanges() {
        return applyChanges;
    }

    /**
     * It gets the delete from cart
     * @return the deleteFromCart
     */
    public JButton getDeleteFromCart() {
        return deleteFromCart;
    }

    /**
     * Gets pack.
     * @return the pack
     */
    public Pack getPack() {
        return p;
    }

    /**
     * Gets pack image.
     * @return the pack image
     */
    public JPanel getPackImage() {
        return packImage;
    }

    /**
     * Gets pack info.
     * @return the pack info
     */
    public JTextPane getPackInfo() {
        return packInfo;
    }

    /**
     * It gets the unit spinner
     * @return the unit spinner
     */
    public JSpinner getUnitSpinner() {
        return unitSpinner;
    }
}