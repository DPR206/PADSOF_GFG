package controller.browserControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.BadLocationException;

import model.store.Store;
import model.user.Employee;
import model.user.RegisteredClient;
import view.App;
import view.browserPanels.BrowseUsersP;
import view.browserPanels.BrowseWalletOwnersP;
import view.browserPanels.BrowserPanel;

public class BrowseUserController extends BrowserController<Employee>{

	    public BrowseUserController(App frame, BrowserPanel<Employee> view, Store model) throws BadLocationException {
	        super(frame, view, model);

	        List<RegisteredClient> users = new ArrayList<>(model.getRegisteredClientList());
	        users.remove(frame.getUser());
	        view.setItemList(users);

	        view.paintEverything();
	    }

		@Override
		public void setActionsForMiniPanels() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void initializeActionsForMiniPanels() {
			// TODO Auto-generated method stub
			
		}

}
