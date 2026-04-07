package productT;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import product.Review;

public class ReviewTest {

    private Review review;

    @Before
    public void setUp() {
        review = new Review(1, 4, "Great product!");
    }

    @Test
    public void testConstructorWithIdScoringComment() {
        Review r = new Review(1, 3, "Good product");
        assertEquals(1, r.getId());
        assertEquals(3, r.getScoring());
        assertEquals("Good product", r.getComment());
        assertNull(r.getAuthor());
    }

    @Test
    public void testGetId() {
        assertEquals(1, review.getId());
    }

    @Test
    public void testGetScoring() {
        assertEquals(4, review.getScoring());
    }

    @Test
    public void testGetComment() {
        assertEquals("Great product!", review.getComment());
    }
}
