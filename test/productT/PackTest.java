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
		 assertEquals(d.toString(), this.p.getDateAddCart().toString());
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
	 
	 @Test
	 public void constructorWithIdTest() {
		 ArrayList<StoreProduct> products = new ArrayList<>();
		 LocalDate date = LocalDate.now();
		 Pack pack = new Pack(5, 10.0, products, date);
		 assertEquals(5, pack.getId());
		 assertEquals(10.0, pack.getPrice(), 0.001);
		 assertEquals(products, pack.getProducts());
		 assertEquals(date, pack.getDateAddCart());
	 }
	 
	 @Test
	 public void constructorDefaultIdTest() {
		 ArrayList<StoreProduct> products = new ArrayList<>();
		 Pack pack = new Pack(10.0, products);
		 assertEquals(10.0, pack.getPrice(), 0.001);
		 assertEquals(products, pack.getProducts());
		 assertNull(pack.getDateAddCart());
	 }
	 
	 @Test
	 public void constructorWithDateTest() {
		 ArrayList<StoreProduct> products = new ArrayList<>();
		 LocalDate date = LocalDate.now();
		 Pack pack = new Pack(10.0, products, date);
		 assertEquals(10.0, pack.getPrice(), 0.001);
		 assertEquals(products, pack.getProducts());
		 assertEquals(date, pack.getDateAddCart());
	 }
	 
	 @Test
	 public void constructorNoProductsTest() {
		 LocalDate date = LocalDate.now();
		 Pack pack = new Pack(10.0, date);
		 assertEquals(10.0, pack.getPrice(), 0.001);
		 assertTrue(pack.getProducts().isEmpty());
		 assertEquals(date, pack.getDateAddCart());
	 }
	 
	 @Test
	 public void constructorNoProductsWithIdTest() {
		 LocalDate date = LocalDate.now();
		 Pack pack = new Pack(5, 10.0, date);
		 assertEquals(5, pack.getId());
		 assertEquals(10.0, pack.getPrice(), 0.001);
		 assertTrue(pack.getProducts().isEmpty());
		 assertEquals(date, pack.getDateAddCart());
	 }
	 
	 @Test
	 public void setPriceTest() {
		 this.p.setPrice(20.0);
		 assertEquals(20.0, this.p.getPrice(), 0.001);
	 }
	 
	 @Test
	 public void setProductsTest() {
		 ArrayList<StoreProduct> newProducts = new ArrayList<>();
		 Comic comic = new Comic("3", 15.99, "new", "desc", "photo.png", 5.0, LocalDate.now(), 10, 100, Year.of(2021), "Author", "Editorial");
		 newProducts.add(comic);
		 this.p.setProducts(newProducts);
		 assertEquals(newProducts, this.p.getProducts());
	 }
	 
	 @Test
	 public void setDateAddCartTest() {
		 LocalDate date = LocalDate.of(2023, 1, 1);
		 this.p.setDateAddCart(date);
		 assertEquals(date, this.p.getDateAddCart());
	 }
	 
	 @Test
	 public void decreaseStockTest() {
		 List<StoreProduct> sp = this.p.getProducts();
		 StoreProduct c1 = sp.get(0);
		 int stock1 = c1.getStock();
		 this.p.decreaseStock();
		 assertEquals(stock1 - 1, c1.getStock());
	 }
	 
	 @Test
	 public void addProductTest() {
		 Comic comic = new Comic("3", 15.99, "new", "desc", "photo.png", 5.0, LocalDate.now(), 10, 100, Year.of(2021), "Author", "Editorial");
		 boolean added = this.p.addProduct(comic);
		 assertTrue(added);
		 assertTrue(this.p.getProducts().contains(comic));
	 }
	 
	 @Test
	 public void eliminateProductTest() {
		 StoreProduct sp = this.p.getProducts().get(0);
		 boolean removed = this.p.eliminateProduct(sp);
		 assertTrue(removed);
		 assertFalse(this.p.getProducts().contains(sp));
	 }
	 
	 @Test
	 public void addArrayProductsTest() {
		 ArrayList<StoreProduct> newProducts = new ArrayList<>();
		 Comic comic = new Comic("3", 15.99, "new", "desc", "photo.png", 5.0, LocalDate.now(), 10, 100, Year.of(2021), "Author", "Editorial");
		 newProducts.add(comic);
		 boolean added = this.p.addArrayProducts(newProducts);
		 assertTrue(added);
		 assertTrue(this.p.getProducts().contains(comic));
	 }
	 
	 @Test
	 public void eliminateArrayProductsTest() {
		 ArrayList<StoreProduct> toRemove = new ArrayList<>();
		 toRemove.add(this.p.getProducts().get(0));
		 boolean removed = this.p.eliminateArrayProducts(toRemove);
		 assertTrue(removed);
		 assertFalse(this.p.getProducts().contains(toRemove.get(0)));
	 }
	 
	 @Test
	 public void totalPriceTest() {
		 double expected = 0;
		 for (StoreProduct sp : this.p.getProducts()) {
			 expected += sp.getPrice();
		 }
		 assertEquals(expected, this.p.totalPrice(), 0.001);
	 }
	 
	 @Test
	 public void getPrintProductsTest() {
		 String expected = this.p.getProducts().get(0).getId() + "," + this.p.getProducts().get(1).getId() + ",";
		 assertEquals(expected, this.p.getPrintProducts());
	 }
	 
	 @Test
	 public void getPrintInfoTest() {
		 assertEquals("DUE", this.p.getPrintInfo());
	 }
	 
	 @Test
	 public void toStringTest() {
		 String expected = this.p.getId() + ";" + this.p.getPrice() + ";" + this.p.getPrintProducts() + ";" + this.p.getProducts();
		 assertEquals(expected, this.p.toString());
	 }
	 
	 @Test
	 public void getPriceWithFixedPercentageDiscountTest() {
		 this.p.setPrice(20.0);
		 PackFixedPercentage discount = new PackFixedPercentage(LocalDateTime.now(), LocalDateTime.now().plusDays(1), 0.1, this.p);
		 this.p.setDiscount(discount);
		 double expected = 20.0 - (20.0 * 0.1);
		 assertEquals(expected, this.p.getPrice(), 0.001);
	 }
	 
	 @Test
	 public void getPriceWithGiftDiscountTest() {
		 this.p.setPrice(15.0);
		 Comic gift = new Comic("gift", 0.0, "gift", "gift", "gift.png", 0.0, LocalDate.now(), 1, 1, Year.of(2020), "gift", "gift");
		 PackGift discount = new PackGift(LocalDateTime.now(), LocalDateTime.now().plusDays(1), 10.0, gift, this.p);
		 this.p.setDiscount(discount);
		 // Assuming price > threshold, gift added
		 double expected = 15.0;
		 assertEquals(expected, this.p.getPrice(), 0.001);
		 assertTrue(this.p.getProducts().contains(gift));
	 }
	 
	 @Test
	 public void getPriceWithVolumeDiscountTest() {
		 this.p.setPrice(15.0);
		 PackVolume discount = new PackVolume(LocalDateTime.now(), LocalDateTime.now().plusDays(1), 10.0, 5.0, this.p);
		 this.p.setDiscount(discount);
		 double expected = 15.0 - 5.0; // since 15 > 10
		 assertEquals(expected, this.p.getPrice(), 0.001);
	 }
}

