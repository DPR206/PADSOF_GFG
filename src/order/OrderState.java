package order;

public enum OrderState {
    PAID("paid"),
    IN_PREPARATION("inPreparation"),
    READY_TO_PICKUP("readyToPickup"),
    PICKED_UP("pickedUp");

    private final String state;

    OrderState(String state){
        this.state = state;
    }

    private String getString(){
        return this.state;
    }
}