package userT;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Year;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.order.Cart;
import model.product.Category;
import model.product.Comic;
import model.product.Pack;
import model.product.StoreProduct;
import model.user.UnregisteredClient;

class UnregisteredClientTest {

	private UnregisteredClient uc;
	private StoreProduct sp;
	private Pack p;

	@BeforeEach
	public void test() {
		this.uc = new UnregisteredClient(true);
		this.sp = new Comic("1", 12.99, "Test Comic", "Description", "photo.png", 4.5, LocalDate.now(), 50, 120, Year.of(2020), "Author", "Editorial", new Category("algo"));
		this.uc.addCart(sp);
		this.p = new Pack(0, 0, null);
	}

	@Test
	public void addProductCartTest() {
		assertTrue(this.uc.getCart().getProducts().contains(sp));
	}

	@Test
	public void removeProductFromCart() {
		this.uc.deleteCart(this.sp);
		assertFalse(this.uc.getCart().getProducts().contains(sp));
	}

	@Test
	public void addPackToCart() {
		Pack p = new Pack(0, 0, null);
		this.uc.addCart(p);
		assertTrue(this.uc.getCart().getPacks().contains(p));
	}

	@Test
	public void deletePackFromCart() {
		this.uc.deleteCart(this.p);
		assertFalse(this.uc.getCart().getPacks().contains(p));
	}

	@Test
	public void getCartTest() {
		Cart c = this.uc.getCart();
		assertEquals(c, this.uc.getCart());
	}

}