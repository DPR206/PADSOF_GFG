package controller.browserControllers;

import model.store.Store;
import model.user.Employee;
import view.App;
import view.browserPanels.BrowseEmployeesP;

public class BrowseEmployeesC extends AbstractBrowserC<Employee> { //DUE
    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param view  the controller's view
     * @param model the controller's model
     */
    BrowseEmployeesC(App frame, BrowseEmployeesP view, Store model) {
        super(frame, view, model);
        initializeActionsForMiniPanels();
    }

    @Override
    public void initializeActionsForMiniPanels() {

    }
}