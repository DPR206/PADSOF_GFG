package view.browserPanels;

import model.product.SecondHandProduct;
import model.store.BetterPager;
import model.user.RegisteredClient;
import view.App;
import view.miniPanels.SecondHandMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

import static main.Main.brownColour;

public class BrowseMyWalletP extends JPanel implements BigView {
    private final JButton firstPage = new JButton("<< First Page");
    private final JButton previousPage = new JButton("< Previous Page");
    private final JButton nextPage = new JButton("Next Page >");
    private final JButton lastPage = new JButton("Last Page >>");
    private final List<SecondHandMiniP> productPanels = new ArrayList<>();
    private final BetterPager<SecondHandProduct> pager = new BetterPager<>();
    private final App app;
    private final RegisteredClient owner;
    private List<SecondHandProduct> secondHandProducts = new ArrayList<>();
    private int currentPageNum;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This panel's constructor
     */
    public BrowseMyWalletP(App app, RegisteredClient owner) throws BadLocationException {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Change this

        this.app = app;
        this.owner = owner;
        currentPageNum = 1;

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

        JLabel title = new JLabel("My wallet'");
        title.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, brownColour));
        this.add(title);

        this.secondHandProducts = Arrays.asList(owner.getWallet().getProducts());

        List<SecondHandProduct> currentSecondHandProducts = pager.pageItemList(secondHandProducts, currentPageNum);

        int index = 1;
        for (SecondHandProduct product : currentSecondHandProducts) {
            SecondHandMiniP miniProduct = new SecondHandMiniP(product, index);
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
        if (currentPageNum != pager.getMaxPageNum(secondHandProducts)) {
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
        return pager.getMaxPageNum(secondHandProducts);
    }

    public List<SecondHandMiniP> getProductPanels() {
        return productPanels;
    }

    /**
     * It makes it possible to assign a controller to this panel's components
     * @param c the desired controller
     */
    public void setController(ActionListener c) {
        if (productPanels != null) {
            for (SecondHandMiniP miniProduct : productPanels) {
                miniProduct.setController(c);
            }
        }
        firstPage.addActionListener(c);
        previousPage.addActionListener(c);
        nextPage.addActionListener(c);
        lastPage.addActionListener(c);
    }
}