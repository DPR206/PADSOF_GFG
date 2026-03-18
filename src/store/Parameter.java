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

	private static final Parameter PARAM;
	
	private Period OfferTime;
	private Period OrderTime;
	private double valuationCost;
	
	/**
	 * Creates
	 * @param offerTime
	 * @param orderTime
	 * @param valuationCost
	 */
	private Parameter(Period offerTime, Period orderTime, double valuationCost) {
		OfferTime = offerTime;
		OrderTime = orderTime;
		this.valuationCost = valuationCost;
	}
	
	

}
