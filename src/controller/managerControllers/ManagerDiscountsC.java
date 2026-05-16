package controller.managerControllers;

import controller.Controller;
import controller.browserControllers.*;
import model.discount.*;
import model.product.*;
import model.store.Store;
import view.App;
import view.managerPanels.ManagerDiscountsP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class ManagerDiscountsC implements Controller {
    private final ManagerDiscountsP view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public ManagerDiscountsC(App frame, Store model, ManagerDiscountsP view) throws BadLocationException {
        this.frame = frame;
        this.model = model;
        this.view = view;

        initializeActions();
    }

    public void updateControllers() {
        new BrowseDiscountsC(frame, view.getBrowseDiscountsP(), model, view);
        new BrowseCategoriesDiscC(frame, view.getBrowseCategoriesDiscP(), model, view);
        new BrowsePacksDiscC(frame, view.getBrowsePacksDiscP(), model, view);
        new BrowseStoreProductsDiscC(frame, view.getBrowseStoreProductsDiscP(), model, view, false);
        new BrowseStoreProductsDiscC(frame, view.getBrowseStoreProductsDiscPForGift(), model, view, true);
    }

    @Override
    public void initializeActions() throws BadLocationException {
        view.getBrowseDiscountsP().setItemList(model.getDiscounts());
        view.getBrowseCategoriesDiscP().setItemList(model.getCategoryList());
        view.getBrowsePacksDiscP().setItemList(model.getPacks());
        view.getBrowseStoreProductsDiscP().setItemList(model.getStoreProductList());
        view.getBrowseStoreProductsDiscPForGift().setItemList(model.getStoreProductList());

        updateControllers();

        view.getCoverage().addActionListener(e -> view.updateDiscountPanels());

        view.getDiscountType().addActionListener(e -> view.updateDiscountPanels());

        view.getOverWholeStore().addActionListener(e -> view.updateDiscountPanels());

        view.getGiftButton().addActionListener(e -> {
            try {
                chooseGift();
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });

        view.getSelectItems().addActionListener(e -> {
            switch (view.getDiscountCoverageCmbBoxSelected()) {
                case PRODUCT -> chooseStoreProducts();
                case PACK -> choosePacks();
                case CATEGORY -> chooseCategories();
            }
        });

        view.getCreateBtn().addActionListener(e -> {
            LocalDateTime startDate = null;
            LocalDateTime endDate = null;
            try {
                startDate = LocalDateTime.parse(view.getStartingDateField().getText());
                endDate = LocalDateTime.parse(view.getEndingDateField().getText());
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid date (<YYYY-MM-DD>T<HH:MM:SS>)", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

            Discount discount = null;

            switch (view.getDiscountTypeCmbBoxSelected()) {
                case FIXED_PERCENTAGE -> {
                    if (view.getFirstField() != null && view.getStartingDateField() != null &&
                        view.getEndingDateField() != null) {

                        double percentage = Double.parseDouble(view.getFirstField().getText());

                        switch (view.getDiscountCoverageCmbBoxSelected()) {
                            case PRODUCT -> {
                                if (view.getOverWholeStore().isSelected()) {
                                    discount = new ProductFixedPercentage(startDate, endDate, percentage,
                                            view.getSelectedProductsList().toArray(new StoreProduct[0]));
                                } else {
                                    discount = new ProductFixedPercentage(startDate, endDate, percentage, true);
                                }
                            }
                            case PACK -> discount = new PackFixedPercentage(startDate, endDate, percentage,
                                    view.getSelectedPacksList().toArray(new Pack[0]));
                            case CATEGORY -> discount = new CategoryFixedPercentage(startDate, endDate, percentage,
                                    view.getSelectedCategoriesList().toArray(new Category[0]));
                        }
                    }
                }

                case GIFT -> {
                    if (view.getFirstField() != null && view.getGift() != null && view.getStartingDateField() != null &&
                        view.getEndingDateField() != null) {

                        double spendingThreshold = Double.parseDouble(view.getFirstField().getText());
                        StoreProduct gift = view.getGift();

                        switch (view.getDiscountCoverageCmbBoxSelected()) {
                            case PRODUCT -> {
                                if (view.getOverWholeStore().isSelected()) {
                                    discount = new ProductGift(startDate, endDate, spendingThreshold, gift, true);
                                } else {
                                    discount = new ProductGift(startDate, endDate, spendingThreshold, gift,
                                            view.getSelectedProductsList().toArray(new StoreProduct[0]));
                                }
                            }
                            case PACK -> discount = new PackGift(startDate, endDate, spendingThreshold, gift,
                                    view.getSelectedPacksList().toArray(new Pack[0]));
                            case CATEGORY -> discount = new CategoryGift(startDate, endDate, spendingThreshold, gift,
                                    view.getSelectedCategoriesList().toArray(new Category[0]));
                        }
                    }
                }

                case QUANTITY -> {
                    if (view.getFirstField() != null && view.getSecondField() != null &&
                        view.getStartingDateField() != null && view.getEndingDateField() != null) {

                        double deduction = Double.parseDouble(view.getFirstField().getText());
                        int numThreshold = Integer.parseInt(view.getSecondField().getText());

                        switch (view.getDiscountCoverageCmbBoxSelected()) {
                            case PRODUCT -> {
                                if (view.getOverWholeStore().isSelected()) {
                                    discount = new ProductQuantity(startDate, endDate, numThreshold, deduction, true);
                                } else {
                                    discount = new ProductQuantity(startDate, endDate, numThreshold, deduction,
                                            view.getSelectedProductsList().toArray(new StoreProduct[0]));
                                }
                            }
                            case PACK -> discount = new PackQuantity(startDate, endDate, numThreshold, deduction,
                                    view.getSelectedPacksList().toArray(new Pack[0]));
                            case CATEGORY -> discount =
                                    new CategoryQuantity(startDate, endDate, numThreshold, deduction,
                                            view.getSelectedCategoriesList().toArray(new Category[0]));
                        }
                    }
                }

                case VOLUME -> {
                    if (view.getFirstField() != null && view.getSecondField() != null &&
                        view.getStartingDateField() != null && view.getEndingDateField() != null) {

                        double deduction = Double.parseDouble(view.getFirstField().getText());
                        int spendingThreshold = Integer.parseInt(view.getSecondField().getText());

                        switch (view.getDiscountCoverageCmbBoxSelected()) {
                            case PRODUCT -> {
                                if (view.getOverWholeStore().isSelected()) {
                                    discount =
                                            new ProductVolume(startDate, endDate, spendingThreshold, deduction, true);
                                } else {
                                    discount = new ProductVolume(startDate, endDate, spendingThreshold, deduction,
                                            view.getSelectedProductsList().toArray(new StoreProduct[0]));
                                }
                            }
                            case PACK -> discount = new PackVolume(startDate, endDate, spendingThreshold, deduction,
                                    view.getSelectedPacksList().toArray(new Pack[0]));
                            case CATEGORY -> discount =
                                    new CategoryVolume(startDate, endDate, spendingThreshold, deduction,
                                            view.getSelectedCategoriesList().toArray(new Category[0]));
                        }
                    }
                }
            }

            if (discount == null) {
                JOptionPane.showMessageDialog(frame, "Uh oh something went wrong when creating the discount", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Discount created successfully", "Discount created",
                        JOptionPane.INFORMATION_MESSAGE);
            }

            view.getFirstField().setText("");
            view.getSecondField().setText("");
            view.getStartingDateField().setText("");
            view.getEndingDateField().setText("");
            view.changeToBrowseDiscountsView();
            updateControllers();
        });
    }

    private void chooseGift() throws BadLocationException {
        view.changeToChooseGiftView();
        updateControllers();
    }

    private void chooseStoreProducts() {
        view.changeToChooseProductsView();
        updateControllers();
    }

    private void choosePacks() {
        view.changeToChoosePacksView();
        updateControllers();
    }

    private void chooseCategories() {
        view.changeToChooseCategoriesView();
        updateControllers();
    }
}