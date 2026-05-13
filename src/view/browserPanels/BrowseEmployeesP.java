package view.browserPanels;

import model.user.Employee;
import view.miniPanels.UserMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;

import static main.Main.brownColour;

public class BrowseEmployeesP extends AbstractBrowserP<Employee> {
    private final String buttonName;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This panel's constructor
     */
    public BrowseEmployeesP(String buttonName) throws BadLocationException {
        super();
        this.buttonName = buttonName;

        paintEverything();
    }

    /**
     * It allows this page's components to be repainted (revalidate() & repaint() didn't work)
     * @throws BadLocationException bad locations within a document model (that is, attempts to reference a location
     *                              that doesn't exist)
     */
    public void paintEverything() throws BadLocationException {
        this.removeAll();

        // Item list was set in constructor

        super.addAllMiniPanels();
        this.add(super.getPageTurner());
        this.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, brownColour));

        this.revalidate();
        this.repaint();
    }

    @Override
    public void addMiniPanel(Employee item, int index) {
        UserMiniP miniUser = new UserMiniP(item, buttonName, ".\\resources\\app\\default_user.png");
        super.addMiniPanel(miniUser);
        this.add(miniUser);
    }
}