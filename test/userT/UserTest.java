package userT;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import user.UnregisteredClient;
import user.UserType;

public class UserTest {

    private UnregisteredClient unregisteredClient;

    @Before
    public void setUp() {
        unregisteredClient = new UnregisteredClient(true);
    }

    @Test
    public void testGetUserName() {
        assertNull(unregisteredClient.getUserName());
    }

    @Test
    public void testGetPassword() {
        assertNull(unregisteredClient.getPassword());
    }

    @Test
    public void testGetType() {
        assertEquals(UserType.UNREGISTERED_CLIENT, unregisteredClient.getType());
    }

    @Test
    public void testGetId() {
        assertNotNull(unregisteredClient.getId());
        assertTrue(unregisteredClient.getId().startsWith("U"));
    }

    @Test
    public void testChangeSearchOrder() {
        unregisteredClient.changeSearchOrder(false);
        unregisteredClient.changeSearchOrder(true);
    }

    @Test
    public void testAddPriceFilter() {
        unregisteredClient.addPriceFilter(5.0, 100.0);
    }

    @Test
    public void testAddPunctuationFilter() {
        unregisteredClient.addPunctuationFilter(1, 4);
    }

    @Test
    public void testGetCart() {
        assertNotNull(unregisteredClient.getCart());
    }

    @Test
    public void testSearchStoreProduct() {
        assertNotNull(unregisteredClient.searchStoreProduct());
    }
}
