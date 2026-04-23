package model.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.product.Category;

public class CategoryFilter{
	private List<Category> categories;
	
	public CategoryFilter(Category...categories) {
		this.categories = new ArrayList<>(Arrays.asList(categories));
	}
	
	public List<Category> getCategories(){
		return this.categories;
	}
}
