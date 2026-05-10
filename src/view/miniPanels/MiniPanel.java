package view.miniPanels;

import javax.swing.*;
import java.awt.*;

public abstract class MiniPanel extends JPanel {
    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public MiniPanel() {
    }

    @Override
    public Dimension getMaximumSize() {
        // Retorna el ancho máximo posible, pero mantiene la altura preferida
        return new Dimension(Integer.MAX_VALUE, getPreferredSize().height);
    }
}