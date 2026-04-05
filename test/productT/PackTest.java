package productT;


import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.*;

import org.junit.Before;
import org.junit.Test;

import discount.*;
import product.*;

public class PackTest {

	private Pack p;
	
	 @Before
	 public void setUp() {
		 Comic comic = new Comic("1",12.99, "alo", "algo2", "foto.png", 4.5, LocalDate.now(), 50, 120, Year.of(2020), "Juan Pérez", "ñe");
		 Comic comic2 = new Comic("0",12.99, "alo2", "algo2", "foto2.png", 43.5, LocalDate.now(), 50, 120, Year.of(2020), "Juan Pérez", "awa");
		 List<StoreProduct> cosa = new ArrayList<>();
		 cosa.add(comic);
		 cosa.add(comic2);
		 this.p = new Pack(1.6, (ArrayList<StoreProduct>) cosa);
	 }
	 
	 @Test
	 public void setDiscountTest() {
		 
		 Discount discount =  new PackFixedPercentage(LocalDateTime.now(), LocalDateTime.now(), 0.90, this.p);
		 this.p.setDiscount(discount);
		 assertEquals(discount.getId(), this.p.getDiscount().getId(), 0.001);
	 }
	 
	 @Test
	 public void getIdTest() {
		 int id = this.p.getId();
		 assertEquals(id, this.p.getId(), 0.001);
	 }	
	 
	 @Test
	 public void getPriceTest() {
		 double price = this.p.getPrice();
		 assertEquals(price, this.p.getPrice(), 0.001);
	 }
	 
	 @Test
	 public void setPriceTest() {
		 double price = 7.9;
		 assertEquals(price, this.p.getPrice(), 0.001);
	 }
	 
	 @Test
	 public void setProductsTest() {
		 Comic comic = new Comic("1",12.99, "alo", "algo2", "foto.png", 4.5, LocalDate.now(), 50, 120, Year.of(2020), "Juan Pérez", "ñe");
		 Comic comic2 = new Comic("0",12.99, "alo2", "algo2", "foto2.png", 43.5, LocalDate.now(), 50, 120, Year.of(2020), "Juan Pérez", "awa");
		 List<StoreProduct> cosa = new ArrayList<>();
		 cosa.add(comic);
		 cosa.add(comic2);
		 
		 this.p.addArrayProducts((ArrayList<StoreProduct>) cosa);
		 assertTrue(this.p.getProducts().contains(comic) && this.p.getProducts().contains(comic2));
	 }
	 
	 @Test
	 public void getCartDateTest() {
		 LocalDate d = this.p.getDateAddCart();
		 assertEquals(d.toString(), this.p.getDateAddCart().toString(), 0.001);
	 }
	 
	 @Test
	 public void increaseStockTest() {
		 List<StoreProduct> sp = this.p.getProducts();
		 StoreProduct c1 = sp.get(0);
		 StoreProduct c2 = sp.get(1);
		 
		 int stock1 = c1.getStock();
		 int stock2 = c2.getStock();
		 
		 this.p.increaseStock();
		 stock1++;
		 assertEquals(stock1, this.p.getProducts().get(0).getStock(), 0.001);
		 
	 }
}
