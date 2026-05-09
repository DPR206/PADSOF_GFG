package controller.browserControllers;

import controller.miniControllers.WalletOwnerMiniC;
import model.store.Store;
import view.App;
import view.RegisteredMakeOfferP;
import view.browserPanels.BrowseSecondHandProductsP;
import view.browserPanels.BrowseWalletOwnersP;
import view.miniPanels.MiniPanel;
import view.miniPanels.UserMiniP;

import javax.swing.text.BadLocationException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrowseWalletOwnersC implements ActionListener, BigController {
    private final BrowseWalletOwnersP view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */
    BrowseSecondHandProductsP deleteThis;
    RegisteredMakeOfferP deleteThisToo;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param model the controller's model
     */
    public BrowseWalletOwnersC(App frame, Store model, BrowseWalletOwnersP view, BrowseSecondHandProductsP deleteThis,
                               RegisteredMakeOfferP deleteThisToo) {
        this.frame = frame;
        this.view = view;
        this.model = model;
        this.deleteThis = deleteThis;
        this.deleteThisToo = deleteThisToo;

        updateControllers();
    }

    public void updateControllers() {
        for (MiniPanel miniPanel : view.getMiniPanels()) {
            miniPanel.setController(new WalletOwnerMiniC(frame, model, (UserMiniP) miniPanel));
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