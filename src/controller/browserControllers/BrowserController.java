package controller.browserControllers;

import model.store.Store;
import view.App;
import view.browserPanels.BrowserPanel;

import javax.swing.text.BadLocationException;

/**
 * It defines a controller for a BrowserPanel
 */
public abstract class BrowserController<G> {
    private final BrowserPanel<G> view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param view  the controller's view
     * @param model the controller's model
     */
    BrowserController(App frame, BrowserPanel<G> view, Store model) {
        this.frame = frame;
        this.view = view;
        this.model = model;

        setActions();
    }

    public App getFrame() {
        return frame;
    }

    public Store getModel() {
        return model;
    }

    public BrowserPanel<G> getView() {
        return view;
    }

    public void setActions() {
        setActionsForMiniPanels();

        view.getFirstPage().addActionListener(e -> {
            try {
                view.setCurrentPageNum(1);
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
            setActionsForMiniPanels();
        });

        view.getPreviousPage().addActionListener(e -> {
            try {
                view.setCurrentPageNum(view.getCurrentPageNum() - 1);
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
            setActionsForMiniPanels();
        });

        view.getNextPage().addActionListener(e -> {
            try {
                view.setCurrentPageNum(view.getCurrentPageNum() + 1);
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
            setActionsForMiniPanels();
        });

        view.getLastPage().addActionListener(e -> {
            try {
                view.setCurrentPageNum(view.getMaxPageNum());
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
            setActionsForMiniPanels();
        });
    }

    public abstract void setActionsForMiniPanels();
}