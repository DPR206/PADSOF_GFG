package controller.browserControllers;

import controller.Controller;
import model.store.Store;
import view.App;
import view.browserPanels.AbstractMixedBrowserP;

import javax.swing.text.BadLocationException;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * It defines a controller for a mixed browser
 * @param <G> the type parameter
 * @param <U> the type parameter
 * @author Ana O.R.
 * @version 1.0
 */
public abstract class AbstractMixedBrowserC<G, U> implements Controller {
    private final AbstractMixedBrowserP<G, U> view;
    private final App frame;
    private final Store model;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param view  the controller's view
     * @param model the controller's model
     */
    AbstractMixedBrowserC(App frame, AbstractMixedBrowserP<G, U> view, Store model) {
        this.frame = frame;
        this.view = view;
        this.model = model;
        view.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                refreshData();
                initializeActionsForMiniPanels();
            }
        });
    }

    /**
     * Refresh data.
     */
    public abstract void refreshData();

    /**
     * Refresh current page.
     */
    public abstract void refreshCurrentPage();

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
    public AbstractMixedBrowserP<G, U> getView() {
        return view;
    }
}