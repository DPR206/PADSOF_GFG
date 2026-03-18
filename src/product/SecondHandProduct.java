
package product;

import java.util.*;

/**
 * Class name: SecondHandProduct
 * <p>
 * Description: It implements the second hand products
 * @author Duna P.R.
 * @version 1.0
 * @see
 */
public class SecondHandProduct extends Product{
	
    private Date valuationDate;
    private boolean available;
    private boolean paidValuation;
    //private double estimatedPrice;
    
    

    /**
     * Create a new secondhand product
     * 
	 * @param price, the estimated price of the product
	 * @param name, the name of the product
	 * @param description, the description of the product
	 * @param photo, the photo of the product
	 * @param type, the type of product
	 * @param valuationDate, the date of the valuation
	 * @param available, if the product is available
	 * @param paidValuation, if the valuation is paid
	 * 
	 */
	public SecondHandProduct(double estimatedPrice, String name, String description, String photo, ProductType type, 
			Date valuationDate, boolean avalable, boolean paidValuation
			/*double estimatedPrice*/) {
		super(estimatedPrice, name, description, photo, type);
		this.valuationDate = valuationDate;
		this.available = avalable;
		this.paidValuation = paidValuation;
		//this.estimatedPrice = estimatedPrice;
	}

	public void changeEstimatedPrice(double price) {
        this.changePrice(price);
    }

    public int getId() {
        return 0; // Sustituir y rellenar
    }
}
