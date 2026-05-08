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
		
		panel.getCards().add(managerComic, ManagerNewProduct.PANEL_COMIC);
        panel.getCards().add(managerFigura, ManagerNewProduct.PANEL_FIGURA);
        panel.getCards().add(managerJuego, ManagerNewProduct.PANEL_JUEGO);
	}

	@Override
    public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {

        case "AÑADIR UN CÓMIC":
            panel.showPanel(ManagerNewProduct.PANEL_COMIC);
            break;

        case "AÑADIR UNA FIGURA":
            panel.showPanel(ManagerNewProduct.PANEL_FIGURA);
            break;

        case "AÑADIR UN JUEGO":
            panel.showPanel(ManagerNewProduct.PANEL_JUEGO);
            break;
		}
	}
}