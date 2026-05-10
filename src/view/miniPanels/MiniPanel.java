package view.miniPanels;

import view.ControllableJPanel;

import java.awt.Dimension;
import java.awt.event.ActionListener;

public abstract class MiniPanel extends ControllableJPanel {
    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public MiniPanel() {
    }
    
    @Override
    public Dimension getMaximumSize() {
        // Retorna el ancho máximo posible, pero mantiene la altura preferida
        return new Dimension(Integer.MAX_VALUE, getPreferredSize().height);
    }

    public abstract void setController(ActionListener c);
}