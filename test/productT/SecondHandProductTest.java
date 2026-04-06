package productT;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import product.*;
import user.RegisteredClient;

public class SecondHandProductTest {

    private SecondHandProduct shp;
    private RegisteredClient owner; 

    @Before
    public void setUp() {
    	this.owner = new RegisteredClient("testuser", LocalDate.now(), "12345678A", "password", true);
        this.owner.setHistories();
        this.shp = new SecondHandProduct("Test Product", "Description", "photo.png", ProductType.COMIC, this.owner);
    }
    
    @Test
    public void getValuationDate() {
        assertNull(this.shp.getValuationDate());
    }

    @Test
    public void setValuationDate() {
        LocalDate date = LocalDate.now();
        shp.setValuationDate(date);
        assertEquals(date, shp.getValuationDate());
    }

    @Test
    public void isAvailable() {
        assertFalse(shp.isAvailable()); // Default false
    }

    @Test
    public void setAvailable() {
        shp.setAvailable(true);
        assertTrue(shp.isAvailable());
    }

    @Test
    public void isPaidValuation() {
        assertFalse(shp.isPaidValuation()); // Default false
    }

    @Test
    public void setPaidValuation() {
        shp.setPaidValuation(true);
        assertTrue(shp.isPaidValuation());
    }

    @Test
    public void getStatus() {
        assertNull(shp.getStatus()); // Initially null
    }

    @Test
    public void setStatus() {
        shp.setStatus(ConservationStatus.VERY_GOOD);
        assertEquals(ConservationStatus.VERY_GOOD, shp.getStatus());
    }

    @Test
    public void changeEstimatedPrice() {
        shp.setEstimatedPrice(20.0);
        assertEquals(20.0, shp.getPrice(), 0.001);
    }

    @Test
    public void changeConservationStatus() {
        shp.setConservationStatus(ConservationStatus.PERFECT);
        assertEquals(ConservationStatus.PERFECT, shp.getStatus());
    }

    @Test
    public void changeAvailability() {
        shp.setAvailability(true);
        assertTrue(shp.isAvailable());
    }

    @Test
    public void valuationWasPaid() {
        shp.valuationWasPaid();
        assertTrue(shp.isPaidValuation());
    }

    @Test
    public void valuate() {
        shp.valuate(25.0, ConservationStatus.SLIGHTLY_USED);
        assertEquals(25.0, shp.getPrice(), 0.001);
        assertEquals(ConservationStatus.SLIGHTLY_USED, shp.getStatus());
        assertNotNull(shp.getValuationDate());
    }

}