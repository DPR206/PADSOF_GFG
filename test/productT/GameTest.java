package productT;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Year;

import product.Category;
import product.Comic;
import product.Game;
import product.GameStyle;

public class GameTest {
	private Game g;
	
	@BeforeEach
	public void setUp() {
		 this.g = new Game(10.1, "Minecraft", "Block game", "dirt.png", 3, 10, "PG7", GameStyle.MINIATURE, new Category("digital"));
	}
	
	@Test
	public void getAgeRangeTest() {
        String ageRange = this.g.getAgeRange();
        assertEquals(ageRange, this.g.getAgeRange());
    }
	@Test
	public void setAgeRangeTest() {
		this.g.setAgeRange("test2");
		assertEquals("test2", this.g.getAgeRange());
	}
	@Test
	public void getGameStyleTest() {
		String gamestyle = this.g.getGameStyle().getFormatedName();
		assertEquals(gamestyle, this.g.getGameStyle().getFormatedName());
	}
	@Test
	public void setNumPagesTest() {
		this.g.setGameStyle(GameStyle.DICE);
		assertEquals(GameStyle.DICE.getFormatedName(), this.g.getGameStyle().getFormatedName());
	}
	@Test
	public void getNumPlayersTest() {
        int num = this.g.getNumPlayers();
        assertEquals(num, this.g.getNumPlayers());
    }
	
	@Test
	public void setNumPlayersTest() {
        this.g.setNumPlayers(3);
        assertEquals(3, this.g.getNumPlayers());
    }
}

