package view.managerPanels;

import model.product.Category;
import model.product.StoreProduct;
import model.user.RegisteredClient;

import javax.swing.*;
import java.awt.*;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.*;
import java.util.List;

/**
 * The type Manager statistics p.
 * @author Ana O.R.
 * @version 1.0
 */
public class ManagerStatisticsP extends JPanel {
    private final JButton productBySales = new JButton("Product By Sales");
    private final JButton clientByOrders = new JButton("Client By Orders");
    private final JButton clientByExchanges = new JButton("Client By Exchanges");
    private final JButton revenueByMonth = new JButton("Revenue By Month");
    private final JButton categoriesByRevenue = new JButton("Categories By Revenue");
    private final JButton productBySalesWithPer = new JButton("Product By Sales with Percentage");
    private final JButton productBySalesWithPerOnMonth =
            new JButton("Product By Sales with Percentage On A Certain Month");
    private final CardLayout cardLayout = new CardLayout();
    private final JList<String> shownStatistics = new JList<>();
    private final JPanel seeStatistics = new JPanel();
    private final List<String> shownData = new ArrayList<>();
    private final JScrollPane scroll = new JScrollPane(shownStatistics);
    private List<StoreProduct> storeProductList = new ArrayList<>();
    private HashMap<RegisteredClient, Integer> clientsAndNums = new HashMap<>();
    private HashMap<Month, Double> monthsAndNums = new HashMap<>();
    private HashMap<Category, Double> categoriesAndNums = new HashMap<>();
    private HashMap<StoreProduct, String> productsAndNums = new HashMap<>();

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Manager statistics p.
     */
    public ManagerStatisticsP() {
        this.setLayout(cardLayout);

        paintEverything();
    }

    /**
     * Paint everything.
     */
    public void paintEverything() {
        this.removeAll();

        JPanel buttons = new JPanel(new GridLayout(2, 4));
        buttons.add(new JLabel("Choose the statistic you want you generate"));
        buttons.add(productBySales);
        buttons.add(clientByOrders);
        buttons.add(clientByExchanges);
        buttons.add(revenueByMonth);
        buttons.add(categoriesByRevenue);
        buttons.add(productBySalesWithPer);
        buttons.add(productBySalesWithPerOnMonth);

        this.add(buttons, "BUTTONS");
        cardLayout.show(this, "BUTTONS");

        this.add(seeStatistics, "SEE_STATISTICS");
        shownStatistics.setFixedCellWidth(1000);
        shownStatistics.setDragEnabled(false);
        shownStatistics.setEnabled(false);
        shownStatistics.setForeground(Color.BLACK);

        this.revalidate();
        this.repaint();
    }

    /**
     * Show product by sales.
     */
    public void showProductBySales() {
        seeStatistics.removeAll();
        shownData.clear();
        int index = 1;
        for (StoreProduct storeProduct : storeProductList) {
            shownData.add(index + "º " + storeProduct.getName());
            index++;
        }
        shownStatistics.setListData(shownData.toArray(shownData.toArray(new String[0])));
        seeStatistics.add(scroll);
        cardLayout.show(this, "SEE_STATISTICS");
    }

    /**
     * Show client by orders.
     */
    public void showClientByOrders() {
        seeStatistics.removeAll();
        shownData.clear();
        int index = 1;
        for (RegisteredClient registeredClient : clientsAndNums.keySet()) {
            shownData.add(index + "º " + registeredClient.getUserName() + " (" + clientsAndNums.get(registeredClient) +
                          " orders)");
            index++;
        }
        shownStatistics.setListData(shownData.toArray(shownData.toArray(new String[0])));
        seeStatistics.add(scroll);
        cardLayout.show(this, "SEE_STATISTICS");
    }

    /**
     * Show client by exchanges.
     */
    public void showClientByExchanges() {
        seeStatistics.removeAll();
        shownData.clear();
        int index = 1;
        for (RegisteredClient registeredClient : clientsAndNums.keySet()) {
            shownData.add(index + "º " + registeredClient.getUserName() + " (" + clientsAndNums.get(registeredClient) +
                          " exchanges)");
            index++;
        }
        shownStatistics.setListData(shownData.toArray(shownData.toArray(new String[0])));
        seeStatistics.add(scroll);
        cardLayout.show(this, "SEE_STATISTICS");
    }

