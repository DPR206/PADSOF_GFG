package controller.clientControllers;

import controller.Controller;
import model.product.Comic;
import model.user.*;
import view.App;
import view.clientPanels.ComicP;

import javax.swing.*;

/**
 * The type Comic c.
 * @author Duna P.R.
 * @version 1.0
 */
public class ComicC implements Controller {

    private final App frame;
    private final ComicP view;
    private final Comic comic;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Comic c.
     * @param frame the frame
     * @param view  the view
     * @param comic the comic
     */
    public ComicC(App frame, ComicP view, Comic comic) {
        this.frame = frame;
        this.view = view;
        this.comic = comic;

        initializeActions();
    }

    @Override
    public void initializeActions() {

        this.view.setName(comic.getName());
        this.view.setAuthor(comic.getAuthor());
        this.view.setEditorial(comic.getEditorial());
        this.view.setYear(String.valueOf(comic.getYear()));
        this.view.setPages(comic.getNumPages());
        this.view.setCategories(comic.getPrintCategories());

        this.view.setPrice(comic.getPrice());
        this.view.setDescriptionText(comic.getDescription());
        this.view.setStock(comic.getStock());
        this.view.setRating(comic.getAveragePunctuation());

        this.view.setImage(comic.getPhoto());
        this.view.setValoraciones(comic.getReviewsList());
        this.view.setMaxStock(comic.getStock());

        this.view.getBtnReturn().addActionListener(e -> frame.goBack());

        this.view.getBtnaddCart().addActionListener(e -> {
            if (frame.getUser().getType() == UserType.REGISTERED_CLIENT) {
                ((RegisteredClient) frame.getUser()).getC()
                                                    .addProductUds(comic, (int) view.getUnitSpinner().getValue());
            } else if (frame.getUser().getType() == UserType.UNREGISTERED_CLIENT) {
                ((UnregisteredClient) frame.getUser()).getCart()
                                                      .addProductUds(comic, (int) view.getUnitSpinner().getValue());
            }
            JOptionPane.showMessageDialog(frame, comic.getName() + " was added to Cart", "Added To Cart",
                    JOptionPane.INFORMATION_MESSAGE);
            updateInterface();
        });
    }

    /**
     * Update interface.
     */
    public void updateInterface() {
        ComicP comicVista = new ComicP();
        new ComicC(frame, comicVista, comic);
        frame.addCard(comicVista, "COMIC");
        frame.changeVisibleCard("COMIC");
    }

}