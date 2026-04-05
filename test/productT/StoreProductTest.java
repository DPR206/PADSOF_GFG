package productT;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Set;

import org.junit.jupiter.api.Test;

import order.Discount;
import product.Category;
import product.Review;
import store.Store;
import user.RegisteredClient;

class StoreProductTest{
	
	@Test
public void addCategory(Category... newCategories) {
}

	@Test
	public void decreaseStock(int value) throws IllegalArgumentException {
	}
	
	@Test
	public void increaseStock(int value) throws IllegalArgumentException {
	}
	
	@Test
	public void removeCategory(Category... categories) {
	}
	
	@Test
	public void addReview(int scoring, String comment, RegisteredClient author) {
	}
	
	@Test
	public void addReview(RegisteredClient author, Review review) {
	}
	
	@Test
	public LocalDate getAddedDate() {
	}
	
	@Test
	public void setAddedDate(LocalDate newAddedDate) {
	} 
	
	@Test
	public double getAveragePunctuation() {
	}
	
	@Test
	public void setAveragePunctuation(double newAveragePunctuation) {
	}
	
	@Test
	public Category[] getCategories() {
	}
	
	@Test
	public void setCategories(HashMap<String, Category> newCategories) {
	}
	
	@Test
	public Discount getDiscount() {
	}
	
	@Test
	public void setDiscount(Discount newDiscount) throws NullPointerException {
	}
	
	@Test
	public String getPrintCategories() {
	}
	
	@Test
	public String getPrintReviews() {
	}
	
	@Test
	public HashMap<RegisteredClient, Review> getReviews() {
	}
	
	@Test
	public int getStock() {
	}
	
	@Test
	public void setStock(int newStock) throws IllegalArgumentException {
	}
	
	@Test
	public String toString() {
	}
}