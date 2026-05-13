package controller.browserControllers;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.BadLocationException;

import controller.miniControllers.EmployeeMiniC;
import controller.miniControllers.WalletOwnerMiniC;
import model.store.Store;
import model.user.Employee;
import model.user.RegisteredClient;
import view.App;
import view.browserPanels.BrowseEmployeesP;
import view.miniPanels.AbstractMiniP;
import view.miniPanels.UserMiniP;

public class BrowseEmployeesC extends AbstractBrowserC<Employee> { //DUE
    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param view  the controller's view
     * @param model the controller's model
     */
    public BrowseEmployeesC(App frame, BrowseEmployeesP view, Store model) {
        super(frame, view, model);
        initializeActionsForMiniPanels();
    }

    @Override
    public void initializeActionsForMiniPanels() {
    	List<Employee> users = new ArrayList<>(Store.getInstance().getEmployeeList());
        
        try {
            super.getView().setItemList(users);
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }

        for (AbstractMiniP miniPanel : super.getView().getMiniPanels()) {
            new EmployeeMiniC(this.getFrame(), (UserMiniP)miniPanel);
        }
    }
    
}