package controller.clientControllers;

import controller.Controller;
import model.user.RegisteredClient;
import view.clientPanels.RegisteredChangePwd;
import view.clientPanels.RegisteredProfile;

import javax.swing.*;
import java.awt.*;

/**
 * The type Registered profile c.
 * @author Duna P.R.
 * @version 1.0
 */
public class RegisteredProfileC implements Controller {

    private final RegisteredProfile vista;
    private final RegisteredClient user;
    private boolean passwordRevelada = false;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Registered profile c.
     * @param vista the vista
     * @param user  the user
     */
    public RegisteredProfileC(RegisteredProfile vista, RegisteredClient user) {

        this.vista = vista;
        this.user = user;

        initializeActions();
    }

    @Override
    public void initializeActions() {

        vista.setNom(user.getUserName());

        vista.setDni(user.getDni());

        vista.getBtnMostrar().addActionListener(e -> showPassword());

        vista.getBtnCambiar().addActionListener(e -> cambiarPwd());
    }

    /**
     * It allows the client to change their password
     */
    private void cambiarPwd() {

        Frame parentFrame = (Frame) SwingUtilities.getWindowAncestor(vista);

        RegisteredChangePwd pagPwd = new RegisteredChangePwd(parentFrame);

        new RegisteredChangePwdC(pagPwd, user);

        pagPwd.setVisible(true);
        actualizarVista();
    }

    /**
     * It shows or hides the password
     */
    private void showPassword() {
        if (passwordRevelada) {
            vista.actualizarPasswordVista("********");
            vista.getBtnMostrar().setText("👁️");
        } else {
            vista.actualizarPasswordVista(user.getPassword());
            vista.getBtnMostrar().setText("🔒");
        }
        passwordRevelada = !passwordRevelada;
    }

    /**
     * It updates the view
     */
    private void actualizarVista() {
        vista.getNom().setText(user.getUserName());
        vista.revalidate();
        vista.repaint();
    }
}