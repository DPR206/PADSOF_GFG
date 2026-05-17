package view.browserPanels;

import model.product.SecondHandProduct;
import view.App;
import view.miniPanels.SecondHandMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;

import java.util.ArrayList;
import java.util.List;

import static main.Main.brownColour;

/**
 * The type Browse second hand products p.
 * @author Ana O.R.
 * @version 1.0
 */
public class BrowseSecondHandProductsP extends AbstractBrowserP<SecondHandProduct> {
    private final String buttonName;
    private final String iconPath;
    private final App frame;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This panel's constructor
     * @param buttonName the button name
     * @param iconPath   the icon path
     * @throws BadLocationException the bad location exception
     */
    public BrowseSecondHandProductsP(App frame, String buttonName, String iconPath) throws BadLocationException {
        super();
        this.frame = frame;
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
        List<SecondHandProduct> alreadyChosen = new ArrayList<>(frame.getMyProducts());
        alreadyChosen.addAll(frame.getTheirProducts());
        SecondHandMiniP miniProduct = new SecondHandMiniP(item, index, buttonName, iconPath, alreadyChosen);
        super.addMiniPanel(miniProduct);
        this.add(miniProduct);
    }
}