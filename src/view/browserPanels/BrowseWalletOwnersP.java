package view.browserPanels;

import model.user.RegisteredClient;
import view.miniPanels.UserMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;

import static main.Main.brownColour;

public class BrowseWalletOwnersP extends AbstractClusterBrowserP<RegisteredClient> {
    private final String buttonName;
    private JPanel usersPanel = new JPanel(new GridLayout(3, 3));

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This panel's constructor
     */
    public BrowseWalletOwnersP(String buttonName) throws BadLocationException {
        super(9);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

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
        usersPanel.removeAll();

        super.addAllMiniPanels();
        this.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, brownColour));
        for (int i = 0; i < (9 - super.getMiniPanels().size()); i++) {
            usersPanel.add(new JLabel(""));
        }
        this.add(usersPanel);
        this.add(super.getPageTurner());

        this.revalidate();
        this.repaint();
    }

    @Override
    public void addMiniPanel(RegisteredClient item, int index) {
        UserMiniP miniUser = new UserMiniP(item, buttonName, ".\\resources\\app\\default_user.png");
        super.addMiniPanel(miniUser);
        usersPanel.add(miniUser);
    }
}