package productT;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import product.Category;
import product.Comic;
import product.ComposedPack;
import product.Pack;
import product.SimplePack;
import product.StoreProduct;

public class ComposedPackTest {

    private Comic comic1, comic2;
    private SimplePack simplePack1, simplePack2;
    private HashSet<Pack> packs;
    private ComposedPack composedPack;

    @BeforeEach
    public void setUp() {
        Category category = new Category("Fiction", 0.0);
        comic1 = new Comic(12.99, "Test Comic 1", "Description 1", "photo1.png", 10, 120, Year.of(2020), "Author1", "Editorial1", category);
        comic2 = new Comic(15.99, "Test Comic 2", "Description 2", "photo2.png", 5, 100, Year.of(2021), "Author2", "Editorial2", category);

        ArrayList<StoreProduct> products1 = new ArrayList<>();
        products1.add(comic1);
        simplePack1 = new SimplePack(25.99, products1);

        ArrayList<StoreProduct> products2 = new ArrayList<>();
        products2.add(comic2);
        simplePack2 = new SimplePack(30.99, products2);

        packs = new HashSet<>();
        packs.add(simplePack1);
        packs.add(simplePack2);

        composedPack = new ComposedPack(50.99, packs);
    }

    @Test
    public void testConstructorWithIdPricePacksAndDate() {
        LocalDate date = LocalDate.now();
        ComposedPack pack = new ComposedPack(1, 50.99, packs, date);
        assertEquals(1, pack.getId());
        assertEquals(50.99, pack.getPrice(), 0.01);
        assertEquals(date, pack.getDateAddCart());
        assertEquals(2, pack.getPacks().size());
    }

    @Test
    public void testConstructorWithPacksPriceAndDate() {
        LocalDate date = LocalDate.now();
        ComposedPack pack = new ComposedPack(packs, 50.99, date);
        assertEquals(50.99, pack.getPrice(), 0.01);
        assertEquals(date, pack.getDateAddCart());
        assertEquals(2, pack.getPacks().size());
    }

    @Test
    public void testConstructorWithPriceAndPacks() {
        ComposedPack pack = new ComposedPack(50.99, packs);
        assertEquals(50.99, pack.getPrice(), 0.01);
        assertEquals(2, pack.getPacks().size());
    }

    @Test
    public void testGetPacks() {
        assertEquals(packs, composedPack.getPacks());
        assertEquals(2, composedPack.getPacks().size());
    }

    @Test
    public void testSetPacks() {
        HashSet<Pack> newPacks = new HashSet<>();
        newPacks.add(simplePack1);
        composedPack.setPacks(newPacks);
        assertEquals(newPacks, composedPack.getPacks());
        assertEquals(1, composedPack.getPacks().size());
    }

    @Test
    public void testAddPack() {
        Category category = new Category("Adventure", 0.0);
        Comic comic3 = new Comic(18.99, "Test Comic 3", "Description 3", "photo3.png", 8, 150, Year.of(2022), "Author3", "Editorial3", category);
        ArrayList<StoreProduct> products3 = new ArrayList<>();
        products3.add(comic3);
        SimplePack simplePack3 = new SimplePack(35.99, products3);

        composedPack.addPack(simplePack3);
        assertEquals(3, composedPack.getPacks().size());
        assertTrue(composedPack.getPacks().contains(simplePack3));
    }

    @Test
    public void testRemovePack() {
        composedPack.removePack(simplePack1);
        assertEquals(1, composedPack.getPacks().size());
        assertFalse(composedPack.getPacks().contains(simplePack1));
    }

    @Test
    public void testAddPacks() {
        HashSet<Pack> additionalPacks = new HashSet<>();
        Category category = new Category("Adventure", 0.0);
        Comic comic3 = new Comic(18.99, "Test Comic 3", "Description 3", "photo3.png", 8, 150, Year.of(2022), "Author3", "Editorial3", category);
        ArrayList<StoreProduct> products3 = new ArrayList<>();
        products3.add(comic3);
        SimplePack simplePack3 = new SimplePack(35.99, products3);
        additionalPacks.add(simplePack3);

        composedPack.addPacks(additionalPacks);
        assertEquals(3, composedPack.getPacks().size());
        assertTrue(composedPack.getPacks().contains(simplePack3));
    }

    @Test
    public void testRemovePacks() {
        HashSet<Pack> removePacks = new HashSet<>();
        removePacks.add(simplePack1);
        removePacks.add(simplePack2);

        composedPack.removePacks(removePacks);
        assertEquals(0, composedPack.getPacks().size());
        assertFalse(composedPack.getPacks().contains(simplePack1));
        assertFalse(composedPack.getPacks().contains(simplePack2));
    }

    @Test
    public void testGetPrice() {
        assertEquals(50.99, composedPack.getPrice(), 0.01);
    }

    @Test
    public void testSetPrice() {
        composedPack.setPrice(55.99);
        assertEquals(55.99, composedPack.getPrice(), 0.01);
    }

    @Test
    public void testToString() {
        String str = composedPack.toString();
        assertTrue(str.startsWith(composedPack.getId() + ";50.99;"));
    }
}
