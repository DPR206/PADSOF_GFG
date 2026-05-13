package view.managerPanels;

import javax.swing.*;
import java.awt.*;
import java.time.Period;

public class ManagerParametersP extends JPanel {
    public JButton applyChanges = new JButton("Apply Changes");
    public JTextField offerTimeField;
    public JTextField orderTimeField;
    public JTextField valuationCostField;
    public JTextField storeAddressField;
    public JTextField exchangeTimeField;
    public JTextField kRecommendField;
    public JTextField scoreAParamField;
    public JTextField scoreBParamField;
    private Period offerTime;
    private Period orderTime;
    private double valuationCost;
    private String storeAddress;
    private Period exchangeTime;
    private int kRecommend;
    private double scoreAParam;
    private double scoreBParam;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public ManagerParametersP() {
        this.setLayout(new GridLayout(3, 3)); // There are 8 configurable parameters
        this.setOpaque(false);
        paintEverything();
    }

    public void paintEverything() {
        this.removeAll();

        offerTimeField = new JTextField(String.valueOf(offerTime));
        orderTimeField = new JTextField(String.valueOf(orderTime));
        valuationCostField = new JTextField(String.valueOf(valuationCost));
        storeAddressField = new JTextField(storeAddress);
        exchangeTimeField = new JTextField((exchangeTime.toString()));
        kRecommendField = new JTextField(kRecommend);
        scoreAParamField = new JTextField(String.valueOf(scoreAParam));
        scoreBParamField = new JTextField(String.valueOf(scoreBParam));

        this.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100)); // Padding :)

        /* Offer time */
        JPanel param1 = new JPanel();
        param1.setLayout(new BoxLayout(param1, BoxLayout.Y_AXIS));
        JLabel label1 = new JLabel("⌛ Offer time: " + offerTime);
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
        JLabel label2 = new JLabel("⌛ Order time: " + orderTime);
        label2.setAlignmentX(Component.LEFT_ALIGNMENT);
        orderTimeField.setAlignmentX(Component.LEFT_ALIGNMENT);
        orderTimeField.setColumns(30);
        orderTimeField.setMaximumSize(offerTimeField.getPreferredSize());
        param2.add(label2);
        param2.add(Box.createRigidArea(new Dimension(0, 10)));
        param2.add(orderTimeField);
        this.add(param2);

        /* Exchange time */
        JPanel param5 = new JPanel();
        param5.setLayout(new BoxLayout(param5, BoxLayout.Y_AXIS));
        JLabel label5 = new JLabel("⌛ Exchange time: " + exchangeTime);
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
        param8.add(offerTimeField);
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

        applyChanges.setMaximumSize(applyChanges.getPreferredSize());
        this.add(applyChanges);

        this.revalidate();
        this.repaint();
    }

    public JButton getApplyChanges() {
        return applyChanges;
    }

    public void setApplyChanges(JButton newApplyChanges) {
        this.applyChanges = newApplyChanges;
    }

    public Period getExchangeTime() {
        return exchangeTime;
    }

    public void setExchangeTime(Period newExchangeTime) {
        this.exchangeTime = newExchangeTime;
    }

    public JTextField getExchangeTimeField() {
        return exchangeTimeField;
    }

    public void setExchangeTimeField(JTextField newExchangeTimeField) {
        this.exchangeTimeField = newExchangeTimeField;
    }

    public Period getOfferTime() {
        return offerTime;
    }

    public void setOfferTime(Period newOfferTime) {
        this.offerTime = newOfferTime;
    }

    public JTextField getOfferTimeField() {
        return offerTimeField;
    }

    public void setOfferTimeField(JTextField newOfferTimeField) {
        this.offerTimeField = newOfferTimeField;
    }

    public Period getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Period newOrderTime) {
        this.orderTime = newOrderTime;
    }

    public JTextField getOrderTimeField() {
        return orderTimeField;
    }

    public void setOrderTimeField(JTextField newOrderTimeField) {
        this.orderTimeField = newOrderTimeField;
    }

    public double getScoreAParam() {
        return scoreAParam;
    }

    public void setScoreAParam(double newScoreAParam) {
        this.scoreAParam = newScoreAParam;
    }

    public JTextField getScoreAParamField() {
        return scoreAParamField;
    }

    public void setScoreAParamField(JTextField newScoreAParamField) {
        this.scoreAParamField = newScoreAParamField;
    }

    public double getScoreBParam() {
        return scoreBParam;
    }

    public void setScoreBParam(double newScoreBParam) {
        this.scoreBParam = newScoreBParam;
    }

    public JTextField getScoreBParamField() {
        return scoreBParamField;
    }

    public void setScoreBParamField(JTextField newScoreBParamField) {
        this.scoreBParamField = newScoreBParamField;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String newStoreAddress) {
        this.storeAddress = newStoreAddress;
    }

    public JTextField getStoreAddressField() {
        return storeAddressField;
    }

    public void setStoreAddressField(JTextField newStoreAddressField) {
        this.storeAddressField = newStoreAddressField;
    }

    public double getValuationCost() {
        return valuationCost;
    }

    public void setValuationCost(double newValuationCost) {
        this.valuationCost = newValuationCost;
    }

    public JTextField getValuationCostField() {
        return valuationCostField;
    }

    public void setValuationCostField(JTextField newValuationCostField) {
        this.valuationCostField = newValuationCostField;
    }

    public int getkRecommend() {
        return kRecommend;
    }

    public void setkRecommend(int newkRecommend) {
        this.kRecommend = newkRecommend;
    }

    public JTextField getkRecommendField() {
        return kRecommendField;
    }

    public void setkRecommendField(JTextField newkRecommendField) {
        this.kRecommendField = newkRecommendField;
    }
}