package view.browserPanels;

import model.product.Pack;
import view.miniPanels.PackDiscMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.io.Serial;
import java.util.List;

import static main.Main.brownColour;

/**
 * The type Browse packs disc p.
 * @author Ana O.R.
 * @version 1.0
 */
public class BrowsePacksDiscP extends AbstractBrowserP<Pack> {
    @Serial
    private static final long serialVersionUID = 1L;
    private final List<Pack> alreadyChosen;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Browse packs disc p.
     * @param alreadyChosen the already chosen
     * @throws BadLocationException the bad location exception
     */
    public BrowsePacksDiscP(List<Pack> alreadyChosen) throws BadLocationException {
        super();
        this.alreadyChosen = alreadyChosen;
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
        PackDiscMiniP miniOrder = new PackDiscMiniP(item, index, alreadyChosen);
        super.addMiniPanel(miniOrder);
        this.add(miniOrder);

    }
}