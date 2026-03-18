package user;

/**
 * Class name: Game
 * <p>
 * Description: It implements the manager of the store
 * @author Sofía C.L.
 * @version 1.3
 * @see User
 */
public class Manager extends User{
    private static final Manager INSTANCE;

    /**Store permission necessary for the manager to do its functions*/
    private StorePermission sp;
    /**no clue :v */
    private Parameter parameter; //?? esto que es xd

    private Manager(String pwd, String userName, int actualID){
        super(pwd, userName, actualID);
    }

    public Manager getManagerNotInitialized(String pwd, String userName){
        if(Manager.INSTANCE == null) Manager.INSTANCE = new Manager(pwd, userName);
        return Manager.INSTANCE;
    }

    public Manager getIntializedManager(){
        if(Manager.INSTANCE != null) return Manager.INSTANCE;
        return null;
    }
}
 /*    
+ addFixedPercDisc(LocalDateTime startDate, LocalDateTime endDate, double percentage): FixedPercDisc
+ addQuantityDisc(LocalDateTime startDate, LocalDateTime endDate, double discount, int numProds): QuantityDisc
+ addGiftDisc(LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold, StoreProduct gift): GiftDisc
+ addVolumeDisc(LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold, double discount): VolumeDisc
+ addEmployee(int id, Sttring password, List<Permission> permissions): Employee
}**/