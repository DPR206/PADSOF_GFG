/**
 *
 */
package store;

import java.time.*;

/**
 * Class Name: Parameter
 * <p>
 * Description: It defines the store parameters
 * @author Duna P.R.
 * @version 1.0
 */
public class Parameter {

	private static final Parameter PARAM = new Parameter();

	private Period OfferTime;
	private Period OrderTime;
	private double valuationCost;
	private double scoreAParam;
	private double scoreBParam;

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
	 * Sets the expire time of an offer
	 *
	 * @param offerTime the offerTime to set
	 */
	private void setOfferTime(Period offerTime) {
		OfferTime = offerTime;
	}

	/**
	 * Obtains the expire time of an order
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
	public void setScoreAParam(double newScoreAParam) {
		this.scoreAParam = newScoreAParam;
	}

	/**
	 * It gets the score b param (scoreWeight = a*score + b)
	 * @return the score b param
	 */
	public double getScoreBParam() {
		return this.scoreBParam;
	}

	/**
	 * It gets the score b param (scoreWeight = a*score + b)
	 * @param newScoreBParam the new score b param
	 */
	public void setScoreBParam(double newScoreBParam) {
		this.scoreBParam = newScoreBParam;
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
	 * It allows the parameter's to be saved
	 * @return the parameter's info
	 */
	@Override
	public String toString(){
		return this.OfferTime + ";" + this.OrderTime + ";" + this.valuationCost;
	}
}