package view.miniPanels;

import model.discount.DiscountType;
import model.discount.ProductFixedPercentage;
import model.product.Pack;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

import static main.Main.brownColour;
import static view.ImageAdder.getPackImagePanel;
import static view.ImageAdder.getScaledImage;

public class PackDiscMiniP extends AbstractMiniP {
    private final JButton button;
    private final Pack p;
    private final JTextPane packInfo;
    private final JPanel packImage;

    /**
     * Instantiates a new Pack mini p.
     * @param p     the p
     * @param index the index
     * @throws BadLocationException the bad location exception
     */
    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public PackDiscMiniP(Pack p, int index, List<Pack> alreadyChosen) throws BadLocationException {
        super();

        this.p = p;
        int width = 350;
        int height = 60;
        this.setLayout(new FlowLayout());

        button = new JButton("Add to discount");
        button.setPreferredSize(new Dimension(("Add to discount").length() * 15, height));
        button.setIcon(getScaledImage(new ImageIcon(".\\resources\\app\\add.png"), height / 3, height / 3));
        if (alreadyChosen.contains(p)) {
            button.setEnabled(false);
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

            doc.insertString(doc.getLength(), "- " + ((ProductFixedPercentage) p.getDiscount()).getPercentage() + "%",
                    attributes);

            StyleConstants.setForeground(attributes, Color.BLACK);
            StyleConstants.setItalic(attributes, false);
        } else {
            doc.insertString(doc.getLength(), "\n", attributes);
        }

        packInfo.setPreferredSize(new Dimension(width + 16, height));

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
     * It makes it possible to assign a controller to this panel's components
     * @param c the desired controller
     */
    public void setController(ActionListener c) {
        button.addActionListener(c);
    }
}