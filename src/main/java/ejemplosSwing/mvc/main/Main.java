package ejemplosSwing.mvc.main;

import java.awt.EventQueue;

import ejemplosSwing.mvc.vista.Aplicacion;

public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Aplicacion().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}