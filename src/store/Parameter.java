/**
 *
 */
package store;

import java.time.*;

/**
 * It defines the store parameters
 * @author Duna P.R.
 * @version 1.0
 */
public class Parameter {

	private static final Parameter PARAM = new Parameter();

	private Period OfferTime;
	private Period OrderTime;
	private double valuationCost;
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

	/**
	 * Creates parameter
	 *
	 */
	private Parameter() {
		OfferTime = Period.ofDays(5);
		OrderTime = Period.ofDays(7);
		this.valuationCost = 10;
		/* scoreWeight = a*<score> + b */
		this.scoreAParam = 0.5;
		this.scoreBParam = 0.5;
		this.itemsPerPage = 35;
	}

/*--------------------------------------------------SETTERS AND GETTERS---------------------------------------------------------------------*/

	/**
	 * Obtains the offer time
	 * @return the offerTime, the expiration time of the offer
	 */
	public Period getOfferTime() {
		return OfferTime;
	}

	/**
	 * Sets the expiration time of an offer
	 *
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
	 *
	 * @param orderTime the orderTime to set
	 */
	private void setOrderTime(Period orderTime) {
		OrderTime = orderTime;
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
	 *
	 * @param valuationCost the valuationCost to set
	 */
	private void setValuationCost(double valuationCost) {
		this.valuationCost = valuationCost;
	}

	/**
	 * Obtains the parameter
	 * @return the param, the parameter of the store
	 */
	public static Parameter getParam() {
		return PARAM;
	}

	/**
	 * It gets the score a param (scoreWeight = a*score + b)
	 * @return the score a param
	 */
	public double getScoreAParam() {
		return this.scoreAParam;
	}

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

	/*------------------------------------------------------------------METHODS------------------------------------------------------------------*/

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
	 * It allows the parameter's to be saved
	 * @return the parameter's info
	 */
	@Override
	public String toString(){
		return this.OfferTime + ";" + this.OrderTime + ";" + this.valuationCost;
	}
}