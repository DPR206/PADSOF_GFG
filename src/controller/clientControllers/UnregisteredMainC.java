package controller.clientControllers;

import controller.Controller;
import controller.browserControllers.MixedBrowseStoreC;
import model.store.Store;
import view.App;
import view.clientPanels.UnregisteredMainP;

import javax.swing.text.BadLocationException;

/**
 * The type Unregistered main c.
 * @author Ana O.R.
 * @version 1.0
 */
public class UnregisteredMainC implements Controller {
    private final UnregisteredMainP view;
    private final App frame;
    private final Store model;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Unregistered main c.
     * @param frame the frame
     * @param model the model
     */
    public UnregisteredMainC(App frame, Store model) {
        this.frame = frame;
        this.view = frame.getUnregisteredMainPanel();
        this.model = model;

        initializeActions();
    }

    @Override
    public void initializeActions() {
        this.view.getCardLayout().show(this.view.getBottom(), "Search");
        linkControllers();

        view.getSearch().addActionListener(e -> {
            this.view.getCardLayout().show(this.view.getBottom(), "Search");
            linkControllers();
        });

        view.getFilters().addActionListener(e -> this.view.getCardLayout().show(this.view.getBottom(), "Filters"));
    }

    /**
     * Link controllers.
     */
    public void linkControllers() {
        try {
            new MixedBrowseStoreC(frame, model, view.getBrowsePanel());
        } catch (BadLocationException ex) {
            throw new RuntimeException(ex);
        }
        new SearcherC(frame, model, view.getFilterPanel());
    }
}