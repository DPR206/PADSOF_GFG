package view.browserPanels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
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
    	
    	
    }
    
	@Override
	public void paintEverything() throws BadLocationException {
		// TODO Auto-generated method stub
		
	}
}
