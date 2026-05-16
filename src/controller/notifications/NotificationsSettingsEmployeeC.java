package controller.notifications;

import model.user.Employee;
import view.notifications.NotificationsSettingsEmployeeP;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * The type Notifications settings employee c.
 * @author Duna P.R.
 * @version 1.0
 */
public class NotificationsSettingsEmployeeC {

    private final NotificationsSettingsEmployeeP vista;
    private final Employee modelo;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Notifications settings employee c.
     * @param vista  the vista
     * @param modelo the modelo
     */
    public NotificationsSettingsEmployeeC(NotificationsSettingsEmployeeP vista, Employee modelo) {
        this.vista = vista;
        this.modelo = modelo;
        cargarPermisosActuales();
        bloquearAjustesParaEmpleado(vista);
    }

    /**
     * It loads the current permissions
     */
    private void cargarPermisosActuales() {
        vista.getExchanges().setSelected(modelo.getEp() != null);
        vista.getValuation().setSelected(modelo.getEp() != null);
        vista.getOrders().setSelected(modelo.getOp() != null);
    }

    /**
     * It forbids an employee from accessing the settings
     * @param panelAjustes the settings panel
     */
    private void bloquearAjustesParaEmpleado(NotificationsSettingsEmployeeP panelAjustes) {
        JCheckBox[] todosLosChecks =
                {panelAjustes.getValuation(), panelAjustes.getExchanges(), panelAjustes.getOrders()};

        for (JCheckBox check : todosLosChecks) {
            //Quitamos cualquier listener previo
            for (ActionListener al : check.getActionListeners()) {
                check.removeActionListener(al);
            }

            check.addActionListener(e -> {
                // Revertimos el estado visual inmediatamente
                check.setSelected(!check.isSelected());

                // Mostramos el mensaje de error
                JOptionPane.showMessageDialog(vista,
                        "Acción no autorizada: Los empleados no pueden modificar los ajustes de notificación.",
                        "Permiso Denegado", JOptionPane.ERROR_MESSAGE);
            });

            check.setEnabled(false);
        }

    }

}