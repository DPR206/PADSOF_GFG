package controller.managerControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.App;
import view.managerPanels.ManagerCreateComicP;
import view.managerPanels.ManagerCreateFiguritaP;
import view.managerPanels.ManagerCreateGameP;
import view.managerPanels.ManagerNewProduct;

public class ManagerNewProductC implements ActionListener{
	private final ManagerNewProduct panel;
	private final App frame;
	private final ManagerCreateComicP managerComic = new ManagerCreateComicP();
	private final ManagerCreateFiguritaP managerFigura = new ManagerCreateFiguritaP();
	private final ManagerCreateGameP managerJuego = new ManagerCreateGameP();

	public ManagerNewProductC(App app,  ManagerNewProduct panel) {
		this.frame = app;
		this.panel = panel;
		
		this.managerComic.setController(new ManagerNewComicC(managerComic, app));
		this.managerFigura.setController(new ManagerNewFigurineC(managerFigura, app));
		this.managerJuego.setController(new ManagerNewGameC(managerJuego, app));
	}

	@Override
    public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {

        case "AÑADIR UN CÓMIC":
        	this.frame.addCard(managerComic, "COMICS");
        	this.frame.changeVisibleCard("COMICS");
            break;

        case "AÑADIR UNA FIGURA":
        	this.frame.addCard(managerFigura, "FIGURAS");
            this.frame.changeVisibleCard("FIGURAS");
            break;

        case "AÑADIR UN JUEGO":
        	this.frame.addCard(managerJuego, "JUEGO");
            this.frame.changeVisibleCard("JUEGO");
            break;
		}
	}
}