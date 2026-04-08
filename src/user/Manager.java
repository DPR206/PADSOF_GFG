package user;

import discount.*;
import exchange.Exchange;
import notification.NotificationDiscount;
import notification.NotificationType;
import order.Order;
import product.*;
import search.SearchType;
import store.Parameter;
import store.Store;

import java.io.IOException;
import java.time.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * It implements the manager of the store
 * @author Sofía C.L.
 * @version 1.4
 * @see User
 */
public class Manager extends User {
    private static Manager INSTANCE = null;
    private final Store s = Store.getInstance();
    /** Store permission necessary for the manager to do its functions */
    private StorePermission sp;

    private Parameter parameter;


/*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    /**
     * Creates a manager
     * @param pwd the password
     * @param userName the user's name
     * @param storePermission the store permission
     * @param p the parameters of the store
     * @param asc the results' order in the search
     */
    private Manager(String pwd, String userName, StorePermission storePermission, Parameter p, boolean asc) {
        super(UserType.MANAGER, pwd, userName, asc);

        this.parameter = p;
        this.sp = new StorePermission();

        this.getSearcher().setTypes(SearchType.S_STORE);
        Store.getInstance().getUsers().put(userName, this);
}

/*----------------------------------------------------- MISC -----------------------------------------------------*/
    /**
     * Obtains the maneger's instance
     * @return the manager of the store
     */
    public static Manager getInstance() {
        if (Manager.INSTANCE == null) {
            Manager.INSTANCE = new Manager("password", "manager", new StorePermission(), Parameter.getParam(), true);
        }
        return Manager.INSTANCE;
    }

    /**
     * Searches for the store products based on the filters
     * @return the products the list of products found
     */
    public List<StoreProduct> searchStoreProduct() {
        return this.getSearcher().searchStoreProducts();
    }

    /**
     * Searches for the store products based on the filters and categories
     * @param categories the categories to filter by
     * @return the products the list of products found
     */
    public List<StoreProduct> searchStoreProductCategory(Category...categories){
    	return this.getSearcher().searchByCategory(categories);
    }

    /**
     * Adds and creates new figurine
     * @param price the price of the figurine
     * @param name the name of the figurine
     * @param description  the description of the figurine
     * @param photo the photo of the figurine
     * @param stock the figurine's stock
     * @param dimensions the figurine's dimensions
     * @param brand the figurine's brand
     * @param material the figurine's material
     * @param categories the figurine's categories
     *
     * @return true if the figurine was added, false if else
     */
    public boolean addFigurine(double price, String name, String description, String photo, int stock, String dimensions, String brand, String material, Category... categories) {
        if (this.sp != null) {
            this.sp.addFigurine(price, name, description, photo, stock, dimensions, brand, material, categories);
            return true;
        }
        System.err.println("You have no permission to do that...");
        return false;
    }

