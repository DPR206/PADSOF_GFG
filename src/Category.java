/**
 * Class name: Category
 * <p>
 * Description: It implements the categories
 * @author Ana O.R.
 * @version 1.0
 * <p>
 * Copyright??
 */
public class Category {
    public String name; /* The category's name */ // NOTE: Debe ser único (mirar explicación a continuación)
    // Encontré dos formas de que fuera único:
    //  a. https://stackoverflow.com/questions/27474457/uniqueness-of-an-attribute-in-an-object
    //  b. https://softwareengineering.stackexchange.com/questions/170912/ways-to-ensure-unique-instances-of-a-class
    // La segunda me llevó a la idea de que se puede hacer una operación similar contando con que Store pueda devolver
    // la categoría con cierto name así que decidí tirar por ese camino, ya que ocuparía menos memoria (supuestamente)

    /**
     * Category's constructor
     * @param store the store
     * @param name  the new category's name
     */
    Category(Store store, String name) {
        if (store.getCategoryFromName(name) == null) {
            this.name = name;
        }
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
}