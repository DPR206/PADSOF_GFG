package model.search;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.product.Category;
/**
 * It implements the category filter for the searching filtering
 * @author Sofia C.L.
 * @version 1.0
 */
public class CategoryFilter implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L; /* Para el Save & Load */
	private List<Category> categories = new ArrayList<>();

	/**
	 * Constructor of the class
	 * @param categories, the list of categories we wish to search
	 */
	public CategoryFilter(List<Category> categories) {
		this.categories = categories;
	}

	/**
	 *
	 * @return the list of the categories to search
	 */
	public List<Category> getCategories(){
		return this.categories;
	}
}