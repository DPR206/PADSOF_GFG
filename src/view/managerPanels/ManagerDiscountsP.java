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

/**
 * The type Manager discounts p.
 * @author Ana O.R.
 * @version 1.0
 */
public class ManagerDiscountsP extends JPanel {
    private final HashMap<String, DiscountType> discountTypesHashMap = new HashMap<>();
    private final HashMap<String, DiscountCoverage> coveragesHashMap = new HashMap<>();
    private final JCheckBox overWholeStore = new JCheckBox("Over Whole Store");
    private final JPanel eastPanel = new JPanel();
    private final JPanel centerPanel = new JPanel();
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

    /**
     * Instantiates a new Manager discounts p.
     * @throws BadLocationException the bad location exception
     */
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

    /**
     * Paint everything.
     * @throws BadLocationException the bad location exception
     */
    public void paintEverything() throws BadLocationException {
        this.removeAll();

        paintEastPanel();
        paintCenterPanel();

        this.revalidate();
        this.repaint();
    }

    /**
     * Paint centre panel.
     * @throws BadLocationException the bad location exception
     */
    public void paintCenterPanel() throws BadLocationException {
        centerPanel.setLayout(cardLayout);
        centerPanel.removeAll();

        /* Init panels */
        browseDiscountsP = new BrowseDiscountsP();
        browseStoreProductsDiscP = new BrowseStoreProductsDiscP(selectedProductsList, "Add to discount");
        List<StoreProduct> giftList = new ArrayList<>();
        giftList.add(gift);
        browseStoreProductsDiscPForGift = new BrowseStoreProductsDiscP(giftList, "Choose this gift");
        browsePacksDiscP = new BrowsePacksDiscP(selectedPacksList);
        browseCategoriesDiscP = new BrowseCategoriesDiscP(selectedCategoriesList);

        centerPanel.add(browseDiscountsP, "BROWSE_DISCOUNTS");
        centerPanel.add(browseStoreProductsDiscP, "CHOOSE_PRODUCTS");
        centerPanel.add(browseStoreProductsDiscPForGift, "CHOOSE_GIFT");
        centerPanel.add(browsePacksDiscP, "CHOOSE_PACKS");
        centerPanel.add(browseCategoriesDiscP, "CHOOSE_CATEGORIES");

        this.add(centerPanel, BorderLayout.CENTER);
    }

    /**
     * Change to choose gift view.
     */
    public void changeToChooseGiftView() {
        cardLayout.show(centerPanel, "CHOOSE_GIFT");
    }

    /**
     * Change to browse discounts view.
     */
    public void changeToBrowseDiscountsView() {
        cardLayout.show(centerPanel, "BROWSE_DISCOUNTS");
    }

    /**
     * Change to choose products view.
     */
    public void changeToChooseProductsView() {
        cardLayout.show(centerPanel, "CHOOSE_PRODUCTS");
    }

    /**
     * Change to choose packs view.
     */
    public void changeToChoosePacksView() {
        cardLayout.show(centerPanel, "CHOOSE_PACKS");
    }

    /**
     * Change to choose categories view.
     */
    public void changeToChooseCategoriesView() {
        cardLayout.show(centerPanel, "CHOOSE_CATEGORIES");
    }

    /**
     * Paint east panel.
     */
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

    /**
     * Update discount panels.
     */
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
                case PRODUCT -> {
                    overWholeStore.setVisible(true);
                    selectItems.setText("Choose products");
                }

                case PACK -> {
                    overWholeStore.setVisible(false);
                    selectItems.setText("Choose packs");
                }

                case CATEGORY -> {
                    overWholeStore.setVisible(false);
                    selectItems.setText("Choose categories");
                }
            }
        } else {
            selectItems.setVisible(false);
        }

        eastPanel.revalidate();
        eastPanel.repaint();
    }

    /**
     * It gets the browse categories disc p
     * @return the browse categories disc p
     */
    public BrowseCategoriesDiscP getBrowseCategoriesDiscP() {
        return browseCategoriesDiscP;
    }

    /**
     * It gets the browse discounts p
     * @return the browse discounts p
     */
    public BrowseDiscountsP getBrowseDiscountsP() {
        return browseDiscountsP;
    }

    /**
     * It gets the browse packs disc p
     * @return the browse packs disc p
     */
    public BrowsePacksDiscP getBrowsePacksDiscP() {
        return browsePacksDiscP;
    }

    /**
     * It gets the browse store products disc p
     * @return the browse store products disc p
     */
    public BrowseStoreProductsDiscP getBrowseStoreProductsDiscP() {
        return browseStoreProductsDiscP;
    }

    /**
     * It gets the browse store products disc p for gift
     * @return the browse store products disc p for gift
     */
    public BrowseStoreProductsDiscP getBrowseStoreProductsDiscPForGift() {
        return browseStoreProductsDiscPForGift;
    }

    /**
     * It gets the coverage
     * @return the coverage
     */
    public JComboBox<String> getCoverage() {
        return coverageCmbBox;
    }

    /**
     * It gets the create button
     * @return the create button
     */
    public JButton getCreateBtn() {
        return createBtn;
    }

    /**
     * It gets the discount coverage cmb box selected
     * @return the discount coverage cmb box selected
     */
    public DiscountCoverage getDiscountCoverageCmbBoxSelected() {
        return coveragesHashMap.get(Objects.requireNonNull(coverageCmbBox.getSelectedItem()).toString());
    }

    /**
     * It gets the discount type
     * @return the discount type
     */
    public JComboBox<String> getDiscountType() {
        return discountTypeCmbBox;
    }

    /**
     * It gets the discount type cmb box selected
     * @return the discount type cmb box selected
     */
    public DiscountType getDiscountTypeCmbBoxSelected() {
        return discountTypesHashMap.get(Objects.requireNonNull(discountTypeCmbBox.getSelectedItem()).toString());
    }

    /**
     * It gets the ending date field
     * @return the ending date field
     */
    public JTextField getEndingDateField() {
        return endingDateField;
    }

    /**
     * It gets the first field
     * @return the first field
     */
    public JTextField getFirstField() {
        return firstField;
    }

    /**
     * It gets the gift
     * @return the gift
     */
    public StoreProduct getGift() {
        return gift;
    }

    /**
     * It sets the gift
     * @param newGift the new gift
     */
    public void setGift(StoreProduct newGift) {
        this.gift = newGift;
    }

    /**
     * It gets the gift button
     * @return the gift button
     */
    public JButton getGiftButton() {
        return giftButton;
    }

    /**
     * It gets the over whole store
     * @return the over whole store
     */
    public JCheckBox getOverWholeStore() {
        return overWholeStore;
    }

    /**
     * It gets the second field
     * @return the second field
     */
    public JTextField getSecondField() {
        return secondField;
    }

    /**
     * It gets the select items
     * @return the select items
     */
    public JButton getSelectItems() {
        return selectItems;
    }

    /**
     * It gets the selected categories list
     * @return the selected categories list
     */
    public List<Category> getSelectedCategoriesList() {
        return selectedCategoriesList;
    }

    /**
     * It gets the selected packs list
     * @return the selected packs list
     */
    public List<Pack> getSelectedPacksList() {
        return selectedPacksList;
    }

    /**
     * It gets the selected products list
     * @return the selected products list
     */
    public List<StoreProduct> getSelectedProductsList() {
        return selectedProductsList;
    }

    /**
     * It gets the starting date field
     * @return the starting date field
     */
    public JTextField getStartingDateField() {
        return startingDateField;
    }

}