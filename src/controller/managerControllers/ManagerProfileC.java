package controller.managerControllers;

import controller.Controller;
import model.user.Manager;
import view.App;
import view.managerPanels.GestorChangePwd;
import view.managerPanels.ManagerProfile;

import javax.swing.text.BadLocationException;

/**
 * The type Manager profile c.
 * @author Duna P.R.
 * @version 1.0
 */
public class ManagerProfileC implements Controller {

    private final ManagerProfile vista;
    private final Manager user;
    private final App frame;
    private boolean passwordRevelada = false;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Manager profile c.
     * @param vista the vista
     * @param user  the user
     * @param frame the frame
     */
    public ManagerProfileC(ManagerProfile vista, Manager user, App frame) {
        this.vista = vista;
        this.user = user;
        this.frame = frame;
        initializeActions();
    }

    @Override
    public void initializeActions() {

        vista.setNom(user.getUserName());

        vista.getBtnMostrar().addActionListener(e -> showPassword());

        vista.getBtnCambiar().addActionListener(e -> {
            try {
                cambiarPwd();
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void cambiarPwd() throws BadLocationException {

        GestorChangePwd pagPwd = new GestorChangePwd();

        new GestorChangePwdC(pagPwd, user);

        frame.addCard(pagPwd, "PROFILE_MANAGER");
        frame.changeVisibleCard("PROFILE_MANAGER");
    }

    private void showPassword() {
        if (passwordRevelada) {
            vista.setPwd("********");
            vista.getBtnMostrar().setText("👁️");
        } else {
            vista.setPwd(user.getPassword());
            vista.getBtnMostrar().setText("🔒");
        }
        passwordRevelada = !passwordRevelada;
    }
}