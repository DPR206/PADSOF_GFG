package model.user;

import es.uam.eps.padsof.telecard.*;
import model.exchange.*;
import model.product.*;
import model.notification.*;
import model.order.Cart;
import model.order.OrderHistory;
import model.search.*;
import model.store.Recommender;
import model.store.Store;
import model.utilities.exceptions.InvalidDni;
import model.utilities.exceptions.PasswordNotValid;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * It implements the registered client
 * @author Duna P.R.
 * @version 2.0
 * @see User
 */
public class RegisteredClient extends User implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L; /* Para el Save & Load */

    private LocalDate registerDate;
    private String dni;
    private Cart c;
    private ExchangeHistory exchangeHistory;
    private Wallet wallet;
    private OrderHistory orderHistory;
    private OfferHistory offerHistory;
    private NotificationHistory notificationHistory;
    private Searcher searcher;
    private int numOrders;
    private int numExchanges;

    /**
     * Creates a new registered client
     *
	 * @param type the type of user
	 * @param pwd the password of the client
	 * @param userName the user's nickname
	 * @param actualID the client's id
	 * @param asc if the search has ascending order
	 * @param registerDate the date of registration
	 * @param dni the user's dni
	 * @param c the user's cart
	 * @param exchangeHistory the user's exchange history
	 * @param wallet the user's wallet
	 * @param orderHistory the user's order history
	 * @param offerHistory the user's offer history
	 * @param notificationHistory the user's notification history
	 * @param searcher the user's searcher
	 * @param numOrders the user's number of orders
	 * @param numExchanges the user's number of exchanges
	 */
	public RegisteredClient(UserType type, String pwd, String userName, String actualID, boolean asc,
			LocalDate registerDate, String dni, Cart c, ExchangeHistory exchangeHistory, Wallet wallet,
			OrderHistory orderHistory, OfferHistory offerHistory, NotificationHistory notificationHistory,
			Searcher searcher, int numOrders, int numExchanges) {
		super(type, pwd, userName, actualID, asc);
		this.registerDate = registerDate;
		this.dni = dni;
		this.c = c;
		this.exchangeHistory = exchangeHistory;
		this.wallet = wallet;
		this.orderHistory = orderHistory;
		this.offerHistory = offerHistory;
		this.notificationHistory = notificationHistory;
		this.searcher = searcher;
		this.numOrders = numOrders;
		this.numExchanges = numExchanges;
	}

	/**
     * Creates a new RegisteredClient
     * @param userName     the user's name
     * @param registerDate the registration date
     * @param dni          the user's dni
     * @param password     the user's password
     * @param asc the products' order in the search
     */
    public RegisteredClient(String userName, LocalDate registerDate, String dni, String password, boolean asc) {
        super(UserType.REGISTERED_CLIENT, password, userName, asc);
        this.registerDate = registerDate;
        this.dni = dni;
        this.c = new Cart();
        this.wallet = new Wallet();
        this.searcher = new Searcher(new SearchStoreProducts(true));
        this.numExchanges = 0;
        this.numOrders = 0;
        this.setHistories();

        this.getSearcher().setTypes(SearchType.S_SECOND_HAND, SearchType.S_STORE);
    }


	/**
     * Creates a new RegisteredCliente with a set registration date(today)
     * @param userName the user's name
     * @param dni      the user's dni
     * @param password the user's password
     * @param asc the products' order in the search
     */
    public RegisteredClient(String userName, String dni, String password, boolean asc) {
        this(userName, LocalDate.now(), dni, password, asc);

    }

    /**
     * Creates the client's new histories
     */
    public void setHistories() {
    	 this.exchangeHistory = new ExchangeHistory(this);
    	 this.orderHistory = new OrderHistory(this);
         this.offerHistory = new OfferHistory(this);
         this.notificationHistory = new NotificationHistory(this);
    }

    /**
     * Obtains the client's registration date
     * @return the registerDate the registration date
     */
    public LocalDate getRegisterDate() {
        return registerDate;
    }

    /**
     * It sets the registration date
     * @param registerDate the registerDate to set
     */
    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }

    /**
     * Obtains the client's dni
     * @return the dni the client's dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * It sets the dni
     * @param dni the dni to set
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Obtains the exchange history
     *
     * @return ExchangeHistory, the exchange history
     */
    public ExchangeHistory getExchangeHistory() {
    	return exchangeHistory;
    }

    /**
     * It changes the client's password
     * @param psswd the new password
     */
    public void changePassword(String psswd) throws InvalidDni, PasswordNotValid {
    	super.changePassword(psswd);
    }

    /**
     * Obtains the client's order history
     *
     * @return the order history
     */
    public OrderHistory getOrderHistory(){
        return this.orderHistory;
    }

	/**
	 * Obtains the offer history
	 *
	 * @return the offer history
	 */
	public OfferHistory getOfferHistory() {
		return offerHistory;
	}

	/**
	 * Obtains the notification history
	 *
	 * @return the notificationHistory the client's notification history
	 */
	public NotificationHistory getNotificationHistory() {
		return notificationHistory;
	}

	/**
     * Obtains the cart of a client
     *
	 * @return the cart
	 */
	public Cart getC() {
		return c;
	}

	/**
	 * Obtains the wallet of a client
	 * @return the wallet
	 */
	public Wallet getWallet() {
		return wallet;
	}

	/**
	 * Obtains the client's number of orders
	 *
	 * @return the number of orders
	 */
	public int getNumOrders() {
		return numOrders;
	}

	/**
	 * Sets the client's number of orders
	 * @param numOrders the numOrders to set
	 */
	public void setNumOrders(int numOrders) {
		this.numOrders = numOrders;
	}

	/**
	 * Obtains the client's number of exchanges
	 *
	 * @return the number of exchanges
	 */
	public int getNumExchanges() {
		return numExchanges;
	}

	/**
	 * Sets the client's number of exchanges
	 *
	 * @param numExchanges the numExchanges to set
	 */
	public void setNumExchanges(int numExchanges) {
		this.numExchanges = numExchanges;
	}

