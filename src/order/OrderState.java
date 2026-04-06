package order;

/**
 * It defines the order's possible states
 * @author Sofía C.L.
 * @version 1.0
 */
public enum OrderState {
    PAID("paid"),
    IN_PREPARATION("inPreparation"),
    READY_TO_PICKUP("readyToPickup"),
    PICKED_UP("pickedUp");

    private final String state;

    OrderState(String state){
        this.state = state;
    }

    public String getString(){
        return this.state;
    }
}