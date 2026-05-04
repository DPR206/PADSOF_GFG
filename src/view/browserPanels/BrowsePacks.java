package view.browserPanels;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
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
import view.miniPanels.PackMiniEdit;
import view.miniPanels.PackMiniP;
import view.miniPanels.StoreProductMiniP;

public class BrowsePacks extends JPanel implements BigView{
	private final JButton firstPage = new JButton("<< First Page");
    private final JButton previousPage = new JButton("< Previous Page");
    private final JButton nextPage = new JButton("Next Page >");
    private final JButton lastPage = new JButton("Last Page >>");
    private final List<PackMiniEdit> packPanels = new ArrayList<>();
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
        packPanels.clear();

        List<Pack> currentPacks = pager.pageItemList(this.packs, currentPageNum);

        int index = 1;
        for (Pack p : currentPacks) {
            PackMiniEdit miniPack = new PackMiniEdit(p, index);
            packPanels.add(miniPack);
            this.add(miniPack);
            index++;
        }

        JPanel pageTurner = new JPanel(new FlowLayout());
        if (currentPageNum != 1) {
            pageTurner.add(firstPage);
            pageTurner.add(previousPage);
        }
        pageTurner.add(new JLabel("Page " + currentPageNum));
        if (currentPageNum != pager.getMaxPageNum(packs)) {
            pageTurner.add(nextPage);
            pageTurner.add(lastPage);
        }
        this.add(pageTurner);

        this.revalidate();
        this.repaint();
		
	}
	
	/**
	 * It gets this page's current page number
     * @return this page's current page number
     */
    public int getCurrentPageNum() {
        return currentPageNum;
    }

    /**
     * It changes this page's current page number
     * @param newCurrentPageNum the desired page number
     * @throws BadLocationException bad locations within a document model (that is, attempts to reference a location
     *                              that doesn't exist)
     */
    public void setCurrentPageNum(int newCurrentPageNum) throws BadLocationException {
        this.currentPageNum = newCurrentPageNum;
        paintEverything();
    }

    /**
     * It gets the available store product list's max page number
     * @return the available store product list's max page number
     */
    public int getMaxPageNum() {
        return pager.getMaxPageNum(this.packs);
    }

    public List<PackMiniEdit> getProductPanels() {
        return packPanels;
    }

    /**
     * It makes it possible to assign a controller to this panel's components
     * @param c the desired controller
     */
    public void setController(ActionListener c) {
        if (packPanels != null) {
            for (PackMiniEdit miniProduct : this.packPanels) {
                miniProduct.setController(c);
            }
        }
        firstPage.addActionListener(c);
        previousPage.addActionListener(c);
        nextPage.addActionListener(c);
        lastPage.addActionListener(c);
    }
}
