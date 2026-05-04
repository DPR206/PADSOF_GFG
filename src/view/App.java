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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;

/**
 * It implements the app's view
 * @author Sofia C.L., Duna P.R. and Ana O.R.
 * @version 1.0
 */
public class App extends JFrame {
    private final LoginP loginPanel;
    private final SignupP signupPanel;
    private final UnregisteredMainP unregisteredMainPanel;
    private final RegisteredMainP registeredMainPanel;
    private final EmployeeMainP employeeMainPanel;
    private final ManagerMainP managerMainPanel;
    private final WelcomeP welcomePanel;
    private final SearchPanel searchPanel;
    //private final BrowseStoreP browseStorePanel;
    // Aquí se declaran todos los paneles de vista como atributos
    private User mainUser = new UnregisteredClient(true);
    private List<StoreProduct> products = Store.getInstance().getStoreProductList();
    private List<Pack> packs = Store.getInstance().getPacks();

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public App() throws IOException, BadLocationException {
        super("Gifts for Geeks"); /* JFrame's title */
        this.setIconImage(new ImageIcon(".\\resources\\logo.png").getImage());

        /* Views */
        loginPanel = new LoginP();
        signupPanel = new SignupP();
        unregisteredMainPanel = new UnregisteredMainP((UnregisteredClient) this.mainUser, this);
        registeredMainPanel = new RegisteredMainP();
        employeeMainPanel = new EmployeeMainP(this);
        managerMainPanel = new ManagerMainP();
        welcomePanel = new WelcomeP();
        searchPanel = new SearchPanel();
        //browseStorePanel = new BrowseStoreP(this);

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
        //BrowseStoreC browseStoreController = new BrowseStoreC(this, model);

        /* Configure controllers' views */
        loginPanel.setController(loginController);
        signupPanel.setController(signupController);
        unregisteredMainPanel.setController(unregisteredMainController);
        registeredMainPanel.setController(registeredMainController);
        employeeMainPanel.setController(employeeMainController);
        managerMainPanel.setController(managerMainController);
        welcomePanel.setController(welcomeController);
        //browseStorePanel.setController(browseStoreController);

        /* Add views to main window */
        ImagePanel bgPanel = new ImagePanel(".\\resources\\background.png");
        bgPanel.setLayout(new BorderLayout());
        this.setContentPane(bgPanel);

        Container container = this.getContentPane();
        container.setLayout(new GridBagLayout());
        //container.setBackground(new Color(246, 243, 238)); // Beige
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
        container.add(registeredMainPanel, gbc);
        registeredMainPanel.setVisible(false);
        container.add(employeeMainPanel, gbc);
        employeeMainPanel.setVisible(false);
        managerMainPanel.setVisible(false);
        container.add(managerMainPanel, gbc);
        welcomePanel.setVisible(false);
        container.add(welcomePanel, gbc);
        welcomePanel.setVisible(true); // Es el primer panel que aparece, creo que el resto se inicializan a "false"
        //container.add(browseStorePanel, gbc);
        //browseStorePanel.setVisible(false);
        /*new RegisteredClient("ama", "51721876M", "pwd", true);
        RegisteredClient ama = (RegisteredClient) Store.getInstance().getUsers().get("ama");
        ama.addProductWallet(
                new SecondHandProduct("Hamster yay", "a", ".\\resources\\hamster.jpg", ProductType.FIGURINE, ama));
        ama.addProductWallet(
                new SecondHandProduct("Hamster yippie", "a", ".\\resources\\hamster.jpg", ProductType.FIGURINE, ama));
        ama.addProductWallet(
                new SecondHandProduct("Hamster :)", "a", ".\\resources\\hamster.jpg", ProductType.FIGURINE, ama));
        ama.addProductWallet(
                new SecondHandProduct("Hamster :3", "a", ".\\resources\\hamster.jpg", ProductType.FIGURINE, ama));
        ama.addProductWallet(
                new SecondHandProduct("Hamster ey", "a", ".\\resources\\hamster.jpg", ProductType.FIGURINE, ama));
        ama.addProductWallet(
                new SecondHandProduct("Hamster hamster", "a", ".\\resources\\hamster.jpg", ProductType.FIGURINE, ama));
        BrowseSomeonesWalletP browseWallet = new BrowseSomeonesWalletP(this, ama);
        container.add(browseWallet, gbc);
        browseWallet.setController(new BrowseWalletC(this, model, browseWallet));*/

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

    public void changeCurrentUser(User user) {
        this.mainUser = user;
    }

    public class ImagePanel extends JPanel {
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
    
    public List<Pack> getPackList(){
    	return this.packs;
    }
    
    public void setPackList(List<Pack> packs) {
    	this.packs = packs;
    }
}