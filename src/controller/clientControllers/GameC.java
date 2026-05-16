package controller.clientControllers;

import controller.Controller;
import model.product.Game;
import model.user.*;
import view.App;
import view.clientPanels.GameP;

import javax.swing.*;

/**
 * The type Game c.
 * @author Duna P.R.
 * @version 1.0
 */
public class GameC implements Controller {

    private final App frame;
    private final GameP view;
    private final Game game;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Game c.
     * @param frame the frame
     * @param view  the view
     * @param game  the game
     */
    public GameC(App frame, GameP view, Game game) {
        this.frame = frame;
        this.view = view;
        this.game = game;

        initializeActions();
    }

    @Override
    public void initializeActions() {

        this.view.setName(game.getName());
        this.view.setGameType(game.getGameStyle().toString());
        this.view.setPlayers(game.getNumPlayers());
        this.view.setAge(game.getAgeRange());
        this.view.setCategories(game.getPrintCategories());

        this.view.setPrice(game.getPrice());
        this.view.setDescriptionText(game.getDescription());
        this.view.setStock(game.getStock());
        this.view.setRating(game.getAveragePunctuation());

        this.view.setImage(game.getPhoto());
        this.view.setValoraciones(game.getReviewsList());
        this.view.setMaxStock(game.getStock());

        this.view.getBtnReturn().addActionListener(e -> frame.goBack());

        this.view.getBtnaddCart().addActionListener(e -> {
            if (frame.getUser().getType() == UserType.REGISTERED_CLIENT) {
                ((RegisteredClient) frame.getUser()).getC().addProductUds(game, (int) view.getUnitSpinner().getValue());
            } else if (frame.getUser().getType() == UserType.UNREGISTERED_CLIENT) {
                ((UnregisteredClient) frame.getUser()).getCart()
                                                      .addProductUds(game, (int) view.getUnitSpinner().getValue());
            }
            JOptionPane.showMessageDialog(frame, game.getName() + " was added to Cart", "Added To Cart",
                    JOptionPane.INFORMATION_MESSAGE);
            updateInterface();
        });
    }

    /**
     * Update interface.
     */
    public void updateInterface() {
        GameP gameVista = new GameP();
        new GameC(frame, gameVista, game);
        frame.addCard(gameVista, "GAME");
        frame.changeVisibleCard("GAME");
    }
}