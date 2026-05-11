package controller.clientControllers;

import controller.Controller;
import controller.browserControllers.BrowseStoreC;
import model.store.Store;
import view.App;
import view.clientPanels.RegisteredMainP;

import javax.swing.text.BadLocationException;

public class RegisteredMainC implements Controller {
    private final RegisteredMainP view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public RegisteredMainC(App frame, Store model) {
        this.frame = frame;
        this.view = frame.getRegisteredMainPanel();
        this.model = model;

        initializeActions();
    }

    public void initializeActions() {
        this.view.getCardLayout().show(this.view.getBottom(), "Search");
        linkControllers();

        view.getSearch().addActionListener(e -> {
            this.view.getCardLayout().show(this.view.getBottom(), "Search");
            linkControllers();
        });

        view.getFilters().addActionListener(e -> {
            this.view.getCardLayout().show(this.view.getBottom(), "Filters");
        });

        view.getSecondHand().addActionListener(e -> {
            this.view.getCardLayout().show(this.view.getBottom(), "Second Hand");
            linkControllers();
        });
    }

    public void linkControllers() {
        try {
            new BrowseStoreC(frame, model, view.getBrowsePanel());
        } catch (BadLocationException ex) {
            throw new RuntimeException(ex);
        }
        new SearcherC(frame, model, view.getFilterPanel());
        new RegisteredMakeOfferC(frame, model, view.getMakeOfferP());
    }

}