package view.browserPanels;

import model.product.Pack;
import view.miniPanels.PackMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;

import static main.Main.brownColour;

public class BrowsePacks extends BrowserPanel<Pack> {
    private final String buttonName;
    private final String iconPath;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public BrowsePacks(String buttonName, String... iconPath) throws BadLocationException {
        super();
        this.buttonName = buttonName;
        this.iconPath = iconPath[0];
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        paintEverything();
    }

    @Override
    public void paintEverything() throws BadLocationException {
        this.removeAll();

        super.addAllMiniPanels();
        this.add(super.getPageTurner());
        this.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, brownColour));

        this.revalidate();
        this.repaint();

    }

    @Override
    public void addMiniPanel(Pack item, int index) throws BadLocationException {
        PackMiniP miniPack = new PackMiniP(item, index, buttonName, iconPath);
        super.addMiniPanel(miniPack);
        this.add(miniPack);
    }
}