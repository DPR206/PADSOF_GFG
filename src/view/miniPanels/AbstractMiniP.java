package view.miniPanels;

import javax.swing.*;
import java.awt.*;

/**
 * The type Abstract mini p.
 * @author Duna P.R. & Ana O.R.
 * @version 1.0
 */
public abstract class AbstractMiniP extends JPanel {
    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Abstract mini p.
     */
    public AbstractMiniP() {
    }

    @Override
    public Dimension getMaximumSize() {
        // Retorna el ancho máximo posible, pero mantiene la altura preferida
        return new Dimension(Integer.MAX_VALUE, getPreferredSize().height);
    }
}