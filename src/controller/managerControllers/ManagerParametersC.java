package controller.managerControllers;

import controller.Controller;
import model.store.Parameter;
import model.store.Store;
import view.App;
import view.managerPanels.ManagerParametersP;

import javax.swing.*;
import java.time.Period;
import java.time.format.DateTimeParseException;

public class ManagerParametersC implements Controller {
    private final ManagerParametersP view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public ManagerParametersC(App frame, Store model, ManagerParametersP view) {
        this.frame = frame;
        this.model = model;
        this.view = view;

        initializeActions();
    }

    @Override
    public void initializeActions() {
        view.setOfferTime(Parameter.getParam().getOfferTime());
        view.setOrderTime(Parameter.getParam().getOrderTime());
        view.setExchangeTime(Parameter.getParam().getExchangeTime());
        view.setkRecommend(Parameter.getParam().getkRecommend());
        view.setScoreAParam(Parameter.getParam().getScoreAParam());
        view.setScoreBParam(Parameter.getParam().getScoreBParam());
        view.setValuationCost(Parameter.getParam().getValuationCost());
        view.setStoreAddress(Parameter.getParam().getStoreAddress());
        view.paintEverything();

        view.getApplyChanges().addActionListener(e -> {
            boolean applyChanges = true;
            Period offerTime = null;
            Period orderTime = null;
            Period exchangeTime = null;
            int kRecommend = 0;
            double scoreAParam = 0;
            double scoreBParam = 0;
            double valuationCost = 0;
            try {
                offerTime = Period.parse(view.getOfferTimeField().getText());
                orderTime = Period.parse(view.getOrderTimeField().getText());
                exchangeTime = Period.parse(view.getExchangeTimeField().getText());
            } catch (DateTimeParseException e1) {
                JOptionPane.showMessageDialog(frame, "Times must be Periods", "Invalid time period",
                        JOptionPane.ERROR_MESSAGE);
                applyChanges = false;
            }

            try {
                kRecommend = Integer.parseInt(view.getkRecommendField().getText());
                scoreAParam = Double.parseDouble(view.getScoreAParamField().getText());
                scoreBParam = Double.parseDouble(view.getScoreBParamField().getText());
                valuationCost = Double.parseDouble(view.getValuationCostField().getText());
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(frame, "Please input valid numbers", "Invalid number format",
                        JOptionPane.ERROR_MESSAGE);
                applyChanges = false;
            }

            String storeAddress = view.getStoreAddressField().getText();

            if (applyChanges) {
                if (JOptionPane.showConfirmDialog(frame, "Are you sure?") == JOptionPane.YES_OPTION) {
                    Parameter.getParam().setOfferTime(offerTime);
                    Parameter.getParam().setOrderTime(orderTime);
                    Parameter.getParam().setExchangeTime(exchangeTime);
                    Parameter.getParam().setkRecommend(kRecommend);
                    Parameter.getParam().setScoreAParam(scoreAParam);
                    Parameter.getParam().setScoreBParam(scoreBParam);
                    Parameter.getParam().setValuationCost(valuationCost);
                    Parameter.getParam().setStoreAddress(storeAddress);
                    view.paintEverything();
                }
            }
        });

    }
}