    /**
     * Show revenue by month.
     */
    public void showRevenueByMonth() {
        seeStatistics.removeAll();
        shownData.clear();
        for (Month month : monthsAndNums.keySet()) {
            shownData.add(month.getDisplayName(TextStyle.FULL, Locale.UK) + " -> " + monthsAndNums.get(month) + "€");
            // NOTE: Están puestos los meses en el formato de Reino Unido porque la app es en inglés
        }
        shownStatistics.setListData(shownData.toArray(shownData.toArray(new String[0])));
        seeStatistics.add(scroll);
        cardLayout.show(this, "SEE_STATISTICS");
    }

    /**
     * Show categories by revenue.
     */
    public void showCategoriesByRevenue() {
        seeStatistics.removeAll();
        shownData.clear();
        int index = 1;
        for (Category category : categoriesAndNums.keySet()) {
            shownData.add(index + "º " + category.getName() + " (" + categoriesAndNums.get(category) + "€)");
            index++;
        }
        shownStatistics.setListData(shownData.toArray(shownData.toArray(new String[0])));
        seeStatistics.add(scroll);
        cardLayout.show(this, "SEE_STATISTICS");
    }

    /**
     * Show product by sales with per.
     */
    public void showProductBySalesWithPer() {
        seeStatistics.removeAll();
        shownData.clear();
        int index = 1;
        for (StoreProduct product : productsAndNums.keySet()) {
            shownData.add(index + "º " + product.getName() + " (" + productsAndNums.get(product) + " sales)");
            index++;
        }
        shownStatistics.setListData(shownData.toArray(shownData.toArray(new String[0])));
        seeStatistics.add(scroll);
        cardLayout.show(this, "SEE_STATISTICS");
    }

    /**
     * Show product by sales with per on month.
     */
    public void showProductBySalesWithPerOnMonth() {
        seeStatistics.removeAll();
        shownData.clear();
        int index = 1;
        for (StoreProduct product : productsAndNums.keySet()) {
            shownData.add(index + "º " + product.getName() + " (" + productsAndNums.get(product) + " sales)");
            index++;
        }
        shownStatistics.setListData(shownData.toArray(shownData.toArray(new String[0])));
        seeStatistics.add(scroll);
        cardLayout.show(this, "SEE_STATISTICS");
    }

    /**
     * It gets the categories by revenue
     * @return the categories by revenue
     */
    public JButton getCategoriesByRevenue() {
        return categoriesByRevenue;
    }

    /**
     * It gets the client by exchanges
     * @return the client by exchanges
     */
    public JButton getClientByExchanges() {
        return clientByExchanges;
    }

    /**
     * It gets the client by orders
     * @return the client by orders
     */
    public JButton getClientByOrders() {
        return clientByOrders;
    }

    /**
     * It gets the product by sales
     * @return the product by sales
     */
    public JButton getProductBySales() {
        return productBySales;
    }

    /**
     * It gets the product by sales with per
     * @return the product by sales with per
     */
    public JButton getProductBySalesWithPer() {
        return productBySalesWithPer;
    }

    /**
     * It gets the product by sales with per on month
     * @return the product by sales with per on month
     */
    public JButton getProductBySalesWithPerOnMonth() {
        return productBySalesWithPerOnMonth;
    }

    /**
     * It gets the revenue by month
     * @return the revenue by month
     */
    public JButton getRevenueByMonth() {
        return revenueByMonth;
    }

    /**
     * It gets the store product list
     * @return the store product list
     */
    public List<StoreProduct> getStoreProductList() {
        return storeProductList;
    }

    /**
     * It sets the store product list
     * @param newStoreProductList the new store product list
     */
    public void setStoreProductList(List<StoreProduct> newStoreProductList) {
        this.storeProductList = newStoreProductList;
    }

    /**
     * It sets the categories and nums
     * @param newCategoriesAndNums the new categories and nums
     */
    public void setCategoriesAndNums(HashMap<Category, Double> newCategoriesAndNums) {
        this.categoriesAndNums = newCategoriesAndNums;
    }

    /**
     * It sets the clients and nums
     * @param newClientsAndNums the new clients and nums
     */
    public void setClientsAndNums(HashMap<RegisteredClient, Integer> newClientsAndNums) {
        this.clientsAndNums = newClientsAndNums;
    }

    /**
     * It sets the months and nums
     * @param newMonthsAndNums the new months and nums
     */
    public void setMonthsAndNums(HashMap<Month, Double> newMonthsAndNums) {
        this.monthsAndNums = newMonthsAndNums;
    }

    /**
     * It sets the products and nums
     * @param newProductsAndNums the new products and nums
     */
    public void setProductsAndNums(HashMap<StoreProduct, String> newProductsAndNums) {
        this.productsAndNums = newProductsAndNums;
    }
}