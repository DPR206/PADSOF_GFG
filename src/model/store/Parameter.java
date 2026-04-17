/**
 *
 */
package model.store;

import java.time.Period;

/**
 * It defines the store parameters
 * @author Duna P.R.
 * @version 1.0
 */
public class Parameter {

    private static Parameter PARAM = new Parameter();

    private Period OfferTime;
    private Period OrderTime;
    private double valuationCost;
    private String storeAddress;
    private Period ExchangeTime;
    /**
     * The maximum number of products the recommender will return
     */
    private int kRecommend;
    /**
     * Param a for the recommender following: weightedScore = (a*score + b)
     */
    private double scoreAParam;
    /**
     * Param b for the recommender following: weightedScore = (a*score + b)
     */
    private double scoreBParam;
    /**
     * The number of lines that can be printed from a certain list, used for printing information
     */
    private int itemsPerPage;

/*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    /**
     * Creates parameter
     *
     */
    private Parameter() {
        OfferTime = Period.ofDays(5);
        OrderTime = Period.ofDays(7);
        ExchangeTime = Period.ofDays(7);
        this.valuationCost = 10;
        this.storeAddress = "C/ GFG nº20";
        /* scoreWeight = a*<score> + b */
        this.scoreAParam = 0.5;
        this.scoreBParam = 0.5;
        this.itemsPerPage = 10;
    }

    /*--------------------------------------------------SETTERS AND GETTERS---------------------------------------------------------------------*/

/*----------------------------------------------------- MISC -----------------------------------------------------*/
    /**
     * Obtains the parameter
     * @return the param, the parameter of the store
     */
    public static Parameter getParam() {
        if (PARAM == null) {
            PARAM = new Parameter();
        }
        return PARAM;
    }

    /**
     * Change the expiration of an offer
     * @param newOfferTime the new offer time
     */
    public void changeOfferTime(Period newOfferTime) {
        this.setOfferTime(newOfferTime);
    }

    /**
     * Change the expiration of an order
     * @param newOrderTime the new order time
     */
    public void changeOrderTime(Period newOrderTime) {
        this.setOrderTime(newOrderTime);
    }

    /**
     * Changes the time it will pass since the offer is accepted and the exchange is done
     * @param newExchangeTime the new exchange time
     */
    public void changeExchangeTime(Period newExchangeTime) {
        this.setExchangeTime(newExchangeTime);
    }

    /**
     * Change the valuation cost
     * @param newCost the new cost
     */
    public void changeValuationCost(double newCost) {
        this.setValuationCost(newCost);
    }

    /**
     * It changes the score a param (scoreWeight = a*score + b)
     * @param newScoreAParam the new score a param
     */
    public void changeScoreAParam(double newScoreAParam) {
        this.setScoreAParam(newScoreAParam);
    }

    /**
     * It changes the score b param (weightedScore = a*score + b)
     * @param newScoreBParam the new score b param
     */
    public void changeScoreBParam(double newScoreBParam) {
        this.setScoreBParam(newScoreBParam);
    }

    /**
     * It changes the number of lines that can be printed from a certain list
     * @param newItemsPerPage the new number of lines that can be printed from a certain list
     */
    public void changeItemsPerPage(int newItemsPerPage) {
        this.setItemsPerPage(newItemsPerPage);
    }

    /**
     * It changes the store's address
     * @param newAddress the new address to set
     */
    public void changeAddress(String newAddress) {
        this.setStoreAddress(newAddress);
    }

    /**
     * It changes the store's maximum number of products the recommender will return
     * @param newKRecommend the new maximum number of products the recommender will return
     */
    public void changeKRecommend(int newKRecommend) {
        this.setkRecommend(newKRecommend);
    }

/*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/
    /**
     * Obtains the amount of time it will pass before the exchange is done
     * @return the exchangeTime
     */
    public Period getExchangeTime() {
        return ExchangeTime;
    }

