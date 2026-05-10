package view.browserPanels;

import model.product.Pack;
import model.store.Store;
import view.miniPanels.PackMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;

import static main.Main.brownColour;

public class BrowsePacks extends BrowserPanel<Pack> {

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public BrowsePacks() throws BadLocationException {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        paintEverything();
    }

    @Override
    public void paintEverything() throws BadLocationException {
        this.removeAll();

        super.setItemList(Store.getInstance().getPacks()); // DUE: Esto debe darlo el controlador

        super.addAllMiniPanels();
        this.add(super.getPageTurner());
        this.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, brownColour));

        this.revalidate();
        this.repaint();

    }

    @Override
    public void addMiniPanel(Pack item, int index) throws BadLocationException {
        PackMiniP miniPack = new PackMiniP(item, index);
        super.addMiniPanel(miniPack);
        this.add(miniPack);
    }
}