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

/**
 * The type Browse packs disc c.
 * @author Ana O.R.
 * @version 1.0
 */
public class BrowsePacksDiscC extends AbstractBrowserC<Pack> {
    private final ManagerDiscountsP parentView;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame      the controller's frame
     * @param view       the controller's view
     * @param model      the controller's model
     * @param parentView the parent view
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
            throw new RuntimeException(ex);
        }

        for (AbstractMiniP miniPanel : super.getView().getMiniPanels()) {
            new PackDiscMiniC(super.getFrame(), (PackDiscMiniP) miniPanel, this, (BrowsePacksDiscP) super.getView(),
                    parentView.getSelectedPacksList());
        }
    }
}