/**
 *
 */
package product;

import user.RegisteredClient;
/**
 * Class name: Review
 * <p>
 * Description: It implements the reviews
 * @author Duna P.R.
 * @version 1.0
 * @see user.User
 */
public class Review {
	private int scoring;
	private String comment;
	private RegisteredClient author;

	/**
	 * Creates a new review
	 *
	 * @param scoring, the punctuation given to a product
	 * @param comment, the comment about the product
	 * @param author, the author of the comment
	 */
	public Review(int scoring, String comment, RegisteredClient author) {
		this.scoring = scoring;
		this.comment = comment;
		this.author = author;
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