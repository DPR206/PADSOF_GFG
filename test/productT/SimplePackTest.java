package productT;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import product.Category;
import product.Comic;
import product.SimplePack;
import product.StoreProduct;

class SimplePackTest {

    private Comic comic;
    private ArrayList<StoreProduct> products;
    private SimplePack simplePack;

    @BeforeEach
    void setUp() {
        Category category = new Category("Fiction", 0.0);
        comic = new Comic(12.99, "Test Comic", "Description", "photo.png", 10, 120, Year.of(2020), "Author", "Editorial", category);
        products = new ArrayList<>();
        products.add(comic);
        simplePack = new SimplePack(25.99, products);
    }

    @Test
    void testConstructorWithPriceAndProducts() {
        SimplePack pack = new SimplePack(25.99, products);
        assertEquals(25.99, pack.getPrice());
        assertEquals(1, pack.getProducts().size());
        assertEquals(comic, pack.getProducts().get(0));
    }

    @Test
    void testConstructorWithPriceProductsAndDate() {
        LocalDate date = LocalDate.now();
        SimplePack pack = new SimplePack(25.99, products, date);
        assertEquals(25.99, pack.getPrice());
        assertEquals(date, pack.getDateAddCart());
        assertEquals(1, pack.getProducts().size());
    }

    @Test
    void testConstructorWithIdPriceAndProducts() {
        SimplePack pack = new SimplePack(1, 25.99, products);
        assertEquals(1, pack.getId());
        assertEquals(25.99, pack.getPrice());
        assertEquals(1, pack.getProducts().size());
    }

    @Test
    void testConstructorWithIdPriceProductsAndDate() {
        LocalDate date = LocalDate.now();
        SimplePack pack = new SimplePack(1, 25.99, products, date);
        assertEquals(1, pack.getId());
        assertEquals(25.99, pack.getPrice());
        assertEquals(date, pack.getDateAddCart());
        assertEquals(1, pack.getProducts().size());
    }

    @Test
    void testGetPrice() {
        assertEquals(25.99, simplePack.getPrice());
    }

    @Test
    void testSetPrice() {
        simplePack.setPrice(30.99);
        assertEquals(30.99, simplePack.getPrice());
    }

    @Test
    void testGetProducts() {
        assertEquals(products, simplePack.getProducts());
        assertEquals(1, simplePack.getProducts().size());
    }

    @Test
    void testSetProducts() {
        ArrayList<StoreProduct> newProducts = new ArrayList<>();
        newProducts.add(comic);
        simplePack.setProducts(newProducts);
        assertEquals(newProducts, simplePack.getProducts());
    }

    @Test
    void testAddProduct() {
        Category category2 = new Category("Adventure", 0.0);
        Comic comic2 = new Comic(15.99, "Test Comic 2", "Description 2", "photo2.png", 5, 100, Year.of(2021), "Author2", "Editorial2", category2);
        assertTrue(simplePack.addProduct(comic2));
        assertEquals(2, simplePack.getProducts().size());
        assertTrue(simplePack.getProducts().contains(comic2));
    }

    @Test
    void testEliminateProduct() {
        assertTrue(simplePack.eliminateProduct(comic));
        assertEquals(0, simplePack.getProducts().size());
        assertFalse(simplePack.getProducts().contains(comic));
    }

    @Test
    void testTotalPrice() {
        assertEquals(12.99, simplePack.totalPrice());
    }

    @Test
    void testDecreaseStock() {
        int initialStock = comic.getStock();
        simplePack.decreaseStock();
        assertEquals(initialStock - 1, comic.getStock());
    }

    @Test
    void testIncreaseStock() {
        int initialStock = comic.getStock();
        simplePack.increaseStock();
        assertEquals(initialStock + 1, comic.getStock());
    }
}
