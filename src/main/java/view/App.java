package view;

import controller.*;
import model.store.Store;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame {

    // Aquí se declaran todos los paneles de vista como atributos
    private final LoginP loginPanel;
    private final SignupP signupPanel;
    private final UnregisteredMainP unregisteredMainPanel;
    private final WelcomeP welcomePanel;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public App() {
        super("Gifts for Geeks"); /* JFrame's title */

        /* Views */
        loginPanel = new LoginP();
        signupPanel = new SignupP();
        unregisteredMainPanel = new UnregisteredMainP();
        welcomePanel = new WelcomeP();

        /* Model */
        Store model = Store.getInstance();

        /* Controllers */
        LoginC loginController = new LoginC(this, model);
        SignupC signupController = new SignupC(this, model);
        UnregisteredMainC unregisteredMainController = new UnregisteredMainC(this, model);
        WelcomeC welcomeController = new WelcomeC(this, model);

        /* Configure controllers' views */
        loginPanel.setController(loginController);
        signupPanel.setController(signupController);
        unregisteredMainPanel.setController(unregisteredMainController);
        welcomePanel.setController(welcomeController);

        /* Add views to main window */
        Container contenedor = this.getContentPane();
        contenedor.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        /*contenedor.add(panelProyecto, gbc);
        contenedor.add(panelAltaTarea, gbc);
        panelProyecto.setVisible(true);
        panelAltaTarea.setVisible(false);*/

        // configurar tamaño de la ventana principal
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(250, 190);
        this.setLocationRelativeTo(null);
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/
    // Aquí van los getters de los atributos
    public LoginP getLoginPanel() {
        return loginPanel;
    }

    public SignupP getSignupPanel() {
        return signupPanel;
    }

    public UnregisteredMainP getUnregisteredMainPanel() {
        return unregisteredMainPanel;
    }

    public WelcomeP getWelcomePanel() {
        return welcomePanel;
    }
}