package view.managerPanels;

import model.discount.DiscountCoverage;
import model.discount.DiscountType;
import model.product.*;
import view.browserPanels.*;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.util.*;
import java.util.List;

import static main.Main.brownColour;

public class ManagerDiscountsP extends JPanel {
    private final HashMap<String, DiscountType> discountTypesHashMap = new HashMap<>();
    private final HashMap<String, DiscountCoverage> coveragesHashMap = new HashMap<>();
    private final JCheckBox overWholeStore = new JCheckBox("Over Whole Store");
    private final JPanel eastPanel = new JPanel();
    private final JPanel centerPanel = new JPanel();
    private final JButton addDiscount = new JButton("Add discount");
    private final JComboBox<String> discountTypeCmbBox;
    private final JComboBox<String> coverageCmbBox;
    private final JButton selectItems = new JButton("Choose a coverage");
    private final JButton giftButton = new JButton("Select Gift");
    private final JLabel firstFieldLabel = new JLabel();
    private final JLabel secondFieldLabel = new JLabel();
    private final JTextField firstField = new JTextField();
    private final JTextField secondField = new JTextField();
    private final JTextField startingDateField = new JTextField();
    private final JTextField endingDateField = new JTextField();
    private final CardLayout cardLayout = new CardLayout();
    private final JButton createBtn = new JButton("Create discount");
    private final List<StoreProduct> selectedProductsList = new ArrayList<>();
    private final List<Pack> selectedPacksList = new ArrayList<>();
    private final List<Category> selectedCategoriesList = new ArrayList<>();
    private BrowseDiscountsP browseDiscountsP;
    private BrowseStoreProductsDiscP browseStoreProductsDiscP;
    private BrowseStoreProductsDiscP browseStoreProductsDiscPForGift;
    private BrowsePacksDiscP browsePacksDiscP;
    private BrowseCategoriesDiscP browseCategoriesDiscP;
    private StoreProduct gift = null;
    private JPanel secondDiscountPanel;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public ManagerDiscountsP() throws BadLocationException {
        this.setLayout(new BorderLayout());

        /* Init del types combo box */
        discountTypesHashMap.put("Fixed percentage", DiscountType.FIXED_PERCENTAGE);
        discountTypesHashMap.put("Gift", DiscountType.GIFT);
        discountTypesHashMap.put("Quantity", DiscountType.QUANTITY);
        discountTypesHashMap.put("Volume", DiscountType.VOLUME);
        discountTypeCmbBox = new JComboBox<>(discountTypesHashMap.keySet().toArray(new String[0]));

        /* Init del coverages combo box */
        coveragesHashMap.put("Categories", DiscountCoverage.CATEGORY);
        coveragesHashMap.put("Packs", DiscountCoverage.PACK);
        coveragesHashMap.put("Store products", DiscountCoverage.PRODUCT);
        coverageCmbBox = new JComboBox<>(coveragesHashMap.keySet().toArray(new String[0]));

        paintEverything();

        cardLayout.show(centerPanel, "BROWSE_DISCOUNTS");
    }

    public void addSelectedProduct(StoreProduct selectedProduct) {
        selectedProductsList.add(selectedProduct);
    }

    public void addSelectedPack(Pack selectedPack) {
        selectedPacksList.add(selectedPack);
    }

    public void addSelectedCategory(Category selectedCategory) {
        selectedCategoriesList.add(selectedCategory);
    }

    public void paintEverything() throws BadLocationException {
        this.removeAll();

        paintEastPanel();
        paintCenterPanel();

        this.revalidate();
        this.repaint();
    }

    public void paintCenterPanel() throws BadLocationException {
        centerPanel.setLayout(cardLayout);
        centerPanel.removeAll();

        /* Init panels */
        browseDiscountsP = new BrowseDiscountsP();
        browseStoreProductsDiscP = new BrowseStoreProductsDiscP(selectedProductsList);
        browseStoreProductsDiscPForGift = new BrowseStoreProductsDiscP(selectedProductsList);
        browsePacksDiscP = new BrowsePacksDiscP(selectedPacksList);
        browseCategoriesDiscP = new BrowseCategoriesDiscP(selectedCategoriesList);

        centerPanel.add(browseDiscountsP, "BROWSE_DISCOUNTS");
        centerPanel.add(browseStoreProductsDiscP, "CHOOSE_PRODUCTS");
        centerPanel.add(browseStoreProductsDiscPForGift, "CHOOSE_GIFT");
        centerPanel.add(browsePacksDiscP, "CHOOSE_PACKS");
        centerPanel.add(browseCategoriesDiscP, "CHOOSE_CATEGORIES");

        this.add(centerPanel, BorderLayout.CENTER);
    }

    public void changeToChooseGiftView() {
        cardLayout.show(centerPanel, "CHOOSE_GIFT");
    }

