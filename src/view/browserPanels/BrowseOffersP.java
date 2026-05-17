package view.browserPanels;

import model.exchange.Offer;
import view.miniPanels.OfferMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.io.Serial;

import static main.Main.brownColour;

/**
 * The type Browse offers p.
 * @author Ana O.R.
 * @version 1.0
 */
public class BrowseOffersP extends AbstractBrowserP<Offer> {

    @Serial
    private static final long serialVersionUID = 1L;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Browse orders p.
     * @throws BadLocationException the bad location exception
     */
    public BrowseOffersP() throws BadLocationException {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        paintEverything();
    }

    @Override
    public void paintEverything() throws BadLocationException {
        this.removeAll();

        super.addAllMiniPanels();

        JPanel turner = super.getPageTurner();
        turner.setAlignmentX(Component.CENTER_ALIGNMENT);
        turner.setBackground(this.getBackground());
        this.add(turner);

        this.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, brownColour));

        this.revalidate();
        this.repaint();

    }

    @Override
    public void addMiniPanel(Offer item, int index) throws BadLocationException {
        OfferMiniP miniOrder = new OfferMiniP(item, index);
        super.addMiniPanel(miniOrder);
        this.add(miniOrder);

    }

}