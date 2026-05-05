package view.browserPanels;

import model.store.BetterPager;
import model.user.RegisteredClient;
import view.App;
import view.miniPanels.UserMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static main.Main.brownColour;

public class BrowseWalletOwnersP extends JPanel implements BigView {
    private final JButton firstPage = new JButton("<< First Page");
    private final JButton previousPage = new JButton("< Previous Page");
    private final JButton nextPage = new JButton("Next Page >");
    private final JButton lastPage = new JButton("Last Page >>");
    private final List<UserMiniP> userPanels = new ArrayList<>();
    private final BetterPager<RegisteredClient> pager = new BetterPager<>();
    private final App app;
    private final int num_rows = 3;
    private final int num_columns = 4;
    private final String buttonName;
    private final List<RegisteredClient> users = new ArrayList<>();
    private int currentPageNum;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This panel's constructor
     */
    public BrowseWalletOwnersP(App app, List<RegisteredClient> users, String buttonName) throws BadLocationException {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.app = app;
        for (RegisteredClient user : users) {
            if (user.getWallet().getProducts().length > 0) {
                this.users.add(user);
            }
        }
        this.buttonName = buttonName;
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
        userPanels.clear();

        List<RegisteredClient> currentRegisteredClients = pager.pageItemList(users, currentPageNum);

        JPanel usersPanel = new JPanel(new GridLayout(num_rows, num_columns));
        int cellsLeft = num_columns * num_rows;
        for (RegisteredClient user : currentRegisteredClients) {
            UserMiniP miniRegisteredClient = new UserMiniP(user, buttonName);
            userPanels.add(miniRegisteredClient);
            usersPanel.add(miniRegisteredClient);
            cellsLeft--;
        }

        for (int i = 0; i < cellsLeft; i++) {
            usersPanel.add(new JLabel(""));
        }

        this.add(usersPanel);

        JPanel pageTurner = new JPanel(new FlowLayout());

        if (currentPageNum != 1) {
            pageTurner.add(firstPage);
            pageTurner.add(previousPage);
        }
        pageTurner.add(new JLabel("Page " + currentPageNum));
        if (currentPageNum != pager.getMaxPageNumCluster(users, num_columns * num_rows)) {
            pageTurner.add(nextPage);
            pageTurner.add(lastPage);
        }
        this.add(pageTurner);

        this.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, brownColour));

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
        return pager.getMaxPageNum(users);
    }

    public List<UserMiniP> getUserPanels() {
        return userPanels;
    }

    /**
     * It makes it possible to assign a controller to this panel's components
     * @param c the desired controller
     */
    public void setController(ActionListener c) {
        if (userPanels != null) {
            for (UserMiniP miniRegisteredClient : userPanels) {
                miniRegisteredClient.setController(c);
            }
        }
        firstPage.addActionListener(c);
        previousPage.addActionListener(c);
        nextPage.addActionListener(c);
        lastPage.addActionListener(c);
    }
}