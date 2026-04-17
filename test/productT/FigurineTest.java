package productT;

import model.product.Category;
import model.product.Figurine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FigurineTest {

    private Figurine f;

/*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/
    @Test
    public void getBrandTest() {
        String brand = this.f.getBrand();
        assertEquals(brand, this.f.getBrand());
    }

    @Test
    public void getDimensionsTest() {
        String dimensions = this.f.getDimension();
        assertEquals(dimensions, this.f.getDimension());
    }

    @Test
    public void getMaterialTest() {
        String material = this.f.getMaterial();
        assertEquals(material, this.f.getMaterial());
    }

    @Test
    public void setBrandTest() {
        this.f.setBrand("Marvel comics or smth");
        assertEquals("Marvel comics or smth", this.f.getBrand());
    }

    @Test
    public void setDimensionsTest() {
        this.f.setDimension("yeah");
        assertEquals("yeah", this.f.getDimension());
    }

    @Test
    public void setMaterialTest() {
        this.f.setMaterial("wood");
        assertEquals("wood", this.f.getMaterial());
    }

    @BeforeEach
    public void setUp() {
        this.f = new Figurine(12.0, "Something", "yuh", "test.png", 1, "2x3x4cm", "DC Comics", "Plastic",
                new Category("test"));
    }
}