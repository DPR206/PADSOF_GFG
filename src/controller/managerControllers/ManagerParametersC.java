package controller.managerControllers;

import controller.Controller;
import model.store.Parameter;
import view.App;
import view.managerPanels.ManagerParametersP;

import javax.swing.*;
import java.time.Period;
import java.time.format.DateTimeParseException;

/**
 * The type Manager parameters c.
 * @author Ana O.R.
 * @version 1.0
 */
public class ManagerParametersC implements Controller {
    private final ManagerParametersP view;
    private final App frame;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Manager parameters c.
     * @param frame the frame
     * @param view  the view
     */
    public ManagerParametersC(App frame, ManagerParametersP view) {
        this.frame = frame;
        this.view = view;

        initializeActions();
    }

    @Override
    public void initializeActions() {
        assignValuesToView();

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
                offerTime = Period.ofDays(Integer.parseInt(view.getOfferTimeField().getText()));
                orderTime = Period.ofDays(Integer.parseInt(view.getOrderTimeField().getText()));
                exchangeTime = Period.ofDays(Integer.parseInt(view.getExchangeTimeField().getText()));
            } catch (DateTimeParseException e1) {
                JOptionPane.showMessageDialog(frame, "Times must be Periods", "Invalid time period",
                        JOptionPane.ERROR_MESSAGE);
                applyChanges = false;
            }

            try {
                kRecommend = Integer.parseInt(view.getKRecommendField().getText());
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
                    assignValuesToView();
                }
            }
        });
    }

    /**
     * Assign values to view.
     */
    public void assignValuesToView() {
        view.setOfferTime(Parameter.getParam().getOfferTime());
        view.setOrderTime(Parameter.getParam().getOrderTime());
        view.setExchangeTime(Parameter.getParam().getExchangeTime());
        view.setKRecommend(Parameter.getParam().getkRecommend());
        view.setScoreAParam(Parameter.getParam().getScoreAParam());
        view.setScoreBParam(Parameter.getParam().getScoreBParam());
        view.setValuationCost(Parameter.getParam().getValuationCost());
        view.setStoreAddress(Parameter.getParam().getStoreAddress());
        view.paintEverything();
    }
}