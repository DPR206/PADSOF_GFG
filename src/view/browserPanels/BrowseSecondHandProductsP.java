package view.browserPanels;

import model.product.SecondHandProduct;
import view.miniPanels.SecondHandMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;

import static main.Main.brownColour;

public class BrowseSecondHandProductsP extends BrowserPanel<SecondHandProduct> {
    private final String buttonName;
    private final String iconPath;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This panel's constructor
     */
    public BrowseSecondHandProductsP(String buttonName, String iconPath) throws BadLocationException {
        super();
        this.buttonName = buttonName;
        this.iconPath = iconPath;
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
    public void addMiniPanel(SecondHandProduct item, int index) throws BadLocationException {
        SecondHandMiniP miniProduct = new SecondHandMiniP(item, index, buttonName, iconPath);
        super.addMiniPanel(miniProduct);
        this.add(miniProduct);
    }
}