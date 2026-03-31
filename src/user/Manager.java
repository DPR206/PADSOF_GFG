package user;

import order.*;
import product.StoreProduct;
import store.Parameter;
import store.Store;

import java.time.LocalDateTime;

/**
 * Class name: Game
 * <p>
 * Description: It implements the manager of the store
 * @author Sofía C.L.
 * @version 1.3
 * @see User
 */
public class Manager extends User {
    private static Manager INSTANCE = null;
    private final Store s;
    /** Store permission necessary for the manager to do its functions */
    private StorePermission sp;
    /** no clue :v */
    private Parameter parameter; //?? esto que es xd

    private Manager(String pwd, String userName, Store s, StorePermission sp, Parameter p) {
        super(UserType.MANAGER, pwd, userName);
        this.s = s;
        this.sp = sp;
        this.parameter = p;
    }

    public static Manager getInstance() {
        if (Manager.INSTANCE == null) {
            Manager.INSTANCE = new Manager("password", "manager", Store.getInstance(), new StorePermission(),
                    Parameter.getParam());
        }
        return Manager.INSTANCE;
    }

    public Manager getIntializedManager() {
        if (Manager.INSTANCE != null) {
            return Manager.INSTANCE;
        }
        return null;
    }

    public void addFixedPercDisc(LocalDateTime startDate, LocalDateTime endDate, double percentage,
                                 StoreProduct... products) {
        FixedPerDisc fpd = new FixedPerDisc(startDate, endDate, percentage, products);
        this.sp.getStore().getDiscounts().add(fpd);
    }

    public void addQuantityDisc(LocalDateTime startDate, LocalDateTime endDate, int discount, int numProds,
                                StoreProduct... products) {
        QuantityDisc qd = new QuantityDisc(startDate, endDate, discount, numProds, products);
        s.getDiscounts().add(qd);
    }

    public void addGiftDisc(LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold, StoreProduct gift,
                            StoreProduct... products) {
        GiftDisc gd = new GiftDisc(startDate, endDate, spendingThreshold, gift, products);
        s.getDiscounts().add(gd);
    }

    public void addVolumeDisc(LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold, double discount,
                              StoreProduct... products) {
        VolumeDisc vd = new VolumeDisc(startDate, endDate, spendingThreshold, discount, products);
        this.sp.getStore().getDiscounts().add(vd);
    }

    public void addEmployee(String password, String userName, Permission permission) {
        Employee emp = new Employee(password, userName, permission);
        s.getUsers().put(emp.getUserName(), emp);
    }
}