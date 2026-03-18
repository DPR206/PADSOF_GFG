package product;

import store.Store;

/**
 * Class name: Category
 * <p>
 * Description: It implements the categories
 * @author Ana O.R.
 * @version 1.1
 * @see Store
 */
public class Category {
    /* The category's name */
    public String name; // NOTE: Debe ser único (mirar explicación a continuación)
    // Encontré dos formas de que fuera único:
    //  a. https://stackoverflow.com/questions/27474457/uniqueness-of-an-attribute-in-an-object
    //  b. https://softwareengineering.stackexchange.com/questions/170912/ways-to-ensure-unique-instances-of-a-class
    // La segunda me llevó a la idea de que se puede hacer una operación similar contando con que Store pueda devolver
    // la categoría con cierto name (hashmap <name, category>) así que decidí tirar por ese camino, ya que ocuparía
    // menos memoria (supuestamente)
    /* The list of products that belong to this category */
    public Product[] products; // DUE: Terminar de implementar esto (estoy pensando cómo hacerlo)

    /*------------------------------------------------- CONSTRUCTORS -------------------------------------------------*/

    /**
     * Category's constructor
     * @param name the new category's name
     */
    Category(String name) {
        this.name = name;
        this.products = new Product[0];
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * It allows a manager to add categories to the store
     * @param store the store
     * @param name  the new category's name
     * @return the new category
     */
    public Category createCategory(Store store, String name) {
        if (store.getCategoryFromName(name) == null) {
            return new Category(name);
        }
        return null;
    }

    /**
     * It allows a manager to change a Category's name
     * @param store the store
     * @param name  the category's new name
     */
    public void changeName(Store store, String name) {
        if (store.getCategoryFromName(name) == null) {
            this.name = name;
        }
    }

    /**
     * It returns the category's name
     * @return the category's name
     */
    public String getName() {
        return this.name;
    }
}