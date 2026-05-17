package view.miniPanels;

import model.exchange.Exchange;
import view.ImageAdder;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.ActionListener;

import static main.Main.brownColour;
import static view.ImageAdder.getScaledImage;

public class ExchangeMiniP extends AbstractMiniP {
    private final JButton button;
    private final Exchange exchange;
    private final JTextPane exchangeInfo;
    private final JLabel exchangeImage;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Discount mini p.
     * @param exchange   the exchange
     * @param index      the index
     * @param buttonName the button name
     */
    public ExchangeMiniP(Exchange exchange, int index, String buttonName) {
        super();

        this.exchange = exchange;
        int width = 350;
        int height = 60;
        this.setLayout(new FlowLayout());

        button = new JButton(buttonName);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(buttonName.length() * 15, height));
        button.setMinimumSize(button.getPreferredSize());
        button.setIcon(getScaledImage(new ImageIcon(".\\resources\\app\\delete.png"), height / 3, height / 3));

        this.exchangeImage = ImageAdder.getImageLabel(".\\resources\\app\\exchange.png", 50, 50);
        this.exchangeInfo = new JTextPane();
        this.exchangeInfo.setEditable(false);
        this.exchangeInfo.setFocusable(false);

        SimpleAttributeSet attributes = new SimpleAttributeSet();
        StyleConstants.setAlignment(attributes, StyleConstants.ALIGN_LEFT);
        StyleConstants.setBold(attributes, true);
        exchangeInfo.setCharacterAttributes(attributes, true);
        exchangeInfo.setText("Exchange nº :" + exchange.getId() + "\n");

        exchangeInfo.setPreferredSize(new Dimension(width + 16, height));

        JTextPane indexNum = new JTextPane();
        indexNum.setEditable(false);
        indexNum.setFocusable(false);

        SimpleAttributeSet attributes2 = new SimpleAttributeSet();
        StyleConstants.setBold(attributes2, true);
        indexNum.setCharacterAttributes(attributes2, true);
        indexNum.setText("\n" + index + ".");
        indexNum.setPreferredSize(new Dimension(25, height));

        this.add(indexNum);
        this.add(exchangeImage);
        this.add(exchangeInfo);
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
     * It gets the exchange info
     * @return the exchange info
     */
    public JTextPane getDiscountInfo() {
        return exchangeInfo;
    }

    public Exchange getExchange() {
        return exchange;
    }

    /**
     * It makes it possible to assign a controller to this panel's components
     * @param c the desired controller
     */
    public void setController(ActionListener c) {
        button.addActionListener(c);
    }
}