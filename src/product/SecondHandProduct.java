/**
 * 
 */
package product;

import java.time.*;

/**
 * Class name: SecondHandProduct
 * <p>
 * Description: It implements the second-hand products
 * @author Duna P.R.
 * @version 1.0
 * @see Product
 */
public class SecondHandProduct extends Product{
	
    private LocalDate valuationDate;
    private boolean available;
    private boolean paidValuation;
    private ConservationStatus status;
    //private double estimatedPrice;
    
    
    /*----------------------------------------------------------CONSTRUCTORS------------------------------------------------------------------*/
    /**
     * Creates a new second-hand product
     * 
	 * @param estimatedPrice, the estimated price of the product
	 * @param name, the name of the product
	 * @param description, the description of the product
	 * @param photo, the photo of the product
	 * @param type, the type of product
	 * @param valuationDate, the date of the valuation
	 * @param available, if the product is available
	 * @param paidValuation, if the valuation is paid
	 * @param status, the conservation status of the product
	 */
	public SecondHandProduct(double estimatedPrice, String name, String description, String photo, ProductType type, 
			LocalDate valuationDate, boolean available, boolean paidValuation, ConservationStatus status
			/*double estimatedPrice*/) {
		super(estimatedPrice, name, description, photo, type);
		this.valuationDate = valuationDate;
		this.available = available;
		this.paidValuation = paidValuation;
		this.status = status;
		//this.estimatedPrice = estimatedPrice;
	}

	/**
	 * Creates a new second-hand product with no valuation
	 * 
	 * @param name, the name of the product
	 * @param description, the description of the product
	 * @param photo, the photo of the product
	 * @param type, the type of product
	 * @param available, if the product is available
	 * @param paidValuation, if the valuation is paid
	 * @param status, the conservation status of the product
	 */
	public SecondHandProduct(String name, String description, String photo, ProductType type,
			boolean available, boolean paidValuation, ConservationStatus status) {
		this(0, name, description, photo, type, null, available, paidValuation, null);
		
	}
	
	/**
	 * Creates a new second-hand product that hasn't paid the valuation
	 * 
	 * @param name, the name of the product
	 * @param description, the description of the product
	 * @param photo, the photo of the product
	 * @param type, the type of product
	 * 
	 */
	public SecondHandProduct(String name, String description, String photo, ProductType type) {
		this(0, name, description, photo, type, null, false, false, null);
	}
	
/*----------------------------------------------------------GETTERS AND SETTERS---------------------------------------------------------------------------*/
	
	/**
	 * Obtains the valuation date
	 * 
	 * @return the valuationDate, the date of valuation
	 */
	public LocalDate getValuationDate() {
		return valuationDate;
	}

	/**
	 * Sets the valuation date
	 * 
	 * @param valuationDate the valuationDate to set
	 */
	public void setValuationDate(LocalDate valuationDate) {
		this.valuationDate = valuationDate;
	}

	/**
	 * Obtains if the product is available
	 * 
	 * @return true if the product is available, false if else
	 */
	public boolean isAvailable() {
		return available;
	}

	/**
	 * Sets if the product is available
	 * 
	 * @param available, true if the product is available, false if else
	 */
	public void setAvailable(boolean available) {
		this.available = available;
	}

	/**
	 * Obtains if the valuations is paid
	 * 
	 * @return true if the valuation is paid, false if else
	 */
	public boolean isPaidValuation() {
		return paidValuation;
	}

	/**
	 * Sets if the valuation was paid
	 * 
	 * @param paidValuation, true if the valuation is paid, false if else
	 */
	public void setPaidValuation(boolean paidValuation) {
		this.paidValuation = paidValuation;
	}
	
	/**
	 * Obtains the conservation status of the product 
	 * 
	 * @return the status, the conservation status
	 */
	public ConservationStatus getStatus() {
		return status;
	}

	/**
	 * Sets the conservation status of the product
	 * 
	 * @param status the status to set
	 */
	public void setStatus(ConservationStatus status) {
		this.status = status;
	}

	
	
/*----------------------------------------------------------METHODS---------------------------------------------------------------------------*/
	

	/**
	 * Changes the estimated price of the second-hand product
	 * 
	 * @param price, the new estimated price
	 */
	public void changeEstimatedPrice(double price) {
        this.changePrice(price);
    }
	
	/**
	 * Changes the conservation status of the second-hand product
	 * 
	 * @param status, the new conservation status
	 */
	public void changeConservationStatus(ConservationStatus status) {
		setStatus(status);
	}
	
	/**
	 * Changes the availability of the second-hand product
	 * 
	 * @param available, whether the product is available or not
	 */
	public void changeAvailability(boolean available) {
		setAvailable(available);
	}

	/**
	 * Marks that the valuation service was paid
	 */
	public void valuationWasPaid() {
		this.paidValuation = true;
	}

	/**
	 * Makes a valuation of a second-hand product
	 * 
	 * @param estimatedPrice, the estimated price
	 * @param status, the conservation status
	 */
	public void valuate(double estimatedPrice, ConservationStatus status) {
		this.changeEstimatedPrice(estimatedPrice);
		this.changeConservationStatus(status);
		this.valuationDate = LocalDate.now();
	}

	@Override
	public String toString() {
		return "SecondHandProduct [valuationDate=" + valuationDate + ", available=" + available + ", paidValuation="
				+ paidValuation + ", status=" + status + ", " + getId() + ", "+ getPrice() + ", "+ getName()
				+ ", " + getDescription() + ", " + getPhoto() + ", " + getType() + ", "
				+ getClass()
				+ "]";
	}
    
	
	//Faltan métodos de intercambio para más adelante
	
	
}
