package controller.managerControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import model.user.*;
import view.App;
import view.managerPanels.GestorChangePwd;
import view.managerPanels.ManagerGestionarEmpleados;

public class ManagerGestionarEmpleadosC implements ActionListener{
	private App frame;
	private ManagerGestionarEmpleados gest;
/*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public ManagerGestionarEmpleadosC(ManagerGestionarEmpleados gest, App frame) {
    	this.frame = frame;
    	this.gest = gest;
    }
    public void actionPerformed(ActionEvent e) {
    	if(e.getActionCommand().equals("AÑADIR")) {
    		String userName = gest.getUserName().getText();
    		char[] pwd = gest.getPwd().getPassword();
    		
    		if(userName.isBlank() || pwd.length == 0) return; //si están vacíos, no hacen nada
    		String password = new String(pwd);
    		
    		Employee emp = new Employee(password, userName, false, null);
    		
    		Permission p[] = new Permission[3];;
    		int i = 0;
    		if(gest.getStoreP().isSelected()) {
    			p[i] = Permission.STORE;
    			i++;
    			emp.setSp(new StorePermission());
    		}
    		if(gest.getOrderP().isSelected()) {
    			p[i] = Permission.ORDER;
    			i++;
    			emp.setOp(new OrderPermission(true));
    		}
    		if(gest.getExchangeP().isSelected()) {
    			p[i] = Permission.EXCHANGE;
    			i++;
    			emp.setEp(new ExchangePermission());
    		}
    		
    		emp.setPerm(p);
    		Manager.getInstance().addEmployee(emp);
    		gest.refresh();
    	}
    }
}
