package controller.managerControllers;

import controller.Controller;
import view.App;
import view.managerPanels.*;

/**
 * The type Manager new product c.
 * @author Sofía C.L.
 * @version 1.0
 */
public class ManagerNewProductC implements Controller {
    private final ManagerNewProduct panel;
    private final App frame;
    private final ManagerCreateComicP managerComic = new ManagerCreateComicP();
    private final ManagerCreateFiguritaP managerFigura = new ManagerCreateFiguritaP();
    private final ManagerCreateGameP managerJuego = new ManagerCreateGameP();

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Manager new product c.
     * @param app   the app
     * @param panel the panel
     */
    public ManagerNewProductC(App app, ManagerNewProduct panel) {
        this.frame = app;
        this.panel = panel;

        new ManagerNewComicC(managerComic);
        new ManagerNewFigurineC(managerFigura);
        new ManagerNewGameC(managerJuego);

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