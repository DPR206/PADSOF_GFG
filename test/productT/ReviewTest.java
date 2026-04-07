package productT;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import product.Review;
import user.RegisteredClient;

public class ReviewTest {

    private Review review;
    private RegisteredClient author;

    @Before
    public void setUp() {
        author = new RegisteredClient("testUser", "12345678A", "password", true);
        review = new Review(4, "Great product!", author);
    }

    @Test
    public void testConstructorWithScoringCommentAuthor() {
        Review r = new Review(5, "Excellent!", author);
        assertEquals(5, r.getScoring());
        assertEquals("Excellent!", r.getComment());
        assertEquals(author, r.getAuthor());
        assertTrue(r.getId() >= 0);
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
    public void testGetAuthor() {
        assertEquals(author, review.getAuthor());
    }

    @Test
    public void testSetAuthor() {
        RegisteredClient newAuthor = new RegisteredClient("newUser", "87654321B", "newpass", false);
        review.setAuthor(newAuthor);
        assertEquals(newAuthor, review.getAuthor());
    }

    @Test
    public void testGetId() {
        assertTrue(review.getId() >= 0);
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
