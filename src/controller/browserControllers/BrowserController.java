package controller.browserControllers;

import model.store.Store;
import view.App;
import view.browserPanels.BrowserPanel;

import javax.swing.text.BadLocationException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * It defines a controller for a BrowserPanel
 */
public abstract class BrowserController<G> implements ActionListener {
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

        updateControllers();
    }

    /**
     * It updates this controller's view's controllers (usually when changing the view's current page)
     */
    public abstract void updateControllers();

    public void actionPerformed(ActionEvent e) {
        try {
            switch (e.getActionCommand()) {
                case "<< First Page" -> view.setCurrentPageNum(1);
                case "< Previous Page" -> view.setCurrentPageNum(view.getCurrentPageNum() - 1);
                case "Next Page >" -> view.setCurrentPageNum(view.getCurrentPageNum() + 1);
                case "Last Page >>" -> view.setCurrentPageNum(view.getMaxPageNum());
            }
            updateControllers();
        } catch (BadLocationException ex) {
            throw new RuntimeException(ex);
        }
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
}