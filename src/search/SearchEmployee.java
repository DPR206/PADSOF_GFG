package search;

import user.Employee;

public class SearchEmployee extends SearchID{
    private List<Employee> employees;

    @Override
    public Employee searchByID(int id){
        for(Employee e: this.employees){
            if(e.getId() == id) return e;
        }
        return null;
    }
}