package main;

/**
 * It implements the store permission's loop
 * @author Ana O.R.
 * @version 1.0
 */
public class StorePermissionLoop extends Loop {
    /** This loop's instance */
    private static StorePermissionLoop INSTANCE;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * The Store Permission loop's constructor
     */
    StorePermissionLoop() {

    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * It gets the Store Permission loop's instance
     * @return the Store Permission loop's instance
     */
    protected static StorePermissionLoop getInstance() {
        if (StorePermissionLoop.INSTANCE == null) {
            StorePermissionLoop.INSTANCE = new StorePermissionLoop();
        }
        return INSTANCE;
    }

    /**
     * The store permission's main loop
     */
    public void storePermissionLoop() {
        // DUE
    }
}