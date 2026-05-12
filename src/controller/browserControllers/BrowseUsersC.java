package controller.browserControllers;

import model.store.Store;
import model.user.Employee;
import view.App;
import view.browserPanels.AbstractBrowserP;

import javax.swing.text.BadLocationException;
import java.util.ArrayList;
import java.util.List;

public class BrowseUsersC extends AbstractBrowserC<Employee> {

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public BrowseUsersC(App frame, AbstractBrowserP<Employee> view, Store model) throws BadLocationException {
        super(frame, view, model);

        List<Employee> users = new ArrayList<>(model.getEmployeeList());
        users.remove(frame.getUser());
        view.setItemList(users);

        view.paintEverything();
    }

    @Override
    public void initializeActionsForMiniPanels() {
        // TODO Auto-generated method stub

    }

    public void setActionsForMiniPanels() {
        // TODO Auto-generated method stub

    }

}