    /**
     * Sets the time it will pass since the offer is accepted and the exchange is done
     * @param exchangeTime the exchangeTime to set
     */
    private void setExchangeTime(Period exchangeTime) {
        ExchangeTime = exchangeTime;
    }

    /**
     * It gets the number of lines that can be printed from a certain list
     * @return the number of lines that can be printed from a certain list
     */
    public int getItemsPerPage() {
        return itemsPerPage;
    }

    /**
     * It sets the number of lines that can be printed from a certain list
     * @param newItemsPerPage the new number of lines that can be printed from a certain list
     */
    private void setItemsPerPage(int newItemsPerPage) {
        this.itemsPerPage = newItemsPerPage;
    }

    /**
     * Obtains the offer time
     * @return the offerTime, the expiration time of the offer
     */
    public Period getOfferTime() {
        return OfferTime;
    }

    /**
     * Sets the expiration time of an offer
     * @param offerTime the offerTime to set
     */
    private void setOfferTime(Period offerTime) {
        OfferTime = offerTime;
    }

    /**
     * Obtains the expiration time of an order
     * @return the orderTime, the expiration time of an order
     */
    public Period getOrderTime() {
        return OrderTime;
    }

    /**
     * Obtains the expiration time of the order
     * @param orderTime the orderTime to set
     */
    private void setOrderTime(Period orderTime) {
        OrderTime = orderTime;
    }

    /**
     * It gets the score a param (scoreWeight = a*score + b)
     * @return the score a param
     */
    public double getScoreAParam() {
        return this.scoreAParam;
    }

    /*------------------------------------------------------------------METHODS------------------------------------------------------------------*/

    /**
     * It sets the score a param (scoreWeight = a*score + b)
     * @param newScoreAParam the new score a param
     */
    private void setScoreAParam(double newScoreAParam) {
        this.scoreAParam = newScoreAParam;
    }

    /**
     * It gets the score b param (weightedScore = a*score + b)
     * @return the score b param
     */
    public double getScoreBParam() {
        return this.scoreBParam;
    }

    /**
     * It gets the score b param (weightedScore = a*score + b)
     * @param newScoreBParam the new score b param
     */
    private void setScoreBParam(double newScoreBParam) {
        this.scoreBParam = newScoreBParam;
    }

    /**
     * Obtains the store's address
     * @return the storeAddress
     */
    public String getStoreAddress() {
        return storeAddress;
    }

    /**
     * Sets the store's address
     * @param storeAddress the storeAddress to set
     */
    private void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    /**
     * Obtains the valuation cost
     * @return the valuationCost, the valuation cost
     */
    public double getValuationCost() {
        return valuationCost;
    }

    /**
     * Sets the cost of a valuation
     * @param valuationCost the valuationCost to set
     */
    private void setValuationCost(double valuationCost) {
        this.valuationCost = valuationCost;
    }

    /**
     * Gets the maximum number of products the recommender will return
     * @return the maximum number of products the recommender will return
     */
    public int getkRecommend() {
        return kRecommend;
    }

    /**
     * Sets the maximum number of products the recommender will return
     * @param newKRecommend the new maximum number of products the recommender will return
     */
    public void setkRecommend(int newKRecommend) {
        this.kRecommend = newKRecommend;
    }

/*--------------------------------------------------- TOSTRING ---------------------------------------------------*/
    /**
     * It allows the parameter's to be saved
     * @return the parameter's info
     */
    @Override
    public String toString() {
        return "Parameter [OfferTime=" + OfferTime + ", OrderTime=" + OrderTime + ", valuationCost=" + valuationCost +
               ", storeAddress=" + storeAddress + ", ExchangeTime=" + ExchangeTime + ", scoreAParam=" + scoreAParam +
               ", scoreBParam=" + scoreBParam + ", itemsPerPage=" + itemsPerPage + "]";
    }

}