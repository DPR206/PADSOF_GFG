package view.browserPanels;

import static main.Main.brownColour;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.text.BadLocationException;

import model.product.Pack;
import model.product.StoreProduct;
import view.miniPanels.PackMiniP;
import view.miniPanels.StoreProductMiniP;

public class BrowseStoreProducts extends AbstractBrowserP<StoreProduct>{
	 private final String buttonName;
	 private final String iconPath;
	 public BrowseStoreProducts(String buttonName, String... iconPath) throws BadLocationException {
	        super();
	        this.buttonName = buttonName;
	        this.iconPath = iconPath[0];
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
	    public void addMiniPanel(StoreProduct item, int index) throws BadLocationException {
	        StoreProductMiniP miniPack = new StoreProductMiniP(item, index, buttonName, iconPath);
	        super.addMiniPanel(miniPack);
	        this.add(miniPack);
	    }

}
