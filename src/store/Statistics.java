/**
 * 
 */
package store;

import java.util.*;
import java.time.*;

import user.RegisteredClient;
import product.*;

/**
 * It implements the statistics
 * 
 * @author Duna P.R.
 * @version 1.1
 */
public class Statistics {
	private static Statistics INSTANCE = null;
	
	private HashMap<Month, Double> revenueByMonth;
	private RegisteredClient clients;
	private Category categories;
	private double revenue_valuation;
	
}
