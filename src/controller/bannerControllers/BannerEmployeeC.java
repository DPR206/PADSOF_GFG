package controller.bannerControllers;

import controller.Controller;
import controller.employeeControllers.*;
import controller.notifications.NotificacionesC;
import model.user.*;
import view.App;
import view.banners.BannerEmployee;
import view.employeePanels.*;
import view.notifications.NotificacionP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.event.ActionListener;

public class BannerEmployeeC implements Controller {

    private BannerEmployee vista;
    private App frame;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     *
     * @param vista
     * @param frame
     */
    public BannerEmployeeC(BannerEmployee vista, App frame) {
        this.vista = vista;
        this.frame = frame;
        filtrarBotones();
        initializeActions();
    }

    private void filtrarBotones() {
        if (frame.getUser().getType() == UserType.EMPLOYEE) {
            if (((Employee) frame.getUser()).getSp() != null) {
                vista.getTienda().setVisible(true);
            } else {
                vista.getTienda().setVisible(false);
            }

            if (((Employee) frame.getUser()).getOp() != null) {
                vista.getBtnCarrito().setVisible(true);
            } else {
                vista.getBtnCarrito().setVisible(false);
            }

            if (((Employee) frame.getUser()).getEp() != null) {
                vista.getIntercambios().setVisible(true);
            } else {
                vista.getIntercambios().setVisible(false);
            }
        }
    }

    private void abrirWelcome() {

        int respuesta =
                JOptionPane.showConfirmDialog(this.frame, "Are you sure you want to log out?", "Confirm log out",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (respuesta == JOptionPane.YES_OPTION) {
            this.vista.setVisible(false);
            this.frame.getWelcomePanel().setVisible(true);

            this.frame.revalidate();
            this.frame.repaint();
        }
    }

    private void abrirIntercambios() throws BadLocationException {

        EmployeeExchangePermP pagExchange = new EmployeeExchangePermP(frame);
        new EmployeeExchangeC();

        pagExchange.setVisible(true);

    }

    private void abrirPedidos() {

        EmployeeOrder pagOrder = new EmployeeOrder();
        new EmployeeOrderC();

        pagOrder.setVisible(true);

    }

    private void abrirTienda() {

        EmployeeTienda pagTienda = new EmployeeTienda();
        new EmployeeTiendaC();

        pagTienda.setVisible(true);
    }

    private void abrirNots() {

        //NotificacionP pagNots = new NotificacionP(new BannerRegistered());
        NotificacionP pagNots = new NotificacionP();

        new NotificacionesC(pagNots, frame);

        pagNots.setVisible(true);
    }

    private void abrirPerfil() {

        EmployeeProfile profile = new EmployeeProfile();

        new EmployeeProfileC(profile, ((Employee) frame.getUser()));

        profile.setVisible(true);
    }

    private void abrirPaginaPrincipal() {

        frame.getEmployeeMainPanel().setVisible(true);
    }

    public void initializeActions() {
        vista.getBtnGoBack().setEnabled(!frame.getLastShownPanels().isEmpty());
        vista.revalidate();
        vista.repaint();

        for (ActionListener listener : vista.getBtnCarrito().getActionListeners()) {
            vista.getBtnCarrito().removeActionListener(listener);
        }
        vista.getBtnCarrito().addActionListener(e -> {
            abrirPedidos();
        });

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

        for (ActionListener listener : vista.getBtnNots().getActionListeners()) {
            vista.getBtnNots().removeActionListener(listener);
        }
        vista.getBtnNots().addActionListener(e -> {
            abrirNots();
        });

        for (ActionListener listener : vista.getTienda().getActionListeners()) {
            vista.getTienda().removeActionListener(listener);
        }
        vista.getTienda().addActionListener(e -> {
            abrirTienda();
        });

        for (ActionListener listener : vista.getBtnExit().getActionListeners()) {
            vista.getBtnExit().removeActionListener(listener);
        }
        vista.getIntercambios().addActionListener(e -> {
            try {
                abrirIntercambios();
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
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