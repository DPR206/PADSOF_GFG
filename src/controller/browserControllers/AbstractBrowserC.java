package controller.browserControllers;

import controller.Controller;
import model.store.Store;
import view.App;
import view.browserPanels.AbstractBrowserP;

import javax.swing.text.BadLocationException;
import java.awt.event.HierarchyEvent;

/**
 * It defines a controller for a browser
 * @param <G> the type parameter
 * @author Ana O.R.
 * @version 1.0
 */
public abstract class AbstractBrowserC<G> implements Controller {
    private final AbstractBrowserP<G> view;
    private final App frame;
    private final Store model;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param view  the controller's view
     * @param model the controller's model
     */
    AbstractBrowserC(App frame, AbstractBrowserP<G> view, Store model) {
        this.frame = frame;
        this.view = view;
        this.model = model;

        view.addHierarchyListener(e -> {
            if ((e.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0) {
                if (view.isShowing()) {
                    refreshData();
                    initializeActionsForMiniPanels();
                }
            }
        });
    }

    /**
     * Refresh data.
     */
    public abstract void refreshData();

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

    /**
     * Initialize actions for mini panels.
     */
    public abstract void initializeActionsForMiniPanels();

    /**
     * Refresh current page.
     */
    public abstract void refreshCurrentPage();

    /**
     * It gets the frame
     * @return the frame
     */
    public App getFrame() {
        return frame;
    }

    /**
     * It gets the model
     * @return the model
     */
    public Store getModel() {
        return model;
    }

    /**
     * It gets the view
     * @return the view
     */
    public AbstractBrowserP<G> getView() {
        return view;
    }
}