    /**
     * Adds and creates new products from a file
     *
     * @param filename the name of the file
     * @return true if the product's were added successfully, false if else
     */
    public boolean addProductFromFile(String filename) {
    	try {
			return this.sp.addProductByFile(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return false;
    }

    /**
     * Adds and creates new game
     * @param price the price of the game
     * @param name the name of the game
     * @param description the description of the game
     * @param photo the photo of the comic
     * @param stock the game's stock
     * @param numPlayers the game's number of players
     * @param ageRange the game's age range
     * @param gameStyle the style of the game
     * @param categories the game's categories
     *
     * @return true if the game was added, false if else
     */
    public boolean addGame(double price, String name, String description, String photo, int stock, int numPlayers,
        String ageRange, GameStyle gameStyle, Category... categories) {
		if (this.sp != null) {
			this.sp.addGame(price, name, description, photo, stock, numPlayers, ageRange, gameStyle, categories);
			return true;
		}
		System.err.println("You have no permission to do that...");
		return false;
	}

    /**
     * Adds and creates new comic
     * @param price the price of the comic
     * @param name the name of the comic
     * @param description the description of the comic
     * @param photo the photo of the comic
     * @param stock the comic's stock
     * @param numPages the comic's number of pages
     * @param year the year of publication
     * @param author the comic's author
     * @param editorial the comic's editorial
     * @param categories the comic's categories
     *
     * @return true if the comic was added, false if else
     */
    public boolean addComic(double price, String name, String description, String photo, int stock, int numPages,
        Year year, String author, String editorial, Category... categories) {
		if (this.sp != null) {
			this.sp.addComic(price, name, description, photo, stock, numPages, year, author, editorial, categories);
			return true;
		}
		System.err.println("You have no permission to do that...");
		return false;
    }

    /**
     * Adds a new employee
     *
     *  @param password, the password of the employee
     *  @param userName, the username of the employee
     *  @param permission, the permission it has
     */
    public void addEmployee(String password, String userName, Permission permission) {
        Employee emp = new Employee(password, userName, permission, true);
        s.getEmployees().put(emp.getId(), emp);
    }

    /* ---- DISCOUNTS ---- */

    /**
     * The fixed percentage discount's constructor with default id whose coverage is Category
     * @param startDate  the discount's start date
     * @param endDate    the discount's end date
     * @param percentage the discount's percentage
     * @param categories the desired categories
     * @return the new CategoryFixedPercentage discount
     */
    public FixedPercentageDiscount createFixedPercentageDiscount(LocalDateTime startDate, LocalDateTime endDate,
                                                                 double percentage, Category... categories) {
        CategoryDiscountFactory factory = new CategoryDiscountFactory(categories);
        Discount discount = new CategoryFixedPercentage(startDate, endDate, percentage, categories);
        NotificationDiscount notification = new NotificationDiscount(LocalDateTime.now(), false, true, NotificationType.DISCOUNT);
        notification.FullNotification(discount);
        Store.getInstance().sendNotificationClients(notification);
        return factory.createFixedPercentageDiscount(startDate, endDate, percentage);
    }

    /**
     * The gift discount's constructor with default id whose coverage is Category
     * @param startDate         the discount's start date
     * @param endDate           the discount's end date
     * @param spendingThreshold the discount's spending threshold
     * @param gift              the discount's gift
     * @param categories        the desired categories
     * @return the new CategoryGift discount
     */
    public GiftDiscount createGiftDiscount(LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold,
                                           StoreProduct gift, Category... categories) {
        CategoryDiscountFactory factory = new CategoryDiscountFactory(categories);
        Discount discount = new CategoryGift(startDate, endDate, spendingThreshold, gift, categories);
        NotificationDiscount notification = new NotificationDiscount(LocalDateTime.now(), false, true, NotificationType.DISCOUNT);
        notification.FullNotification(discount);
        Store.getInstance().sendNotificationClients(notification);
        return factory.createGiftDiscount(startDate, endDate, spendingThreshold, gift);
    }

    /**
     * The quantity discount's constructor with default id whose coverage is Category
     * @param startDate    the discount's start date
     * @param endDate      the discount's end date
     * @param numThreshold the discount's number of purchased products threshold
     * @param deduction    the discount's deduction
     * @param categories   the desired categories
     * @return the new CategoryQuantity discount
     */
    public QuantityDiscount createQuantityDiscount(LocalDateTime startDate, LocalDateTime endDate, int numThreshold,
                                                   double deduction, Category... categories) {
        CategoryDiscountFactory factory = new CategoryDiscountFactory(categories);
        Discount discount = new CategoryQuantity(startDate, endDate, numThreshold, deduction, categories);
        NotificationDiscount notification = new NotificationDiscount(LocalDateTime.now(), false, true, NotificationType.DISCOUNT);
        notification.FullNotification(discount);
        Store.getInstance().sendNotificationClients(notification);
        return factory.createQuantityDiscount(startDate, endDate, numThreshold, deduction);
    }

    /**
     * The volume discount's constructor with default id whose coverage is Category
     * @param startDate         the discount's start date
     * @param endDate           the discount's end date
     * @param spendingThreshold the discount's spending threshold
     * @param deduction         the discount's deduction
     * @param categories        the desired categories
     * @return the new CategoryVolume discount
     */
    public VolumeDiscount createVolumeDiscount(LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold,
                                               double deduction, Category... categories) {
        CategoryDiscountFactory factory = new CategoryDiscountFactory(categories);
        Discount discount = new CategoryVolume(startDate, endDate, spendingThreshold, deduction, categories);
        NotificationDiscount notification = new NotificationDiscount(LocalDateTime.now(), false, true, NotificationType.DISCOUNT);
        notification.FullNotification(discount);
        Store.getInstance().sendNotificationClients(notification);
        return factory.createVolumeDiscount(startDate, endDate, spendingThreshold, deduction);
    }

    /**
     * The fixed percentage discount's constructor with default id whose coverage is Pack
     * @param startDate  the discount's start date
     * @param endDate    the discount's end date
     * @param percentage the discount's percentage
     * @param packs      the desired packs
     * @return the new PackFixedPercentage discount
     */
    public FixedPercentageDiscount createFixedPercentageDiscount(LocalDateTime startDate, LocalDateTime endDate,
                                                                 double percentage, Pack... packs) {
        PackDiscountFactory factory = new PackDiscountFactory(packs);
        Discount discount = new PackFixedPercentage(startDate, endDate, percentage, packs);
        NotificationDiscount notification = new NotificationDiscount(LocalDateTime.now(), false, true, NotificationType.DISCOUNT);
        notification.FullNotification(discount);
        Store.getInstance().sendNotificationClients(notification);
        return factory.createFixedPercentageDiscount(startDate, endDate, percentage);
    }

    /**
     * The gift discount's constructor with default id whose coverage is Pack
     * @param startDate         the discount's start date
     * @param endDate           the discount's end date
     * @param spendingThreshold the discount's spending threshold
     * @param gift              the discount's gift
     * @param packs             the desired packs
     * @return the new PackGift discount
     */
    public GiftDiscount createGiftDiscount(LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold,
                                           StoreProduct gift, Pack... packs) {
        PackDiscountFactory factory = new PackDiscountFactory(packs);
        Discount discount = new PackGift(startDate, endDate, spendingThreshold, gift, packs);
        NotificationDiscount notification = new NotificationDiscount(LocalDateTime.now(), false, true, NotificationType.DISCOUNT);
        notification.FullNotification(discount);
        Store.getInstance().sendNotificationClients(notification);
        return factory.createGiftDiscount(startDate, endDate, spendingThreshold, gift);
    }

    /**
     * The quantity discount's constructor with default id whose coverage is Pack
     * @param startDate    the discount's start date
     * @param endDate      the discount's end date
     * @param numThreshold the discount's number of purchased products threshold
     * @param deduction    the discount's deduction
     * @param packs        the desired packs
     * @return the new PackQuantity discount
     */
    public QuantityDiscount createQuantityDiscount(LocalDateTime startDate, LocalDateTime endDate, int numThreshold,
                                                   double deduction, Pack... packs) {
        PackDiscountFactory factory = new PackDiscountFactory(packs);
        Discount discount = new PackQuantity(startDate, endDate, numThreshold, deduction, packs);
        NotificationDiscount notification = new NotificationDiscount(LocalDateTime.now(), false, true, NotificationType.DISCOUNT);
        notification.FullNotification(discount);
        Store.getInstance().sendNotificationClients(notification);
        return factory.createQuantityDiscount(startDate, endDate, numThreshold, deduction);
    }

    /**
     * The volume discount's constructor with default id whose coverage is Pack
     * @param startDate         the discount's start date
     * @param endDate           the discount's end date
     * @param spendingThreshold the discount's spending threshold
     * @param deduction         the discount's deduction
     * @param packs             the desired packs
     * @return the new PackVolume discount
     */
    public VolumeDiscount createVolumeDiscount(LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold,
                                               double deduction, Pack... packs) {
        PackDiscountFactory factory = new PackDiscountFactory(packs);
        Discount discount = new PackVolume(startDate, endDate, spendingThreshold, deduction, packs);
        NotificationDiscount notification = new NotificationDiscount(LocalDateTime.now(), false, true, NotificationType.DISCOUNT);
        notification.FullNotification(discount);
        Store.getInstance().sendNotificationClients(notification);
        return factory.createVolumeDiscount(startDate, endDate, spendingThreshold, deduction);
    }

    /**
     * The fixed percentage discount's constructor with default id whose coverage is Product
     * @param startDate      the discount's start date
     * @param endDate        the discount's end date
     * @param percentage     the discount's percentage
     * @param overWholeStore whether the discount is applied over all the store products or not
     * @param products       the desired products
     * @return the new ProductFixedPercentage discount
     */
    public ProductFixedPercentage createFixedPercentageDiscount(LocalDateTime startDate, LocalDateTime endDate,
                                                                double percentage, boolean overWholeStore,
                                                                StoreProduct... products) {
        ProductDiscountFactory factory;
        Discount discount;

        if (overWholeStore) {
            factory = new ProductDiscountFactory(true);
            discount = new ProductFixedPercentage(startDate, endDate, percentage, overWholeStore);
        } else {
            factory = new ProductDiscountFactory(products);
            discount = new ProductFixedPercentage(startDate, endDate, percentage, products);
        }

        NotificationDiscount notification = new NotificationDiscount(LocalDateTime.now(), false, true, NotificationType.DISCOUNT);
        notification.FullNotification(discount);
        Store.getInstance().sendNotificationClients(notification);

        return factory.createFixedPercentageDiscount(startDate, endDate, percentage);
    }

    /**
     * The gift discount's constructor with default id whose coverage is Product
     * @param startDate         the discount's start date
     * @param endDate           the discount's end date
     * @param spendingThreshold the discount's spending threshold
     * @param gift              the discount's gift
     * @param overWholeStore    whether the discount is applied over all the store products or not
     * @param products          the desired products
     * @return the new ProductGift discount
     */
    public ProductGift createGiftDiscount(LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold,
                                          StoreProduct gift, boolean overWholeStore, StoreProduct... products) {
        ProductDiscountFactory factory;
        Discount discount;

        if (overWholeStore) {
            factory = new ProductDiscountFactory(true);
            discount = new ProductGift(startDate, endDate, spendingThreshold, gift, overWholeStore);
        } else {
            factory = new ProductDiscountFactory(products);
            discount = new ProductGift(startDate, endDate, spendingThreshold, gift, products);
        }

        NotificationDiscount notification = new NotificationDiscount(LocalDateTime.now(), false, true, NotificationType.DISCOUNT);
        notification.FullNotification(discount);
        Store.getInstance().sendNotificationClients(notification);
        return factory.createGiftDiscount(startDate, endDate, spendingThreshold, gift);
    }

    /**
     * The quantity discount's constructor with default id whose coverage is Product
     * @param startDate      the discount's start date
     * @param endDate        the discount's end date
     * @param numThreshold   the discount's number of purchased targetProducts threshold
     * @param deduction      the discount's deduction
     * @param overWholeStore whether the discount is applied over all the store products or not
     * @param products       the desired products
     * @return the new ProductQuantity discount
     */
    public ProductQuantity createQuantityDiscount(LocalDateTime startDate, LocalDateTime endDate, int numThreshold,
                                                  double deduction, boolean overWholeStore, StoreProduct... products) {
        ProductDiscountFactory factory;
        Discount discount;

        if (overWholeStore) {
            factory = new ProductDiscountFactory(true);
            discount = new ProductQuantity(startDate, endDate, numThreshold, deduction, true);
        } else {
            factory = new ProductDiscountFactory(products);
            discount = new ProductQuantity(startDate, endDate, numThreshold, deduction, products);
        }

        NotificationDiscount notification = new NotificationDiscount(LocalDateTime.now(), false, true, NotificationType.DISCOUNT);
        notification.FullNotification(discount);
        Store.getInstance().sendNotificationClients(notification);
        return factory.createQuantityDiscount(startDate, endDate, numThreshold, deduction);
    }

    /**
     * The volume discount's constructor with default id whose coverage is Product
     * @param startDate         the discount's start date
     * @param endDate           the discount's end date
     * @param spendingThreshold the discount's spending threshold
     * @param deduction         the discount's deduction
     * @param overWholeStore    whether the discount is applied over all the store products or not
     * @param products          the desired products
     * @return the new ProductVolume discount
     */
    public ProductVolume createVolumeDiscount(LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold,
                                              double deduction, boolean overWholeStore, StoreProduct... products) {
        ProductDiscountFactory factory;
        Discount discount;

        if (overWholeStore) {
            factory = new ProductDiscountFactory(true);
            discount = new ProductVolume(startDate, endDate, spendingThreshold, deduction, overWholeStore);
        } else {
            factory = new ProductDiscountFactory(products);
            discount = new ProductVolume(startDate, endDate, spendingThreshold, deduction, products);
        }

        NotificationDiscount notification = new NotificationDiscount(LocalDateTime.now(), false, true, NotificationType.DISCOUNT);
        notification.FullNotification(discount);
        Store.getInstance().sendNotificationClients(notification);
        return factory.createVolumeDiscount(startDate, endDate, spendingThreshold, deduction);
    }

    /**
     * Change the expiration of an offer parameter
     * @param newOfferTime the new offer time
     */
    public void changeOfferTime(Period newOfferTime) {
        Parameter.getParam().changeOfferTime(newOfferTime);
    }

    /**
     * Change the expiration of an order parameter
     * @param newOrderTime the new order time
     */
    public void changeOrderTime(Period newOrderTime) {
        Parameter.getParam().changeOrderTime(newOrderTime);
    }

    /**
     * Change the valuation cost parameter
     * @param newCost the new cost
     */
    public void changeValuationCost(double newCost) {
        Parameter.getParam().changeValuationCost(newCost);
    }

    /**
     * It changes the store's maximum number of products the recommender will return
     * @param newKRecommend the new maximum number of products the recommender will return
     */
    public void changeKRecommend(int newKRecommend) {
        Parameter.getParam().setkRecommend(newKRecommend);
    }

    /**
     * It changes the score a param (scoreWeight = a*score + b)
     * @param newScoreAParam the new score a param
     */
    public void changeScoreAParam(double newScoreAParam) {
        Parameter.getParam().changeScoreAParam(newScoreAParam);
    }

    /**
     * It changes the score b param (weightedScore = a*score + b)
     * @param newScoreBParam the new score b param
     */
    public void changeScoreBParam(double newScoreBParam) {
        Parameter.getParam().changeScoreBParam(newScoreBParam);
    }

    /**
     * It changes the number of lines that can be printed from a certain list parameter
     * @param newItemsPerPage the new number of lines that can be printed from a certain list
     */
    public void changeItemsPerPage(int newItemsPerPage) {
        Parameter.getParam().changeItemsPerPage(newItemsPerPage);
    }

    /**
     * Searches for the pack based on the id
     *
     * @param id, id of the pack
     * @return the pack with said id
     */
    public Pack searchPackByID(int id){
        return this.getSearcher().searchPackByID(id);
    }


    /**
     * Searches for the employee based on the id
     *
     * @param id, id of the employee
     * @return the employee with said id
     */
    public Employee searchEmployeeByID(int id){
        return this.getSearcher().searchEmployeeByID(id);
    }

    /**
     * Searches for the order based on the id
     *
     * @param id, id of the order
     * @return the order with said id
     */
    public Order searchOrderByID(int id){
        return this.getSearcher().searchOrderByID(id);
    }

    /**
     * Searches for the exchange based on the id
     *h
     * @param id, id of the exchange
     * @return the exchange with said id
     */
    public Exchange searchExchangeByID(int id){
        return this.getSearcher().searchExchangeByID(id);
    }

    /**
     * It allows a manager to add a pack to the store
     * @param price,    price of the pack
     * @param products, list of products that the pack contains
     * @param date,     date when the pack was created
     */
    public void addPack(double price, ArrayList<StoreProduct> products, LocalDate date) {
        this.sp.addPack(price, products, date);
    }

    /**
     * Searches for the store products
     * @return the store product based on the filters
     */
    public List<StoreProduct> searchStoreProducts(){
        return this.sp.searchStoreProducts();
    }

    /**
     * Searches for the store products based on the category
     * @param c, the categories we want our searched products to belong to
     * @return the store product based on the filters
     */
    public List<StoreProduct> searchStoreProductByCategory(Category... c){
        return this.sp.searchStoreProductByCategory(c);
    }

/*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/
    /**
     * Obtains the maneger's (and creates one if there wasn't one)
     * @return the manager of the store
     */
    public Manager getIntializedManager() {
        if (Manager.INSTANCE != null) {
            return Manager.INSTANCE;
        }
        return null;
    }
    
    /**
     * Changes the price of a pack
     *
     *
     * @param p, the pack to search
     * @param price, new price of the pack
     *
     */
    public void setPackPrice(Pack p, double price) {
    	this.sp.setPackPrice(p, price);
    }
    
    /**
     * Changes the discount of a pack
     *
     *
     * @param p, the pack to search
     * @param discount, new discount of the pack
     *
     */
    public void setPackDiscount(Pack p, Discount discount) {
    	this.sp.setPackDiscount(p, discount);
    }
    
    /**
     * Adds a product to the pack
     *
     *
     * @param p, the pack to search
     * @param sp, the store product to add
     *
     */
    public void PackAddProduct(Pack p, StoreProduct sp) {
    	this.sp.PackAddProduct(p, sp);
    }
    
    /**
     * Adds a list of products to the pack
     *
     *
     * @param p, the pack to search
     * @param lsp, the list of store product to add
     *
     */
    public void PackAddListProducts(Pack p, ArrayList<StoreProduct> lsp) {
    	this.sp.PackAddListProducts(p, lsp);
    }
    
    /**
     * Deletes a product from a pack
     * 
     * @param p the pack to delete from
     * @param sp the store product to delete
     */
    public void DeleteProductFromPack(Pack p, StoreProduct sp) {
    	this.sp.DeleteProductFromPack(p, sp);
    }
    
    /**
     * Deletes a list of store products from the pack
     *
     *
     * @param p, the pack to search
     * @param sp, the list of store products to delete
     *
     */
    public void DeleteListOfProductFromPack(Pack p, ArrayList<StoreProduct> sp) {
    	this.sp.DeleteListOfProductFromPack(p, sp);
    }
    
    /**
     * Sets the list of products of the pack
     *
     *
     * @param p, the pack to search
     * @param sp, the list of store products to add
     *
     */
    public void setPackProducts(Pack p, ArrayList<StoreProduct> sp) {
    	this.sp.setPackProducts(p, sp);
    }
    
    
    /**
     * Sets the categories of a product.
     *
     * @param sp the product to update
     * @param categories the categories to assign
     */
    public void setCategories(StoreProduct sp, HashMap<String, Category> categories) {
       this.sp.setCategories(sp, categories);
    }

    /**
     * Adds categories to a product.
     *
     * @param sp the product to update
     * @param categories the categories to add
     */
    public void addCategories(StoreProduct sp, Category... categories) {
        this.sp.addCategories(sp, categories);
    }

    /**
     * Removes categories from a product.
     *
     * @param sp the product to update
     * @param categories the categories to remove
     */
    public void removeCategories(StoreProduct sp, Category... categories) {
        this.sp.removeCategories(sp, categories);
    }

    /**
     * Sets the description of a product.
     *
     * @param sp the product to update
     * @param desc the new description
     */
    public void setDescription(StoreProduct sp, String desc) {
        this.sp.setDescription(sp, desc);
    }

    /**
     * Sets the discount of a product.
     *
     * @param sp the product to update
     * @param disc the discount to apply
     */
    public void setDiscount(StoreProduct sp, Discount disc) {
       this.sp.setDiscount(sp, disc);
    }

    /**
     * Sets the name of a product.
     *
     * @param sp the product to update
     * @param name the new name
     */
    public void setName(StoreProduct sp, String name) {
        this.sp.setName(sp, name);
    }

    /**
     * Sets the photo of a product.
     *
     * @param sp the product to update
     * @param photo the photo reference
     */
    public void setPhoto(StoreProduct sp, String photo) {
       this.sp.setPhoto(sp, photo);
    }

    /**
     * Sets the price of a product.
     *
     * @param sp the product to update
     * @param price the new price
     */
    public void setPrice(StoreProduct sp, double price) {
        this.sp.setPrice(sp, price);
    }

    /**
     * Sets the total sales of a product.
     *
     * @param sp the product to update
     * @param sales the number of sales
     */
    public void setSales(StoreProduct sp, int sales) {
        this.sp.setSales(sp, sales);
    }

    /**
     * Sets the monthly sales of a product.
     *
     * @param sp the product to update
     * @param sales the sales by month
     */
    public void setSalesByMonth(StoreProduct sp, HashMap<Month, Integer> sales) {
       this.sp.setSalesByMonth(sp, sales);
    }

    /**
     * Sets the stock of a product.
     *
     * @param sp the product to update
     * @param stock the available stock
     */
    public void setStock(StoreProduct sp, int stock) {
        this.sp.setStock(sp, stock);
    }

    /**
     * Sets the type of a product.
     *
     * @param sp the product to update
     * @param pt the product type
     */
    public void setType(StoreProduct sp, ProductType pt) {
        this.sp.setType(sp, pt);
    }   
}