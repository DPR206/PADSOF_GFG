package controller.browserControllers;

import controller.miniControllers.PackDiscMiniC;
import model.product.Pack;
import model.store.Store;
import view.App;
import view.browserPanels.BrowsePacksDiscP;
import view.managerPanels.ManagerDiscountsP;
import view.miniPanels.AbstractMiniP;
import view.miniPanels.PackDiscMiniP;

import javax.swing.text.BadLocationException;

public class BrowsePacksDiscC extends AbstractBrowserC<Pack> {
    private final ManagerDiscountsP parentView;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param view  the controller's view
     * @param model the controller's model
     */
    public BrowsePacksDiscC(App frame, BrowsePacksDiscP view, Store model, ManagerDiscountsP parentView) {
        super(frame, view, model);
        this.parentView = parentView;
        super.initializeActions();
        initializeActionsForMiniPanels();
    }

    @Override
    public void initializeActionsForMiniPanels() {
        try {
            super.getView().setItemList(Store.getInstance().getPacks());
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }

        for (AbstractMiniP miniPanel : super.getView().getMiniPanels()) {
            new PackDiscMiniC(super.getFrame(), super.getModel(), (PackDiscMiniP) miniPanel, this,
                    (BrowsePacksDiscP) super.getView(), parentView.getSelectedPacksList());
        }
    }
}