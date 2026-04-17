package ejemplosSwing.layouts;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;


/**
 * Tambien podemos crear ventanas subclasificando de JFrame
 * @author Juan de Lara
 *
 */
class MyJFrameClass1 extends JFrame {
	private static final long serialVersionUID = 9118673928693575927L;

	public MyJFrameClass1(String text) {
		super(text);
		JButton button1    = new JButton("NORTE");
		JButton button2    = new JButton("SUR");
		JButton button3    = new JButton("ESTE");
		JButton button4    = new JButton("OESTE");
		JButton button5    = new JButton("CENTRO");

		int APP_WIDTH  = 500;
		int APP_HEIGHT = 550;

		this.add(button1,  BorderLayout.NORTH);
		this.add(button2,  BorderLayout.SOUTH);
		this.add(button3,  BorderLayout.EAST);
		this.add(button4,  BorderLayout.WEST);
		this.add(button5,  BorderLayout.CENTER);

		// listeners para los botones
		button1.addActionListener(new MyActionListener1("NORTE"));
		button2.addActionListener(new MyActionListener1("SUR"));
		button3.addActionListener(new MyActionListener1("ESTE"));
		button4.addActionListener(new MyActionListener1("OESTE"));
		button5.addActionListener(new MyActionListener1("CENTRO"));

		this.setSize(APP_WIDTH, APP_HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}

/**
 * Esta clase implementa un escuchador de eventos para la clase MyJFrameClass1.
 * Normalmente ser�a una clase interna a MyJFrameClass1.
 * @author Juan de Lara
 *
 */
class MyActionListener1 implements ActionListener {
	private String message;
	public MyActionListener1(String message) {
		this.message = message;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, message);
	}
}

public class BorderLayoutWithButtonsSubActList {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Runnable runner = new Runnable() {
			public void run() {
				// Ventana principal
				final MyJFrameClass1 frame = new MyJFrameClass1("Una Ventana de Ejemplo que Hereda de JFrame con Listeners Expl�citos");
			}
		};
		SwingUtilities.invokeLater(runner);
	}

}