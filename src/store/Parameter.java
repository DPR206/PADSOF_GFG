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
	
	/**
	 * Creates parameter
	 * 
	 */
	private Parameter() {
		OfferTime = Period.ofDays(5);
		OrderTime = Period.ofDays(7);
		this.valuationCost = 10;
	}

	/**
	 * @return the offerTime
	 */
	public Period getOfferTime() {
		return OfferTime;
	}

	/**
	 * @param offerTime the offerTime to set
	 */
	public void setOfferTime(Period offerTime) {
		OfferTime = offerTime;
	}

	/**
	 * @return the orderTime
	 */
	public Period getOrderTime() {
		return OrderTime;
	}

	/**
	 * @param orderTime the orderTime to set
	 */
	public void setOrderTime(Period orderTime) {
		OrderTime = orderTime;
	}

	/**
	 * @return the valuationCost
	 */
	public double getValuationCost() {
		return valuationCost;
	}

	/**
	 * @param valuationCost the valuationCost to set
	 */
	public void setValuationCost(double valuationCost) {
		this.valuationCost = valuationCost;
	}

	/**
	 * @return the param
	 */
	public static Parameter getParam() {
		return PARAM;
	}
	
	
	
}
