package userT;

import static org.junit.jupiter.api.Assertions.*;

import model.product.*;
import model.user.StorePermission;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.store.Store;
import java.time.Year;
import java.util.List;

class StorePermissionTest {

    private StorePermission storePermission;
    private Store store;

    @BeforeEach
    void setUp() {
        storePermission = new StorePermission();
        store = Store.getInstance();
        store.getStoreProductList().clear();
    }

    @Test
    void testAddComic() {
        int initialSize = store.getStoreProductList().size();

        storePermission.addComic(
            15.0, "The Batman", "Year One", "batman.png",
            10, 100, Year.of(1987), "Frank Miller", "DC Comics"
        );

        List<StoreProduct> products = store.getStoreProductList();
        assertEquals(initialSize + 1, products.size());
        assertTrue(products.get(0) instanceof Comic);
        assertEquals("The Batman", products.get(0).getName());
    }

    @Test
    void testAddGame() {
        Category cat = new Category("Strategy");
        storePermission.addGame(
            40.0, "Catan", "Island building", "catan.jpg",
            5, 4, "10+", GameStyle.GAMEBOARD, cat
        );

        List<StoreProduct> products = store.getStoreProductList();
        assertEquals(1, products.size());
        assertEquals("Catan", products.get(0).getName());
    }

    @Test
    void testSetPrice() {
        storePermission.addFigurine(
            50.0, "Spider-man", "Marvel Figurine", "spidey.png",
            2, "10x10x20", "Hasbro", "Plastic"
        );

        StoreProduct sp = store.getStoreProductList().get(0);

        storePermission.setPrice(sp, 65.0);

        assertEquals(65.0, sp.getPrice(), 0.01);
    }

    @Test
    void testSetStock() {
        storePermission.addComic(
            10.0, "Spider-man #1", "Classic", "photo.png",
            5, 32, Year.of(1963), "Stan Lee", "Marvel"
        );

        StoreProduct sp = store.getStoreProductList().get(0);
        storePermission.setStock(sp, 20);

        assertEquals(20, sp.getStock());
    }

    @Test
    void testSearchByCategory() {
        Category fiction = new Category("Fiction");
        storePermission.addComic(
            10.0, "Comic Fiction", "Desc", "img.png",
            1, 20, Year.of(2020), "A", "E", fiction
        );

        List<StoreProduct> result = storePermission.searchStoreProductByCategory(fiction);

        assertFalse(result.isEmpty());
        assertEquals("Comic Fiction", result.get(0).getName());
    }

    @Test
    @DisplayName("Debería añadir categorías a un producto existente")
    void testAddCategories() {
        storePermission.addComic(5.0, "Test", "D", "P", 1, 10, Year.of(2022), "A", "E");
        StoreProduct sp = store.getStoreProductList().get(0);

        Category newCat = new Category("New Genre");
        storePermission.addCategories(sp, newCat);

        // Asumiendo que getCategories() devuelve un array o colección
        boolean found = false;
        for (Category c : sp.getCategories()) {
            if (c.getName().equals("New Genre")) {
                found = true;
                break;
            }
        }
        assertTrue(found, "La categoría debería haber sido añadida");
    }
}