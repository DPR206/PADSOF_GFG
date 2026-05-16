package controller.miniControllers;

import controller.Controller;
import controller.maxiPanels.MaxiValuateSecondHandC;
import model.store.Store;
import view.App;
import view.maxiPanels.MaxiValuateSecondHandP;
import view.miniPanels.SecondHandMiniP;

import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The type Second hand valuate mini c.
 * @author Ana O.R.
 * @version 1.0
 */
public class SecondHandValuateMiniC implements Controller {
    private final SecondHandMiniP view;
    private final App frame;
    private final Store model;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param model the controller's model
     * @param view  the view
     */
    public SecondHandValuateMiniC(App frame, Store model, SecondHandMiniP view) {
        this.frame = frame;
        this.view = view;
        this.model = model;

        initializeActions();
    }

    private void seeProduct() {
        try {
            MaxiValuateSecondHandP newView = new MaxiValuateSecondHandP(frame, view.getSecondHandProduct());
            new MaxiValuateSecondHandC(frame, model, newView);
            frame.addCard(newView, "VALUATE_SECOND_HAND");
            frame.changeVisibleCard("VALUATE_SECOND_HAND");
        } catch (BadLocationException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void initializeActions() {
        view.setFocusable(true);
        view.setCursor(new Cursor(Cursor.HAND_CURSOR));
        view.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                seeProduct();
            }
        });

        view.getProductImage().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    seeProduct();
                }
            }
        });

        view.getProductInfo().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    seeProduct();
                }
            }
        });

        view.getButton().addActionListener(e -> seeProduct());
    }
}