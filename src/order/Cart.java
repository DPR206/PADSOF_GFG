package order;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import product.StoreProduct;

public class Cart {
	private List<StoreProduct> sp = new ArrayList<>();
	private LocalDate modificationDate;
	private boolean expired;
	
	public Cart() {
		this.expired = false;
		this.modificationDate = LocalDate.now();
	}
	
	public void addProduct(StoreProduct p) {
		
	}
}
