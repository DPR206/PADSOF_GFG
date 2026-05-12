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
    private List<StoreProduct> storeProductList = new ArrayList<>();
    private HashMap<RegisteredClient, Integer> clientsAndNums = new HashMap<>();
    private HashMap<Month, Double> monthsAndNums = new HashMap<>();
    private HashMap<Category, Double> categoriesAndNums = new HashMap<>();
    private HashMap<StoreProduct, String> productsAndNums = new HashMap<>();
    private List<String> shownData = new ArrayList<>();
    private JScrollPane scroll = new JScrollPane(shownStatistics);

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public ManagerStatisticsP() {
        this.setLayout(cardLayout);

        paintEverything();
    }

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

    public JButton getCategoriesByRevenue() {
        return categoriesByRevenue;
    }

    public JButton getClientByExchanges() {
        return clientByExchanges;
    }

    public JButton getClientByOrders() {
        return clientByOrders;
    }

    public JButton getProductBySales() {
        return productBySales;
    }

    public JButton getProductBySalesWithPer() {
        return productBySalesWithPer;
    }

    public JButton getProductBySalesWithPerOnMonth() {
        return productBySalesWithPerOnMonth;
    }

    public JButton getRevenueByMonth() {
        return revenueByMonth;
    }

    public List<StoreProduct> getStoreProductList() {
        return storeProductList;
    }

    public void setStoreProductList(List<StoreProduct> newStoreProductList) {
        this.storeProductList = newStoreProductList;
    }

    public void setCategoriesAndNums(HashMap<Category, Double> newCategoriesAndNums) {
        this.categoriesAndNums = newCategoriesAndNums;
    }

    public void setClientsAndNums(HashMap<RegisteredClient, Integer> newClientsAndNums) {
        this.clientsAndNums = newClientsAndNums;
    }

    public void setMonthsAndNums(HashMap<Month, Double> newMonthsAndNums) {
        this.monthsAndNums = newMonthsAndNums;
    }

    public void setProductsAndNums(HashMap<StoreProduct, String> newProductsAndNums) {
        this.productsAndNums = newProductsAndNums;
    }
}