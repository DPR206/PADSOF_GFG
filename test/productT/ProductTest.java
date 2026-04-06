package productT;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Year;

import org.junit.Before;
import org.junit.Test;

import product.*;

public class ProductTest {

    private Comic c;

    @Before
    public void setUp() {
        c = new Comic("1", 12.99, "Test Comic", "Description", "photo.png", 4.5, LocalDate.now(), 50, 120, Year.of(2020), "Author", "Editorial");
    }

    @Test
    public void changePrice() {
        c.setPrice(15.99);
        assertEquals(15.99, c.getPrice(), 0.001);
    }

    @Test
    public void changeName() {
        c.setName("New Name");
        assertEquals("New Name", c.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void changeNameNull() {
        c.setName(null);
    }

    @Test
    public void changeDescription() {
        c.setDescription("New Description");
        assertEquals("New Description", c.getDescription());
    }

    @Test(expected = IllegalArgumentException.class)
    public void changeDescriptionNull() {
        c.setDescription(null);
    }

    @Test
    public void changePhoto() {
        c.setPhoto("newphoto.png");
        assertEquals("newphoto.png", c.getPhoto());
    }

    @Test(expected = IllegalArgumentException.class)
    public void changePhotoNull() {
        c.setPhoto(null);
    }

    @Test
    public void changeType() {
        c.setType(ProductType.GAME);
        assertEquals(ProductType.GAME, c.getType());
    }

    @Test
    public void getId() {
        assertEquals("1", c.getId());
    }

    @Test
    public void getPrice() {
        assertEquals(12.99, c.getPrice(), 0.001);
    }

    @Test
    public void getName() {
        assertEquals("Test Comic", c.getName());
    }

    @Test
    public void getDescription() {
        assertEquals("Description", c.getDescription());
    }

    @Test
    public void getPhoto() {
        assertEquals("photo.png", c.getPhoto());
    }

    @Test
    public void getType() {
        assertEquals(ProductType.COMIC, c.getType());
    }

    @Test
    public void smallPrintInfoTest() {
        String expected = "Name: Test Comic, Price: [12.99]€";
        assertEquals(expected, c.smallPrintInfo());
    }
}