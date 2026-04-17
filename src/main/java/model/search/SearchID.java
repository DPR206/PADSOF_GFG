package model.search;

/**
 * This interface defines a generic contract for searching
 * objects by their ID.
 *
 * <p>Classes implementing this interface will provide their own
 * specific implementation of the search logic and return type.</p>
 *
 * @author Sofía C.L.
 * @version 1.4
 * @see SearchStoreProducts
 */
public interface SearchID{

    /**
     * Searches for an object by its ID.
     *
     * <p>The type of object returned depends on the implementing class.
     * If no matching object is found, {@code null} may be returned.</p>
     *
     * @param id the ID of the object to search for
     * @return the object corresponding to the given ID, or {@code null} if not found
     */
    Object searchByID(int id);
}