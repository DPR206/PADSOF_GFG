package view.browserPanels;

import model.product.SecondHandProduct;
import model.store.Store;
import view.App;
import view.miniPanels.SecondHandMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;

import static main.Main.brownColour;

public class BrowseSecondHandProductsP extends BrowserPanel<SecondHandProduct> {

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This panel's constructor
     */
    public BrowseSecondHandProductsP(App app) throws BadLocationException {
        super(app);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        paintEverything();
    }

    @Override
    public void paintEverything() throws BadLocationException {
        this.removeAll();
        getMiniPanels().clear();

        super.setItemList(Store.getInstance().getSecondHandProductList()); // DUE: Esto debe darlo el controlador

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