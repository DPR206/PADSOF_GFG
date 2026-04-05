package product;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CategoryTest {

    private Category cat;

    @Before
    public void setUp() {
        cat = new Category("Electronics", 100.0);
    }

    @Test
    public void testConstructorFull() {
        Category c = new Category("Books", 50.0);
        assertEquals("Books", c.getName());
        assertEquals(50.0, c.getRevenue(), 0.001);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNameNull() {
        new Category(null, 10.0);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNegativeRevenue() {
        new Category("Sports", -5.0);
    }

    @Test
    public void testConstructorOnlyName() {
        Category c = new Category("Toys");
        assertEquals("Toys", c.getName());
        assertEquals(0.0, c.getRevenue(), 0.001);
    }

    @Test
    public void testIncreaseRevenue() {
        cat.increaseRevenue(50.0);
        assertEquals(150.0, cat.getRevenue(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIncreaseRevenueNegative() {
        cat.increaseRevenue(-10.0);
    }

    @Test
    public void testAddProduct() {
        StoreProduct sp = new StoreProduct(1, "Phone", 500.0, 10);
        cat.addProduct(sp);
        assertTrue(cat.getProducts().contains(sp));
    }

    @Test
    public void testRemoveProduct() {
        StoreProduct sp = new StoreProduct(1, "Phone", 500.0, 10);
        cat.addProduct(sp);
        cat.removeProduct(sp);
        assertFalse(cat.getProducts().contains(sp));
    }

    @Test
    public void testGetName() {
        assertEquals("Electronics", cat.getName());
    }

    @Test
    public void testSetName() {
        cat.setName("NewName");
        assertEquals("NewName", cat.getName());
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameNull() {
        cat.setName(null);
    }

    @Test
    public void testGetRevenue() {
        assertEquals(100.0, cat.getRevenue(), 0.001);
    }

    @Test
    public void testSetRevenue() {
        cat.setRevenue(250.0);
        assertEquals(250.0, cat.getRevenue(), 0.001);
    }

    @Test
    public void testGetProducts() {
        assertNotNull(cat.getProducts());
        assertTrue(cat.getProducts().isEmpty());
    }

    @Test
    public void testToString() {
        assertEquals("Electronics;100.0", cat.toString());
    }
}
