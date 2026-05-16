package controller.clientControllers;

import controller.Controller;
import model.product.SecondHandProduct;
import view.App;
import view.clientPanels.SecondHandOthersP;

/**
 * The type Second hand others c.
 * @author Duna P.R.
 * @version 1.0
 */
public class SecondHandOthersC implements Controller {

    private final App frame;
    private final SecondHandOthersP view;
    private final SecondHandProduct product;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Second hand others c.
     * @param frame   the frame
     * @param view    the view
     * @param product the product
     */
    public SecondHandOthersC(App frame, SecondHandOthersP view, SecondHandProduct product) {
        this.frame = frame;
        this.view = view;
        this.product = product;

        initializeActions();
    }

    @Override
    public void initializeActions() {

        this.view.setName(product.getName());
        this.view.setImage(product.getPhoto());
        this.view.setDescriptionText(product.getDescription());
        this.view.setValuation(product.getPrice(), product.getStatus());

        if (product.isAvailable()) {
            this.view.getBtnOffer().setVisible(true);
        } else {
            this.view.getBtnOffer().setVisible(false);
        }

        this.view.getBtnReturn().addActionListener(e -> frame.goBack());

        this.view.getBtnOffer().addActionListener(e -> {
            //aquí se abre offer
        });
    }

}