package view.managerPanels;

import javax.swing.*;
import java.awt.*;
import java.time.Period;

import static view.ImageAdder.getImageLabel;

/**
 * The type Manager parameters p.
 * @author Ana O.R.
 * @version 1.0
 */
public class ManagerParametersP extends JPanel {
    private final JButton applyChanges = new JButton("Apply Changes");
    private JTextField offerTimeField;
    private JTextField orderTimeField;
    private JTextField valuationCostField;
    private JTextField storeAddressField;
    private JTextField exchangeTimeField;
    private JTextField kRecommendField;
    private JTextField scoreAParamField;
    private JTextField scoreBParamField;
    private Period offerTime;
    private Period orderTime;
    private double valuationCost;
    private String storeAddress;
    private Period exchangeTime;
    private int kRecommend;
    private double scoreAParam;
    private double scoreBParam;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Manager parameters p.
     */
    public ManagerParametersP() {
        this.setLayout(new GridLayout(3, 3)); // There are 8 configurable parameters
        paintEverything();
    }

    /**
     * Paint everything.
     */
    public void paintEverything() {
        this.removeAll();
        this.setOpaque(false);

        offerTimeField = new JTextField(offerTime == null ? "" : String.valueOf(offerTime.getDays()));
        orderTimeField = new JTextField(orderTime == null ? "" : String.valueOf(orderTime.getDays()));
        exchangeTimeField = new JTextField(exchangeTime == null ? "" : String.valueOf(exchangeTime.getDays()));
        kRecommendField = new JTextField(String.valueOf(kRecommend));
        scoreAParamField = new JTextField(String.valueOf(scoreAParam));
        scoreBParamField = new JTextField(String.valueOf(scoreBParam));
        valuationCostField = new JTextField(String.valueOf(valuationCost));
        storeAddressField = new JTextField(storeAddress == null ? "" : storeAddress);

        this.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100)); // Padding :)

        /* Offer time */
        JPanel param1 = new JPanel();
        param1.setLayout(new BoxLayout(param1, BoxLayout.Y_AXIS));
        JLabel label1 =
                new JLabel("⌛ Offer time (days): " + (offerTime == null ? "" : String.valueOf(offerTime.getDays())));
        label1.setAlignmentX(Component.LEFT_ALIGNMENT);
        offerTimeField.setAlignmentX(Component.LEFT_ALIGNMENT);
        offerTimeField.setColumns(30);
        offerTimeField.setMaximumSize(offerTimeField.getPreferredSize());
        param1.add(label1);
        param1.add(Box.createRigidArea(new Dimension(0, 10)));
        param1.add(offerTimeField);
        this.add(param1);

        /* Order time */
        JPanel param2 = new JPanel();
        param2.setLayout(new BoxLayout(param2, BoxLayout.Y_AXIS));
        JLabel label2 =
                new JLabel("⌛ Order time (days): " + (orderTime == null ? "" : String.valueOf(orderTime.getDays())));
        label2.setAlignmentX(Component.LEFT_ALIGNMENT);
        orderTimeField.setAlignmentX(Component.LEFT_ALIGNMENT);
        orderTimeField.setColumns(30);
        orderTimeField.setMaximumSize(orderTimeField.getPreferredSize());
        param2.add(label2);
        param2.add(Box.createRigidArea(new Dimension(0, 10)));
        param2.add(orderTimeField);
        this.add(param2);

        /* Exchange time */
        JPanel param5 = new JPanel();
        param5.setLayout(new BoxLayout(param5, BoxLayout.Y_AXIS));
        JLabel label5 = new JLabel(
                "⌛ Exchange time (days): " + (exchangeTime == null ? "" : String.valueOf(exchangeTime.getDays())));
        label5.setAlignmentX(Component.LEFT_ALIGNMENT);
        exchangeTimeField.setAlignmentX(Component.LEFT_ALIGNMENT);
        exchangeTimeField.setColumns(30);
        exchangeTimeField.setMaximumSize(exchangeTimeField.getPreferredSize());
        param5.add(label5);
        param5.add(Box.createRigidArea(new Dimension(0, 10)));
        param5.add(exchangeTimeField);
        this.add(param5);

        /* Number of recommendations (k param) */
        JPanel param6 = new JPanel();
        param6.setLayout(new BoxLayout(param6, BoxLayout.Y_AXIS));
        JLabel label6 = new JLabel("📊 Nº of recommendations per client: " + kRecommend);
        label6.setAlignmentX(Component.LEFT_ALIGNMENT);
        kRecommendField.setAlignmentX(Component.LEFT_ALIGNMENT);
        kRecommendField.setColumns(30);
        kRecommendField.setMaximumSize(kRecommendField.getPreferredSize());
        param6.add(label6);
        param6.add(Box.createRigidArea(new Dimension(0, 10)));
        param6.add(kRecommendField);
        this.add(param6);

        /* Score A param */
        JPanel param7 = new JPanel();
        param7.setLayout(new BoxLayout(param7, BoxLayout.Y_AXIS));
        JLabel label7 = new JLabel("📊 Score A parameter (weightedScore = (a*score + b)): " + scoreAParam);
        label7.setAlignmentX(Component.LEFT_ALIGNMENT);
        scoreAParamField.setAlignmentX(Component.LEFT_ALIGNMENT);
        scoreAParamField.setColumns(30);
        scoreAParamField.setMaximumSize(scoreAParamField.getPreferredSize());
        param7.add(label7);
        param7.add(Box.createRigidArea(new Dimension(0, 10)));
        param7.add(scoreAParamField);
        this.add(param7);

        /* Score B param */
        JPanel param8 = new JPanel();
        param8.setLayout(new BoxLayout(param8, BoxLayout.Y_AXIS));
        JLabel label8 = new JLabel("📊 Score B parameter (weightedScore = (a*score + b)): " + scoreBParam);
        label8.setAlignmentX(Component.LEFT_ALIGNMENT);
        scoreBParamField.setAlignmentX(Component.LEFT_ALIGNMENT);
        scoreBParamField.setColumns(30);
        scoreBParamField.setMaximumSize(scoreBParamField.getPreferredSize());
        param8.add(label8);
        param8.add(Box.createRigidArea(new Dimension(0, 10)));
        param8.add(scoreBParamField);
        this.add(param8);

        /* Valuation cost */
        JPanel param3 = new JPanel();
        param3.setLayout(new BoxLayout(param3, BoxLayout.Y_AXIS));
        JLabel label3 = new JLabel("💰 Valuation cost: " + valuationCost);
        label3.setAlignmentX(Component.LEFT_ALIGNMENT);
        valuationCostField.setAlignmentX(Component.LEFT_ALIGNMENT);
        valuationCostField.setColumns(30);
        valuationCostField.setMaximumSize(valuationCostField.getPreferredSize());
        param3.add(label3);
        param3.add(Box.createRigidArea(new Dimension(0, 10)));
        param3.add(valuationCostField);
        this.add(param3);

        /* Store address */
        JPanel param4 = new JPanel();
        param4.setLayout(new BoxLayout(param4, BoxLayout.Y_AXIS));
        JLabel label4 = new JLabel("🏠 Store address: " + storeAddress);
        label4.setAlignmentX(Component.LEFT_ALIGNMENT);
        storeAddressField.setAlignmentX(Component.LEFT_ALIGNMENT);
        storeAddressField.setColumns(30);
        storeAddressField.setMaximumSize(storeAddressField.getPreferredSize());
        param4.add(label4);
        param4.add(Box.createRigidArea(new Dimension(0, 10)));
        param4.add(storeAddressField);
        this.add(param4);

        JPanel botonPanel = new JPanel();
        applyChanges.setIcon(getImageLabel(".\\resources\\app\\save.png", 35, 35).getIcon());
        applyChanges.setPreferredSize(new Dimension(("Apply Changes").length() * 16, 50));
        applyChanges.setAlignmentX(Component.RIGHT_ALIGNMENT);
        applyChanges.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        botonPanel.add(applyChanges);
        this.add(botonPanel);

        this.revalidate();
        this.repaint();
    }

    /**
     * It gets the apply changes
     * @return the apply changes
     */
    public JButton getApplyChanges() {
        return applyChanges;
    }

    /**
     * It gets the exchange time field
     * @return the exchange time field
     */
    public JTextField getExchangeTimeField() {
        return exchangeTimeField;
    }

    /**
     * It gets the recommend field
     * @return the recommend field
     */
    public JTextField getKRecommendField() {
        return kRecommendField;
    }

    /**
     * It gets the offer time field
     * @return the offer time field
     */
    public JTextField getOfferTimeField() {
        return offerTimeField;
    }

    /**
     * It gets the order time field
     * @return the order time field
     */
    public JTextField getOrderTimeField() {
        return orderTimeField;
    }

    /**
     * It gets the score a param field
     * @return the score a param field
     */
    public JTextField getScoreAParamField() {
        return scoreAParamField;
    }

    /**
     * It gets the score b param field
     * @return the score b param field
     */
    public JTextField getScoreBParamField() {
        return scoreBParamField;
    }

    /**
     * It gets the store address field
     * @return the store address field
     */
    public JTextField getStoreAddressField() {
        return storeAddressField;
    }

    /**
     * It gets the valuation cost field
     * @return the valuation cost field
     */
    public JTextField getValuationCostField() {
        return valuationCostField;
    }

    /**
     * It sets the exchange time
     * @param newExchangeTime the new exchange time
     */
    public void setExchangeTime(Period newExchangeTime) {
        this.exchangeTime = newExchangeTime;
    }

    /**
     * It sets the recommend
     * @param newKRecommend the new k recommend
     */
    public void setKRecommend(int newKRecommend) {
        this.kRecommend = newKRecommend;
    }

    /**
     * It sets the offer time
     * @param newOfferTime the new offer time
     */
    public void setOfferTime(Period newOfferTime) {
        this.offerTime = newOfferTime;
    }

    /**
     * It sets the order time
     * @param newOrderTime the new order time
     */
    public void setOrderTime(Period newOrderTime) {
        this.orderTime = newOrderTime;
    }

    /**
     * It sets the score a param
     * @param newScoreAParam the new score a param
     */
    public void setScoreAParam(double newScoreAParam) {
        this.scoreAParam = newScoreAParam;
    }

    /**
     * It sets the score b param
     * @param newScoreBParam the new score b param
     */
    public void setScoreBParam(double newScoreBParam) {
        this.scoreBParam = newScoreBParam;
    }

    /**
     * It sets the store address
     * @param newStoreAddress the new store address
     */
    public void setStoreAddress(String newStoreAddress) {
        this.storeAddress = newStoreAddress;
    }

    /**
     * It sets the valuation cost
     * @param newValuationCost the new valuation cost
     */
    public void setValuationCost(double newValuationCost) {
        this.valuationCost = newValuationCost;
    }
}