package controller.clientControllers;

import controller.Controller;
import controller.browserControllers.BrowseStoreC;
import model.store.Store;
import view.App;
import view.clientPanels.UnregisteredMainP;

import javax.swing.text.BadLocationException;

public class UnregisteredMainC implements Controller {
    private final UnregisteredMainP view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public UnregisteredMainC(App frame, Store model) throws BadLocationException {
        this.frame = frame;
        this.view = frame.getUnregisteredMainPanel();
        this.model = model;

        initializeActions();
        linkControllers();
    }

    public void initializeActions() {
        this.view.getCardLayout().show(this.view.getBottom(), "Search");

        view.getSearch().addActionListener(e -> {
            this.view.getCardLayout().show(this.view.getBottom(), "Search");
        });

        view.getFilters().addActionListener(e -> {
            this.view.getCardLayout().show(this.view.getBottom(), "Filters");
        });
    }

    public void linkControllers() throws BadLocationException {
        new BrowseStoreC(frame, model, view.getBrowsePanel());
        new SearcherC(frame, model, view.getFilterPanel());
    }
}