package controller.clientControllers;

import controller.Controller;
import model.product.Figurine;
import model.user.*;
import view.App;
import view.clientPanels.FigurineP;

import javax.swing.*;

/**
 * The type Figurine c.
 * @author Duna P.R.
 * @version 1.0
 */
public class FigurineC implements Controller {

    private final App frame;
    private final FigurineP view;
    private final Figurine figurine;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Figurine c.
     * @param frame    the frame
     * @param view     the view
     * @param figurine the figurine
     */
    public FigurineC(App frame, FigurineP view, Figurine figurine) {
        this.frame = frame;
        this.view = view;
        this.figurine = figurine;

        initializeActions();
    }

    @Override
    public void initializeActions() {

        this.view.setName(figurine.getName());
        this.view.setBrand(figurine.getBrand());
        this.view.setMaterial(figurine.getMaterial());
        this.view.setDimensions(figurine.getDimension());
        this.view.setCategories(figurine.getPrintCategories());

        this.view.setPrice(figurine.getPrice());
        this.view.setDescriptionText(figurine.getDescription());
        this.view.setStock(figurine.getStock());
        this.view.setRating(figurine.getAveragePunctuation());

        this.view.setImage(figurine.getPhoto());
        this.view.setValoraciones(figurine.getReviewsList());
        this.view.setMaxStock(figurine.getStock());

        this.view.getBtnReturn().addActionListener(e -> frame.goBack());

        this.view.getBtnaddCart().addActionListener(e -> {
            if (frame.getUser().getType() == UserType.REGISTERED_CLIENT) {
                ((RegisteredClient) frame.getUser()).getC()
                                                    .addProductUds(figurine, (int) view.getUnitSpinner().getValue());
            } else if (frame.getUser().getType() == UserType.UNREGISTERED_CLIENT) {
                ((UnregisteredClient) frame.getUser()).getCart()
                                                      .addProductUds(figurine, (int) view.getUnitSpinner().getValue());
            }
            JOptionPane.showMessageDialog(frame, figurine.getName() + " was added to Cart", "Added To Cart",
                    JOptionPane.INFORMATION_MESSAGE);
            updateInterface();
        });
    }

    /**
     * Update interface.
     */
    public void updateInterface() {
        FigurineP figurineVista = new FigurineP();
        new FigurineC(frame, figurineVista, figurine);
        frame.addCard(figurineVista, "FIGURINE");
        frame.changeVisibleCard("FIGURINE");
    }

}