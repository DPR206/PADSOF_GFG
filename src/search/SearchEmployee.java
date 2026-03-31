package search;

import store.Store;
import user.Employee;
import user.User;

import java.util.List;

/**
 * Class name: SearchEmployee
 * <p>
 * Description: It implements the employee filter through ID
 * @author Sofía C.L.
 * @version 1.3
 * @see SearchStoreProducts
  */
public class SearchEmployee implements SearchID{
    private List<User> users;
     /**
	 * Creates the class
	 *
	 * @param s, the store to create the list of users
	 */
    public SearchEmployee(Store s){
        this.users = s.getUsers();
    }

    /**
	 * Creates the class
	 *
	 * @param id, searches through all the ids to return the employee but returns null if the employee doesn't exist
	 */
    public Employee searchByID(int id){
        for(Employee e: this.employees){
            if(e.getId() == id) return e;
        }
        return null;
    }
}