package view.browserPanels;

import static main.Main.brownColour;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.BadLocationException;

import model.product.Pack;
import model.product.StoreProduct;
import view.miniPanels.PackMiniP;
import view.miniPanels.StoreProductMiniP;

public class BrowseInOrder extends AbstractMixedBrowserP<Pack, StoreProduct>{

	/**
     * This panel's constructor
     * @throws BadLocationException the bad location exception
     */
    public BrowseInOrder() throws BadLocationException {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        paintEverything();
    }
	@Override
	public void paintEverything() throws BadLocationException {
		this.clearItemsContainer();
        this.removeAll();

        super.addAllMiniPanels();
        if (super.getFirstMiniPanels().isEmpty() && super.getSecondMiniPanels().isEmpty()) {
            this.add(new JLabel("No packs or products to be seen"));
        }

        this.add(containerItems, BorderLayout.NORTH);

        JPanel northWrapper = new JPanel(new BorderLayout());
        northWrapper.setOpaque(false);
        northWrapper.add(containerItems, BorderLayout.NORTH);

        this.add(northWrapper, BorderLayout.NORTH);

        JPanel filler = new JPanel();
        filler.setOpaque(false);
        this.add(filler, BorderLayout.CENTER);

        this.add(super.getPageTurner(), BorderLayout.SOUTH);
        this.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, brownColour));

        this.revalidate();
        this.repaint();		
	}

	@Override
	public void addFirstMiniPanel(Pack item, int index) throws BadLocationException {
		PackMiniP miniPack = new PackMiniP(item, index, "", ".\\resources\\app\\cart.png");
        super.addFirstMiniPanel(miniPack);
        this.add(miniPack);
		
	}

	@Override
	public void addSecondMiniPanel(StoreProduct item, int index) throws BadLocationException {
		StoreProductMiniP miniPack = new StoreProductMiniP(item, index, "", ".\\resources\\app\\cart.png");
        super.addSecondMiniPanel(miniPack);
        this.add(miniPack);
		
	}

}
