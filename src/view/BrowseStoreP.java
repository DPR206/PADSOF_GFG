package view;

import model.product.StoreProduct;
import model.store.BetterPager;

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
    public BrowseStoreP(List<StoreProduct> storeProducts) throws BadLocationException {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Change this

        currentPageNum = 1;
        this.storeProducts = storeProducts;

        paintEverything();
    }

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

        System.out.println("CurrentPageNum: " + currentPageNum);

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

    public int getCurrentPageNum() {
        return currentPageNum;
    }

    public void setCurrentPageNum(int newCurrentPageNum) throws BadLocationException {
        System.out.println("setCurrentPageNum: " + newCurrentPageNum);
        this.currentPageNum = newCurrentPageNum;
        paintEverything();
    }

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