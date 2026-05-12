package view.miniPanels;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractMiniP extends JPanel {
    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public AbstractMiniP() {
    }

    @Override
    public Dimension getMaximumSize() {
        // Retorna el ancho máximo posible, pero mantiene la altura preferida
        return new Dimension(Integer.MAX_VALUE, getPreferredSize().height);
    }
}