package productT;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;

import product.Comic;
import product.SimplePack;
import product.StoreProduct;

public class SimplePackTest {

    private Comic comic;
    private ArrayList<StoreProduct> products;
    private SimplePack simplePack;

    @BeforeEach
    public void setUp() {
        comic = new Comic("1", 12.99, "alo", "algo2", "foto.png", 4.5, LocalDate.now(), 50, 120, Year.of(2020), "Juan Pérez", "ñe");
        Comic comic2 = new Comic("0", 12.99, "alo2", "algo2", "foto2.png", 43.5, LocalDate.now(), 50, 120, Year.of(2020), "Juan Pérez", "awa");
        
        products = new ArrayList<>();
        products.add(comic);
        products.add(comic2);
        
        this.simplePack = new SimplePack(25.99, products);
    }

    @Test
    public void testConstructorWithPriceAndProducts() {
        SimplePack pack = new SimplePack(25.99, products);
        assertEquals(25.99, pack.getPrice(), 0.01);
        assertEquals(2, pack.getProducts().size()); 
        assertEquals(comic, pack.getProducts().get(0));
    }

    @Test
    public void testGetPrice() {
        assertEquals(25.99, simplePack.getPrice(), 0.01);
    }

    @Test
    public void testAddProduct() {
        Comic comicExtra = new Comic("3", 10.0, "Extra", "Desc", "img.png", 5.0, LocalDate.now(), 10, 100, Year.of(2021), "Autor", "Ed");
        
        assertTrue(simplePack.addProduct(comicExtra));
        assertEquals(3, simplePack.getProducts().size());
    }

    @Test
    public void testEliminateProduct() {
        assertTrue(simplePack.eliminateProduct(comic));
        assertEquals(1, simplePack.getProducts().size());
    }

    @Test
    public void testDecreaseStock() {
        int initialStock = comic.getStock();
        simplePack.decreaseStock();
        assertEquals(initialStock - 1, comic.getStock());
    }
}
