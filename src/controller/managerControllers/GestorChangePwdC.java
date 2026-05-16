package controller.managerControllers;

import controller.Controller;
import model.user.Manager;
import model.utilities.exceptions.PasswordNotValid;
import view.managerPanels.GestorChangePwd;

/**
 * The type Gestor change pwd c.
 * @author Sofía C.L.
 * @version 1.0
 */
public class GestorChangePwdC implements Controller {
    private final Manager user;
    private final GestorChangePwd pagPwd;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Gestor change pwd c.
     * @param pagPwd the pag pwd
     * @param user   the user
     */
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
                throw new RuntimeException(e1);
            }
        });
    }
}