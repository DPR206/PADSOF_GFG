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
import view.browserPanels.BrowseWalletOwnersP;

public class BrowseUserController extends BrowserController<Employee>{

	    public BrowseUserController(App frame, BrowseWalletOwnersP view, Store model) throws BadLocationException {
	        super(frame, view, model);

	        List<RegisteredClient> users = new ArrayList<>(model.getRegisteredClientList());
	        users.remove(frame.getUser());
	        view.setItemList(users);

	        view.paintEverything();
	    }

}
