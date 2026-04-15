package productT;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Year;

import product.Category;
import product.Comic;

public class ComicTest {

	private Comic c;
	
	@BeforeEach
	public void setUp() {
		 c = new Comic("1", 12.99, "Test Comic", "Description", "photo.png", 4.5, LocalDate.now(), 50, 120, Year.of(2020), "Author", "Editorial", new Category("algo"));
	}
	
	@Test
	public void getAuthorTest() {
		String author = this.c.getAuthor();
		assertEquals(author, this.c.getAuthor());
	}
	@Test
	public void setAuthorTest() {
		this.c.setAuthor("test");
		assertEquals("test", this.c.getAuthor());
	}
	@Test
	public void getEditorialTest() {
        String editorial = this.c.getEditorial();
        assertEquals(editorial, this.c.getEditorial());
    }
	@Test
	public void setEditorialTest() {
		this.c.setEditorial("test2");
		assertEquals("test2", this.c.getEditorial());
	}
	@Test
	public void getNumPagesTest() {
		int num = this.c.getNumPages();
		assertEquals(num, this.c.getNumPages());
	}
	@Test
	public void setNumPagesTest() {
		this.c.setNumPages(3);
		assertEquals(3, this.c.getNumPages());
	}
	@Test
	public void getYearTest() {
        Year y = this.c.getYear();
        assertEquals(y, this.c.getYear());
    }
	
	@Test
	public void setYearTest() {
        this.c.setYear(Year.of(2000));
        assertEquals(Year.of(2000), this.c.getYear());
    }
}
