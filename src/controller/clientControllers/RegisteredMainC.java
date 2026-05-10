package controller.clientControllers;

import controller.browserControllers.BrowseStoreC;
import controller.employeeControllers.RegisteredMakeOfferC;
import model.store.Store;
import model.user.RegisteredClient;
import view.App;
import view.clientPanels.RegisteredMainP;

import javax.swing.text.BadLocationException;

public class RegisteredMainC {
    private final RegisteredMainP view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public RegisteredMainC(App frame, Store model) throws BadLocationException {
        this.frame = frame;
        this.view = frame.getRegisteredMainPanel();
        this.model = model;

        inicializar();
        linkControllers();
    }

    private void inicializar() {
        //this.frame.changeVisibleBanner("BANNER_REGISTERED");

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
                     .setSecondItemList(((RegisteredClient) this.frame.getUser()).searchStoreProduct());
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

        view.getSecondHand().addActionListener(e -> {
            this.view.getCardLayout().show(this.view.getBottom(), "Second Hand");
        });
    }

    public void linkControllers() throws BadLocationException {
        new BrowseStoreC(frame, model, view.getBrowsePanel());
        this.view.getFilterPanel().setController(new SearcherC(frame, model, view.getFilterPanel()));
        this.view.getMakeOfferP().setController(new RegisteredMakeOfferC(frame, model, view.getMakeOfferP()));
    }

}