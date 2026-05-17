package view.browserPanels;

import model.product.SecondHandProduct;
import model.user.RegisteredClient;
import model.user.User;
import view.App;
import view.miniPanels.SecondHandMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;

import java.awt.*;

import static main.Main.brownColour;

/**
 * The type Browse someone's wallet p.
 * @author Ana O.R.
 * @version 1.0
 */
public class BrowseSomeonesWalletP extends AbstractBrowserP<SecondHandProduct> {
    private final User owner;
    private final App frame;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This panel's constructor
     * @param owner the owner
     * @throws BadLocationException the bad location exception
     */
    public BrowseSomeonesWalletP(App frame, RegisteredClient owner) throws BadLocationException {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.frame = frame;
        this.owner = owner;

        paintEverything();
    }

    @Override
    public void paintEverything() throws BadLocationException {
        this.removeAll();

        if (owner == frame.getUser()) {
            JLabel title = new JLabel("My wallet'");
            title.setFont(new Font(title.getFont().getFontName(), Font.BOLD, 20));
            this.add(title);
        } else {
            JLabel title = new JLabel(owner.getUserName() + "'s wallet'");
            title.setFont(new Font(title.getFont().getFontName(), Font.BOLD, 20));
            this.add(title);
        }

        super.addAllMiniPanels();
        this.add(super.getPageTurner());
        this.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, brownColour));

        this.revalidate();
        this.repaint();
    }

    @Override
    public void addMiniPanel(SecondHandProduct item, int index) throws BadLocationException {
        SecondHandMiniP miniProduct = new SecondHandMiniP(item, index, "Add to Offer", null);
        super.addMiniPanel(miniProduct);
        this.add(miniProduct);
    }

    /**
     * It gets the owner
     * @return the owner
     */
    public User getOwner() {
        return owner;
    }
}