package controllers;

import controllers.security.AuthAdmin;
import controllers.security.Secured;
import play.mvc.*;
import play.data.*;
import play.db.ebean.Transactional;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import views.html.*;

// Import models
import models.*;
import models.users.*;


public class HomeController extends Controller {

    // Declare a private FormFactory instance
    private FormFactory formFactory;

    //  Inject an instance of FormFactory it into the controller via its constructor
    @Inject
    public HomeController(FormFactory f) {
        this.formFactory = f;
    }

    // Method retuns the logged in user (or null)
    private User getUserFromSession() {
        return User.getUserById(session().get("email"));
    }

    public Result index() {

        return ok(index.render(getUserFromSession()));
    }

    public Result about() {

        return ok(about.render(getUserFromSession()));
    }

    public Result employees(Long cat) {

        // Get list of all categories in ascending order
        List<Department> departmentList = Department.findAll();
        java.util.List<Employee> employeesList = new ArrayList<Employee>();

        if (cat == 0) {
            // Get list of all categories in ascending order
            employeesList = Employee.findAll();
        }
        else {
            // Get products for selected category
            // Find category first then extract products for that cat.
            employeesList = Department.find.ref(cat).getEmployee();
        }

        return ok(Employee.render(employeesList, departmentList, getUserFromSession()));
    }
}
