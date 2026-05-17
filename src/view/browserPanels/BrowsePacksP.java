package view.browserPanels;

import model.product.Pack;
import view.miniPanels.PackMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;

import static main.Main.brownColour;

/**
 * The type Browse packs p.
 * @author Sofía C.L.
 * @version 1.0
 */
public class BrowsePacksP extends AbstractBrowserP<Pack> {
    private final String buttonName;
    private final String iconPath;

    /**
     * Instantiates a new Browse packs p.
     * @param buttonName the button name
     * @param iconPath   the icon path
     * @throws BadLocationException the bad location exception
     */
    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public BrowsePacksP(String buttonName, String... iconPath) throws BadLocationException {
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