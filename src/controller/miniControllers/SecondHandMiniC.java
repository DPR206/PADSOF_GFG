package controller.miniControllers;

import controller.browserControllers.BrowserController;
import model.product.SecondHandProduct;
import model.store.Store;
import view.App;
import view.browserPanels.BrowserPanel;
import view.miniPanels.SecondHandMiniP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class SecondHandMiniC implements ActionListener {
    private final SecondHandMiniP view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */
    private final BrowserController<SecondHandProduct> browserController;
    private final BrowserPanel<SecondHandProduct> browserPanel;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param model the controller's model
     */
    public SecondHandMiniC(App frame, Store model, SecondHandMiniP view,
                           BrowserController<SecondHandProduct> browserController,
                           BrowserPanel<SecondHandProduct> browserPanel) {
        this.frame = frame;
        this.view = view;
        this.model = model;
        this.browserController = browserController;
        this.browserPanel = browserPanel;

        //NOTE: Los mouseListeners y keyListeners los asigna las clases que hereden es esta
    }

    @Override
    public abstract void actionPerformed(ActionEvent e);

    public BrowserController<SecondHandProduct> getBrowserController() {
        return browserController;
    }

    public BrowserPanel<SecondHandProduct> getBrowserPanel() {
        return browserPanel;
    }

    public App getFrame() {
        return frame;
    }

    public Store getModel() {
        return model;
    }

    public SecondHandMiniP getView() {
        return view;
    }
}