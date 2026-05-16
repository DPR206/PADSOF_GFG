package controller.miniControllers;

import controller.Controller;
import view.managerPanels.ManagerIndividualSimplePack;
import view.miniPanels.PackMiniP;

/**
 * The type Manage mini simple pack c.
 * @author Sofía C.L.
 * @version 1.0
 */
public class ManageMiniSimplePackC implements Controller {

    private final PackMiniP selected;
    private ManagerIndividualSimplePack mip;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Manage mini simple pack c.
     * @param selected the selected
     */
    public ManageMiniSimplePackC(PackMiniP selected) {
        this.selected = selected;
    }

    @Override
    public void initializeActions() {
        this.selected.getButton().addActionListener(e -> {
            this.mip = new ManagerIndividualSimplePack(selected.getPack());
            //CREAR CONTROLER
        });

    }
    /*DUE*/
}