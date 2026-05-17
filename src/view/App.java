package view;

import controller.accessControllers.*;
import controller.bannerControllers.*;
import controller.clientControllers.RegisteredMainC;
import controller.clientControllers.UnregisteredMainC;
import controller.employeeControllers.EmployeeMainC;
import controller.managerControllers.ManagerMainC;
import model.product.SecondHandProduct;
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
    private final JPanel cards;
    private final JPanel banners;
    private final Map<String, JPanel> allPanels = new HashMap<>();
    private final BannerUnregistered bannerUnregisteredPanel;
    private final BannerRegistered bannerRegisteredPanel;
    private final BannerEmployee bannerEmployeePanel;
    private final BannerManager bannerManagerPanel;
    private final List<String> lastShownPanels = new ArrayList<>();
    private final Store model = Store.getInstance();
    private final ArrayList<SecondHandProduct> theirProducts = new ArrayList<>();
    private final ArrayList<SecondHandProduct> myProducts = new ArrayList<>();
    private String currentShownPanel;
    private User mainUser = new UnregisteredClient(true);

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new App.
     * @throws IOException          the io exception
     * @throws BadLocationException the bad location exception
     */
    public App() throws IOException, BadLocationException {
        super("Gifts for Geeks"); /* JFrame's title */
        this.setIconImage(new ImageIcon(".\\resources\\app\\logo.png").getImage());

        /* Views */
        welcomePanel = new WelcomeP();
        loginPanel = new LoginP();
        signupPanel = new SignupP();
        unregisteredMainPanel = new UnregisteredMainP();
        registeredMainPanel = new RegisteredMainP();
        employeeMainPanel = new EmployeeMainP(this);
        managerMainPanel = new ManagerMainP();
        searchPanel = new SearchPanel();

        bannerUnregisteredPanel = new BannerUnregistered();
        bannerRegisteredPanel = new BannerRegistered();
        bannerEmployeePanel = new BannerEmployee();
        bannerManagerPanel = new BannerManager();

        /* Add views to main window */
        ImagePanel bgPanel = new ImagePanel(".\\resources\\app\\background.png");
        bgPanel.setLayout(new BorderLayout());
        this.setContentPane(bgPanel);

        Container container = this.getContentPane();
        container.setLayout(new BorderLayout());

        banners = new JPanel(new CardLayout());
        banners.setOpaque(false);
        container.add(banners, BorderLayout.NORTH);

        cards = new JPanel(new CardLayout());
        cards.setOpaque(false);
        container.add(cards, BorderLayout.CENTER);

        addCard(welcomePanel, "WELCOME");
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

        /* Controllers */
        new WelcomeC(this, model);
        new LoginC(this, model);
        new SignupC(this, model);

        new UnregisteredMainC(this, model);
        new RegisteredMainC(this, model);

        new EmployeeMainC(this, model);
        new ManagerMainC(this, model);

        new BannerUnregisteredC(bannerUnregisteredPanel, this, model);
        new BannerRegisteredC(bannerRegisteredPanel, this, model);
        new BannerEmployeeC(bannerEmployeePanel, this);
        new BannerManagerC(bannerManagerPanel, this);

        /* Main panel */
        changeVisibleBanner("BANNER_UNREGISTERED");
        currentShownPanel = "WELCOME";
        changeVisibleCard("WELCOME");

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

        this.pack();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/
    /**
     * Add product from their wallet.
     * @param product the product
     */
    public void addProductFromTheirWallet(SecondHandProduct product) {
        theirProducts.add(product);
        System.out.println("Their products: " + theirProducts);
    }

    /**
     * Add product from my wallet.
     * @param product the product
     */
    public void addProductFromMyWallet(SecondHandProduct product) {
        myProducts.add(product);
        System.out.println("My products: " + myProducts);
    }

    private void addBanner(JPanel newView, String constraints) {
        banners.add(newView, constraints);
        newView.setOpaque(true);
        allPanels.put(constraints, newView);
    }

    /**
     * Change visible card.
     * @param cardName the card name
     * @throws BadLocationException the bad location exception
     */
    public void changeVisibleCard(String cardName) throws BadLocationException {
        System.out.println("Changing visible card: " + cardName);
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, cardName);
        getViewFromName(cardName).requestFocusInWindow();

        if (currentShownPanel != null && !currentShownPanel.equals(cardName)) {
            lastShownPanels.add(currentShownPanel);
        }
        currentShownPanel = cardName;

        updateBanners();
    }

    /**
     * Update banners.
     * @throws BadLocationException the bad location exception
     */
    public void updateBanners() throws BadLocationException {
        new BannerUnregisteredC(bannerUnregisteredPanel, this, model);
        new BannerRegisteredC(bannerRegisteredPanel, this, model);
        new BannerEmployeeC(bannerEmployeePanel, this);
        new BannerManagerC(bannerManagerPanel, this);
    }

    /**
     * Go back.
     * @throws BadLocationException the bad location exception
     */
    public void goBack() throws BadLocationException {
        if (!lastShownPanels.isEmpty()) {
            String previousPanel = lastShownPanels.removeLast();
            System.out.println("Going back to card: " + previousPanel);

            CardLayout cl = (CardLayout) (cards.getLayout());
            cl.show(cards, previousPanel);
            getViewFromName(previousPanel).requestFocusInWindow();

            currentShownPanel = previousPanel;

            updateBanners();
        }
    }

    /**
     * Change visible banner.
     * @param cardName the card name
     */
    public void changeVisibleBanner(String cardName) {
        CardLayout cl = (CardLayout) (banners.getLayout());
        cl.show(banners, cardName);

        banners.revalidate();
        banners.repaint();
    }

    /**
     * Add card.
     * @param newView     the new view
     * @param constraints the constraints
     */
    public void addCard(JPanel newView, String constraints) {
        cards.add(newView, constraints);
        //newView.setVisible(false);
        newView.setOpaque(false);
        allPanels.put(constraints, newView);
    }

    /**
     * Update view.
     * @param cardName   the card name
     * @param bannerName the banner name
     * @throws BadLocationException the bad location exception
     */
    public void updateView(String cardName, String bannerName) throws BadLocationException {
        changeVisibleCard(cardName);
        changeVisibleBanner(bannerName);
    }

    /**
     * Change current user.
     * @param user the user
     */
    public void changeCurrentUser(User user) {
        this.lastShownPanels.clear();
        switch (user.getType()) {
            case UNREGISTERED_CLIENT -> currentShownPanel = "UNREGISTERED_MAIN";
            case REGISTERED_CLIENT -> currentShownPanel = "REGISTERED_MAIN";
            case EMPLOYEE -> currentShownPanel = "EMPLOYEE_MAIN";
            case MANAGER -> currentShownPanel = "MANAGER_MAIN";
            default -> currentShownPanel = "WELCOME";
        }
        this.mainUser = user;
    }

    /**
     * The type Image panel.
     */
    public class ImagePanel extends JPanel {
        private static final long serialVersionUID = 1L;
        private final Image backgroundImage;

        /**
         * Instantiates a new Image panel.
         * @param filePath the file path
         */
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
    /**
     * It gets the app
     * @return the app
     */
    public App getApp() {
        return this;
    }

    /**
     * It gets the employee main panel
     * @return the employee main panel
     */
    public EmployeeMainP getEmployeeMainPanel() {
        return employeeMainPanel;
    }

    /**
     * It gets the last shown panels
     * @return the last shown panels
     */
    public List<String> getLastShownPanels() {
        return lastShownPanels;
    }

    /**
     * It gets the login panel
     * @return the login panel
     */
    public LoginP getLoginPanel() {
        return loginPanel;
    }

    /**
     * It gets the manager main panel
     * @return the manager main panel
     */
    public ManagerMainP getManagerMainPanel() {
        return managerMainPanel;
    }

    public ArrayList<SecondHandProduct> getMyProducts() {
        return myProducts;
    }

    /**
     * It gets the registered main panel
     * @return the registered main panel
     */
    public RegisteredMainP getRegisteredMainPanel() {
        return registeredMainPanel;
    }

    /**
     * It gets the search panel
     * @return the search panel
     */
    public SearchPanel getSearchPanel() {
        return searchPanel;
    }

    /**
     * It gets the signup panel
     * @return the signup panel
     */
    public SignupP getSignupPanel() {
        return signupPanel;
    }

    public ArrayList<SecondHandProduct> getTheirProducts() {
        return theirProducts;
    }

    /**
     * It gets the unregistered main panel
     * @return the unregistered main panel
     */
    public UnregisteredMainP getUnregisteredMainPanel() {
        return unregisteredMainPanel;
    }

    /**
     * It gets the user
     * @return the user
     */
    public User getUser() {
        return this.mainUser;
    }

    /**
     * It gets the view from name
     * @param name the name
     * @return the view from name
     */
    public JPanel getViewFromName(String name) {
        return allPanels.get(name);
    }

    /**
     * It gets the welcome panel
     * @return the welcome panel
     */
    public WelcomeP getWelcomePanel() {
        return welcomePanel;
    }

    /**
     * It sets the unregistered client
     * @param u the u
     */
    public void setUnregisteredClient(UnregisteredClient u) {
        this.mainUser = u;
    }
}