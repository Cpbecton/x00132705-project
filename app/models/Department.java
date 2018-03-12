package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.validation.*;

// Product Entity managed by the ORM
@Entity
public class Department extends Model {

    // Properties
    // Annotate id as the primary key
    @Id
    private Long id;

    @Constraints.Required
    private String department;

    @OneToMany
    private List<Employee> employees;

    // Default constructor
    public Department() {

    }

    public Department(Long id, String department, List<Employee> employees) {
        this.setId(id);
        this.setDepartment(department);
        this.setEmployee(employees);
    }

    //Generic query helper for entity Computer with id Long
    public static Finder<Long,Department> find = new Finder<Long,Department>(Department.class);

    //Find all Departments in the database in ascending order by name
    public static List<Department> findAll() {
        return Department.find.where().orderBy("Department asc").findList();
    }

    // Generate options for an HTML select control
    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<>();

        // Get all categories from the DB and add to the options Hash map
        for(Department d: Department.findAll()) {
            options.put(d.getId().toString(), d.getDepartment());
        }
        return options;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<Employee> getEmployee() {
        return employees;
    }

    public void setEmployee(List<Employee> employees) {
        this.employees = employees;
    }
}
