package view.miniPanels;

import view.ControllableJPanel;

import java.awt.event.ActionListener;

public abstract class MiniPanel extends ControllableJPanel {
    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public MiniPanel() {
    }

    public abstract void setController(ActionListener c);
}