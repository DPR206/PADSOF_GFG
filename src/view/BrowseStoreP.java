package view;

import model.product.StoreProduct;
import model.store.BetterPager;
import view.miniPanels.StoreProductMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BrowseStoreP extends JPanel {
    private final JButton firstPage = new JButton("<< First Page");
    private final JButton previousPage = new JButton("< Previous Page");
    private final JButton nextPage = new JButton("Next Page >");
    private final JButton lastPage = new JButton("Last Page >>");
    private final List<StoreProductMiniP> productPanels = new ArrayList<>();
    private final List<StoreProduct> storeProducts;
    private final BetterPager<StoreProduct> pager = new BetterPager<>();
    private int currentPageNum;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This panel's constructor
     */
    public BrowseStoreP(List<StoreProduct> storeProducts) throws BadLocationException {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Change this

        currentPageNum = 1;
        this.storeProducts = storeProducts;

        paintEverything();
    }

    /**
     * It allows this page's components to be repainted (revalidate() & repaint() didn't work)
     * @throws BadLocationException bad locations within a document model (that is, attempts to reference a location
     *                              that doesn't exist)
     */
    public void paintEverything() throws BadLocationException {
        this.removeAll();
        productPanels.clear();

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
        return pager.getMaxPageNum(storeProducts);
    }

    /**
     * It makes it possible to assign a controller to this panel's components
     * @param c the desired controller
     */
    public void setController(ActionListener c) {
        if (productPanels != null) {
            for (StoreProductMiniP miniProduct : productPanels) {
                miniProduct.setController(c);
            }
        }
        firstPage.addActionListener(c);
        previousPage.addActionListener(c);
        nextPage.addActionListener(c);
        lastPage.addActionListener(c);
    }
}