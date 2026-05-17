package view.browserPanels;

import static main.Main.brownColour;

import java.awt.Component;
import java.io.Serial;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.text.BadLocationException;

import model.order.Order;
import view.miniPanels.OrderMini;
import view.miniPanels.OrderMiniManageP;

public class BrowseStoreOrdersP extends AbstractBrowserP<Order>{
	@Serial
    private static final long serialVersionUID = 1L;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Browse orders p.
     * @throws BadLocationException the bad location exception
     */
    public BrowseStoreOrdersP() throws BadLocationException {
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
        OrderMiniManageP miniOrder = new OrderMiniManageP(index, item);
        super.addMiniPanel(miniOrder);
        this.add(miniOrder);

    }
}
