package controller.managerControllers;

import controller.Controller;
import view.App;
import view.managerPanels.*;

public class ManagerNewProductC implements Controller {
    private final ManagerNewProduct panel;
    private final App frame;
    private final ManagerCreateComicP managerComic = new ManagerCreateComicP();
    private final ManagerCreateFiguritaP managerFigura = new ManagerCreateFiguritaP();
    private final ManagerCreateGameP managerJuego = new ManagerCreateGameP();

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public ManagerNewProductC(App app, ManagerNewProduct panel) {
        this.frame = app;
        this.panel = panel;

        new ManagerNewComicC(managerComic, app);
        new ManagerNewFigurineC(managerFigura, app);
        new ManagerNewGameC(managerJuego, app);

        initializeActions();
    }

    @Override
    public void initializeActions() {
        panel.getComics().addActionListener(e -> {
            this.frame.addCard(managerComic, "COMICS");
            this.frame.changeVisibleCard("COMICS");
        });

        panel.getFiguras().addActionListener(e -> {
            this.frame.addCard(managerFigura, "FIGURAS");
            this.frame.changeVisibleCard("FIGURAS");
        });

        panel.getJuegos().addActionListener(e -> {
            this.frame.addCard(managerJuego, "JUEGO");
            this.frame.changeVisibleCard("JUEGO");
        });
    }
}