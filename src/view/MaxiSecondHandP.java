package view;

import model.product.SecondHandProduct;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

import static view.ImageAdder.getImageLabel;

/**
 * Zoom view of a second hand product with customizable button
 */
public class MaxiSecondHandP extends JPanel {
    private final App app;
    private final JTextPane productInfo;
    private final SecondHandProduct product;
    private final JButton addToOffer;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public MaxiSecondHandP(App app, SecondHandProduct product, String buttonName) throws BadLocationException {
        this.setLayout(new BorderLayout());
        this.app = app;
        this.product = product;

        productInfo = new JTextPane();
        productInfo.setEditable(false);
        productInfo.setFocusable(false);

        addToOffer = new JButton(buttonName);
        addToOffer.setPreferredSize(new Dimension(100, 50));

        paintEverything();
    }

    public void paintEverything() throws BadLocationException {
        this.removeAll();

        /* Product info */
        SimpleAttributeSet attributes = new SimpleAttributeSet();
        StyleConstants.setFontSize(attributes, 20);
        StyleConstants.setAlignment(attributes, StyleConstants.ALIGN_LEFT);
        StyleConstants.setBold(attributes, true);
        productInfo.setCharacterAttributes(attributes, true);
        productInfo.setText("\n\n" + product.getName() + "\n\n");

        attributes = new SimpleAttributeSet();
        StyleConstants.setFontSize(attributes, 15);
        StyleConstants.setAlignment(attributes, StyleConstants.ALIGN_LEFT);

        Document doc = productInfo.getStyledDocument();
        doc.insertString(doc.getLength(), ("- Type: " + product.getType() + "\n\n"), attributes);
        doc.insertString(doc.getLength(), ("- Valuation: " + product.getPrice() + "€\n\n"), attributes);
        doc.insertString(doc.getLength(), ("- Conservation status: " + product.getStatus() + "\n\n"), attributes);
        doc.insertString(doc.getLength(), ("- Description:\n"), attributes);
        /* Manual wrapping */
        int wordWrapLimit = 110;
        StyleConstants.setAlignment(attributes, StyleConstants.ALIGN_JUSTIFIED);
        for (int i = 0; i < product.getDescription().length() % wordWrapLimit; i++) {
            String string = product.getDescription()
                                   .substring(Math.min(i * wordWrapLimit, product.getDescription().length()),
                                           Math.min((i + 1) * wordWrapLimit, product.getDescription().length()));
            doc.insertString(doc.getLength(), " " + string + "          " + "\n", attributes);
        }

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(productInfo, BorderLayout.CENTER);
        rightPanel.add(addToOffer, BorderLayout.SOUTH);

        this.add(rightPanel, BorderLayout.EAST);

        this.add(getImageLabel(product.getPhoto(), 600, 600), BorderLayout.WEST);

        this.revalidate();
        this.repaint();
    }
}