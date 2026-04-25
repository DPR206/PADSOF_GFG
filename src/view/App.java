package view;

import controller.*;
import model.store.SaverLoader;
import model.store.Store;
import model.user.UnregisteredClient;
import model.user.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

/**
 * It implements the app's view
 * @author Sofia C.L., Duna P.R. and Ana O.R.
 * @version 1.0
 */
public class App extends JFrame {

    // Aquí se declaran todos los paneles de vista como atributos
    private final LoginP loginPanel;
    private final SignupP signupPanel;
    private final UnregisteredMainP unregisteredMainPanel;
    private final RegisteredMainP registeredMainPanel;
    private final EmployeeMainP employeeMainPanel;
    private final ManagerMainP managerMainPanel;
    private final WelcomeP welcomePanel;
    private final SearchPanel searchPanel;
    private User mainUser = new UnregisteredClient(true);

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public App() {
        super("Gifts for Geeks"); /* JFrame's title */

        if ((new File(".\\resources\\data.txt")).isFile()) {
            try {
                SaverLoader.getInstance()
                           .loadStore("parameter", "categories", "reviews", "storeProducts", "secondHandProducts",
                                   "packs", "discounts", "offers", "exchanges", "orders", "users");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        /* Views */
        loginPanel = new LoginP();
        signupPanel = new SignupP();
        unregisteredMainPanel = new UnregisteredMainP((UnregisteredClient) this.mainUser);
        registeredMainPanel = new RegisteredMainP();
        employeeMainPanel = new EmployeeMainP();
        managerMainPanel = new ManagerMainP();
        welcomePanel = new WelcomeP();
        searchPanel = new SearchPanel();


        /* Model */
        Store model = Store.getInstance();

        /* Controllers */
        LoginC loginController = new LoginC(this, model);
        SignupC signupController = new SignupC(this, model);
        UnregisteredMainC unregisteredMainController = new UnregisteredMainC(this, model);
        RegisteredMainC registeredMainController = new RegisteredMainC(this, model);
        EmployeeMainC employeeMainController = new EmployeeMainC(this, model);
        ManagerMainC managerMainController = new ManagerMainC(this, model);
        WelcomeC welcomeController = new WelcomeC(this, model);

        /* Configure controllers' views */
        loginPanel.setController(loginController);
        signupPanel.setController(signupController);
        unregisteredMainPanel.setController(unregisteredMainController);
        welcomePanel.setController(welcomeController);

        /* Add views to main window */
        Container container = this.getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        container.add(loginPanel, gbc);
        loginPanel.setVisible(false);
        container.add(signupPanel, gbc);
        signupPanel.setVisible(false);
        container.add(unregisteredMainPanel, gbc);
        unregisteredMainPanel.setVisible(false);
        container.add(welcomePanel, gbc);
        welcomePanel.setVisible(true); // Es el primer panel que aparece, creo que el resto se inicializan a "false"

        /* Configure main window's size and default actions */
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    SaverLoader.getInstance()
                               .saveStore("parameter", "categories", "reviews", "storeProducts", "secondHandProducts",
                                       "packs", "discounts", "offers", "exchanges", "orders", "users");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                e.getWindow().dispose();
            }
        });

        this.setSize(700, 500);
        this.setLocationRelativeTo(null);

    }

    public void changeCurrentUser(User user) {
        this.mainUser = user;
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/
    public App getApp() {
        return this;
    }
    // Aquí van los getters de los atributos

    public EmployeeMainP getEmployeeMainPanel() {
        return employeeMainPanel;
    }

    public LoginP getLoginPanel() {
        return loginPanel;
    }

    public ManagerMainP getManagerMainPanel() {
        return managerMainPanel;
    }

    public RegisteredMainP getRegisteredMainPanel() {
        return registeredMainPanel;
    }

    public SearchPanel getSearchPanel() {
        return searchPanel;
    }

    public SignupP getSignupPanel() {
        return signupPanel;
    }

    public UnregisteredMainP getUnregisteredMainPanel() {
        return unregisteredMainPanel;
    }

    public User getUser() {
        return this.mainUser;
    }

    public WelcomeP getWelcomePanel() {
        return welcomePanel;
    }

    public void setUnregisteredClient(UnregisteredClient u) {
        this.mainUser = u;
    }
}