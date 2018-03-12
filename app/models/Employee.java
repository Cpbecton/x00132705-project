package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

// Product Entity managed by the ORM
@Entity
public class Employee extends Model {

    // Properties
    // Annotate id as the primary key
    @Id
    private Long id;

    // Other fields marked as being required (for validation purposes)
    @Constraints.Required
    private String employee;

    @ManyToOne
    private Department department;

    @Constraints.Required
    private String address;

    // Default constructor
    public  Employee() {
    }

    // Constructor to initialise object
    public  Employee(Long id, String employee, Department department, String address) {
        this.id = id;
        this.employee = employee;
        this.department = department;
        this.address = address;
    }

    //Generic query helper for entity Computer with id Long
    public static Finder<Long,Employee> find = new Finder<Long,Employee>(Employee.class);

    // Find all Products in the database
    // Filter product name 
    public static List<Employee> findAll() {
        return Employee.find.all();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
