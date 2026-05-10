package controller.managerControllers;

import controller.Controller;
import model.user.Manager;
import model.utilities.exceptions.PasswordNotValid;
import view.managerPanels.GestorChangePwd;

public class GestorChangePwdC implements Controller {
    private Manager user;
    private GestorChangePwd pagPwd;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public GestorChangePwdC(GestorChangePwd pagPwd, Manager user) {
        this.user = user;
        this.pagPwd = pagPwd;

        initializeActions();
    }

    @Override
    public void initializeActions() {
        pagPwd.getBoton().addActionListener(e -> {
            String newName = pagPwd.getUserName().getText();
            this.user.setUserName(newName);
            char[] pwd = pagPwd.getPwd().getPassword();
            String password = new String(pwd);
            try {
                this.user.changePassword(password);
            } catch (PasswordNotValid e1) {
                e1.printStackTrace();
            }
        });
    }
}