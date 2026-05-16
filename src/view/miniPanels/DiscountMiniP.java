package view.miniPanels;

import model.discount.Discount;
import view.ImageAdder;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.ActionListener;

import static main.Main.brownColour;
import static view.ImageAdder.getScaledImage;

public class DiscountMiniP extends AbstractMiniP {
    private final JButton button;
    private final Discount discount;
    private final JTextPane discountInfo;
    private final JLabel discountImage;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public DiscountMiniP(Discount discount, int index, String buttonName) {
        super();

        this.discount = discount;
        int width = 350;
        int height = 60;
        this.setLayout(new FlowLayout());

        button = new JButton(buttonName);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(buttonName.length() * 15, height));
        button.setMinimumSize(button.getPreferredSize());
        button.setIcon(getScaledImage(new ImageIcon(".\\resources\\app\\delete.png"), height / 3, height / 3));

        this.discountImage = ImageAdder.getImageLabel(".\\resources\\app\\discount.png", 50, 50);
        this.discountInfo = new JTextPane();
        this.discountInfo.setEditable(false);
        this.discountInfo.setFocusable(false);

        SimpleAttributeSet attributes = new SimpleAttributeSet();
        StyleConstants.setAlignment(attributes, StyleConstants.ALIGN_LEFT);
        StyleConstants.setBold(attributes, true);
        discountInfo.setCharacterAttributes(attributes, true);
        discountInfo.setText(discount.getCoverage() + " " + discount.getType() + " :" + discount.getId() + "\n");

        discountInfo.setPreferredSize(new Dimension(width + 16, height));

        JTextPane indexNum = new JTextPane();
        indexNum.setEditable(false);
        indexNum.setFocusable(false);

        SimpleAttributeSet attributes2 = new SimpleAttributeSet();
        StyleConstants.setBold(attributes2, true);
        indexNum.setCharacterAttributes(attributes2, true);
        indexNum.setText("\n" + index + ".");
        indexNum.setPreferredSize(new Dimension(25, height));

        this.add(indexNum);
        this.add(discountImage);
        this.add(discountInfo);
        this.add(button);

        this.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, brownColour));
    }

    /**
     * Gets button.
     * @return the button
     */
    public JButton getButton() {
        return button;
    }

    public Discount getDiscount() {
        return discount;
    }

    public JLabel getDiscountImage() {
        return discountImage;
    }

    public JTextPane getDiscountInfo() {
        return discountInfo;
    }

    /**
     * It makes it possible to assign a controller to this panel's components
     * @param c the desired controller
     */
    public void setController(ActionListener c) {
        button.addActionListener(c);
    }
}