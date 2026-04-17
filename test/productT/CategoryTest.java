package productT;

import model.product.Category;
import model.product.Comic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Year;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest {

    private Category cat;

    @Test
    public void testConstructorFull() {
        Category c = new Category("Books", 50.0);
        assertEquals("Books", c.getName());
        assertEquals(50.0, c.getRevenue(), 0.001);
    }

    /*@Test
    public void testConstructorNameNull() {
        new Category(null, 10.0);
    }*/

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

    /*@Test
    public void testIncreaseRevenueNegative() {
        cat.increaseRevenue(-10.0);
    }*/

    @Test
    public void testAddProduct() {
        Comic comic = new Comic("1", 12.99, "alo", "algo2", "foto.png", 4.5, LocalDate.now(), 50, 120, Year.of(2020),
                "Juan Pérez", "Editorial Fantástica", this.cat);

        cat.addProduct(comic);
        assertTrue(cat.getProducts().contains(comic));
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

    /*@Test
    public void testSetNameNull() {
        cat.setName(null);
    }*/

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

    @BeforeEach
    public void setUp() {
        cat = new Category("Electronics", 100.0);
    }
}