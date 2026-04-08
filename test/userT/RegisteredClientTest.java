package userT;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Year;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exchange.ExchangeHistory;
import exchange.OfferHistory;
import notification.NotificationHistory;
import product.Category;
import product.Comic;
import product.Pack;
import product.StoreProduct;
import user.RegisteredClient;
import order.OrderHistory;

class RegisteredClientTest {

	private RegisteredClient rc;
	private StoreProduct sp;
	private Pack p;
	
	@BeforeEach
	public void setUp() {
		this.rc = new RegisteredClient("test subject", LocalDate.now(), "334441223E", "FJKSJSW394", true);
		this.sp = new Comic("1", 12.99, "Test Comic", "Description", "photo.png", 4.5, LocalDate.now(), 50, 120, Year.of(2020), "Author", "Editorial", new Category("algo"));
		this.p = new Pack(0, 0, null);
		this.rc.setHistories();
	}
	
	@Test
	public void getRegisterDateTest() {
		LocalDate ld = this.rc.getRegisterDate();
		assertTrue(this.rc.getRegisterDate().equals(ld));
	}
	
	@Test
	public void setRegisterDateTest() {
		LocalDate ld = LocalDate.now();
		this.rc.setRegisterDate(ld);
		assertTrue(this.rc.getRegisterDate().equals(ld));
	}
	
	@Test
	public void getRegisterDNITest() {
		String ld = this.rc.getDni();
		assertTrue(this.rc.getDni().equals(ld));
	}
	
	@Test
	public void setDNITest() {
		String ld = "new";
		this.rc.setDni(ld);
		assertTrue(this.rc.getRegisterDate().equals(ld));
	}
	
	@Test
	public void changePwdTest() {
		String newPwd = "new PWD";
		this.rc.changePassword(newPwd);
		assertEquals(newPwd, this.rc.getPassword());
	}
	
	@Test
	public void getExchangeHistoryTest() {
		ExchangeHistory eh = this.rc.getExchangeHistory();
		assertEquals(eh, this.rc.getExchangeHistory());
	}
	
	@Test
	public void getOrderHistoryTest() {
		OrderHistory eh = this.rc.getOrderHistory();
		assertEquals(eh, this.rc.getOrderHistory());
	}
	
	@Test
	public void getOfferHistoryTest() {
		OfferHistory eh = this.rc.getOfferHistory();
		assertEquals(eh, this.rc.getOfferHistory());
	}
	
	@Test
	public void getNotificationHistoryTest() {
		NotificationHistory eh = this.rc.getNotificationHistory();
		assertEquals(eh, this.rc.getNotificationHistory());
	}
	
	@Test
	public void getCartTest() {
		NotificationHistory eh = this.rc.getNotificationHistory();
		assertEquals(eh, this.rc.getNotificationHistory());
	}
}
