package controller.managerControllers;

import controller.Controller;
import controller.browserControllers.BrowseEmployeesC;
import model.store.Store;
import model.user.Employee;
import model.user.Permission;
import view.App;
import view.managerPanels.ManagerGestionarEmpleados;

import javax.swing.*;
import javax.swing.text.BadLocationException;

import java.util.ArrayList;
import java.util.List;

public class ManagerGestionarEmpleadosC implements Controller {
    private App frame;
    private ManagerGestionarEmpleados gest;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public ManagerGestionarEmpleadosC(ManagerGestionarEmpleados gest, App frame) {
        this.frame = frame;
        this.gest = gest;

        initializeActions();
    }

    @Override
    public void initializeActions() {
    	
    	try {
    		gest.getBrowse().setItemList(Store.getInstance().getEmployeeList());
    		gest.getBrowse().paintEverything();
    		new BrowseEmployeesC(frame, gest.getBrowse(), Store.getInstance());
    	} catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    	
        System.out.println(Store.getInstance().getEmployeeList());
        gest.getConfirmar().addActionListener(e -> {
            String userName = gest.getUserName().getText();
            char[] pwd = gest.getPwd().getPassword();
            int count = 0;
            if (userName.isBlank() || pwd.length == 0) {
                return; //si están vacíos, no hacen nada
            }
            String password = new String(pwd);

            List<Permission> perms = new ArrayList<>();

            if (gest.getStoreP().isSelected()) {
                perms.add(Permission.STORE);
                count++;
            }
            if (gest.getOrderP().isSelected()) {
                perms.add(Permission.ORDER);
                count++;
            }
            if (gest.getExchangeP().isSelected()) {
                perms.add(Permission.EXCHANGE);
                count++;
            }
            
            if(count == 0) {
            	return;
            }
            
            Permission[] p = perms.toArray(new Permission[0]);

            Employee emp = new Employee(password, userName, false, p);
            System.out.println(emp.getId());

            Store.getInstance().addEmployee(emp);
           
        
            
            try {
            	this.gest.getBrowse().setItemList(Store.getInstance().getEmployeeList());
				this.gest.getBrowse().paintEverything();
			} catch (BadLocationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });
    }
}