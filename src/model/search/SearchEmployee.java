package model.search;

import model.store.Store;
import model.user.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements a search mechanism to find employees
 * in the store by their unique identifier (ID).
 *
 * <p>It retrieves all employees from the {@link Store} singleton
 * and performs a linear search to find a match.</p>
 *
 * @author Sofía C.L.
 * @version 1.4
 * @see SearchStoreProducts
 */
public class SearchEmployee implements SearchID {

    /**
     * Singleton instance of the store.
     */
    private Store s = Store.getInstance();

    /**
     * List of employees available in the store.
     */
    private List<Employee> users;

    /**
     * Constructs a SearchEmployee object and initializes
     * the employee list from the store.
     */
    public SearchEmployee(){
        this.users = new ArrayList<>(s.getEmployees().values());
    }

    /**
     * Searches for an employee by their ID.
     *
     * <p>If an employee with the given ID exists, it is returned.
     * Otherwise, {@code null} is returned.</p>
     *
     * @param id the ID of the employee to search for
     * @return the {@link Employee} with the given ID, or {@code null} if not found
     */
    @Override
    public Employee searchByID(int id){
        for(Employee e : this.users){
            if(Integer.parseInt(e.getId()) == id) {
                return e;
            }
        }
        return null;
    }
}