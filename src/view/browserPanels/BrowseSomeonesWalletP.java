package view.browserPanels;

import model.product.SecondHandProduct;
import model.user.RegisteredClient;
import model.user.User;
import view.miniPanels.SecondHandMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;

import static main.Main.brownColour;

public class BrowseSomeonesWalletP extends BrowserPanel<SecondHandProduct> {
    private final User owner;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    /**
     * This panel's constructor
     */
    public BrowseSomeonesWalletP(RegisteredClient owner) throws BadLocationException {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.owner = owner;

        paintEverything();
    }

    @Override
    public void paintEverything() throws BadLocationException {
        this.removeAll();

        JLabel title = new JLabel(owner.getUserName() + "'s wallet'");
        title.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, brownColour));
        this.add(title);

        super.addAllMiniPanels();
        this.add(super.getPageTurner());
        this.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, brownColour));

        this.revalidate();
        this.repaint();
    }

    @Override
    public void addMiniPanel(SecondHandProduct item, int index) throws BadLocationException {
        SecondHandMiniP miniProduct = new SecondHandMiniP(item, index);
        super.addMiniPanel(miniProduct);
        this.add(miniProduct);
    }

    public User getOwner() {
        return owner;
    }
}