    public void changeToBrowseDiscountsView() {
        cardLayout.show(centerPanel, "BROWSE_DISCOUNTS");
    }

    public void changeToChooseProductsView() {
        cardLayout.show(centerPanel, "CHOOSE_PRODUCTS");
    }

    public void changeToChoosePacksView() {
        cardLayout.show(centerPanel, "CHOOSE_PACKS");
    }

    public void changeToChooseCategoriesView() {
        cardLayout.show(centerPanel, "CHOOSE_CATEGORIES");
    }

    public void paintEastPanel() {
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
        eastPanel.setBorder(BorderFactory.createLineBorder(brownColour));

        eastPanel.removeAll();

        //--------------------------  Combo boxes ---------------------------
        JPanel comboBoxesPanel = new JPanel();

        JPanel coveragePanel = new JPanel();
        coveragePanel.setLayout(new BoxLayout(coveragePanel, BoxLayout.Y_AXIS));

        JLabel discountCoverageLabel = new JLabel("Discount coverage:");
        discountCoverageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        coveragePanel.add(discountCoverageLabel);
        coverageCmbBox.setMaximumSize(coverageCmbBox.getPreferredSize());
        coverageCmbBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        coveragePanel.add(coverageCmbBox);
        overWholeStore.setAlignmentX(Component.LEFT_ALIGNMENT);
        coveragePanel.add(overWholeStore);

        coveragePanel.setAlignmentY(Component.TOP_ALIGNMENT);
        coveragePanel.setMaximumSize(coveragePanel.getPreferredSize());
        comboBoxesPanel.add(coveragePanel);

        JPanel typePanel = new JPanel();
        typePanel.setLayout(new BoxLayout(typePanel, BoxLayout.Y_AXIS));

        JLabel discountTypeLabel = new JLabel("Discount type:");

        discountTypeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        typePanel.add(discountTypeLabel);
        discountTypeCmbBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        typePanel.add(discountTypeCmbBox);

        typePanel.setAlignmentY(Component.TOP_ALIGNMENT);
        typePanel.setMaximumSize(typePanel.getPreferredSize());
        comboBoxesPanel.add(typePanel);

        comboBoxesPanel.setMaximumSize(comboBoxesPanel.getPreferredSize());

        //--------------------------- First field ---------------------------

        firstField.setColumns(20);
        firstField.setMaximumSize(firstField.getPreferredSize());

        //--------------------------- Second Field --------------------------

        secondDiscountPanel = new JPanel(cardLayout);

        JPanel giftButtonPanel = new JPanel();
        giftButton.setMaximumSize(giftButton.getPreferredSize());
        giftButtonPanel.add(giftButton);

        giftButtonPanel.setMaximumSize(giftButtonPanel.getPreferredSize());

        JPanel textFieldPanel = new JPanel();
        textFieldPanel.setLayout(new BoxLayout(textFieldPanel, BoxLayout.Y_AXIS));
        textFieldPanel.add(secondFieldLabel);
        secondField.setColumns(20);
        secondField.setMaximumSize(secondField.getPreferredSize());
        textFieldPanel.add(secondField);
        JLabel blankLabel = new JLabel(" ");

        secondDiscountPanel.add(giftButtonPanel, "Gift button");
        secondDiscountPanel.add(textFieldPanel, "Text field");
        secondDiscountPanel.add(blankLabel, "Hide");

        secondDiscountPanel.setMaximumSize(secondDiscountPanel.getPreferredSize());

        //------------------------- Others ----------------------------------

        startingDateField.setColumns(20);
        startingDateField.setMaximumSize(startingDateField.getPreferredSize());
        endingDateField.setColumns(20);
        endingDateField.setMaximumSize(endingDateField.getPreferredSize());

        //-------------------------------------------------------------------

        eastPanel.add(Box.createVerticalGlue());
        eastPanel.add(new JLabel("CREATE A DISCOUNT:"));
        eastPanel.add(comboBoxesPanel);
        eastPanel.add(firstFieldLabel);
        eastPanel.add(firstField);
        eastPanel.add(secondDiscountPanel);
        eastPanel.add(selectItems);
        eastPanel.add(new JLabel("Starting date (<YYYY-MM-DD>T<HH:MM:SS>)"));
        eastPanel.add(startingDateField);
        eastPanel.add(new JLabel("Ending date (<YYYY-MM-DD>T<HH:MM:SS>)"));
        eastPanel.add(endingDateField);
        eastPanel.add(createBtn);
        eastPanel.add(Box.createVerticalGlue());

        updateDiscountPanels();

        this.add(eastPanel, BorderLayout.EAST);
    }

