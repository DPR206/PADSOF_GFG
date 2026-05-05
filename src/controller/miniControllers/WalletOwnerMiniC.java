package controller.miniControllers;

import controller.browserControllers.BigController;
import model.store.Store;
import view.App;
import view.browserPanels.BigView;
import view.miniPanels.UserMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.event.*;

public class WalletOwnerMiniC implements ActionListener {
    private final UserMiniP view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */
    private final BigController bigController;
    private final BigView bigView;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param model the controller's model
     */
    public WalletOwnerMiniC(App frame, Store model, UserMiniP view, BigController bigController, BigView bigView) {
        this.frame = frame;
        this.view = view;
        this.model = model;
        this.bigController = bigController;
        this.bigView = bigView;

        view.getUserImage().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JOptionPane.showMessageDialog(frame, "Aquí se cambiaría a la página del producto");
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("DUE")) { //DUE
            // Acción
            try {
                bigView.paintEverything();
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
            bigController.updateControllers();
        }

    }
}