package controller.managerControllers;

import controller.Controller;
import model.store.Statistics;
import model.store.Store;
import view.App;
import view.managerPanels.ManagerStatisticsP;

import javax.swing.*;
import java.time.Month;

public class ManagerStatisticsC implements Controller {
    private final ManagerStatisticsP view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public ManagerStatisticsC(App frame, Store model, ManagerStatisticsP view) {
        this.frame = frame;
        this.model = model;
        this.view = view;

        initializeActions();
    }

    @Override
    public void initializeActions() {
        view.getProductBySales().addActionListener(e -> {
            view.setStoreProductList(Statistics.getINSTANCE().getProductsBySales());
            view.showProductBySales();
        });

        view.getClientByOrders().addActionListener(e -> {
            view.setClientsAndNums(Statistics.getINSTANCE().getUserAndOrders());
            view.showClientByOrders();
        });

        view.getClientByExchanges().addActionListener(e -> {
            view.setClientsAndNums(Statistics.getINSTANCE().getUserAndExchanges());
            view.showClientByExchanges();
        });

        view.getRevenueByMonth().addActionListener(e -> {
            view.setMonthsAndNums(Statistics.getINSTANCE().getRevenueByMonth());
            view.showRevenueByMonth();
        });

        view.getCategoriesByRevenue().addActionListener(e -> {
            view.setCategoriesAndNums(Statistics.getINSTANCE().getRevenueAllCategories());
            view.showCategoriesByRevenue();
        });

        view.getProductBySalesWithPer().addActionListener(e -> {
            view.setProductsAndNums(Statistics.getINSTANCE().getProductsTotalPercentage());
            view.showProductBySalesWithPer();
        });

        view.getProductBySalesWithPerOnMonth().addActionListener(e -> {
            String input = JOptionPane.showInputDialog("Enter the desired month:");
            view.setProductsAndNums(
                    Statistics.getINSTANCE().getProductsTotalPercentage(Month.valueOf(input.toUpperCase())));
            view.showProductBySalesWithPerOnMonth();
        });
    }
}