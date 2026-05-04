package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import view.banners.BannerManager;

public class ManagerGestionEmplIndividual extends JPanel{
	
	private JCheckBox storePerm = new JCheckBox("Trabajar con productos");
	private JCheckBox orderPerm = new JCheckBox("Trabajar con pedidos");
	private JCheckBox exchangePerm = new JCheckBox("Trabajar con intercambios");
	private BannerManager banner = new BannerManager();
	private JTextField userName = new JTextField();
	private JTextField pwd = new JTextField();
	private JButton confirmar = new JButton("CONFIRMAR");
	
	public ManagerGestionEmplIndividual() {
		
		super();
		
		this.setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Usuario
        JLabel lblUsuario = new JLabel("NOMBRE DE USUARIO");
        lblUsuario.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(lblUsuario);

        panel.add(this.userName);

        panel.add(Box.createVerticalStrut(15));

        // Contraseña
        JLabel lblPass = new JLabel("CONTRASEÑA");
        lblPass.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(lblPass);

        panel.add(this.pwd);

        panel.add(Box.createVerticalStrut(15));

        // Permisos
        JLabel lblPermisos = new JLabel("PERMISOS");
        lblPermisos.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(lblPermisos);

        panel.add(this.storePerm);
        panel.add(this.orderPerm);
        panel.add(this.exchangePerm);

        panel.add(Box.createVerticalGlue()); // empuja el botón abajo

        // Botón a la derecha
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        this.confirmar.setPreferredSize(new Dimension(140, 40));
        panelBoton.add(this.confirmar);

        panel.add(panelBoton);
        
        this.add(panel, BorderLayout.CENTER);
        this.add(this.banner, BorderLayout.NORTH);
    }
}
