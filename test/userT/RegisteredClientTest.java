package userT;

import static org.junit.jupiter.api.Assertions.*;

import model.product.*;
import model.user.RegisteredClient;
import model.utilities.exceptions.InvalidId;
import model.utilities.exceptions.PasswordNotValid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.store.Store;

import java.time.*;

/**
 * Unit test for RegisteredClient
 */
public class RegisteredClientTest {

    private RegisteredClient client;
    private String userName = "TestUser";
    private String dni = "12345678Z";
    private String password = "password123";

    @BeforeEach
    public void setUp() {
        client = new RegisteredClient(userName, dni, password, true);

        Store.getInstance().getStoreProductList().clear();
    }

    @Test
    public void testConstructorAndInitialization() {
        assertEquals(userName, client.getUserName());
        assertEquals(dni, client.getDni());
        assertEquals(LocalDate.now(), client.getRegisterDate());
        assertNotNull(client.getC());
        assertNotNull(client.getWallet());
        assertNotNull(client.getOrderHistory());
        assertEquals(0, client.getNumOrders());
    }

    @Test
    public void testSetAndGetDni() {
        String newDni = "87654321X";
        client.setDni(newDni);
        assertEquals(newDni, client.getDni());
    }

    @Test
    public void testIncreaseNumOrders() {
        client.increaseNumOrders();
        assertEquals(1, client.getNumOrders());

        client.increaseNumOrders(5);
        assertEquals(6, client.getNumOrders());

        client.increaseNumOrders(-2);
        assertEquals(6, client.getNumOrders());
    }

    @Test
    public void testIncreaseNumExchanges() {
        client.increaseNumExchanges();
        assertEquals(1, client.getNumExchanges());

        client.increaseNumExchanges(3);
        assertEquals(4, client.getNumExchanges());
    }

    @Test
    public void testAddProductWallet() {
        SecondHandProduct shp = new SecondHandProduct("name", "description", "photo", ProductType.COMIC, client);

        client.addProductWallet(shp);

        assertEquals(1, client.getWallet().getProducts().length);
        assertTrue(Store.getInstance().getSecondHandProducts().containsValue(shp));
    }

    @Test
    public void testRemoveProductWallet() {
        SecondHandProduct shp = new SecondHandProduct("name", "description", "photo", ProductType.COMIC, client);
        client.addProductWallet(shp);

        client.removeProductWallet(shp);

        assertEquals(0, client.getWallet().getProducts().length);
        assertFalse(Store.getInstance().getSecondHandProducts().containsValue(shp));
    }

    @Test
    public void testAddCart() {
    	Category category = new Category("Fiction");
        Comic comic = new Comic(12.99, "Test Comic", "Description", "photo.png", 4, 120, Year.of(2020), "Author", "Editorial", category);

        client.addCart(comic);

        assertTrue(client.getC().getProducts().contains(comic));
    }

    @Test
    public void testChangePassword() throws InvalidId, PasswordNotValid {
        String newPass = "newSecurePass12@";
        client.changePassword(newPass);
        assertEquals(newPass, client.getPassword());
    }

    @Test
    public void testReviewProduct() {
        Comic comic = new Comic(10.0, "Hulk", "Green", "hulk.jpg", 5, 20,
                                Year.of(2000), "Author", "Editorial");

        client.reviewProduct(comic, 5, "Excelente comic!");

        assertFalse(comic.getReviews().isEmpty());
        assertEquals(5, comic.getReviews().get(client).getScoring());
    }
}