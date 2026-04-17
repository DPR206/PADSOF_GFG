package productT;

import model.product.Category;
import model.product.Comic;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.*;
import java.util.HashMap;

public class StoreProductTest {

	private Comic comic;
	private Category category;

	@BeforeEach
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
		comic.decreaseStock(2);
		assertEquals(initial - 2, comic.getStock());
	}

	@Test
	public void decreaseStockToZeroTest() {
		comic.decreaseStock(100);
		assertEquals(0, comic.getStock());
	}

	@Test
	void decreaseStockNegativeTest() {
	    assertThrows(IllegalArgumentException.class, () -> {
	        comic.decreaseStock(-1);
	    });
	}

	@Test
	public void increaseStockTest() {
		int initial = comic.getStock();
		comic.increaseStock(10);
		assertEquals(initial + 10, comic.getStock());
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
		comic.setAddedDate(date);
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
		assertEquals(0, comic.getAveragePunctuation());
	}

	@Test
	public void setAveragePunctuationTest() {
		comic.setAveragePunctuation(3.5);
		assertEquals(3.5, comic.getAveragePunctuation());
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
		assertEquals(4, comic.getStock());
	}

	@Test
	public void setStockTest() {
		comic.setStock(100);
		assertEquals(100, comic.getStock());
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
		assertTrue(salesByMonth.size() == 12);
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
		assertTrue(str.contains("Test Comic"));
	}
}