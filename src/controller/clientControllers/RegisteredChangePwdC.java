package controller.clientControllers;

import controller.Controller;
import model.store.Store;
import model.user.RegisteredClient;
import model.utilities.exceptions.PasswordNotValid;
import view.clientPanels.RegisteredChangePwd;

import javax.swing.*;

/**
 * The type Registered change pwd c.
 * @author Duna P.R.
 * @version 1.0
 */
public class RegisteredChangePwdC implements Controller {

    private final RegisteredChangePwd vista;
    private final RegisteredClient user;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Registered change pwd c.
     * @param pagPwd the pag pwd
     * @param user   the user
     */
    public RegisteredChangePwdC(RegisteredChangePwd pagPwd, RegisteredClient user) {

        this.vista = pagPwd;
        this.user = user;

        initializeActions();
    }

    @Override
    public void initializeActions() {

        vista.getBtnCambiar().addActionListener(e -> cambiarPwd());

    }

    /**
     * It allows the client to change their password
     */
    private void cambiarPwd() {
        String nombreViejo = user.getUserName();
        String pass1 = vista.getPwd1();
        String pass2 = vista.getPwd2();
        String username = vista.getNom();

        if (pass1.isEmpty() || pass2.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Please fill in all fields", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!pass1.equals(pass2)) {
            JOptionPane.showMessageDialog(vista, "Passwords did not match", "Warning :(", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                user.changePassword(pass1);
                user.setUserName(username);

                Store.getInstance().getUsers().remove(nombreViejo);

                // Luego insertamos el usuario con el nuevo nombre como llave
                Store.getInstance().getUsers().put(username, user);

                JOptionPane.showMessageDialog(vista, "Password changed successfully!", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                vista.dispose();
            } catch (PasswordNotValid e) {
                JOptionPane.showMessageDialog(null, e.toString(), "Warning :(", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}