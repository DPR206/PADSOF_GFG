package view.browserPanels;

import model.product.Pack;
import model.product.StoreProduct;
import view.miniPanels.PackMiniP;
import view.miniPanels.StoreProductMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;

import static main.Main.brownColour;

/**
 * The type Mixed browse in pack p.
 * @author Sofia C.L..
 * @version 1.0
 */
public class MixedBrowseInPackP extends AbstractMixedBrowserP<Pack, StoreProduct> {

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This panel's constructor
     * @throws BadLocationException the bad location exception
     */
    public MixedBrowseInPackP() throws BadLocationException {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        paintEverything();
    }

    /**
     * It allows this page's components to be repainted (revalidate() & repaint() didn't work)
     * @throws BadLocationException bad locations within a document model (that is, attempts to reference a location
     *                              that doesn't exist)
     */
    @Override
    public void paintEverything() throws BadLocationException {
        this.clearItemsContainer();
        this.removeAll();

        super.addAllMiniPanels();
        if (super.getFirstMiniPanels().isEmpty() && super.getSecondMiniPanels().isEmpty()) {
            this.add(new JLabel("No packs or products to be seen"));
        }

        this.add(containerItems, BorderLayout.NORTH);

        JPanel northWrapper = new JPanel(new BorderLayout());
        northWrapper.setOpaque(false);
        northWrapper.add(containerItems, BorderLayout.NORTH);

        this.add(northWrapper, BorderLayout.NORTH);

        JPanel filler = new JPanel();
        filler.setOpaque(false);
        this.add(filler, BorderLayout.CENTER);

        this.add(super.getPageTurner(), BorderLayout.SOUTH);
        this.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, brownColour));

        this.revalidate();
        this.repaint();
    }

    @Override
    public void addFirstMiniPanel(Pack item, int index) throws BadLocationException {
        PackMiniP miniPack = new PackMiniP(item, index, "MANAGE", ".\\resources\\app\\cart.png");
        super.addFirstMiniPanel(miniPack);
        this.add(miniPack);
    }

    @Override
    public void addSecondMiniPanel(StoreProduct item, int index) throws BadLocationException {
        StoreProductMiniP miniPack = new StoreProductMiniP(item, index, "MANAGE", ".\\resources\\app\\cart.png");
        super.addSecondMiniPanel(miniPack);
        this.add(miniPack);
    }
}