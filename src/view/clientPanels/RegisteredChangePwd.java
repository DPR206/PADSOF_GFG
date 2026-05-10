package view.clientPanels;

import java.awt.*;

import javax.swing.*;

public class RegisteredChangePwd extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField nom;
	private JButton btnCambiar;
	private JPasswordField pwd1, pwd2;


	/*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public RegisteredChangePwd(Frame padre) {
    	super(padre, "Cambio contraseña cliente", true);
		configurarEstructura();

		pack();
		setLocationRelativeTo(padre);
    }


	private void configurarEstructura() {

		getContentPane().setLayout(new BorderLayout(10, 10));

		JPanel information = new JPanel();
		information.setLayout(new BoxLayout(information, BoxLayout.Y_AXIS));
		information.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

		JLabel tituloNom = new JLabel("Username");
		tituloNom.setFont(new Font("SansSerif", Font.BOLD, 14));
        tituloNom.setAlignmentX(Component.LEFT_ALIGNMENT);

        nom = new JTextField(20);
		nom.setFont(new Font("SansSerif", Font.PLAIN, 14));
        nom.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel tituloPwd1 = new JLabel("Write your new password");
        tituloPwd1.setFont(new Font("SansSerif", Font.BOLD, 14));
        tituloPwd1.setAlignmentX(Component.LEFT_ALIGNMENT);

        pwd1 = new JPasswordField();
        pwd1.setPreferredSize(new Dimension(200, 30));
        pwd1.setMaximumSize(new Dimension(200, 30)); // Evita que se estire al máximo
        pwd1.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel tituloPwd2 = new JLabel("Write your new password again");
        tituloPwd2.setFont(new Font("SansSerif", Font.BOLD, 14));
        tituloPwd2.setAlignmentX(Component.LEFT_ALIGNMENT);

        pwd2 = new JPasswordField();
        pwd2.setPreferredSize(new Dimension(200, 30));
        pwd2.setMaximumSize(new Dimension(200, 30)); // Evita que se estire al máximo
        pwd2.setAlignmentX(Component.LEFT_ALIGNMENT);

        btnCambiar = new JButton("Cambiar");
        btnCambiar.setFont(new Font("SansSerif", Font.PLAIN, 14));
        btnCambiar.setBorderPainted(true);
        btnCambiar.setContentAreaFilled(true);
        btnCambiar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        information.add(tituloNom);
        information.add(nom);
        information.add(Box.createVerticalStrut(40));
        information.add(tituloPwd1);
        information.add(pwd1);
        information.add(Box.createVerticalStrut(40));
        information.add(tituloPwd2);
        information.add(pwd2);
        information.add(Box.createVerticalStrut(40));
        information.add(btnCambiar);

        getContentPane().add(information, BorderLayout.CENTER);
	}


	/**
	 * @return the nom
	 */
	public String getNom() {
		return new String(nom.getText());
	}

	/**
	 * @return the btnCambiar
	 */
	public JButton getBtnCambiar() {
		return btnCambiar;
	}


	/**
	 * @return the pwd1
	 */
	public String getPwd1() {
		return new String(pwd1.getPassword());
	}


	/**
	 * @return the pwd2
	 */
	public String getPwd2() {
		return new String(pwd2.getPassword());
	}


}