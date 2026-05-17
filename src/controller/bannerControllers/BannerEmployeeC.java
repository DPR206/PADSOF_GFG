package controller.bannerControllers;

import controller.Controller;
import controller.employeeControllers.*;
import controller.notifications.NotificacionesC;
import model.store.Store;
import model.user.*;
import view.App;
import view.banners.BannerEmployee;
import view.employeePanels.*;
import view.notifications.NotificacionP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.event.ActionListener;

/**
 * It implements the employee's banner
 * @author Duna P.R.
 * @version 1.0
 */
public class BannerEmployeeC implements Controller {

    private final BannerEmployee vista;
    private final App frame;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Banner employee c.
     * @param vista the vista
     * @param frame the frame
     */
    public BannerEmployeeC(BannerEmployee vista, App frame) {
        this.vista = vista;
        this.frame = frame;
        filtrarBotones();
        initializeActions();
    }

    /**
     * It filters the buttons to be shown based on the employee's permissions
     */
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
     * It opens the exchanges page
     * @throws BadLocationException the bad location exception
     */
    private void abrirIntercambios() throws BadLocationException {

        EmployeeExchangePermP pagExchange = new EmployeeExchangePermP();
        new EmployeeExchangePermC(frame, Store.getInstance(), pagExchange);

        frame.addCard(pagExchange, "EMPLOYEE_EXCHANGE");
        frame.changeVisibleCard("EMPLOYEE_EXCHANGE");

    }

    /**
     * It opens the orders page
     */
    private void abrirPedidos() throws BadLocationException {

        EmployeeOrder pagOrder = new EmployeeOrder();
        new EmployeOrderC(frame, pagOrder);

        frame.addCard(pagOrder, "EMPLOYEE_ORDERS");
        frame.changeVisibleCard("EMPLOYEE_ORDERS");

    }

    /**
     * It opens the store page
     */
    private void abrirTienda() throws BadLocationException {

        EmployeeTienda pagTienda = new EmployeeTienda();
        new EmployeeTiendaC();

        frame.addCard(pagTienda, "EMPLOYEE_STORE");
        frame.changeVisibleCard("EMPLOYEE_STORE");
    }

    /**
     * It opens the notifications page
     */
    private void abrirNots() throws BadLocationException {

        NotificacionP pagNots = new NotificacionP();

        new NotificacionesC(pagNots, frame);

        frame.addCard(pagNots, "EMPLOYEE_NOTIFICATIONS");
        frame.changeVisibleCard("EMPLOYEE_NOTIFICATIONS");
    }

    /**
     * It opens the profile page
     */
    private void abrirPerfil() throws BadLocationException {

        EmployeeProfile profile = new EmployeeProfile();

        new EmployeeProfileC(profile, frame);

        frame.addCard(profile, "EMPLOYEE_PROFILE");
        frame.changeVisibleCard("EMPLOYEE_PROFILE");
    }

    /**
     * It opens the main page
     */
    private void abrirPaginaPrincipal() throws BadLocationException {

        frame.changeVisibleCard("EMPLOYEE_MAIN");
    }

    @Override
    public void initializeActions() {
        vista.getBtnGoBack().setEnabled(!frame.getLastShownPanels().isEmpty());
        vista.revalidate();
        vista.repaint();

        for (ActionListener listener : vista.getBtnCarrito().getActionListeners()) {
            vista.getBtnCarrito().removeActionListener(listener);
        }
        vista.getBtnCarrito().addActionListener(e -> {
            try {
                abrirPedidos();
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });

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

        for (ActionListener listener : vista.getBtnNots().getActionListeners()) {
            vista.getBtnNots().removeActionListener(listener);
        }
        vista.getBtnNots().addActionListener(e -> {
            try {
                abrirNots();
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });

        for (ActionListener listener : vista.getTienda().getActionListeners()) {
            vista.getTienda().removeActionListener(listener);
        }
        vista.getTienda().addActionListener(e -> {
            try {
                abrirTienda();
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
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