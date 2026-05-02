package view.browserPanels;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.BadLocationException;

import model.product.Pack;
import model.product.StoreProduct;
import model.store.BetterPager;
import view.App;
import view.miniPanels.PackMiniP;
import view.miniPanels.StoreProductMiniP;

public class BrowsePacks extends JPanel implements BigView{
	private final JButton firstPage = new JButton("<< First Page");
    private final JButton previousPage = new JButton("< Previous Page");
    private final JButton nextPage = new JButton("Next Page >");
    private final JButton lastPage = new JButton("Last Page >>");
    private final List<PackMiniP> PackPanels = new ArrayList<>();
    private final BetterPager<Pack> pager = new BetterPager<>();
    private final App app;
    private List<Pack> packs = new ArrayList<>();
    private int currentPageNum;
    
    public BrowsePacks(App app) throws BadLocationException{
    	this.app = app;
    	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Change this
        currentPageNum = 1;

        paintEverything();
    	
    }
    
	@Override
	public void paintEverything() throws BadLocationException {
		this.removeAll();
        PackPanels.clear();

        this.storeProducts = app.getProducts();

        List<StoreProduct> currentStoreProducts = pager.pageItemList(storeProducts, currentPageNum);

        int index = 1;
        for (StoreProduct product : currentStoreProducts) {
            StoreProductMiniP miniProduct = new StoreProductMiniP(product, index);
            productPanels.add(miniProduct);
            this.add(miniProduct);
            index++;
        }

        JPanel pageTurner = new JPanel(new FlowLayout());
        if (currentPageNum != 1) {
            pageTurner.add(firstPage);
            pageTurner.add(previousPage);
        }
        pageTurner.add(new JLabel("Page " + currentPageNum));
        if (currentPageNum != pager.getMaxPageNum(storeProducts)) {
            pageTurner.add(nextPage);
            pageTurner.add(lastPage);
        }
        this.add(pageTurner);

        this.revalidate();
        this.repaint();
		
	}
}
