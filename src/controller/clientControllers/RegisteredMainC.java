package controller.clientControllers;

import controller.Controller;
import controller.browserControllers.BrowseRecomStoreProductsC;
import controller.browserControllers.MixedBrowseStoreC;
import model.store.Store;
import view.App;
import view.clientPanels.RegisteredMainP;

import javax.swing.text.BadLocationException;

/**
 * The type Registered main c.
 * @author Duna P.R. & Ana O.R.
 * @version 1.0
 */
public class RegisteredMainC implements Controller {
    private final RegisteredMainP view;
    private final App frame;
    private final Store model;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Registered main c.
     * @param frame the frame
     * @param model the model
     */
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
        });

        view.getFilters().addActionListener(e -> {
            this.view.getCardLayout().show(this.view.getBottom(), "Filters");
        });

        view.getSecondHand().addActionListener(e -> {
            this.view.getCardLayout().show(this.view.getBottom(), "Second Hand");
        });
    }

    /**
     * Link controllers.
     */
    public void linkControllers() {
        try {
            new MixedBrowseStoreC(frame, model, view.getBrowsePanel());
            new BrowseRecomStoreProductsC(frame, view.getRecommendedProductsPanel(), model);
        } catch (BadLocationException ex) {
            throw new RuntimeException(ex);
        }
        new SearcherC(frame, model, view.getFilterPanel());
        new RegisteredBrowseForOfferC(frame, model, view.getMakeOfferP());
    }

}