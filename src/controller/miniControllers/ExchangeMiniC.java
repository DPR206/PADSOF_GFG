package controller.miniControllers;

import controller.Controller;
import controller.browserControllers.BrowseExchangesC;
import model.store.Store;
import view.App;
import view.browserPanels.BrowseExchangesP;
import view.miniPanels.ExchangeMiniP;

import javax.swing.*;
import java.awt.*;

/**
 * The type Exchange mini c.
 */
public class ExchangeMiniC implements Controller {
    private final ExchangeMiniP view;
    private final App frame;
    private final Store model;
    private final BrowseExchangesC browserController;
    private final BrowseExchangesP browserPanel;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame             the controller's frame
     * @param model             the controller's model
     * @param view              the view
     * @param browserController the browser controller
     * @param browserPanel      the browser panel
     */
    public ExchangeMiniC(App frame, Store model, ExchangeMiniP view, BrowseExchangesC browserController,
                         BrowseExchangesP browserPanel) {
        this.frame = frame;
        this.view = view;
        this.model = model;
        this.browserController = browserController;
        this.browserPanel = browserPanel;

        initializeActions();
    }

    @Override
    public void initializeActions() {
        view.setFocusable(true);
        view.setCursor(new Cursor(Cursor.HAND_CURSOR));

        view.getButton().addActionListener(e -> {
            view.getExchange().changeExchanged(true);
            JOptionPane.showMessageDialog(frame, "Exchange: " + view.getExchange().getId() + " was marked as done",
                    "Manage Exchange", JOptionPane.INFORMATION_MESSAGE);

            browserController.refreshCurrentPage();
            browserController.initializeActionsForMiniPanels();

        });
    }
}