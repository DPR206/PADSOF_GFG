package controller.managerControllers;

import model.user.Manager;
import model.utilities.exceptions.PasswordNotValid;
import view.managerPanels.GestorChangePwd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestorChangePwdC implements ActionListener {
	private Manager user;
	private GestorChangePwd pagPwd;
/*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public GestorChangePwdC(GestorChangePwd pagPwd, Manager user) {
    	this.user = user;
    	this.pagPwd = pagPwd;
    }

    @SuppressWarnings("deprecation")
	@Override
    public void actionPerformed(ActionEvent e) {
    	if(e.getActionCommand().equals("CAMBIAR")) {
    		String newName = pagPwd.getUserName().getText();
    		this.user.setUserName(newName);
    		char[] pwd = pagPwd.getPwd().getPassword();
    		String password = new String(pwd);
    		try {
				this.user.changePassword(password);
			} catch (PasswordNotValid e1) {
				e1.printStackTrace();
			}
    	}
    }
}