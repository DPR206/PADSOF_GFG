package order;

/**
 * It defines the order's possible states
 * @author Sofía C.L.
 * @version 1.0
 */
public enum OrderState {
	/** The order has been paid */
    PAID("paid"),
    /** The order is being prepared */
    IN_PREPARATION("inPreparation"),
    /** The order is ready to pick up */
    READY_TO_PICKUP("readyToPickup"),
    /** The order has been picked up*/
    PICKED_UP("pickedUp");

    private final String state;

    OrderState(String state){
        this.state = state;
    }

    /**
     * Obtains the order's state in a string
     * @return a string with the order state
     */
    public String getString(){
        return this.state;
    }
}