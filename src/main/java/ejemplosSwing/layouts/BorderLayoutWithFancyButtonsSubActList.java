package ejemplosSwing.layouts;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import javax.swing.ImageIcon;


/**
 * Tambien  podemos crear ventanas subclasificando de JFrame
 * @author Juan de Lara
 *
 */
class MyJFrameClass2 extends JFrame {
	private static final long serialVersionUID = 9118673928693575927L;

	protected ImageIcon createImageIcon(String path, String description) {
		if (new File(path).exists()) {
			return new ImageIcon(path, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	public MyJFrameClass2(String text) {
		super(text);

		// Creamos iconos con las imagenes. Paths relativos.
		ImageIcon icon1 = createImageIcon("figuras/Image1.gif", "Una figura");
		ImageIcon icon2 = createImageIcon("figuras/Image2.gif", "Una figura");
		ImageIcon icon3 = createImageIcon("figuras/Image3.gif", "Una figura");
		ImageIcon icon4 = createImageIcon("figuras/Image4.gif", "Una figura");
		ImageIcon icon5 = createImageIcon("figuras/Image5.gif", "Una figura");

		// Incluimos texto e imagenes
		JButton button1    = new JButton("NORTE", icon1);
		// Podemos anyadir codigo HTML
		JButton button2    = new JButton("<html><center><b><u>S</u>ur</b><br><font color=#dc143c>Otra linea</font>", icon2);
		// Solo imagen
		JButton button3    = new JButton(icon3);
		JButton button4    = new JButton("OESTE", icon4);
		JButton button5    = new JButton("CENTRO", icon5);

		int APP_WIDTH  = 500;
		int APP_HEIGHT = 550;

		this.add(button1,  BorderLayout.NORTH);
		this.add(button2,  BorderLayout.SOUTH);
		this.add(button3,  BorderLayout.EAST);
		this.add(button4,  BorderLayout.WEST);
		this.add(button5,  BorderLayout.CENTER);

		// listeners para los botones
		button1.addActionListener(new MyActionListener2("NORTE"));
		button2.addActionListener(new MyActionListener2("SUR"));
		button3.addActionListener(new MyActionListener2("ESTE"));
		button4.addActionListener(new MyActionListener2("OESTE"));
		button5.addActionListener(new MyActionListener2("CENTRO"));

		this.setSize(APP_WIDTH, APP_HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

	}
}

/**
 * Esta clase implementa un escuchador de eventos para la clase MyJFrameClass2.
 * Normalmente debe ser una clase interna a MyJFrameClass2.
 * @author Juan de Lara
 *
 */
class MyActionListener2 implements ActionListener {
	private String message;
	public MyActionListener2(String message) {
		this.message = message;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, message);
	}
}

public class BorderLayoutWithFancyButtonsSubActList {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Runnable runner = new Runnable() {
			public void run() {
				// Ventana principal
				final MyJFrameClass2 frame = new MyJFrameClass2("Una Ventana de Ejemplo que Hereda de JFrame con Listeners Explicitos");
			}
		};
		SwingUtilities.invokeLater(runner);
	}

}