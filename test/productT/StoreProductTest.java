package productT;

import product.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;



public class StoreProductTest {

	private Comic comic;
	private Category category;

	@Before
	public void setUp() {
		this.category = new Category("Fiction");
		this.comic = new Comic(12.99, "Test Comic", "Description", "photo.png", 4, 120, Year.of(2020), "Author", "Editorial", category);
	}
	

	@Test
	public void addCategoryTest() {
		comic.addCategory(category);
		assertTrue(comic.getPrintCategories().contains("Fiction"));
	}

	@Test
	public void decreaseStockTest() {
		int initial = comic.getStock();
		comic.decreaseStock(5);
		assertEquals(initial - 5, comic.getStock());
	}

	@Test
	public void decreaseStockToZeroTest() {
		comic.decreaseStock(100); 
		assertEquals(0, comic.getStock()); 
	}

	@Test(expected = IllegalArgumentException.class)
	public void decreaseStockNegativeTest() {
		comic.decreaseStock(-1);
	}

	@Test
	public void increaseStockTest() {
		int initial = comic.getStock();
		comic.increaseStock(10);
		assertEquals(initial + 10, comic.getStock());
	}

	@Test(expected = IllegalArgumentException.class)
	public void increaseStockNegativeTest() {
		comic.increaseStock(-1);
	}

	@Test
	public void removeCategoryTest() {
		comic.addCategory(category);
		comic.removeCategory(category);
		assertFalse(comic.getPrintCategories().contains("Fiction"));
	}

	@Test
	public void getAddedDateTest() {
		LocalDate date = LocalDate.now();
		assertEquals(date, comic.getAddedDate());
	}

	@Test
	public void setAddedDateTest() {
		LocalDate newDate = LocalDate.of(2023, 1, 1);
		comic.setAddedDate(newDate);
		assertEquals(newDate, comic.getAddedDate());
	}

	@Test
	public void getAveragePunctuationTest() {
		assertEquals(4.5, comic.getAveragePunctuation(), 0.001);
	}

	@Test
	public void setAveragePunctuationTest() {
		comic.setAveragePunctuation(3.5);
		assertEquals(3.5, comic.getAveragePunctuation(), 0.001);
	}

	@Test
	public void getCategoriesTest() {
		comic.addCategory(category);
		Category[] categories = comic.getCategories();
		assertTrue(categories.length > 0);
	}

	@Test
	public void setCategoriesTest() {
		HashMap<String, Category> newCategories = new HashMap<>();
		newCategories.put("Fiction", category);
		comic.setCategories(newCategories);
		assertTrue(comic.getPrintCategories().contains("Fiction"));
	}

	@Test
	public void getStockTest() {
		assertEquals(50, comic.getStock());
	}

	@Test
	public void setStockTest() {
		comic.setStock(100);
		assertEquals(100, comic.getStock());
	}

	@Test(expected = IllegalArgumentException.class)
	public void setStockNegativeTest() {
		comic.setStock(-1);
	}

	@Test
	public void getSalesTest() {
		assertEquals(0, comic.getSales());
	}

	@Test
	public void setSalesTest() {
		comic.setSales(10);
		assertEquals(10, comic.getSales());
	}

	@Test
	public void increaseSalesTest() {
		int initial = comic.getSales();
		comic.increaseSales(LocalDate.now());
		assertEquals(initial + 1, comic.getSales());
	}

	@Test
	public void increaseSalesByNumberTest() {
		int initial = comic.getSales();
		comic.increaseSales(5, LocalDate.now());
		assertEquals(initial + 5, comic.getSales());
	}

	@Test
	public void getSalesByMonthTest() {
		HashMap<Month, Integer> salesByMonth = comic.getSalesByMonth();
		assertNotNull(salesByMonth);
		assertTrue(salesByMonth.size() == 12); // 12 months
	}

	@Test
	public void setSalesByMonthTest() {
		HashMap<Month, Integer> newSales = new HashMap<>();
		for (Month month : Month.values()) {
			newSales.put(month, 0);
		}
		comic.setSalesByMonth(newSales);
		assertEquals(newSales, comic.getSalesByMonth());
	}

	@Test
	public void getPrintCategoriesTest() {
		comic.addCategory(category);
		String printCategories = comic.getPrintCategories();
		assertTrue(printCategories.contains("Fiction"));
		assertTrue(printCategories.contains(","));
	}

	@Test
	public void getPrintReviewsTest() {
		String printReviews = comic.getPrintReviews();
		assertNotNull(printReviews);
	}

	@Test
	public void getReviewsTest() {
		assertTrue(comic.getReviews().isEmpty());
	}

	@Test
	public void getReviewsListTest() {
		assertTrue(comic.getReviewsList().isEmpty());
	}

	@Test
	public void toStringTest() {
		String str = comic.toString();
		assertNotNull(str);
		assertTrue(str.contains("Test Comic")); // Should contain name
	}
}