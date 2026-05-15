package view.browserPanels;

import static main.Main.brownColour;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.text.BadLocationException;

import controller.miniControllers.StoreProductDeleteMiniC;
import model.product.Pack;
import model.product.StoreProduct;
import view.miniPanels.PackMiniP;
import view.miniPanels.StoreProductMiniP;

public class BrowsePacksPackP extends AbstractBrowserP<Pack>{
	private Pack original;

	public BrowsePacksPackP(Pack original) {
		this.original= original;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        try {
			paintEverything();
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void paintEverything() throws BadLocationException {
		this.removeAll();

        super.addAllMiniPanels();
        this.add(super.getPageTurner());
        this.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, brownColour));

        this.revalidate();
        this.repaint();

	}

	@Override
    public void addMiniPanel(Pack item, int index) throws BadLocationException {
        PackMiniP miniProduct = new PackMiniP(item, index, "DELETE", null);
        miniProduct.setController(null);
        super.addMiniPanel(miniProduct);
        this.add(miniProduct);
    }

}
