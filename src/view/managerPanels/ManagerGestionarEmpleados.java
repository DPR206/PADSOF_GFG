package view.managerPanels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

import model.store.Store;
import view.App;
import view.browserPanels.BrowseUsersP;
import view.miniPanels.EmployeeMini;
import model.user.Employee;

public class ManagerGestionarEmpleados extends JPanel {
    private JCheckBox storeP = new JCheckBox("Trabajar con productos");
    private JCheckBox orderP = new JCheckBox("Trabajar con pedidos");
    private JCheckBox exchangeP = new JCheckBox("Trabajar con intercambios");

    private JTextField userName = new JTextField();
    private JPasswordField pwd = new JPasswordField();
    private JPanel mainThings = new JPanel();
    private JButton confirmar = new JButton("CONFIRMAR");

    public ManagerGestionarEmpleados(App app) {
        super();

        this.setLayout(new BorderLayout());

        mainThings.setLayout(new BoxLayout(mainThings, BoxLayout.Y_AXIS));
        mainThings.setOpaque(true);

        JScrollPane scroll = new JScrollPane(mainThings);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new Dimension(450, 0));

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
        BrowseUsersP browse = null;
        try {
			browse = new BrowseUsersP(employees, "GESTIONAR");
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        this.add(panel, BorderLayout.EAST);
        this.add(browse, BorderLayout.CENTER);
    }

    public void setController(ActionListener c) {
        this.confirmar.addActionListener(c);
    }

    public JCheckBox getStoreP() {
        return storeP;
    }

    public JCheckBox getOrderP() {
        return orderP;
    }

    public JCheckBox getExchangeP() {
        return exchangeP;
    }

    public JTextField getUserName() {
        return userName;
    }

    public JPasswordField getPwd() {
        return pwd;
    }

    public void refresh() {
        mainThings.removeAll(); // Limpia la lista actual

        List<Employee> emps = Store.getInstance().getEmployeeList();
        int index = 1;

        for (Employee emp : emps) {
            mainThings.add(new EmployeeMini(emp, index));
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
}
