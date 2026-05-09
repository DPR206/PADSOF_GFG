package view.browserPanels;

import model.product.SecondHandProduct;
import model.user.RegisteredClient;
import model.user.User;
import view.App;
import view.miniPanels.SecondHandMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.util.List;

import static main.Main.brownColour;

public class BrowseSomeonesWalletP extends BrowserPanel<SecondHandProduct> {
    User owner;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This panel's constructor
     */
    public BrowseSomeonesWalletP(App app, RegisteredClient owner) throws BadLocationException {
        super(app);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.owner = owner;

        paintEverything();
    }

    @Override
    public void paintEverything() throws BadLocationException {
        this.removeAll();
        getMiniPanels().clear();

        JLabel title = new JLabel(owner.getUserName() + "'s wallet'");
        title.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, brownColour));
        this.add(title);

        super.setItemList(
                List.of(((RegisteredClient) owner).getWallet().getProducts())); // DUE: Esto debe darlo el controlador

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
}