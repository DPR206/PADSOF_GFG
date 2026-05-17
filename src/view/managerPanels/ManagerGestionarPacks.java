package view.managerPanels;

import view.App;
import view.browserPanels.BrowsePacksP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The type Manager gestionar packs.
 * @author Sofia C.L.
 * @version 1.0
 */
public class ManagerGestionarPacks extends JPanel {

    private final JButton newPack = new JButton("Crear nuevo pack");
    private BrowsePacksP browser;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Manager gestionar packs.
     * @param app the app
     */
    public ManagerGestionarPacks(App app) {
        super();

        try {
            this.browser = new BrowsePacksP("MANAGE", ".\\resources\\app\\arrow_right.png");
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
        this.setLayout(new BorderLayout());

        JPanel mainThings = new JPanel();
        mainThings.setLayout(new BoxLayout(mainThings, BoxLayout.Y_AXIS));
        /**Esto lo hará el controlador
         List<Pack> packs = Store.getInstance().getPacks();

         int index = 1;

         for (Pack p : packs) {
         try {
         mainThings.add(new PackMiniP(p, index, "Manage", null));
         index++;
         } catch (BadLocationException e) {
         e.printStackTrace();
         }
         }*/

        this.newPack.setPreferredSize(new Dimension(120, 30));

        JPanel auxiliar = new JPanel();
        auxiliar.add(newPack);

        this.add(this.browser, BorderLayout.CENTER);
        this.add(auxiliar, BorderLayout.EAST);

    }

    /**
     * It gets the browser
     * @return the browser
     */
    public BrowsePacksP getBrowser() {
        return this.browser;
    }

    /**
     * It gets the confirmación
     * @return the confirmación
     */
    public JButton getConfirmacion() {
        return this.newPack;
    }

    /**
     * It sets the controller
     * @param c the c
     */
    public void setController(ActionListener c) {
        this.newPack.addActionListener(c);
    }
}