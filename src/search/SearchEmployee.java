package search;

import store.Store;
import user.Employee;
import user.User;

import java.util.ArrayList;
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
    
    private Store s = Store.getInstance();
    private List<Employee> users;
     /**
	 * Creates the class
	 *
	 * @param s, the store to create the list of users
	 */
    public SearchEmployee(){
        this.users = new ArrayList<>(s.getEmployees().values());
    }

    /**
	 * Creates the class
	 *
	 * @param id, searches through all the ids to return the employee but returns null if the employee doesn't exist
	 */
    public Employee searchByID(int id){
        for(Employee e: this.users){
            if(Integer.parseInt(e.getId()) == id) return e;
        }
        return null;
    }
}