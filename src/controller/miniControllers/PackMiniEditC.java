package controller.miniControllers;

import controller.browserControllers.BigController;
import model.store.Store;
import view.App;
import view.employeePanels.SPManageIndividualPack;
import view.browserPanels.BrowserPanel;
import view.miniPanels.PackMiniEdit;

import javax.swing.*;
import java.awt.event.*;

public class PackMiniEditC implements ActionListener {

    private App frame;
    private Store model;
    private PackMiniEdit view;
    private BigController bigController;
    private BrowserPanel browserPanel;
    private SPManageIndividualPack spm;

/*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public PackMiniEditC(App frame, Store model, PackMiniEdit view, BigController bigController,
                         BrowserPanel browserPanel) {
        this.frame = frame;
        this.view = view;
        this.model = model;
        this.bigController = bigController;
        this.browserPanel = browserPanel;
        this.spm = new SPManageIndividualPack(this.view.getPack());

        view.getPackImage().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JOptionPane.showMessageDialog(frame, "Aquí se cambiaría a la página del producto");
                }
            }
        });

        view.getPackInfo().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JOptionPane.showMessageDialog(frame, "Aquí se cambiaría a la página del pack");
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Gestionar")) {
            //MOSTRAR EL PACK, DUE
        }

    }
}