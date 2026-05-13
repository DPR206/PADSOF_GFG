package view.managerPanels;

import model.discount.DiscountCoverage;
import model.discount.DiscountType;
import model.product.StoreProduct;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class ManagerDiscountsP extends JPanel {
    private JPanel eastPanel = new JPanel();
    private JPanel westPanel = new JPanel(); // DUE: Debe ser un browse discounts
    private JButton addDiscount = new JButton("Add discount");
    private JComboBox<String> discountType;
    private JComboBox<String> coverage;
    private JButton giftButton = new JButton("Select Gift");
    private JLabel firstFieldLabel = new JLabel("Choose a type");
    private JLabel secondFieldLabel = new JLabel("Choose a type");
    private JTextField firstField = new JTextField();
    private JTextField secondField = new JTextField();
    private StoreProduct gift;
    private CardLayout cardLayout = new CardLayout();
    private JPanel secondDiscountPanel;
    private JButton selectItems = new JButton("Choose a coverage");
    private HashMap<String, DiscountType> discountTypes = new HashMap<>();
    private HashMap<String, DiscountCoverage> coverages = new HashMap<>();

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public ManagerDiscountsP() {
        this.setLayout(new BorderLayout());

        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));

        discountTypes.put("Fixed percentage", DiscountType.FIXED_PERCENTAGE);
        discountTypes.put("Gift", DiscountType.GIFT);
        discountTypes.put("Quantity", DiscountType.QUANTITY);
        discountTypes.put("Volume", DiscountType.VOLUME);
        discountType = new JComboBox<>(discountTypes.keySet().toArray(new String[0]));

        coverages.put("Categories", DiscountCoverage.CATEGORY);
        coverages.put("Packs", DiscountCoverage.PACK);
        coverages.put("Store products", DiscountCoverage.PRODUCT); // DUE: Preguntar por whole store
        coverage = new JComboBox<>(coverages.keySet().toArray(new String[0]));

        selectItems.setEnabled(false);
        firstFieldLabel.setVisible(false);
        firstField.setVisible(false);
        secondFieldLabel.setVisible(false);

        paintEverything();
    }

    public void paintEverything() {
        this.removeAll();

        paintEastPanel();

        this.add(westPanel, BorderLayout.CENTER);

        this.revalidate();
        this.repaint();
    }

    public void paintEastPanel() {
        eastPanel.removeAll();

        //-------------------------------------------------------------------
        JPanel comboBoxesPanel = new JPanel();

        JPanel coveragePanel = new JPanel();
        coveragePanel.setLayout(new BoxLayout(coveragePanel, BoxLayout.Y_AXIS));

        JLabel discountCoverageLabel = new JLabel("Discount coverage:");
        discountCoverageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        coveragePanel.add(discountCoverageLabel);
        coverage.setMaximumSize(coverage.getPreferredSize());
        coveragePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        coveragePanel.add(coverage);

        comboBoxesPanel.add(coveragePanel);

        JPanel typePanel = new JPanel();
        typePanel.setLayout(new BoxLayout(typePanel, BoxLayout.Y_AXIS));

        JLabel discountTypeLabel = new JLabel("Discount type:");
        discountTypeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        typePanel.add(discountTypeLabel);
        discountType.setMaximumSize(discountType.getPreferredSize());
        typePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        typePanel.add(discountType);

        comboBoxesPanel.add(typePanel);

        eastPanel.add(comboBoxesPanel);
        //-------------------------------------------------------------------

        eastPanel.add(firstFieldLabel);
        eastPanel.add(firstField);

        //-------------------------------------------------------------------

        secondDiscountPanel = new JPanel(cardLayout);
        secondDiscountPanel.add(giftButton, "Gift button");
        secondDiscountPanel.add(secondField, "Text field");

//        eastPanel.revalidate();
//        eastPanel.repaint();

        this.add(eastPanel, BorderLayout.EAST);
    }

    public void changeDiscountPanels(DiscountType discountType, DiscountCoverage coverage) {
        switch (discountType) {
            case FIXED_PERCENTAGE -> {
                firstFieldLabel.setVisible(true);
                firstField.setVisible(true);
                secondFieldLabel.setVisible(true);
                firstFieldLabel.setText("Percentage:");
                cardLayout.show(secondDiscountPanel, "Text field");
            }
            case GIFT -> {
                firstFieldLabel.setVisible(false);
                firstField.setVisible(true);
                secondFieldLabel.setVisible(true);
                cardLayout.show(secondDiscountPanel, "Gift button");
            }
            case QUANTITY -> {
                firstFieldLabel.setVisible(true);
                firstField.setVisible(true);
                secondFieldLabel.setVisible(true);
                firstFieldLabel.setText("Quantity:");
                cardLayout.show(secondDiscountPanel, "Text field");
            }
            case VOLUME -> {
                firstFieldLabel.setVisible(true);
                firstField.setVisible(true);
                secondFieldLabel.setVisible(true);
                firstFieldLabel.setText("Volume:");
                cardLayout.show(secondDiscountPanel, "Text field");
            }
        }

        switch (coverage) {
            case PRODUCT -> selectItems.setText("Choose products");
            case PACK -> selectItems.setText("Choose packs");
            case CATEGORY -> selectItems.setText("Choose categories");
        }

        eastPanel.revalidate();
        eastPanel.repaint();
    }

    public JButton getAddDiscount() {
        return addDiscount;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public JComboBox<String> getCoverage() {
        return coverage;
    }

    public DiscountCoverage getCoverageFromString(String coverageName) {
        return coverages.get(coverageName);
    }

    public HashMap<String, DiscountCoverage> getCoverages() {
        return coverages;
    }

    public JComboBox<String> getDiscountType() {
        return discountType;
    }

    public HashMap<String, DiscountType> getDiscountTypes() {
        return discountTypes;
    }

    public JPanel getEastPanel() {
        return eastPanel;
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

    public JButton getGiftButton() {
        return giftButton;
    }

    public JPanel getSecondDiscountPanel() {
        return secondDiscountPanel;
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

    public DiscountType getTypeFromString(String typeName) {
        return discountTypes.get(typeName);
    }

    public JPanel getWestPanel() {
        return westPanel;
    }

}