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
import java.awt.event.ActionListener;

public class BannerManagerC implements Controller {

    private BannerManager vista;
    private App frame;
    private Store store = Store.getInstance();

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * @param vista
     */
    public BannerManagerC(BannerManager vista, App frame) {
        this.vista = vista;
        this.frame = frame;
        initializeActions();
    }

    private void abrirWelcome() {

        int respuesta =
                JOptionPane.showConfirmDialog(this.frame, "Are you sure you want to log out?", "Confirm log out",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (respuesta == JOptionPane.YES_OPTION) {
            this.frame.updateView("WELCOME", "BANNER_UNREGISTERED");

            this.frame.revalidate();
            this.frame.repaint();
        }
    }

    private void abrirPerfil() {

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

    private void abrirPaginaPrincipal() {

        frame.changeVisibleCard("MANAGER_MAIN");
    }

    public void initializeActions() {
        vista.getBtnGoBack().setEnabled(!frame.getLastShownPanels().isEmpty());
        vista.revalidate();
        vista.repaint();

        for (ActionListener listener : vista.getHome().getActionListeners()) {
            vista.getHome().removeActionListener(listener);
        }
        vista.getHome().addActionListener(e -> {
            abrirPaginaPrincipal();
        });

        for (ActionListener listener : vista.getBtnPerfil().getActionListeners()) {
            vista.getBtnPerfil().removeActionListener(listener);
        }
        vista.getBtnPerfil().addActionListener(e -> {
            abrirPerfil();
        });

        for (ActionListener listener : vista.getBtnExit().getActionListeners()) {
            vista.getBtnExit().removeActionListener(listener);
        }
        vista.getBtnExit().addActionListener(e -> {
            this.frame.changeCurrentUser(new UnregisteredClient(true));
            abrirWelcome();
        });

        for (ActionListener listener : vista.getBtnGoBack().getActionListeners()) {
            vista.getBtnGoBack().removeActionListener(listener);
        }
        vista.getBtnGoBack().addActionListener(e -> {
            goBack();
        });
    }

    private void goBack() {
        frame.goBack();
    }

}