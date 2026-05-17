package view.browserPanels;

import model.order.Order;
import view.miniPanels.OrderMini;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.io.Serial;

import static main.Main.brownColour;

/**
 * The type Browse orders p.
 * @author Duna P.R.
 * @version 1.0
 */
public class BrowseOrdersP extends AbstractBrowserP<Order> {

    @Serial
    private static final long serialVersionUID = 1L;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Browse orders p.
     * @throws BadLocationException the bad location exception
     */
    public BrowseOrdersP() throws BadLocationException {
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
    public void addMiniPanel(Order item, int index) throws BadLocationException {
        OrderMini miniOrder = new OrderMini(index);
        super.addMiniPanel(miniOrder);
        this.add(miniOrder);

    }

}