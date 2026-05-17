package controller.accessControllers;

import model.product.Pack;
import model.product.StoreProduct;
import model.store.Store;
import model.user.*;
import view.App;
import view.accessPanels.LoginP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

/**
 * It implements the log-in controller
 * @author Ana O.R.
 * @version 1.0
 */
public class LoginC extends MainLoopSelector {
    private final LoginP view;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param model the controller's model
     */
    public LoginC(App frame, Store model) {
        super(frame, model);
        this.view = frame.getLoginPanel();
        initializeActions();
    }

    @Override
    public void initializeActions() {
        /* Type Enter to press key */
        view.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    view.getLogin().doClick();
                }
            }
        });

        /* Log in button */
        view.getLogin().addActionListener(e -> {
            User user = super.getModel().logIn(view.getUsername(), view.getPassword());
            List<StoreProduct> cartProducts = null;
            List<Pack> cartPacks = null;
            if (user != null && user.getType() == UserType.UNREGISTERED_CLIENT) {
                cartProducts = ((UnregisteredClient) super.getFrame().getUser()).getCart().getProducts();
                cartPacks = ((UnregisteredClient) super.getFrame().getUser()).getCart().getPacks();
            }
            if (user == null) {
                JOptionPane.showMessageDialog(super.getFrame(), "Incorrect username or password", "Error log in",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                super.getFrame().changeCurrentUser(user);
                if (super.getFrame().getUser().getType() == UserType.REGISTERED_CLIENT && cartPacks != null &&
                    cartProducts != null) {
                    ((RegisteredClient) super.getFrame().getUser()).getC().getProducts().addAll(cartProducts);
                    ((RegisteredClient) super.getFrame().getUser()).getC().getPacks().addAll(cartPacks);
                }
                try {
                    super.loopSelector();
                } catch (BadLocationException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}