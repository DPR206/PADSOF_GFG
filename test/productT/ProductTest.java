package productT;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.*;
import product.*;

public class ProductTest {

    private Comic c;

    @BeforeEach
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

    // ARREGLADO: Uso de assertThrows
    @Test
    public void changeNameNull() {
        assertThrows(IllegalArgumentException.class, () -> c.setName(null));
    }

    @Test
    public void changeDescription() {
        c.setDescription("New Description");
        assertEquals("New Description", c.getDescription());
    }

    @Test
    public void changeDescriptionNull() {
        assertThrows(IllegalArgumentException.class, () -> c.setDescription(null));
    }

    @Test
    public void changePhoto() {
        c.setPhoto("newphoto.png");
        assertEquals("newphoto.png", c.getPhoto());
    }

    @Test
    public void changePhotoNull() {
        assertThrows(IllegalArgumentException.class, () -> c.setPhoto(null));
    }

    @Test
    public void changeType() {
        // Asegúrate de importar ProductType o usar Product.ProductType si es interna
        c.setType(ProductType.GAME);
        assertEquals(ProductType.GAME, c.getType());
    }

    @Test
    public void smallPrintInfoTest() {
        String expected = "Name: Test Comic, Price: [12.99]€";
        assertEquals(expected, c.smallPrintInfo());
    }
    
    // Los getters simples no suelen fallar a menos que la lógica de Comic esté mal
    @Test
    public void getId() {
        assertEquals("1", c.getId());
    }
}