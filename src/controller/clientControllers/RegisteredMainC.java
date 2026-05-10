package controller.clientControllers;

import controller.Controller;
import controller.browserControllers.BrowseStoreC;
import controller.employeeControllers.RegisteredMakeOfferC;
import model.store.Store;
import model.user.RegisteredClient;
import view.App;
import view.clientPanels.RegisteredMainP;

import javax.swing.text.BadLocationException;

public class RegisteredMainC implements Controller {
    private final RegisteredMainP view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public RegisteredMainC(App frame, Store model) throws BadLocationException {
        this.frame = frame;
        this.view = frame.getRegisteredMainPanel();
        this.model = model;

        initializeActions();
        linkControllers();
    }

    public void initializeActions() {
        //this.frame.changeVisibleBanner("BANNER_REGISTERED");
        try {
            this.view.getBrowsePanel().setFirstItemList(model.getPacks());
            this.view.getBrowsePanel().setSecondItemList(model.getStoreProductList());
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
        this.view.getCardLayout().show(this.view.getBottom(), "Search");

        view.getSearch().addActionListener(e -> {
            try {
                this.view.getBrowsePanel()
                         .setSecondItemList(((RegisteredClient) this.frame.getUser()).searchStoreProduct());
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
        new SearcherC(frame, model, view.getFilterPanel());
        new RegisteredMakeOfferC(frame, model, view.getMakeOfferP());
    }

}