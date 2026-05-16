package view.browserPanels;

import model.store.Parameter;
import view.miniPanels.AbstractMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Mixed browser panel.
 * @param <G> the type parameter
 * @param <U> the type parameter
 * @author Ana O.R.
 * @version 1.0
 */
public abstract class AbstractMixedBrowserP<G, U> extends JPanel {
    @Serial
    private static final long serialVersionUID = 1L;
    private final JButton firstPage = new JButton("<< First Page");
    private final JButton previousPage = new JButton("< Previous Page");
    private final JButton nextPage = new JButton("Next Page >");
    private final JButton lastPage = new JButton("Last Page >>");
    private final List<AbstractMiniP> firstMiniPanels = new ArrayList<>();
    private final List<AbstractMiniP> secondMiniPanels = new ArrayList<>();
    /**
     * The Container items.
     */
    protected JPanel containerItems;
    private int currentPageNum;
    private List<G> firstItemList = new ArrayList<>();
    private List<U> secondItemList = new ArrayList<>();

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Mixed browser panel.
     */
    public AbstractMixedBrowserP() {
        currentPageNum = 1;

        this.setLayout(new BorderLayout());

        containerItems = new JPanel();
        containerItems.setLayout(new BoxLayout(containerItems, BoxLayout.Y_AXIS));
        containerItems.setOpaque(false);

        this.add(containerItems, BorderLayout.NORTH);
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
        firstMiniPanels.clear();
        secondMiniPanels.clear();
        containerItems.removeAll();

        int itemsPerPage = Parameter.getParam().getItemsPerPage();
        int startGlobalIndex = (currentPageNum - 1) * itemsPerPage;
        int totalItems = firstItemList.size() + secondItemList.size();
        int maxIndex = Math.min(startGlobalIndex + itemsPerPage, totalItems);

        for (int i = startGlobalIndex; i < maxIndex; i++) {
            int panelIndex = (i % itemsPerPage) + 1;
            if (i < firstItemList.size()) {
                addFirstMiniPanel(firstItemList.get(i), panelIndex);
            } else {
                int secondListIndex = i - firstItemList.size();
                addSecondMiniPanel(secondItemList.get(secondListIndex), panelIndex);
            }
        }

    }

    /**
     * Add mini panel.
     * @param item  the item
     * @param index the index
     * @throws BadLocationException the bad location exception
     */
    public abstract void addFirstMiniPanel(G item, int index) throws BadLocationException;

    /**
     * Add mini panel.
     * @param newMiniPanel the new mini panel
     */
    public void addFirstMiniPanel(AbstractMiniP newMiniPanel) {
        this.firstMiniPanels.add(newMiniPanel);
        this.containerItems.add(newMiniPanel);
    }

    /**
     * Add mini panel.
     * @param item  the item
     * @param index the index
     * @throws BadLocationException the bad location exception
     */
    public abstract void addSecondMiniPanel(U item, int index) throws BadLocationException;

    /**
     * Add mini panel.
     * @param newMiniPanel the new mini panel
     */
    public void addSecondMiniPanel(AbstractMiniP newMiniPanel) {
        this.secondMiniPanels.add(newMiniPanel);
        this.containerItems.add(newMiniPanel);
    }

    /**
     * Clear items' container.
     */
    protected void clearItemsContainer() {
        this.containerItems.removeAll();
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
     * Gets mini panels.
     * @return the mini panels
     */
    public List<AbstractMiniP> getFirstMiniPanels() {
        return firstMiniPanels;
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
        int totalItems = firstItemList.size() + secondItemList.size();
        int itemsPerPage = Parameter.getParam().getItemsPerPage();
        if (totalItems == 0) {
            return 1;
        }

        if (totalItems % itemsPerPage == 0) {
            return totalItems / itemsPerPage;
        }
        return (totalItems / itemsPerPage) + 1;
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
     * Gets previous page.
     * @return the previous page
     */
    public JButton getPreviousPage() {
        return previousPage;
    }

    /**
     * Gets mini panels.
     * @return the mini panels
     */
    public List<AbstractMiniP> getSecondMiniPanels() {
        return secondMiniPanels;
    }

    /**
     * It sets the first item list
     * @param newFirstItemList the new first item list
     * @throws BadLocationException the bad location exception
     */
    public void setFirstItemList(List<G> newFirstItemList) throws BadLocationException {
        this.firstItemList = newFirstItemList;
    }

    /**
     * It sets the second item list
     * @param newSecondItemList the new second item list
     * @throws BadLocationException the bad location exception
     */
    public void setSecondItemList(List<U> newSecondItemList) throws BadLocationException {
        this.secondItemList = newSecondItemList;
    }
}