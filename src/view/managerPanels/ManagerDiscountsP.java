package view.managerPanels;

import model.discount.DiscountCoverage;
import model.discount.DiscountType;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class ManagerDiscountsP extends JPanel {
    public JPanel eastPanel = new JPanel();
    public JPanel westPanel = new JPanel(); // DUE: Debe ser un browse discounts
    public JButton addDiscount = new JButton("Add discount");
    public JComboBox<String> discountType;
    public JComboBox<String> coverage;
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
        coverages.put("Store products", DiscountCoverage.PRODUCT);
        coverage = new JComboBox<>(coverages.keySet().toArray(new String[0]));
    }

    public void paintEverything() {
        this.removeAll();

        paintEastPanel();

        this.add(westPanel, BorderLayout.CENTER);
        this.add(eastPanel, BorderLayout.EAST);

        this.revalidate();
        this.repaint();
    }

    public void paintEastPanel() {
        this.removeAll();

        JLabel type = new JLabel("Discount's type");

        this.revalidate();
        this.repaint();
    }
}