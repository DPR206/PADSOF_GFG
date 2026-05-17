package view.managerPanels;

import model.store.Store;
import model.user.Employee;
import view.App;
import view.browserPanels.BrowseEmployeesP;
import view.miniPanels.EmployeeMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * The type Manager gestionar empleados.
 * @author Sofia C.L.
 * @version 1.0
 */
public class ManagerGestionarEmpleados extends JPanel {
    private final JCheckBox storeP = new JCheckBox("Trabajar con productos");
    private final JCheckBox orderP = new JCheckBox("Trabajar con pedidos");
    private final JCheckBox exchangeP = new JCheckBox("Trabajar con intercambios");
    private final JTextField userName = new JTextField();
    private final JPasswordField pwd = new JPasswordField();
    private final JPanel mainThings = new JPanel();
    private final JButton confirmar = new JButton("CONFIRMAR");
    private BrowseEmployeesP browse = null;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Manager gestionar empleados.
     * @param app the app
     */
    public ManagerGestionarEmpleados(App app) {
        super();

        this.setLayout(new BorderLayout());

        mainThings.setLayout(new BoxLayout(mainThings, BoxLayout.Y_AXIS));
        mainThings.setOpaque(true);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Usuario
        panel.add(new JLabel("Nombre de usuario:"));
        this.userName.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        panel.add(this.userName);

        // Espacio
        panel.add(Box.createVerticalStrut(10));

        // Contraseña
        panel.add(new JLabel("Contraseña:"));
        this.pwd.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        panel.add(this.pwd);

        panel.add(Box.createVerticalStrut(10));

        // Permisos
        panel.add(new JLabel("Permisos (al menos uno)"));

        panel.add(this.storeP);
        panel.add(this.orderP);
        panel.add(this.exchangeP);
        panel.add(this.confirmar);
        List<Employee> employees = Store.getInstance().getEmployeeList();

        try {
            browse = new BrowseEmployeesP("GESTIONAR");
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }

        this.add(panel, BorderLayout.EAST);
        this.add(browse, BorderLayout.CENTER);
    }

    /**
     * Refresh.
     */
    public void refresh() {
        mainThings.removeAll(); // Limpia la lista actual

        List<Employee> emps = Store.getInstance().getEmployeeList();
        int index = 1;

        for (Employee emp : emps) {
            mainThings.add(new EmployeeMiniP(emp, index));
            index++;
        }

        if (emps.isEmpty()) {
            JLabel emptyLabel = new JLabel("No hay empleados para mostrar.");
            emptyLabel.setAlignmentX(LEFT_ALIGNMENT);
            mainThings.add(emptyLabel);
        }

        mainThings.revalidate();
        mainThings.repaint();
    }

    /**
     * It gets the browse
     * @return the browse
     */
    public BrowseEmployeesP getBrowse() {
        return this.browse;
    }

    /**
     * It gets the confirmar
     * @return the confirmar
     */
    public JButton getConfirmar() {
        return confirmar;
    }

    /**
     * It gets the exchange p
     * @return the exchange p
     */
    public JCheckBox getExchangeP() {
        return exchangeP;
    }

    /**
     * It gets the main things
     * @return the main things
     */
    public JPanel getMainThings() {
        return mainThings;
    }

    /**
     * It gets the order p
     * @return the order p
     */
    public JCheckBox getOrderP() {
        return orderP;
    }

    /**
     * It gets the pwd
     * @return the pwd
     */
    public JPasswordField getPwd() {
        return pwd;
    }

    /**
     * It gets the store p
     * @return the store p
     */
    public JCheckBox getStoreP() {
        return storeP;
    }

    /**
     * It gets the user name
     * @return the user name
     */
    public JTextField getUserName() {
        return userName;
    }

    /**
     * It sets the controller
     * @param c the c
     */
    public void setController(ActionListener c) {
        this.confirmar.addActionListener(c);
    }
}