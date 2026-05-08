package view;

import controller.*;
import model.product.Pack;
import model.product.StoreProduct;
import model.store.Store;
import model.user.UnregisteredClient;
import model.user.User;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.List;

/**
 * It implements the app's view
 * @author Sofia C.L., Duna P.R. and Ana O.R.
 * @version 1.0
 */
public class App extends JFrame {
    private static final long serialVersionUID = 1L;
    private final LoginP loginPanel;
    private final SignupP signupPanel;
    private final UnregisteredMainP unregisteredMainPanel;
    private final RegisteredMainP registeredMainPanel;
    private final EmployeeMainP employeeMainPanel;
    private final ManagerMainP managerMainPanel;
    private final WelcomeP welcomePanel;
    private final SearchPanel searchPanel;
    private final Container container;
    private final GridBagConstraints gbc;
    JPanel cards;
    //private final BrowseStoreP browseStorePanel;
    // Aquí se declaran todos los paneles de vista como atributos
    private User mainUser = new UnregisteredClient(true);
    private List<StoreProduct> products = Store.getInstance().getStoreProductList();
    private List<Pack> packs = Store.getInstance().getPacks();

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public App() throws IOException, BadLocationException {
        super("Gifts for Geeks"); /* JFrame's title */
        this.setIconImage(new ImageIcon(".\\resources\\app\\logo.png").getImage());

        /* Views */
        welcomePanel = new WelcomeP();
        loginPanel = new LoginP();
        signupPanel = new SignupP();
        unregisteredMainPanel = new UnregisteredMainP((UnregisteredClient) this.mainUser, this);
        registeredMainPanel = new RegisteredMainP();
        employeeMainPanel = new EmployeeMainP(this);
        managerMainPanel = new ManagerMainP();
        searchPanel = new SearchPanel();
        //browseStorePanel = new BrowseStoreP(this);

        /* Model */
        Store model = Store.getInstance();

        /* Controllers */
        WelcomeC welcomeController = new WelcomeC(this, model);
        LoginC loginController = new LoginC(this, model);
        SignupC signupController = new SignupC(this, model);
        UnregisteredMainC unregisteredMainController = new UnregisteredMainC(this, model);
        RegisteredMainC registeredMainController = new RegisteredMainC(this, model);
        EmployeeMainC employeeMainController = new EmployeeMainC(this, model);
        ManagerMainC managerMainController = new ManagerMainC(this, model);

        /* Configure controllers' views */
        loginPanel.setController(loginController);
        signupPanel.setController(signupController);
        unregisteredMainPanel.setController(unregisteredMainController);
        registeredMainPanel.setController(registeredMainController);
        employeeMainPanel.setController(employeeMainController);
        managerMainPanel.setController(managerMainController);

        /* Add views to main window */
        ImagePanel bgPanel = new ImagePanel(".\\resources\\app\\background.png");
        bgPanel.setLayout(new BorderLayout());
        this.setContentPane(bgPanel);

        container = this.getContentPane();
        container.setLayout(new BorderLayout());
        //container.setBackground(new Color(246, 243, 238)); // Beige
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        cards = new JPanel(new CardLayout());
        cards.setOpaque(false);
        JPanel banner = new JPanel();
        banner.setOpaque(false);
        container.add(cards, BorderLayout.CENTER);
        container.add(banner, BorderLayout.NORTH);

        addCard(welcomePanel, "WELCOME", welcomeController);
        addCard(loginPanel, "LOGIN");
        addCard(signupPanel, "SIGNUP");
        addCard(unregisteredMainPanel, "UNREGISTERED_MAIN");
        addCard(registeredMainPanel, "REGISTERED_MAIN");
        addCard(employeeMainPanel, "EMPLOYEE_MAIN");
        addCard(managerMainPanel, "MANAGER_MAIN");

        /* Main panel */
        welcomePanel.setVisible(true); // Es el primer panel que aparece, creo que el resto se inicializan a "false"

        /* Configure main window's size and default actions */
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    if (JOptionPane.showConfirmDialog(null, "Save the store?", "One last thing...",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                        model.saveStore("data", "statics");
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                e.getWindow().dispose();
            }
        });

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    public void changeVisibleCard(String cardName) {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, cardName);
    }

    public void addCard(JPanel newView, String constraints) {
        cards.add(newView, constraints);
        newView.setVisible(false);
        newView.setOpaque(false);
    }

    public void addCard(ControllableJPanel newView, String constraints, ActionListener controller) {
        newView.setController(controller);
        cards.add(newView, constraints);
        newView.setVisible(false);
        newView.setOpaque(false);
    }

    public void changeCurrentUser(User user) {
        this.mainUser = user;
    }

    public void addToContainer(JPanel newView) {
        container.add(newView, gbc);
    }

    public class ImagePanel extends JPanel {
        private static final long serialVersionUID = 1L;
        private Image backgroundImage;

        /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
        public ImagePanel(String filePath) {
            this.backgroundImage = new ImageIcon(filePath).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Draw the image to fill the entire panel
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /*public BrowseStoreP getBrowseStorePanel() {
        return browseStorePanel;
    }*/
    // Aquí van los getters de los atributos
    public App getApp() {
        return this;
    }

    public EmployeeMainP getEmployeeMainPanel() {
        return employeeMainPanel;
    }

    public LoginP getLoginPanel() {
        return loginPanel;
    }

    public ManagerMainP getManagerMainPanel() {
        return managerMainPanel;
    }

    public List<Pack> getPackList() {
        return this.packs;
    }

    public void setPackList(List<Pack> packs) {
        this.packs = packs;
    }

    public List<StoreProduct> getProducts() {
        return this.products;
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

    public void setsProductList(List<StoreProduct> products) {
        this.products = products;
    }
}