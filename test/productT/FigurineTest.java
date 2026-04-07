package productT;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import product.*;

class FigurineTest {

	private Figurine f;
	
	@BeforeEach
	public void setUp() {
		this.f = new Figurine(12.0, "Something", "yuh", "test.png", 1, "2x3x4cm", "DC Comics", "Plastic", new Category("test"));
	}
	
	@Test
	public void getBrandTest() {
		String brand = this.f.getBrand();
		assertEquals(brand, this.f.getBrand());
	}
	
	@Test
	public void setBrandTest() {
		this.f.setBrand("Marvel comics or smth");
		assertEquals("Marvel comics or smth", this.f.getBrand());
	}
	
	@Test
	public void getDimensionsTest() {
		String dimensions = this.f.getDimension();
		assertEquals(dimensions, this.f.getDimension());
	}
	
	@Test
	public void setDimensionsTest() {
		this.f.setDimension("yeah");
		assertEquals("yeah", this.f.getDimension());
	}
	
	@Test
	public void getMaterialTest() {
		String material = this.f.getMaterial();
		assertEquals(material, this.f.getMaterial());
	}
	
	@Test
	public void setMaterialTest() {
		this.f.setMaterial("wood");
		assertEquals("wood", this.f.getMaterial());
	}
}
