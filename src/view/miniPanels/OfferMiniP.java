package view.miniPanels;

import model.exchange.Offer;
import view.ImageAdder;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;

import static main.Main.brownColour;
import static view.ImageAdder.getScaledImage;

/**
 * The type Offer mini p.
 * @author Ana O.R.
 * @version 1.0
 */
public class OfferMiniP extends AbstractMiniP {
    private final JButton acceptButton;
    private final JButton declineButton;
    private final Offer offer;
    private final JTextPane offerInfo;
    private final JLabel offerImage;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Discount mini p.
     * @param offer the offer
     * @param index the index
     */
    public OfferMiniP(Offer offer, int index) {
        super();

        this.offer = offer;
        int width = 350;
        int height = 60;
        this.setLayout(new FlowLayout());

        acceptButton = new JButton("Accept offer");
        acceptButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        acceptButton.setPreferredSize(new Dimension(("Accept offer").length() * 15, height));
        acceptButton.setMinimumSize(acceptButton.getPreferredSize());
        acceptButton.setIcon(getScaledImage(new ImageIcon(".\\resources\\app\\yes.png"), height / 3, height / 3));

        declineButton = new JButton("Decline offer");
        declineButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        declineButton.setPreferredSize(new Dimension(("Decline offer").length() * 15, height));
        declineButton.setMinimumSize(declineButton.getPreferredSize());
        declineButton.setIcon(getScaledImage(new ImageIcon(".\\resources\\app\\no.png"), height / 3, height / 3));

        this.offerImage = ImageAdder.getImageLabel(".\\resources\\app\\exchange.png", 50, 50);
        this.offerInfo = new JTextPane();
        this.offerInfo.setEditable(false);
        this.offerInfo.setFocusable(false);

        SimpleAttributeSet attributes = new SimpleAttributeSet();
        StyleConstants.setAlignment(attributes, StyleConstants.ALIGN_LEFT);
        StyleConstants.setBold(attributes, true);
        offerInfo.setCharacterAttributes(attributes, true);
        offerInfo.setText("New offer from " + offer.getOrigin() + "!");

        offerInfo.setPreferredSize(new Dimension(width + 16, height));

        JTextPane indexNum = new JTextPane();
        indexNum.setEditable(false);
        indexNum.setFocusable(false);

        SimpleAttributeSet attributes2 = new SimpleAttributeSet();
        StyleConstants.setBold(attributes2, true);
        indexNum.setCharacterAttributes(attributes2, true);
        indexNum.setText("\n" + index + ".");
        indexNum.setPreferredSize(new Dimension(25, height));

        this.add(indexNum);
        this.add(offerImage);
        this.add(offerInfo);
        this.add(acceptButton);
        this.add(declineButton);

        this.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, brownColour));
    }

    /**
     * It gets the accept button
     * @return the accept button
     */
    public JButton getAcceptButton() {
        return acceptButton;
    }

    /**
     * It gets the decline button
     * @return the decline button
     */
    public JButton getDeclineButton() {
        return declineButton;
    }

    /**
     * It gets the offer
     * @return the offer
     */
    public Offer getOffer() {
        return offer;
    }

    /**
     * It gets the offer info
     * @return the offer info
     */
    public JTextPane getOfferInfo() {
        return offerInfo;
    }

}