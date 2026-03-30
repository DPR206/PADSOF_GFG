/**
 *
 */
package product;

import user.RegisteredClient;
/**
 * Class name: Review
 * <p>
 * Description: It implements the reviews
 * @author Duna P.R. and Ana O.R.
 * @version 1.1
 * @see user.User
 */
public class Review {
	public static int totalId = -1;
	private final int id;
	private int scoring;
	private String comment;
	private RegisteredClient author;

	/**
	 * A review's complete constructor
	 *
	 * @param scoring, the punctuation given to a product
	 * @param comment, the comment about the product
	 * @param author, the author of the comment
	 */
	public Review(int id, int scoring, String comment, RegisteredClient author) {
		this.id = id;
		this.scoring = scoring;
		this.comment = comment;
		this.author = author;
	}

	/**
	 * Creates a new review
	 *
	 * @param scoring, the punctuation given to a product
	 * @param comment, the comment about the product
	 * @param author, the author of the comment
	 */
	public Review(int scoring, String comment, RegisteredClient author) {
		this.id = ++totalId;
		this.scoring = scoring;
		this.comment = comment;
		this.author = author;
	}

	/**
	 * It sets the review's author
	 * @param newAuthor the review's new author
	 */
	public void setAuthor(RegisteredClient newAuthor) {
		this.author = newAuthor;
	}

	/**
	 * It gets the review's id
	 * @return the review's id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * It gets the review's scoring
	 * @return the review's scoring
	 */
	public int getScoring() {
		return this.scoring;
	}

	/**
	 * Written information of a review
	 *
	 * @return String, information of a review
	 */
	public String toString() {
		return author.toString()+"\nScoring: "+scoring+"/5\n"+comment;
	}

}