    public void updateDiscountPanels() {
        DiscountType type = getDiscountTypeCmbBoxSelected();
        DiscountCoverage coverage = getDiscountCoverageCmbBoxSelected();

        switch (type) {
            case FIXED_PERCENTAGE -> {
                firstFieldLabel.setText("Percentage:");
                cardLayout.show(secondDiscountPanel, "Hide");
            }
            case GIFT -> {
                firstFieldLabel.setText("Num. threshold:");
                cardLayout.show(secondDiscountPanel, "Gift button");
            }
            case QUANTITY -> {
                firstFieldLabel.setText("Deduction:");
                secondFieldLabel.setText("Quantity:");
                cardLayout.show(secondDiscountPanel, "Text field");
            }
            case VOLUME -> {
                firstFieldLabel.setText("Percentage:");
                secondFieldLabel.setText("Spending threshold:");
                cardLayout.show(secondDiscountPanel, "Text field");
            }
        }

        if (!overWholeStore.isSelected()) {
            selectItems.setVisible(true);
            switch (coverage) {
                case PRODUCT -> selectItems.setText("Choose products");
                case PACK -> selectItems.setText("Choose packs");
                case CATEGORY -> selectItems.setText("Choose categories");
            }
        } else {
            selectItems.setVisible(false);
        }

        eastPanel.revalidate();
        eastPanel.repaint();
    }

    public JButton getAddDiscount() {
        return addDiscount;
    }

    public BrowseCategoriesDiscP getBrowseCategoriesDiscP() {
        return browseCategoriesDiscP;
    }

    public BrowseDiscountsP getBrowseDiscountsP() {
        return browseDiscountsP;
    }

    public BrowsePacksDiscP getBrowsePacksDiscP() {
        return browsePacksDiscP;
    }

    public BrowseStoreProductsDiscP getBrowseStoreProductsDiscP() {
        return browseStoreProductsDiscP;
    }

    public BrowseStoreProductsDiscP getBrowseStoreProductsDiscPForGift() {
        return browseStoreProductsDiscPForGift;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public JPanel getCenterPanel() {
        return centerPanel;
    }

    public JComboBox<String> getCoverage() {
        return coverageCmbBox;
    }

    public JComboBox<String> getCoverageCmbBox() {
        return coverageCmbBox;
    }

    public DiscountCoverage getCoverageFromString(String coverageName) {
        return coveragesHashMap.get(coverageName);
    }

    public HashMap<String, DiscountCoverage> getCoveragesHashMap() {
        return coveragesHashMap;
    }

    public JButton getCreateBtn() {
        return createBtn;
    }

    public DiscountCoverage getDiscountCoverageCmbBoxSelected() {
        return coveragesHashMap.get(Objects.requireNonNull(coverageCmbBox.getSelectedItem()).toString());
    }

    public JComboBox<String> getDiscountType() {
        return discountTypeCmbBox;
    }

    public JComboBox<String> getDiscountTypeCmbBox() {
        return discountTypeCmbBox;
    }

    public DiscountType getDiscountTypeCmbBoxSelected() {
        return discountTypesHashMap.get(Objects.requireNonNull(discountTypeCmbBox.getSelectedItem()).toString());
    }

    public HashMap<String, DiscountType> getDiscountTypesHashMap() {
        return discountTypesHashMap;
    }

    public JPanel getEastPanel() {
        return eastPanel;
    }

    public JTextField getEndingDateField() {
        return endingDateField;
    }

    public JTextField getFirstField() {
        return firstField;
    }

    public JLabel getFirstFieldLabel() {
        return firstFieldLabel;
    }

    public StoreProduct getGift() {
        return gift;
    }

    public void setGift(StoreProduct newGift) {
        this.gift = newGift;
    }

    public JButton getGiftButton() {
        return giftButton;
    }

    public JCheckBox getOverWholeStore() {
        return overWholeStore;
    }

    public JPanel getSecondDiscountPanel() {
        return secondDiscountPanel;
    }

    public void setSecondDiscountPanel(JPanel newSecondDiscountPanel) {
        this.secondDiscountPanel = newSecondDiscountPanel;
    }

    public JTextField getSecondField() {
        return secondField;
    }

    public JLabel getSecondFieldLabel() {
        return secondFieldLabel;
    }

    public JButton getSelectItems() {
        return selectItems;
    }

    public List<Category> getSelectedCategoriesList() {
        return selectedCategoriesList;
    }

    public List<Pack> getSelectedPacksList() {
        return selectedPacksList;
    }

    public List<StoreProduct> getSelectedProductsList() {
        return selectedProductsList;
    }

    public JTextField getStartingDateField() {
        return startingDateField;
    }

    public DiscountType getTypeFromString(String typeName) {
        return discountTypesHashMap.get(typeName);
    }

    public HashMap<String, DiscountCoverage> getcoveragesHashMap() {
        return coveragesHashMap;
    }

    public HashMap<String, DiscountType> getdiscountTypesHashMap() {
        return discountTypesHashMap;
    }

}