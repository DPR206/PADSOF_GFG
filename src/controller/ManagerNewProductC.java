package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.ManagerCreateComicP;
import view.ManagerCreateFiguritaP;
import view.ManagerCreateGameP;
import view.ManagerNewProduct;

public class ManagerNewProductC implements ActionListener{
	private final ManagerNewProduct panel;
	private final ManagerCreateComicP managerComic = new ManagerCreateComicP();
	private final ManagerCreateFiguritaP managerFigura = new ManagerCreateFiguritaP();
	private final ManagerCreateGameP managerJuego = new ManagerCreateGameP();

	public ManagerNewProductC(ManagerNewProduct panel) {
		this.panel = panel;
	}

	@Override
    public void actionPerformed(ActionEvent e) {

		String name;
		double price;
		int stock;
		String description;

        if(e.getActionCommand().equals("AÑADIR UN CÓMIC")) {
        	
        }
        else if(e.getActionCommand().equals("AÑADIR UNA FIGURA")) {
        	
        }
        else if(e.getActionCommand().equals("AÑADIR UN JUEGO")) {
        	
        }
        	
	}
}