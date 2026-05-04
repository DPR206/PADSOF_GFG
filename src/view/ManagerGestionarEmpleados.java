package view;

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

import model.product.Pack;
import model.store.Store;
import view.banners.BannerManager;
import view.miniPanels.EmployeeMini;
import view.miniPanels.PackMiniEdit;

import model.user.Employee;

public class ManagerGestionarEmpleados extends JPanel{
	private BannerManager banner = new BannerManager();
	private JButton newEmployee = new JButton("Crear nuevo empleado");
	private JCheckBox storeP = new JCheckBox("Trabajar con productos");
	private JCheckBox orderP = new JCheckBox("Trabajar con pedidos");
	private JCheckBox exchangeP = new JCheckBox("Trabajar con intercambios");
	public ManagerGestionarEmpleados(App app) {
		super();
		
		this.setLayout(new BorderLayout());
		this.add(banner, BorderLayout.NORTH);
    	
		JPanel mainThings = new JPanel();
    	mainThings.setLayout(new BoxLayout(mainThings, BoxLayout.Y_AXIS));
    	
    	List<Employee> emps = Store.getInstance().getEmployeeList();
    	
    	JScrollPane scroll = new JScrollPane(mainThings);
    	
    	int index = 1;
    	
    	for(Employee emp: emps) {
    		try {
				mainThings.add(new EmployeeMini(emp, index));
				index++;
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
    	}
    	
    	this.newEmployee.setPreferredSize(new Dimension(120, 30));
    	
    	JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Usuario
        panel.add(new JLabel("Nombre de usuario:"));
        JTextField usuario = new JTextField();
        usuario.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        panel.add(usuario);

        // Espacio
        panel.add(Box.createVerticalStrut(10));

        // Contraseña
        panel.add(new JLabel("Contraseña:"));
        JPasswordField password = new JPasswordField();
        password.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        panel.add(password);

        panel.add(Box.createVerticalStrut(10));

        // Permisos
        panel.add(new JLabel("Permisos (al menos uno)"));

        panel.add(this.storeP);
        panel.add(this.orderP);
        panel.add(this.exchangeP);

        panel.add(Box.createVerticalStrut(20));

        // Botón alineado a la derecha
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton boton = new JButton("AÑADIR");
        panelBoton.add(boton);

        panel.add(panelBoton);

        // Márgenes
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

    	
    	this.add(scroll, BorderLayout.WEST);
    	this.add(panel, BorderLayout.EAST);
    	
	}	
	
	public void setController(ActionListener c) {
		this.newEmployee.addActionListener(c);
	}
	
	public BannerManager getBanner() {
		return this.banner;
	}
}
