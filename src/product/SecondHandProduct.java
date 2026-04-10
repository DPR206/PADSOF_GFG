/**
 *
 */
package product;

import es.uam.eps.padsof.telecard.*;
import notification.*;
import store.*;
import user.RegisteredClient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Scanner;

/**
 * It implements the second-hand products
 *
 * @author Duna P.R. and Ana O.R.
 * @version 1.5
 * @see Product
 */
public class SecondHandProduct extends Product{

    private LocalDate valuationDate;
    private boolean available;
    private boolean paidValuation;
    private ConservationStatus status;
    private RegisteredClient owner;
    //private double estimatedPrice;


/*----------------------------------------------------------CONSTRUCTORS------------------------------------------------------------------*/

	/**
	 * A second-hand product's general constructor
	 *
	 * @param id the exchange's id
	 * @param estimatedPrice, the estimated price of the product
	 * @param name, the name of the product
	 * @param description, the description of the product
	 * @param photo, the photo of the product
	 * @param type, the type of product
	 * @param valuationDate, the date of the valuation
	 * @param available, if the product is available
	 * @param paidValuation, if the valuation is paid
	 * @param status, the conservation status of the product
	 * @param owner, the owner of the product
	 */
	public SecondHandProduct(String id, double estimatedPrice, String name, String description, String photo,
							 ProductType type, LocalDate valuationDate, boolean available, boolean paidValuation,
                             ConservationStatus status, RegisteredClient owner) {
		super(id, estimatedPrice, name, description, photo, type);
		this.valuationDate = valuationDate;
		this.available = available;
		this.paidValuation = paidValuation;
		this.status = status;
		this.owner = owner;
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
	 * @param owner, the owner of the product
	 */
	public SecondHandProduct(double estimatedPrice, String name, String description, String photo, ProductType type,
			LocalDate valuationDate, boolean available, boolean paidValuation, ConservationStatus status, RegisteredClient owner
			/*double estimatedPrice*/) {
		super(estimatedPrice, name, description, photo, type);
		this.valuationDate = valuationDate;
		this.available = available;
		this.paidValuation = paidValuation;
		this.status = status;
		this.owner = owner;
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
	 * @param owner, the owner of the product
	 */
	public SecondHandProduct(String name, String description, String photo, ProductType type,
			boolean available, boolean paidValuation, ConservationStatus status, RegisteredClient owner) {
		this(0, name, description, photo, type, null, available, paidValuation, null, owner);

	}

	/**
	 * Creates a new second-hand product that hasn't paid the valuation
	 *
	 * @param name, the name of the product
	 * @param description, the description of the product
	 * @param photo, the photo of the product
	 * @param type, the type of product
	 * @param owner, the owner of the product
	 */
	public SecondHandProduct(String name, String description, String photo, ProductType type, RegisteredClient owner) {
		this(0, name, description, photo, type, null, false, false, null, owner);
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
	 * Makes a valuation of a second-hand product with default valuation date
	 *
	 * @param estimatedPrice, the estimated price
	 * @param status, the conservation status
	 */
	public void valuate(double estimatedPrice, ConservationStatus status) {
		this.setEstimatedPrice(estimatedPrice);
		this.setConservationStatus(status);
		this.valuationDate = LocalDate.now();
		NotificationNewSecondHand notification = new NotificationNewSecondHand(LocalDateTime.now(), false, true,
                NotificationType.NEW_SECONDHAND_PRODUCT);
        notification.FullNotification(this);
        Store.getInstance().sendNotificationClients(notification);
	}

	/**
	 * Makes a valuation of a second-hand product
	 *
	 * @param estimatedPrice, the estimated price
	 * @param status, the conservation status
	 * @param date the valuation date
	 */
	public void valuate(double estimatedPrice, ConservationStatus status, LocalDate date) {
		this.setEstimatedPrice(estimatedPrice);
		this.setConservationStatus(status);
		this.setValuationDate(date);
		NotificationNewSecondHand notification = new NotificationNewSecondHand(LocalDateTime.now(), false, true,
                NotificationType.NEW_SECONDHAND_PRODUCT);
        notification.FullNotification(this);
        Store.getInstance().sendNotificationClients(notification);
	}

	/**
	 * It prints the product's info
	 */
	@Override
	public void bigPrintInfo() {
		/* [TYPE;ID;NAME;DESC;PHOTO];VAL_DATE;AVAILABLE;PAID_VAL;STATUS */
		super.bigPrintInfo();
		System.out.println("Conservation Status: " + this.status+ "\n");
	}

	/**
	 * It prints the product's info when managed
	 */
	@Override
	public void printAllInfo() {
		this.bigPrintInfo();
		System.out.println(" ");
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

	/**
	 * Obtains the owner of the product
	 *
	 * @return the owner
	 */
	public RegisteredClient getOwner() {
		return owner;
	}

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

/*----------------------------------------------------METHODS-----------------------------------------------------*/
	/**
	 * Pays the valuation of a second-hand product
	 *
	 * @throws InvalidCardNumberException the card number was invalid
	 * @throws FailedInternetConnectionException the Internet connection failed
	 * @throws OrderRejectedException the order was rejected
	 */
	public void payValuation() throws InvalidCardNumberException,
	FailedInternetConnectionException, OrderRejectedException {

		Scanner sc = new Scanner(System.in);
		try {
			System.out.print("Introduce tu número de tarjeta: ");
			String cardNumber = sc.next();
			System.out.println(TeleChargeAndPaySystem.isValidCardNumber(cardNumber));
			TeleChargeAndPaySystem.charge(cardNumber, "Valuation", Parameter.getParam().getValuationCost(), true);
		} catch (InvalidCardNumberException _) {} // revisar
		this.setPaidValuation(true);
		Statistics.getINSTANCE().addRevenue((Double)Parameter.getParam().getValuationCost(), RevenueType.VALUATION, LocalDate.now(),
											Collections.emptyList());

		NotificationPayment notification = new NotificationPayment(LocalDateTime.now(), false, true, NotificationType.PAYMENT);
		notification.FullNotification("valuation");
		this.owner.getNotificationHistory().addNotification(notification);

		NotificationEmployeeValuation notification2 = new NotificationEmployeeValuation (LocalDateTime.now(), false, true,
														NotificationType.EMPLOYEE_VALUATION);
		notification2.FullNotification(this);
		Store.getInstance().sendNotificationEmployees(notification2);
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