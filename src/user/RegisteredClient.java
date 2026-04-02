/**
 *
 */
package user;

import order.*;
import product.*;
import exchange.*;
import search.*;

import java.time.LocalDate;
import java.util.*;

/**
 * It implements the registered client
 * @author Duna P.R.
 * @version 1.0
 * @see User
 */
public class RegisteredClient extends User {
	
    private LocalDate registerDate;
    private String dni;
    private Cart c;
    private ExchangeHistory exchangeHistory;
    private OrderHistory op;
    private Wallet wallet;
    private OrderHistory orderHistory;
    private Searcher searcher;
    private int numOrders;
    private int numExchanges;

    //searcher
    //sugestioner
    //offerhistory

    /**
     * Creates a new RegisteredClient
     * @param userName     the user's name
     * @param registerDate the registration date
     * @param dni          the user's dni
     * @param password     the user's password
     */
    public RegisteredClient(String userName, LocalDate registerDate, String dni, String password, boolean asc) {
        super(UserType.REGISTERED_CLIENT, password, userName, asc);
        this.registerDate = registerDate;
        this.dni = dni;
        this.c = new Cart();
        this.exchangeHistory = new ExchangeHistory(this);
        this.orderHistory = new OrderHistory(this);
        this.wallet = new Wallet();
        this.searcher = new Searcher(new SearchStoreProducts(true));
        this.numExchanges = 0;
        this.numOrders = 0;

        this.getSearcher().setTypes(SearchType.S_SECOND_HAND, SearchType.S_STORE);
    }

    /**
     * Creates a new RegisteredCliente with a set registration date(today)
     * @param userName the user's name
     * @param dni      the user's dni
     * @param password the user's password
     */
    public RegisteredClient(String userName, String dni, String password, boolean asc) {
        this(userName, LocalDate.now(), dni, password, asc);
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
    public void changePassword(String psswd) {
        //Tengo que hacer la comprobación de que la contraseña es segura
        super.changePassword(psswd);
    }

    /**
     * Obtains the client's order history
     *
     * @return the order history
     */
    public OrderHistory getOrderHistory(){
        return this.op;
    }

    /**
	 * @return the numOrders
	 */
	public int getNumOrders() {
		return numOrders;
	}

	/**
	 * @param numOrders the numOrders to set
	 */
	public void setNumOrders(int numOrders) {
		this.numOrders = numOrders;
	}

	/**
	 * @return the numExchanges
	 */
	public int getNumExchanges() {
		return numExchanges;
	}

	/**
	 * @param numExchanges the numExchanges to set
	 */
	public void setNumExchanges(int numExchanges) {
		this.numExchanges = numExchanges;
	}

	/**
     * Adds a new product to the wallet
     *
     * @param product, the new product
     */
    public void addProductWallet(SecondHandProduct product) {
    	this.wallet.addProducts(product);
    }

    /**
     * Removes products from the wallet
     *
     * @param products, the product to remove
     */
    public void removeProductWallet(SecondHandProduct...products) {
    	this.wallet.removeProducts(products);
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

    public List<SecondHandProduct> searchSecondHandProducts() {
        return this.searcher.browseSecondHandProduct();
    }

    public List<StoreProduct> searchStoreProduct() {
        return this.searcher.searchStoreProducts();
    }


    //requestValuation
    //makeAnOffer
    //browseNotifications
    //review

    @Override
    public String toString() {
        return super.toString();
    }
}