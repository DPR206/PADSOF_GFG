package search;

import user.Employee;
package search;
/**
 * Class name: SearchEmployee
 * <p>
 * Description: It implements the employee filter through ID
 * @author Sofía C.L.
 * @version 1.3
 * @see SearchStoreProducts
  */
public class SearchEmployee extends SearchID{
    private List<User> users;
    
    public SearchEmployee(Store s){
        this.users = s.getUsers();
    }
       
    @Override
    public Employee searchByID(int id){
        for(Employee e: this.employees){
            if(e.getId() == id) return e;
        }
        return null;
    }
}