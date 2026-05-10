package controller.clientControllers;

import controller.Controller;
import model.user.RegisteredClient;
import view.clientPanels.RegisteredWalletP;

public class RegisteredWalletC implements Controller {
    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public RegisteredWalletC(RegisteredWalletP pagWallet, RegisteredClient user) {
        initializeActions();
    }

    @Override
    public void initializeActions() {

    }
}