package userT;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import user.UnregisteredClient;
import user.UserType;

class UserTest {

    private UnregisteredClient unregisteredClient;

    @BeforeEach
    void setUp() {
        unregisteredClient = new UnregisteredClient(true);
    }

    @Test
    void testGetUserName() {
        assertNull(unregisteredClient.getUserName());
    }

    @Test
    void testGetPassword() {
        assertNull(unregisteredClient.getPassword());
    }

    @Test
    void testGetType() {
        assertEquals(UserType.UNREGISTERED_CLIENT, unregisteredClient.getType());
    }

    @Test
    void testGetId() {
        assertNotNull(unregisteredClient.getId());
        assertTrue(unregisteredClient.getId().startsWith("U"));
    }

    @Test
    void testChangeSearchOrder() {
        unregisteredClient.changeSearchOrder(false);
        unregisteredClient.changeSearchOrder(true);
    }

    @Test
    void testAddPriceFilter() {
        unregisteredClient.addPriceFilter(5.0, 100.0);
    }

    @Test
    void testAddPunctuationFilter() {
        unregisteredClient.addPunctuationFilter(1, 4);
    }

    @Test
    void testGetCart() {
        assertNotNull(unregisteredClient.getCart());
    }

    @Test
    void testSearchStoreProduct() {
        assertNotNull(unregisteredClient.searchStoreProduct());
    }
}
