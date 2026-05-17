package view.maxiPanels;

import model.product.ConservationStatus;
import model.product.SecondHandProduct;
import view.App;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.HashMap;

import static view.ImageAdder.getImageLabel;

/**
 * The type Maxi valuate second hand p.
 * @author Ana O.R.
 * @version 1.0
 */
public class MaxiValuateSecondHandP extends JPanel {
    private final App app;
    private final JTextPane productInfo;
    private final SecondHandProduct product;
    private final JButton valuate = new JButton("Valuate");
    private final JComboBox<String> conservationStatus;
    private final JTextField valuation = new JTextField();
    private final JPanel valuationStuff;
    private final HashMap<String, ConservationStatus> conservationStatutes = new HashMap<>();

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Maxi valuate second hand p.
     * @param app     the app
     * @param product the product
     * @throws BadLocationException the bad location exception
     */
    public MaxiValuateSecondHandP(App app, SecondHandProduct product) throws BadLocationException {
        this.setLayout(new BorderLayout());
        this.app = app;
        this.product = product;

        productInfo = new JTextPane();
        productInfo.setEditable(false);
        productInfo.setFocusable(false);

        valuate.setPreferredSize(new Dimension(100, 50));
        conservationStatutes.put("Perfect", ConservationStatus.PERFECT);
        conservationStatutes.put("Very good", ConservationStatus.VERY_GOOD);
        conservationStatutes.put("Slightly used", ConservationStatus.SLIGHTLY_USED);
        conservationStatutes.put("Evidently used", ConservationStatus.EVIDENTLY_USED);
        conservationStatutes.put("Very used", ConservationStatus.VERY_USED);
        conservationStatutes.put("Damaged", ConservationStatus.DAMAGED);

        conservationStatus = new JComboBox<>(conservationStatutes.keySet().toArray(new String[0]));
        valuation.setColumns(20);

        valuationStuff = new JPanel(new FlowLayout());
        valuationStuff.add(conservationStatus);

        JPanel valuateButtonStuff = new JPanel(new GridLayout(2, 0));
        valuateButtonStuff.add(new JLabel("Valuation (XX.XX)€:"));
        valuateButtonStuff.add(valuation);

        valuationStuff.add(valuateButtonStuff);
        valuationStuff.add(valuate);

        paintEverything();
    }

    /**
     * Paint everything.
     * @throws BadLocationException the bad location exception
     */
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
        rightPanel.add(valuationStuff, BorderLayout.SOUTH);

        this.add(rightPanel, BorderLayout.EAST);

        this.add(getImageLabel(product.getPhoto(), 600, 600), BorderLayout.WEST);

        this.revalidate();
        this.repaint();
    }

    /**
     * It gets the app
     * @return the app
     */
    public App getApp() {
        return app;
    }

    /**
     * It gets the conservation status
     * @return the conservation status
     */
    public JComboBox<String> getConservationStatus() {
        return conservationStatus;
    }

    /**
     * It gets the conservation status from name
     * @param conservationStatusName the conservation status name
     * @return the conservation status from name
     */
    public ConservationStatus getConservationStatusFromName(String conservationStatusName) {
        return conservationStatutes.get(conservationStatusName);
    }

    /**
     * It gets the product
     * @return the product
     */
    public SecondHandProduct getProduct() {
        return product;
    }

    /**
     * It gets the product info
     * @return the product info
     */
    public JTextPane getProductInfo() {
        return productInfo;
    }

    /**
     * It gets the valuate button
     * @return the valuate button
     */
    public JButton getValuate() {
        return valuate;
    }

    /**
     * It gets the valuation
     * @return the valuation
     */
    public JTextField getValuation() {
        return valuation;
    }
}