package main;

/**
 * It implements the exchange permission's loop
 * @author Ana O.R.
 * @version 1.0
 */
public class ExchangePermissionLoop extends Loop {
    /** This loop's instance */
    private static ExchangePermissionLoop INSTANCE;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * The Exchange Permission loop's constructor
     */
    ExchangePermissionLoop() {

    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * It gets the Exchange Permission loop's instance
     * @return the Exchange Permission loop's instance
     */
    protected static ExchangePermissionLoop getInstance() {
        if (ExchangePermissionLoop.INSTANCE == null) {
            ExchangePermissionLoop.INSTANCE = new ExchangePermissionLoop();
        }
        return INSTANCE;
    }

    /**
     * The exchange permission's main loop
     */
    public void exchangePermissionLoop() {
        // DUE
    }
}