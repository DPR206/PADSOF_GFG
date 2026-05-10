package view.browserPanels;

import model.store.BetterPager;
import view.miniPanels.MiniPanel;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Browser panel.
 * @param <G> the type parameter
 */
public abstract class BrowserPanel<G> extends JPanel {
    private final JButton firstPage = new JButton("<< First Page");
    private final JButton previousPage = new JButton("< Previous Page");
    private final JButton nextPage = new JButton("Next Page >");
    private final JButton lastPage = new JButton("Last Page >>");
    private final BetterPager<G> pager = new BetterPager<>();
    private int currentPageNum;
    private List<MiniPanel> miniPanels = new ArrayList<>();
    private List<G> itemList = new ArrayList<>();

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Browser panel.
     */
    public BrowserPanel() {
        currentPageNum = 1;
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

        List<G> currentItemList = pager.pageItemList(itemList, currentPageNum);

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
    public void addMiniPanel(MiniPanel newMiniPanel) {
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

    public JButton getFirstPage() {
        return firstPage;
    }

    public List<G> getItemList() {
        return itemList;
    }

    /**
     * Sets item list.
     * @param newItemList the new item list
     */
    public void setItemList(List<G> newItemList) {
        this.itemList = newItemList;
    }

    public JButton getLastPage() {
        return lastPage;
    }

    /**
     * It gets the available store product list's max page number
     * @return the available store product list's max page number
     */
    public int getMaxPageNum() {
        return pager.getMaxPageNum(itemList);
    }

    /**
     * Gets mini panels.
     * @return the mini panels
     */
    public List<MiniPanel> getMiniPanels() {
        return miniPanels;
    }

    /**
     * Sets mini panels.
     * @param newMiniPanels the new mini panels
     */
    public void setMiniPanels(List<MiniPanel> newMiniPanels) {
        this.miniPanels = newMiniPanels;
    }

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
     * It makes it possible to assign a controller to this panel's components
     * @param c the desired controller
     */
    public void setController(ActionListener c) {
        if (itemList != null) {
            for (MiniPanel miniPanel : miniPanels) {
                miniPanel.setController(c);
            }
        }
        firstPage.addActionListener(c);
        previousPage.addActionListener(c);
        nextPage.addActionListener(c);
        lastPage.addActionListener(c);
    }
}