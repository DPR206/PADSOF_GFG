package view.browserPanels;

import model.store.BetterPager;
import view.miniPanels.AbstractMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Abstract cluster browser p.
 * @param <G> the type parameter
 * @author Ana O.R.
 * @version 1.0
 */
public abstract class AbstractClusterBrowserP<G> extends JPanel {
    private final JButton firstPage = new JButton("<< First Page");
    private final JButton previousPage = new JButton("< Previous Page");
    private final JButton nextPage = new JButton("Next Page >");
    private final JButton lastPage = new JButton("Last Page >>");
    private final BetterPager<G> pager = new BetterPager<>();
    private final int clusterSize;
    private int currentPageNum;
    private List<AbstractMiniP> miniPanels = new ArrayList<>();
    private List<G> itemList = new ArrayList<>();

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Browser panel.
     * @param clusterSize the cluster size
     */
    public AbstractClusterBrowserP(int clusterSize) {
        currentPageNum = 1;
        this.clusterSize = clusterSize;
    }

    /**
     * It allows this page's components to be repainted (revalidate() & repaint() didn't work)
     * @throws BadLocationException bad locations within a document model (that is, attempts to reference a location
     *                              that doesn't exist)
     */
    public abstract void paintEverything() throws BadLocationException;

    /**
     * Add all mini panels.
     * @throws BadLocationException the bad location exception
     */
    public void addAllMiniPanels() throws BadLocationException {
        miniPanels.clear();

        List<G> currentItemList = pager.pageItemListCluster(itemList, currentPageNum, clusterSize);

        int index = 1;
        for (G item : currentItemList) {
            addMiniPanel(item, index);
            index++;
        }
    }

    /**
     * Add mini panel.
     * @param item  the item
     * @param index the index
     * @throws BadLocationException the bad location exception
     */
    public abstract void addMiniPanel(G item, int index) throws BadLocationException;

    /**
     * Add mini panel.
     * @param newMiniPanel the new mini panel
     */
    public void addMiniPanel(AbstractMiniP newMiniPanel) {
        this.miniPanels.add(newMiniPanel);
    }

    /**
     * Gets current page num.
     * @return the current page num
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
     * It gets the first page
     * @return the first page
     */
    public JButton getFirstPage() {
        return firstPage;
    }

    /**
     * It gets the last page
     * @return the last page
     */
    public JButton getLastPage() {
        return lastPage;
    }

    /**
     * It gets the available store product list's max page number
     * @return the available store product list's max page number
     */
    public int getMaxPageNum() {
        return pager.getMaxPageNumCluster(itemList, clusterSize);
    }

    /**
     * Gets mini panels.
     * @return the mini panels
     */
    public List<AbstractMiniP> getMiniPanels() {
        return miniPanels;
    }

    /**
     * Sets mini panels.
     * @param newMiniPanels the new mini panels
     */
    public void setMiniPanels(List<AbstractMiniP> newMiniPanels) {
        this.miniPanels = newMiniPanels;
    }

    /**
     * It gets the next page
     * @return the next page
     */
    public JButton getNextPage() {
        return nextPage;
    }

    /**
     * Gets page turner.
     * @return the page turner
     */
    public JPanel getPageTurner() {
        JPanel pageTurner = new JPanel(new FlowLayout());
        if (currentPageNum != 1) {
            pageTurner.add(firstPage);
            pageTurner.add(previousPage);
        }
        pageTurner.add(new JLabel("Page " + currentPageNum));
        if (currentPageNum != getMaxPageNum()) {
            pageTurner.add(nextPage);
            pageTurner.add(lastPage);
        }
        return pageTurner;
    }

    /**
     * Gets pager.
     * @return the pager
     */
    public BetterPager<G> getPager() {
        return pager;
    }

    /**
     * Gets previous page.
     * @return the previous page
     */
    public JButton getPreviousPage() {
        return previousPage;
    }

    /**
     * Sets item list.
     * @param newItemList the new item list
     * @throws BadLocationException the bad location exception
     */
    public void setItemList(List<G> newItemList) throws BadLocationException {
        this.itemList = newItemList;
    }
}