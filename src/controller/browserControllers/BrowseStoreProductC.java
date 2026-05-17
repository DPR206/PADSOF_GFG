package controller.browserControllers;

import controller.Controller;
import controller.managerControllers.*;
import model.product.*;
import model.store.Store;
import view.App;
import view.browserPanels.BrowseStoreProducts;
import view.managerPanels.*;
import view.miniPanels.AbstractMiniP;
import view.miniPanels.StoreProductMiniP;

import javax.swing.text.BadLocationException;
import java.util.List;

/**
 * The type Browse store product c.
 * @author Sofía C.L.
 * @version 1.0
 */
public class BrowseStoreProductC implements Controller {

    private final BrowseStoreProducts browser;
    private final App frame;
    private ManageIndividualFiguraP manageFigurine = null;
    private ManagerIndividualGameP manageGame = null;
    private ManagerIndividualComicP manageComic = null;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Browse store product c.
     * @param browser the browser
     * @param frame   the frame
     */
    public BrowseStoreProductC(BrowseStoreProducts browser, App frame) {
        this.browser = browser;
        this.frame = frame;
        try {
            initializeActions();
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initializeActions() throws BadLocationException {
        List<StoreProduct> products = Store.getInstance().getStoreProductList();
        this.browser.setItemList(products);

        /*Añadimos el controlador*/
        List<AbstractMiniP> panels = this.browser.getMiniPanels();

        /*Asignamos acción*/
        for (AbstractMiniP panel : panels) {
            StoreProductMiniP miniSp = (StoreProductMiniP) panel;
            miniSp.getButton().addActionListener(e -> {
                if (miniSp.getStoreProduct() instanceof Comic) {
                    this.manageComic = new ManagerIndividualComicP();
                    new ManageComicC(miniSp, this.manageComic);
                    this.frame.addCard(this.manageComic, "MANAGE COMIC");
                    try {
                        this.frame.changeVisibleCard("MANAGE COMIC");
                    } catch (BadLocationException ex) {
                        throw new RuntimeException(ex);
                    }
                } else if (miniSp.getStoreProduct() instanceof Figurine) {
                    this.manageFigurine = new ManageIndividualFiguraP();
                    new ManageFigurineC(miniSp, this.manageFigurine);
                    this.frame.addCard(this.manageFigurine, "MANAGE FIGURINE");
                    try {
                        this.frame.changeVisibleCard("MANAGE FIGURINE");
                    } catch (BadLocationException ex) {
                        throw new RuntimeException(ex);
                    }
                } else if (miniSp.getStoreProduct() instanceof Game) {
                    this.manageGame = new ManagerIndividualGameP();
                    new ManageGameC(miniSp, this.manageGame);
                    this.frame.addCard(this.manageGame, "MANAGE GAME");
                    try {
                        this.frame.changeVisibleCard("MANAGE GAME");
                    } catch (BadLocationException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
        }
    }
}