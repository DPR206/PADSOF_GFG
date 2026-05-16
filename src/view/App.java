package view;

import controller.accessControllers.*;
import controller.bannerControllers.*;
import controller.clientControllers.RegisteredMainC;
import controller.clientControllers.UnregisteredMainC;
import controller.employeeControllers.EmployeeMainC;
import controller.managerControllers.ManagerMainC;
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
    private String currentShownPanel;
    private User mainUser = new UnregisteredClient(true);

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
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
    private void addBanner(JPanel newView, String constraints) {
        banners.add(newView, constraints);
        newView.setOpaque(true);
        allPanels.put(constraints, newView);
    }

    public void changeVisibleCard(String cardName) {
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

    public void updateBanners() {
        new BannerUnregisteredC(bannerUnregisteredPanel, this, model);
        new BannerRegisteredC(bannerRegisteredPanel, this, model);
        new BannerEmployeeC(bannerEmployeePanel, this);
        new BannerManagerC(bannerManagerPanel, this);
    }

    public void goBack() {
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

    public void changeVisibleBanner(String cardName) {
        CardLayout cl = (CardLayout) (banners.getLayout());
        cl.show(banners, cardName);

        banners.revalidate();
        banners.repaint();
    }

    public void addCard(JPanel newView, String constraints) {
        cards.add(newView, constraints);
        //newView.setVisible(false);
        newView.setOpaque(false);
        allPanels.put(constraints, newView);
    }

    public void updateView(String cardName, String bannerName) {
        changeVisibleCard(cardName);
        changeVisibleBanner(bannerName);
    }

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

    public class ImagePanel extends JPanel {
        private static final long serialVersionUID = 1L;
        private final Image backgroundImage;

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

    public List<String> getLastShownPanels() {
        return lastShownPanels;
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

    public JPanel getViewFromName(String name) {
        return allPanels.get(name);
    }

    public WelcomeP getWelcomePanel() {
        return welcomePanel;
    }

    public void setUnregisteredClient(UnregisteredClient u) {
        this.mainUser = u;
    }
}