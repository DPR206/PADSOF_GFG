package view.browserPanels;

import model.user.RegisteredClient;
import view.App;
import view.miniPanels.UserMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.util.List;

import static main.Main.brownColour;

public class BrowseWalletOwnersP extends BrowserPanel<RegisteredClient> {
    private final String buttonName;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This panel's constructor
     */
    public BrowseWalletOwnersP(App app, List<RegisteredClient> users, String buttonName) throws BadLocationException {
        super(app);
        this.setLayout(new GridLayout(3, 3));

        super.setItemList(users);
        this.buttonName = buttonName;

        paintEverything();
    }

    /**
     * It allows this page's components to be repainted (revalidate() & repaint() didn't work)
     * @throws BadLocationException bad locations within a document model (that is, attempts to reference a location
     *                              that doesn't exist)
     */
    public void paintEverything() throws BadLocationException {
        this.removeAll();
        

        // Item list was set in constructor

        super.addAllMiniPanels();
        this.add(super.getPageTurner());
        this.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, brownColour));

        this.revalidate();
        this.repaint();
    }

    @Override
    public void addMiniPanel(RegisteredClient item, int index) {
        UserMiniP miniUser = new UserMiniP(item, buttonName);
        super.addMiniPanel(miniUser);
        this.add(miniUser);
    }
}