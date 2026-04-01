/**
 *
 */
package product;

import store.Store;

import java.time.LocalDate;

/**
 * Class name: SecondHandProduct
 * <p>
 * Description: It implements the second-hand products
 * @author Duna P.R. and Ana O.R.
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
	 * A second-hand product's general constructor
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
	public SecondHandProduct(String id, double estimatedPrice, String name, String description, String photo,
							 ProductType type, LocalDate valuationDate, boolean available, boolean paidValuation,
                             ConservationStatus status) {
		super(id, estimatedPrice, name, description, photo, type);
		this.valuationDate = valuationDate;
		this.available = available;
		this.paidValuation = paidValuation;
		this.status = status;
		Store.getInstance().addSecondHandProduct(this);
	}

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
		Store.getInstance().addSecondHandProduct(this);
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
		this.setEstimatedPrice(estimatedPrice);
		this.setConservationStatus(status);
		this.valuationDate = LocalDate.now();
	}

/*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/
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
	 * Changes the availability of the second-hand product
	 *
	 * @param available, whether the product is available or not
	 */
	public void setAvailability(boolean available) {
		setAvailable(available);
	}

	/**
	 * Changes the conservation status of the second-hand product
	 *
	 * @param status, the new conservation status
	 */
	public void setConservationStatus(ConservationStatus status) {
		setStatus(status);
	}

	/**
	 * Changes the estimated price of the second-hand product
	 *
	 * @param price, the new estimated price
	 */
	public void setEstimatedPrice(double price) {
        this.setPrice(price);
    }

/*--------------------------------------------------- TOSTRING ---------------------------------------------------*/
    @Override
    public String toString() {
        /* [TYPE;ID;NAME;DESC;PHOTO];VAL_DATE;AVAILABLE;PAID_VAL;STATUS */
        return super.toString() + ";" + this.valuationDate + this.available + ";" + this.paidValuation + ";" +
               this.status.name();
    }


	//Faltan métodos de intercambio para más adelante


}