/*-----------------------------------------------------METHODS----------------------------------------------------------------*/

	/**
     * Adds a new product to the wallet
     *
     * @param product, the new product
     */
    public void addProductWallet(SecondHandProduct product) {
    	this.wallet.addProducts(product);
    	Store.getInstance().addSecondHandProduct(product);
    }

    /**
     * Removes products from the wallet
     *
     * @param products, the product to remove
     */
    public void removeProductWallet(SecondHandProduct...products) {
    	this.wallet.removeProducts(products);
    	for(SecondHandProduct p : products)
    		Store.getInstance().deleteSecondHandProduct(p);
    }

    /**
     * Adds a product to the cart
     * @param product, the product to add
     *
     */
    public void addCart(StoreProduct product) {
    	this.c.addProduct(product);
    }

    /**
     * Adds a pack to the cart
     *
     * @param pack, the pack to add
     *
     */
    public void addCart(Pack pack) {
    	this.c.addPack(pack);
    }

    /**
     * Removes a product from the cart
     *
     * @param product, the product to remove
     *
     */
    public void deleteCart(StoreProduct product){
    	this.c.cancelProduct(product);
    }

    /**
     * Removes a pack from the cart
     *
     * @param pack, the pack to remove
     *
     */
    public void deleteCart(Pack pack) {
    	this.c.cancelPack(pack);
    }


    /**
     * Searches for the second-hand products
     * @return the results in a list of second-hand products
     */
    public List<SecondHandProduct> searchSecondHandProducts() {
        return this.searcher.browseSecondHandProduct();
    }

    /**
     * Searches for the store products based on the filters
     * @return the results in a list of store products
     */
    public List<StoreProduct> searchStoreProduct() {
        return this.searcher.searchStoreProducts();
    }

    /**
     * Increase the number of orders by 1
     */
    public void increaseNumOrders()
    {
    	this.numOrders++;
    }

    /**
     * Increases the number of orders
     *
     * @param i the number to increase
     */
    public void increaseNumOrders(int i) {
    	if(i > 0)
    		this.numOrders += i;
    }

    /**
     * Increases the number of exchanges by 1
     */
    public void increaseNumExchanges() {
    	this.numExchanges++;
    }

    /**
     * Increases the number of exchanges
     *
     * @param i the number to increase
     */
    public void increaseNumExchanges(int i) {
    	this.numExchanges += i;
    }

    /**
     * Makes the buying process of the cart
     */
    public void buy() {
        try {
			this.c.payOrder();
		} catch (InvalidCardNumberException e) {
			System.out.println("Invalid card number");
		} catch (FailedInternetConnectionException e) {
			System.out.println("Failed Internet connection");
		} catch (OrderRejectedException e) {
			System.out.println("Order rejected");
		}
    }

   /**
    * Requests a valuation for a second-hand product
    * @param sp the second-hand product to evaluate
    */
    public void requestValuation(SecondHandProduct sp) {

    	try {
			sp.payValuation();
		} catch (InvalidCardNumberException e) {
			System.out.println("Invalid Card Number");
		} catch (FailedInternetConnectionException e) {
			System.out.println("Failed Internet Connection");
		} catch (OrderRejectedException e) {
			System.out.println("Order Rejected");
		}

    	NotificationEmployeeValuation
			    notification = new NotificationEmployeeValuation(LocalDateTime.now(), false, true, NotificationType.EMPLOYEE_VALUATION);
    	notification.FullNotification(sp);
    	Store.getInstance().sendNotificationEmployees(notification);
    }

    /**
     * Adds a review to a store product
     *
     * @param sp the store product bought
     * @param review the review of the product
     */
    public void reviewProduct(StoreProduct sp, Review review) {
    	sp.addReview(this, review);
    }

    /**
     * Creates a review for a store product
     * @param sp the store product bought
     * @param scoring the scoring from 0 to 5
     * @param comment the comment on the product
     */
    public void reviewProduct(StoreProduct sp, int scoring, String comment) {
    	if(scoring >= 0  && scoring <= 5) {
    		Review r = new Review(scoring, comment, this);
    		sp.addReview(this, r);
    	}
    }

    /**
     * Makes an offer for a second-hand product
     * @param theirProduct the product the client is interested in
     * @param myProducts the products the clients offers
     */
    public void makeAnOfferOneProduct(SecondHandProduct theirProduct, SecondHandProduct...myProducts) {
		ArrayList<SecondHandProduct> originProducts = new ArrayList<>(List.of(myProducts));
		ArrayList<SecondHandProduct> destinationProducts = new ArrayList<>(List.of(theirProduct));
    	Offer offer = new Offer(this, theirProduct.getOwner(), originProducts, destinationProducts);
    	this.getOfferHistory().addOffer(offer);
    	theirProduct.getOwner().getOfferHistory().addOffer(offer);
    }

    /**
     * Makes an offer for several second-hand products
     * @param theirProducts the products the client is interested in
     * @param myProducts the products the client offers
     */
    public void makeAnOffer(ArrayList<SecondHandProduct> theirProducts, ArrayList<SecondHandProduct> myProducts) {
    	/*Offer offer = new Offer(this, theirProducts.getFirst().getOwner(), myProducts, theirProducts);
    	this.getOfferHistory().addOffer(offer);
    	theirProducts.getFirst().getOwner().getOfferHistory().addOffer(offer);*/ //DUE: Arreglar esto
		System.out.println("Fix this please");
    }

    /**
     * The notifications of the client
     *
     * @return a list with all the notifications that haven't been erased ordered by most recent
     */
    public List<Notification> browseNotifications(){
    	List<Notification> notifications = new ArrayList<>();
    	for(Notification n : this.notificationHistory.getNotificationsSorted())
    		if(n.isVisible())
    			notifications.add(n);
    	return notifications;
    }

	/**
	 * It allows the registered client to browse recommendations on products imilar to those they bought previously
	 * @return the recommendation
	 */
	public List<StoreProduct> browseSimilarProducts() {
		return Recommender.getInstance().recommendSimilarProducts(this);
	}

    /**
     * Changes an interest in a type of notification
     *
     * @param type the type of notification
     * @param interest true if interested, false if else
     */
    public void changeNotificationInterest(NotificationType type, boolean interest) {
    	this.notificationHistory.getSettings().changeInterest(type, interest);
    }

    /**
     * A string with the client's information
     *
     * @return a string with the client's information
     */
    @Override
	public String toString() {
		return "RegisteredClient [registerDate=" + registerDate + ", dni=" + dni + ", c=" + c + ", exchangeHistory="
				+ exchangeHistory + ", wallet=" + wallet + ", orderHistory=" + getOrderHistory() + ", offerHistory="
				+ offerHistory + ", notificationHistory=" + notificationHistory + ", searcher=" + searcher
				+ ", numOrders=" + numOrders + ", numExchanges=" + numExchanges + ", toString()=" + super.toString()
				+ "]";
	}

}