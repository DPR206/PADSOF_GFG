import store.Store;
import user.Manager;

public class ExchangeDemo {
    Store s = new Store();
    Manager manager = new Manager("password", "manager", 0, s);
}