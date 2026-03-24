package order;
public class Order {
    private List<StoreProduct> p = new ArrayList<>();
    private OrderState state;

    public Order(){} //uff q difícil D:"

    public void changeStatus(OrderState state) {
        this.state = state;
    }
    
}
