package controller.browserControllers;

import controller.miniControllers.EmployeeMiniC;
import model.store.Store;
import model.user.Employee;
import view.App;
import view.browserPanels.BrowseEmployeesP;
import view.miniPanels.AbstractMiniP;
import view.miniPanels.UserMiniP;

import javax.swing.text.BadLocationException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Browse employees c.
 * @author Sofía C.L. & Ana O.R.
 * @version 1.0
 */
public class BrowseEmployeesC extends AbstractClusterBrowserC<Employee> {
    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param view  the controller's view
     * @param model the controller's model
     */
    public BrowseEmployeesC(App frame, BrowseEmployeesP view, Store model) {
        super(frame, view, model);
        super.initializeActions();
        initializeActionsForMiniPanels();
    }

    @Override
    public void initializeActionsForMiniPanels() {
        List<Employee> users = new ArrayList<>(Store.getInstance().getEmployeeList());

        try {
            super.getView().setItemList(users);
        } catch (BadLocationException ex) {
            throw new RuntimeException(ex);
        }

        for (AbstractMiniP miniPanel : super.getView().getMiniPanels()) {
            new EmployeeMiniC(this.getFrame(), (UserMiniP) miniPanel);
        }
    }

}