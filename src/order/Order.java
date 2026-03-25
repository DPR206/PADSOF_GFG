package order;
public class Order {
    private static int changeId = 0;
    
    private int id;
    private double price;
    private LocalDateTime pickedUpDate;

    private List<StoreProduct> p = new ArrayList<>();
    private 
    private OrderState state;

    public void changeStatus(OrderState state) {
        this.state = state;
    }


    
}
