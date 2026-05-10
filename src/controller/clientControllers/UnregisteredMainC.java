package controller.clientControllers;

import controller.browserControllers.BrowseStoreC;
import model.store.Store;
import model.user.UnregisteredClient;
import view.App;
import view.clientPanels.UnregisteredMainP;

import javax.swing.text.BadLocationException;

public class UnregisteredMainC {
    private final UnregisteredMainP view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public UnregisteredMainC(App frame, Store model) {
        this.frame = frame;
        this.view = frame.getUnregisteredMainPanel();
        this.model = model;

        inicializar();
        linkControllers();
    }

    private void inicializar() {
        this.view.getBrowsePanel().setFirstItemList(model.getPacks());
        this.view.getBrowsePanel().setSecondItemList(model.getStoreProductList());
        try {
            view.getBrowsePanel().paintEverything();
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
        this.view.getCardLayout().show(this.view.getBottom(), "Search");

        view.getSearch().addActionListener(e -> {
            this.view.getBrowsePanel()
                     .setSecondItemList(((UnregisteredClient) this.frame.getUser()).searchStoreProduct());
            try {
                view.getBrowsePanel().setCurrentPageNum(1);
            } catch (BadLocationException ex) {
                ex.printStackTrace();
            }
            this.view.getCardLayout().show(this.view.getBottom(), "Search");
        });

        view.getFilters().addActionListener(e -> {
            this.view.getCardLayout().show(this.view.getBottom(), "Filters");
        });
    }

    public void linkControllers() {
        new BrowseStoreC(frame, model, view.getBrowsePanel());
        this.view.getFilterPanel().setController(new SearcherC(frame, model, view.getFilterPanel()));
    }
}