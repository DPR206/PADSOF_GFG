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
	
	

}
