package controller.browserControllers;

import controller.miniControllers.SecondHandMiniC;
import model.store.Store;
import view.App;
import view.browserPanels.BrowseSecondHandProductsP;
import view.miniPanels.SecondHandMiniP;

import javax.swing.text.BadLocationException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrowseSecondHandProductsC implements ActionListener, BigController {
    private final BrowseSecondHandProductsP view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param model the controller's model
     */
    public BrowseSecondHandProductsC(App frame, Store model, BrowseSecondHandProductsP view) {
        this.frame = frame;
        this.view = view;
        this.model = model;

        updateControllers();
    }

    public void updateControllers() {
        for (SecondHandMiniP miniPanel : view.getProductPanels()) {
            miniPanel.setController(new SecondHandMiniC(frame, model, miniPanel, this, view));
        }
    }

    @Override
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
}