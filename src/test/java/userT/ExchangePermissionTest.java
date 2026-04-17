package userT;

import model.exchange.Exchange;
import model.product.*;
import model.user.ExchangePermission;
import model.user.RegisteredClient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ExchangePermissionTest {

    private ExchangePermission exchangePermission;
    private Exchange exchange;
    private RegisteredClient client1;

    @BeforeEach
    void setUp() {
        exchangePermission = new ExchangePermission();

        RegisteredClient client1 = new RegisteredClient("user1", "50046352Y", "12345678", true);
        RegisteredClient client2 = new RegisteredClient("user2", "50046372Y", "12Y45678", true);
        exchange = new Exchange(LocalDateTime.now(), false, client1,
        		new ArrayList<SecondHandProduct>(), client2, new ArrayList<SecondHandProduct>());
    }

    @Test
    void testManageExchangeSuccess() {

        exchangePermission.manageExchange(exchange, true);

        assertTrue(exchange.isExchanged(), "El exchange debería estar marcado como true");
    }

    @Test
    void testManageExchangeNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            exchangePermission.manageExchange(null, true);
        });
    }

    @Test
    void testValuateSuccess() {
        SecondHandProduct product = new SecondHandProduct("name", "description", "photo", ProductType.COMIC,
    			false, true, null, client1);
        double price = 100.0;
        ConservationStatus status = ConservationStatus.PERFECT;

        exchangePermission.valuate(product, price, status);

        assertEquals(price, product.getPrice());
        assertEquals(status, product.getStatus());
        assertEquals(LocalDate.now(), product.getValuationDate());
        assertTrue(product.isAvailable());
    }

    @Test
    void testValuateNegativePrice() {
        SecondHandProduct product = new SecondHandProduct("name", "description", "photo", ProductType.COMIC,
    			false, true, null, client1);

        assertThrows(NullPointerException.class, () -> {
            exchangePermission.valuate(product, -10.0, ConservationStatus.VERY_GOOD);
        });
    }

    @Test
    void testValuateWithDateSuccess() {
        SecondHandProduct product = new SecondHandProduct("name", "description", "photo", ProductType.COMIC,
    			false, true, null, client1);
        LocalDate specificDate = LocalDate.of(2020, 5, 20);

        exchangePermission.valuate(product, 200.0, ConservationStatus.SLIGHTLY_USED, specificDate);

        assertEquals(specificDate, product.getValuationDate());
        assertTrue(product.isAvailable());
    }

    @Test
    void testValuateWithDateNullProduct() {
        LocalDate date = LocalDate.now();
        assertThrows(NullPointerException.class, () -> {
            exchangePermission.valuate(null, 100.0, ConservationStatus.VERY_GOOD, date);
        });
    }
}