package controller.browserControllers;

import controller.Controller;
import model.store.Store;
import view.App;
import view.browserPanels.AbstractClusterBrowserP;

import javax.swing.text.BadLocationException;

public abstract class AbstractClusterBrowserC<G> implements Controller {
    private final AbstractClusterBrowserP<G> view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param view  the controller's view
     * @param model the controller's model
     */
    AbstractClusterBrowserC(App frame, AbstractClusterBrowserP<G> view, Store model) {
        this.frame = frame;
        this.view = view;
        this.model = model;

        initializeActions();
    }

    @Override
    public void initializeActions() {
        initializeActionsForMiniPanels();

        view.getFirstPage().addActionListener(e -> {
            try {
                view.setCurrentPageNum(1);
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
            initializeActionsForMiniPanels();
        });

        view.getPreviousPage().addActionListener(e -> {
            try {
                view.setCurrentPageNum(view.getCurrentPageNum() - 1);
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
            initializeActionsForMiniPanels();
        });

        view.getNextPage().addActionListener(e -> {
            try {
                view.setCurrentPageNum(view.getCurrentPageNum() + 1);
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
            initializeActionsForMiniPanels();
        });

        view.getLastPage().addActionListener(e -> {
            try {
                view.setCurrentPageNum(view.getMaxPageNum());
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
            initializeActionsForMiniPanels();
        });
    }

    public abstract void initializeActionsForMiniPanels();

    public App getFrame() {
        return frame;
    }

    public Store getModel() {
        return model;
    }

    public AbstractClusterBrowserP<G> getView() {
        return view;
    }
}