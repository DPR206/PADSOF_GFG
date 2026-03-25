package user;

import order.FixedPerDisc;
import order.GiftDisc;
import order.QuantityDisc;
import order.VolumeDisc;
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
    private static final Manager INSTANCE = null;
    private final Store s;
    /** Store permission necessary for the manager to do its functions */
    private StorePermission sp;
    /** no clue :v */
    private Parameter parameter; //?? esto que es xd

    private Manager(String pwd, String userName, int actualID, Store s, StorePermission sp, Parameter p) {
        super(pwd, userName, actualID);
        this.s = s;
        this.sp = sp;
        this.parameter = p;
    }

    public Manager getManagerNotInitialized(String pwd, String userName) {
        if (Manager.INSTANCE == null) Manager.INSTANCE = new Manager(pwd, userName);
        return Manager.INSTANCE;
    }

    public Manager getIntializedManager() {
        if (Manager.INSTANCE != null) return Manager.INSTANCE;
        return null;
    }

    public void addFixedPercDisc(LocalDateTime startDate, LocalDateTime endDate, double percentage) {
        FixedPerDisc fpd = new FixedPerDisc(startDate, endDate, percentage);
        this.sp.getDiscounts().add(fpd);
    }

    public void addQuantityDisc(LocalDateTime startDate, LocalDateTime endDate, int discount, int numProds) {
        QuantityDisc qd = new QuantityDisc(startDate, endDate, discount, numProds);
        s.getDiscounts().add(qd);
    }

    public void addGiftDisc(LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold, StoreProduct gift) {
        GiftDisc gd = new GiftDisc(startDate, endDate, spendingThreshold, gift);
        s.getDiscounts().add(gd);
    }

    public void addVolumeDisc(LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold, double discount) {
        VolumeDisc vd = new VolumeDisc(startDate, endDate, spendingThreshold, discount);
        this.sp.getDiscounts().add(vd);
    }

    public void addEmployee(String password, String userName, Permission permission) {
        Eployee emp = new Employee(password, userName, permission);
        s.getUsers().put(emp.getUserName(), emp);
    }
}
 

