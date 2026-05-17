package view.browserPanels;

import model.exchange.Exchange;
import view.miniPanels.ExchangeMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.io.Serial;

import static main.Main.brownColour;

public class BrowseExchangesP extends AbstractBrowserP<Exchange> {
    @Serial
    private static final long serialVersionUID = 1L;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Browse discounts p.
     * @throws BadLocationException the bad location exception
     */
    public BrowseExchangesP() throws BadLocationException {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        paintEverything();
    }

    @Override
    public void paintEverything() throws BadLocationException {
        this.removeAll();

        super.addAllMiniPanels();
        this.add(super.getPageTurner());
        this.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, brownColour));

        this.revalidate();
        this.repaint();
    }

    @Override
    public void addMiniPanel(Exchange item, int index) throws BadLocationException {
        ExchangeMiniP miniOrder = new ExchangeMiniP(item, index, "Set as exchanged");
        super.addMiniPanel(miniOrder);
        this.add(miniOrder);
    }
}