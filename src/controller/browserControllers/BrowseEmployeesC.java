package controller.browserControllers;

import model.store.Store;
import model.user.User;
import view.App;
import view.browserPanels.BrowseUsersP;

public class BrowseEmployeesC extends BrowserController<User> { //DUE
    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param view  the controller's view
     * @param model the controller's model
     */
    BrowseEmployeesC(App frame, BrowseUsersP view, Store model) {
        super(frame, view, model);
        initializeActionsForMiniPanels();
    }

    @Override
    public void initializeActionsForMiniPanels() {

    }
}