package view.browserPanels;

import static main.Main.brownColour;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.text.BadLocationException;

import model.product.Pack;
import model.product.StoreProduct;
import view.miniPanels.*;
import controller.miniControllers.*;

public class BrowsePackProductsP extends AbstractBrowserP<StoreProduct> {

	private Pack p;

	public BrowsePackProductsP(Pack p) {
		this.p= p;
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
    public void addMiniPanel(StoreProduct item, int index) throws BadLocationException {
        StoreProductMiniP miniProduct = new StoreProductMiniP(item, index, "DELETE", null);
        miniProduct.setController(new StoreProductDeleteMiniC(miniProduct, this.p));
        super.addMiniPanel(miniProduct);
        this.add(miniProduct);
    }

}