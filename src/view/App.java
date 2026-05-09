package view;

import controller.accessControllers.*;
import controller.bannerControllers.BannerRegisteredC;
import controller.bannerControllers.BannerUnregisteredC;
import controller.clientControllers.RegisteredMainC;
import controller.clientControllers.UnregisteredMainC;
import controller.employeeControllers.EmployeeMainC;
import controller.managerControllers.ManagerMainC;
import model.product.Pack;
import model.product.StoreProduct;
import model.store.Store;
import model.user.UnregisteredClient;
import model.user.User;
import view.accessPanels.*;
import view.banners.*;
import view.clientPanels.*;
import view.employeePanels.EmployeeMainP;
import view.managerPanels.ManagerMainP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.Serial;
import java.util.*;
import java.util.List;

/**
 * It implements the app's view
 * @author Sofia C.L., Duna P.R. and Ana O.R.
 * @version 1.0
 */
public class App extends JFrame {
    @Serial
    private static final long serialVersionUID = 1L;
    private final WelcomeP welcomePanel;
    private final LoginP loginPanel;
    private final SignupP signupPanel;
    private final UnregisteredMainP unregisteredMainPanel;
    private final RegisteredMainP registeredMainPanel;
    private final EmployeeMainP employeeMainPanel;
    private final ManagerMainP managerMainPanel;
    private final SearchPanel searchPanel;
    //private final GridBagConstraints gbc;
    private final JPanel cards;
    private final JPanel banners;
    private final Map<String, JPanel> allPanels = new HashMap<>();
    private String lastShownPanel;
    private String currentShownPanel;
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

        unregisteredMainPanel = new UnregisteredMainP(this);
        registeredMainPanel = new RegisteredMainP();
        employeeMainPanel = new EmployeeMainP(this);
        managerMainPanel = new ManagerMainP();
        searchPanel = new SearchPanel();

        BannerUnregistered bannerUnregisteredPanel = new BannerUnregistered();
        BannerRegistered bannerRegisteredPanel = new BannerRegistered();
        BannerEmployee bannerEmployeePanel = new BannerEmployee();
        BannerManager bannerManagerPanel = new BannerManager();

        /* Model */
        Store model = Store.getInstance();

        /* Controllers */
        WelcomeC welcomeController = new WelcomeC(this, model);
        LoginC loginController = new LoginC(this, model);
        SignupC signupController = new SignupC(this, model);

        new UnregisteredMainC(this, model, (UnregisteredClient) this.mainUser);
        RegisteredMainC registeredMainController = new RegisteredMainC(this, model);
        EmployeeMainC employeeMainController = new EmployeeMainC(this, model);
        ManagerMainC managerMainController = new ManagerMainC(this, model);

        new BannerUnregisteredC(bannerUnregisteredPanel, this);
        new BannerRegisteredC(bannerRegisteredPanel, this);
        new BannerEmployee();
        new BannerManager();

        /* Configure controllers' views */
        loginPanel.setController(loginController);
        signupPanel.setController(signupController);

        registeredMainPanel.setController(registeredMainController);
        employeeMainPanel.setController(employeeMainController);
        managerMainPanel.setController(managerMainController);

        //bannerUnregisteredPanel.setController(bannerUnregisteredController);

        /* Add views to main window */
        ImagePanel bgPanel = new ImagePanel(".\\resources\\app\\background.png");
        bgPanel.setLayout(new BorderLayout());
        this.setContentPane(bgPanel);

        Container container = this.getContentPane();
        container.setLayout(new BorderLayout());
        //container.setBackground(new Color(246, 243, 238)); // Beige
//        gbc = new GridBagConstraints();
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//
//        gbc.weightx = 1.0;
//        gbc.weighty = 1.0;
//        gbc.fill = GridBagConstraints.BOTH;

        banners = new JPanel(new CardLayout());
        banners.setOpaque(false);
        container.add(banners, BorderLayout.NORTH);

        cards = new JPanel(new CardLayout());
        cards.setOpaque(false);
        container.add(cards, BorderLayout.CENTER);

        addCard(welcomePanel, "WELCOME", welcomeController);
        addCard(loginPanel, "LOGIN");
        addCard(signupPanel, "SIGNUP");
        addCard(unregisteredMainPanel, "UNREGISTERED_MAIN");
        addCard(registeredMainPanel, "REGISTERED_MAIN");
        addCard(employeeMainPanel, "EMPLOYEE_MAIN");
        addCard(managerMainPanel, "MANAGER_MAIN");

        addBanner(bannerUnregisteredPanel, "BANNER_UNREGISTERED");
        addBanner(bannerRegisteredPanel, "BANNER_REGISTERED");
        addBanner(bannerEmployeePanel, "BANNER_EMPLOYEE");
        addBanner(bannerManagerPanel, "BANNER_MANAGER");

        /* Main panel */
        bannerUnregisteredPanel.setVisible(true);
        welcomePanel.setVisible(true); // Es el primer panel que aparece, creo que el resto se inicializan a "false"
        lastShownPanel = "WELCOME";
        currentShownPanel = "WELCOME";

//        RegisteredClient rc = new RegisteredClient("taha", "10282634M", "password", true);
//        rc.addProductWallet(new SecondHandProduct("Cool hamster huh",
//                "The greatest hamster you'll ever see, made from clay from the artic forest if that even " +
//                "exists blablabla don't fuck this description up. Keep testing oh poor lad can't wrap text can't you " +
//                "believe it", ".\\resources\\hamster.jpg", ProductType.FIGURINE, rc));
//        RegisteredSecondHandP test =
//                new RegisteredSecondHandP(this, Store.getInstance().getSecondHandProductList().getFirst(),
//                        "Add to Offer");
//        addCard(test, "TEST");
//        test.setVisible(true);


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
    private void addBanner(JPanel newView, String constraints) {
        banners.add(newView, constraints);
        newView.setVisible(false);
        newView.setOpaque(true);
        allPanels.put(constraints, newView);
    }

    public void changeVisibleCard(String cardName) {
        System.out.println("Showing: " + cardName);
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, cardName);
        getViewFromName(cardName).requestFocusInWindow();
        lastShownPanel = currentShownPanel;
        currentShownPanel = cardName;
    }

    public void goBack() {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, lastShownPanel);
    }

    public void changeVisibleBanner(String cardName) {
        CardLayout cl = (CardLayout) (banners.getLayout());
        cl.show(banners, cardName);
    }

    public void addCard(JPanel newView, String constraints) {
        cards.add(newView, constraints);
        newView.setVisible(false);
        newView.setOpaque(false);
        allPanels.put(constraints, newView);
    }

    public void addCard(ControllableJPanel newView, String constraints, ActionListener controller) {
        newView.setController(controller);
        addCard(newView, constraints);
    }

    public void changeCurrentUser(User user) {
        this.mainUser = user;
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

    public JPanel getViewFromName(String name) {
        return allPanels.get(name);
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