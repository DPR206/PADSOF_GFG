package view.browserPanels;

import model.store.BetterPager;
import model.store.Parameter;
import view.miniPanels.MiniPanel;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Mixed browser panel.
 * @param <G> the type parameter
 * @param <U> the type parameter
 */
public abstract class MixedBrowserPanel<G, U> extends JPanel {
    private static final long serialVersionUID = 1L;
	private final JButton firstPage = new JButton("<< First Page");
    private final JButton previousPage = new JButton("< Previous Page");
    private final JButton nextPage = new JButton("Next Page >");
    private final JButton lastPage = new JButton("Last Page >>");
    private final BetterPager<G> firstPager = new BetterPager<>();
    private final BetterPager<U> secondPager = new BetterPager<>();
    private final List<MiniPanel> firstMiniPanels = new ArrayList<>();
    private final List<MiniPanel> secondMiniPanels = new ArrayList<>();
    private int currentPageNum;
    private List<G> firstItemList = new ArrayList<>();
    private List<U> secondItemList = new ArrayList<>();
    protected JPanel containerItems;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Mixed browser panel.
     */
    public MixedBrowserPanel() {
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
    public void addFirstMiniPanel(MiniPanel newMiniPanel) {
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
    public void addSecondMiniPanel(MiniPanel newMiniPanel) {
        this.secondMiniPanels.add(newMiniPanel);
        this.containerItems.add(newMiniPanel);
    }

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

    public List<G> getFirstItemList() {
        return firstItemList;
    }

    public void setFirstItemList(List<G> newFirstItemList) {
        this.firstItemList = newFirstItemList;
    }

    /**
     * Gets mini panels.
     * @return the mini panels
     */
    public List<MiniPanel> getFirstMiniPanels() {
        return firstMiniPanels;
    }

    public JButton getFirstPage() {
        return firstPage;
    }

    public BetterPager<G> getFirstPager() {
        return firstPager;
    }

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
     * Gets firstPager.
     * @return the firstPager
     */
    public BetterPager<G> getPager() {
        return firstPager;
    }

    /**
     * Gets previous page.
     * @return the previous page
     */
    public JButton getPreviousPage() {
        return previousPage;
    }

    public List<U> getSecondItemList() {
        return secondItemList;
    }

    public void setSecondItemList(List<U> newSecondItemList) {
        this.secondItemList = newSecondItemList;
    }

    /**
     * Gets mini panels.
     * @return the mini panels
     */
    public List<MiniPanel> getSecondMiniPanels() {
        return secondMiniPanels;
    }

    public BetterPager<U> getSecondPager() {
        return secondPager;
    }

    /**
     * It makes it possible to assign a controller to this panel's components
     * @param c the desired controller
     */
    public void setController(ActionListener c) {
        for (MiniPanel miniPanel : firstMiniPanels) {
            miniPanel.setController(c);
        }
        for (MiniPanel miniPanel : secondMiniPanels) {
            miniPanel.setController(c);
        }
        firstPage.addActionListener(c);
        previousPage.addActionListener(c);
        nextPage.addActionListener(c);
        lastPage.addActionListener(c);
    }
}