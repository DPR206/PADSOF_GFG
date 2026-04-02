package user;

import order.*;
import product.*;
import store.Parameter;
import store.Store;

import java.time.LocalDateTime;

/**
 * Class name: Game
 * <p>
 * Description: It implements the manager of the store
 * @author Sofía C.L.
 * @version 1.4
 * @see User
 */
public class Manager extends User {
    private static Manager INSTANCE = null;
    private final Store s = Store.getInstance();
    /** Store permission necessary for the manager to do its functions */
    private StorePermission sp;
    /** no clue :v */
    private Parameter parameter; 

    private Manager(String pwd, String userName, StorePermission sp, Parameter p, boolean asc) {
        super(UserType.MANAGER, pwd, userName, asc);

        this.sp = sp;
        this.parameter = p;
    }

    public static Manager getInstance() {
        if (Manager.INSTANCE == null) {
            Manager.INSTANCE = new Manager("password", "manager", new StorePermission(), Parameter.getParam(), true);
        }
        return Manager.INSTANCE;
    }

    public Manager getIntializedManager() {
        if (Manager.INSTANCE != null) {
            return Manager.INSTANCE;
        }
        return null;
    }


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

        if (overWholeStore) {
            factory = new ProductDiscountFactory(true);
        } else {
            factory = new ProductDiscountFactory(products);
        }

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

        if (overWholeStore) {
            factory = new ProductDiscountFactory(true);
        } else {
            factory = new ProductDiscountFactory(products);
        }

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

        if (overWholeStore) {
            factory = new ProductDiscountFactory(true);
        } else {
            factory = new ProductDiscountFactory(products);
        }

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

        if (overWholeStore) {
            factory = new ProductDiscountFactory(true);
        } else {
            factory = new ProductDiscountFactory(products);
        }

        return factory.createVolumeDiscount(startDate, endDate, spendingThreshold, deduction);
    }
}