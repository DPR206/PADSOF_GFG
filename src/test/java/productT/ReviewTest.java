package productT;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import product.Review;

class ReviewTest {

    private Review review;

    @BeforeEach
    void setUp() {
        review = new Review(1, 4, "Great product!");
    }

    @Test
    void testConstructorWithIdScoringComment() {
        Review r = new Review(1, 3, "Good product");
        assertEquals(1, r.getId());
        assertEquals(3, r.getScoring());
        assertEquals("Good product", r.getComment());
        assertNull(r.getAuthor());
    }

    @Test
    void testGetId() {
        assertEquals(1, review.getId());
    }

    @Test
    void testGetScoring() {
        assertEquals(4, review.getScoring());
    }

    @Test
    void testGetComment() {
        assertEquals("Great product!", review.getComment());
    }
}
