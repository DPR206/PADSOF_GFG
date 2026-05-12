package view.browserPanels;

import static main.Main.brownColour;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.text.BadLocationException;

import model.order.Order;
import view.miniPanels.OrderMini;

public class BrowseOrdersP extends AbstractBrowserP<Order> {

	private static final long serialVersionUID = 1L;

	/**
	 * @throws BadLocationException 
	 * 
	 */
	public BrowseOrdersP() throws BadLocationException {
		super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        paintEverything();
	}

	@Override
	public void paintEverything() throws BadLocationException {
		this.removeAll();

        JLabel title = new JLabel("Your orders");
        title.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, brownColour));
        this.add(title);

        super.addAllMiniPanels();
        this.add(super.getPageTurner());
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
