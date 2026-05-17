package controller.bannerControllers;

import controller.Controller;
import controller.managerControllers.ManagerProfileC;
import model.store.Store;
import model.user.Manager;
import model.user.UnregisteredClient;
import view.App;
import view.banners.BannerManager;
import view.managerPanels.ManagerProfile;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.event.ActionListener;

/**
 * It implements the manager's banner
 * @author Duna P.R.
 * @version 1.0
 */
public class BannerManagerC implements Controller {

    private final BannerManager vista;
    private final App frame;
    private final Store store = Store.getInstance();

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Banner manager c.
     * @param vista the vista
     * @param frame the frame
     */
    public BannerManagerC(BannerManager vista, App frame) {
        this.vista = vista;
        this.frame = frame;
        initializeActions();
    }

    /**
     * It opens the welcome page
     */
    private void abrirWelcome() throws BadLocationException {

        int respuesta =
                JOptionPane.showConfirmDialog(this.frame, "Are you sure you want to log out?", "Confirm log out",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (respuesta == JOptionPane.YES_OPTION) {
            this.frame.updateView("WELCOME", "BANNER_UNREGISTERED");

            this.frame.revalidate();
            this.frame.repaint();
        }
    }

    /**
     * It opens the profile page
     */
    private void abrirPerfil() throws BadLocationException {

        Manager currentManager = store.getManager();

        // Debug: Verifica si llega nulo a la consola
        if (currentManager == null) {
            System.out.println("ERROR: El manager en Store es NULL");
            JOptionPane.showMessageDialog(frame, "Error: No user session found.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Detiene la ejecución para evitar el crash
        }

        ManagerProfile perfil = new ManagerProfile();

        new ManagerProfileC(perfil, Store.getInstance().getManager(), frame);

        frame.addCard(perfil, "PERFIL_MANAGER");
        frame.changeVisibleCard("PERFIL_MANAGER");
    }

    /**
     * It opens the main page
     */
    private void abrirPaginaPrincipal() throws BadLocationException {

        frame.changeVisibleCard("MANAGER_MAIN");
    }

    @Override
    public void initializeActions() {
        vista.getBtnGoBack().setEnabled(!frame.getLastShownPanels().isEmpty());
        vista.revalidate();
        vista.repaint();

        for (ActionListener listener : vista.getHome().getActionListeners()) {
            vista.getHome().removeActionListener(listener);
        }
        vista.getHome().addActionListener(e -> {
            try {
                abrirPaginaPrincipal();
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });

        for (ActionListener listener : vista.getBtnPerfil().getActionListeners()) {
            vista.getBtnPerfil().removeActionListener(listener);
        }
        vista.getBtnPerfil().addActionListener(e -> {
            try {
                abrirPerfil();
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });

        for (ActionListener listener : vista.getBtnExit().getActionListeners()) {
            vista.getBtnExit().removeActionListener(listener);
        }
        vista.getBtnExit().addActionListener(e -> {
            this.frame.changeCurrentUser(new UnregisteredClient(true));
            try {
                abrirWelcome();
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });

        for (ActionListener listener : vista.getBtnGoBack().getActionListeners()) {
            vista.getBtnGoBack().removeActionListener(listener);
        }
        vista.getBtnGoBack().addActionListener(e -> {
            try {
                frame.goBack();
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}