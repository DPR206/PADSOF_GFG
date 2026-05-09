package view.miniPanels;

import model.product.Pack;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static main.Main.brownColour;
import static view.ImageAdder.getPackImagePanel;
import static view.ImageAdder.getScaledImage;

public class PackMiniP extends MiniPanel {
    private final JButton addToCart = new JButton("Add to Cart");
    private final Pack p;
    private final JTextPane packInfo;
    private final JPanel packImage;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public PackMiniP(Pack p, int index) throws BadLocationException {
        super();

        this.p = p;
        int width = 350;
        int height = 60;
        this.setLayout(new FlowLayout());

        addToCart.setPreferredSize(new Dimension(125, height));
        addToCart.setIcon(getScaledImage(new ImageIcon(".\\resources\\app\\cart.png"), height / 4, height / 4));

        this.packImage = getPackImagePanel(p, height, height);//getImageLabel(p.getPhoto(), height, height);
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
        doc.insertString(doc.getLength(), // DUE Añadir descuento
                ("Price: " + String.format("%.2f", this.p.getPrice()) + " €\n"), attributes);

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
        this.add(addToCart);

        this.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, brownColour));
    }

    public Pack getPack() {
        return p;
    }

    public JPanel getPackImage() {
        return packImage;
    }

    public JTextPane getPackInfo() {
        return packInfo;
    }

    /**
     * It makes it possible to assign a controller to this panel's components
     * @param c the desired controller
     */
    public void setController(ActionListener c) {
        addToCart.addActionListener(c);
    }
}