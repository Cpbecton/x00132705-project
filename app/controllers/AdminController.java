package controllers;

import controllers.security.AuthAdmin;
import controllers.security.Secured;
import play.data.Form;
import play.data.FormFactory;
import play.db.ebean.Transactional;
import play.mvc.*;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import views.html.admin.*;
import models.*;
import models.users.User;

// Require Login
@Security.Authenticated(Secured.class)
// Authorise user (check if admin)
@With(AuthAdmin.class)
public class AdminController extends Controller {

    // Declare a private FormFactory instance
    private FormFactory formFactory;

    //  Inject an instance of FormFactory it into the controller via its constructor
    @Inject
    public AdminController(FormFactory f) {
        this.formFactory = f;
    }

    // Method returns the logged in user (or null)
    private User getUserFromSession() {
        return User.getUserById(session().get("email"));
    }


    public Result artists(Long cat) {

        // Get list of all categories in ascending order
        List<Genre> genreList = Genre.findAll();
        List<Artist> artistsList = new ArrayList<Artist>();

        if (cat == 0) {
            // Get list of all categories in ascending order
            artistsList = Artist.findAll();
        }
        else {
            // Get products for selected category
            // Find category first then extract products for that cat.
            artistsList = Genre.find.ref(cat).getArtist();
        }

        return ok(Artists.render(artistsList, genreList, getUserFromSession()));
    }

    // Render and return  the add new product page
    // The page will load and display an empty add product form

    public Result addArtist() {

        // Create a form by wrapping the Product class
        // in a FormFactory form instance
        Form<Artist> addArtistForm = formFactory.form(Artist.class);

        // Render the Add Product View, passing the form object
        return ok(addArtist.render(addArtistForm, getUserFromSession()));
    }

    @Transactional
    public Result addArtistSubmit() {

        // Create a product form object (to hold submitted data)
        // 'Bind' the object to the submitted form (this copies the filled form)
        Form<Artist> newArtistForm = formFactory.form(Artist.class).bindFromRequest();

        // Check for errors (based on Product class annotations)
        if(newArtistForm.hasErrors()) {
            // Display the form again
            return badRequest(addArtist.render(newArtistForm, getUserFromSession()));
        }

        // Extract the product from the form object
        Artist a = newArtistForm.get();

        if (a.getId() == null) {
            // Save to the database via Ebean (remember Product extends Model)
            a.save();
        }
        // Product already exists so update
        else if (a.getId() != null) {
            a.update();
        }

        // Set a success message in temporary flash
        // for display in return view
        flash("success", "Artist " + a.getArtist() + " has been created/ updated");

        // Redirect to the admin home
        return redirect(routes.AdminController.artists(0));
    }

    // Update a product by ID
    // called when edit button is pressed
    @Transactional
    public Result updateArtist(Long id) {

        Artist a;
        Form<Artist> artistsForm;

        try {
            // Find the product by id
            a = Artist.find.byId(id);

            // Create a form based on the Product class and fill using p
            artistsForm = formFactory.form(Artist.class).fill(a);

            } catch (Exception ex) {
                // Display an error message or page
                return badRequest("error");
        }
        // Render the updateProduct view - pass form as parameter
        return ok(addArtist.render(artistsForm, getUserFromSession()));
    }

    // Delete Product by id
    @Transactional
    public Result deleteArtist(Long id) {

        // find product by id and call delete method
        Artist.find.ref(id).delete();
        // Add message to flash session
        flash("success", "Artist has been deleted");

        // Redirect to products page
        return redirect(routes.AdminController.artists(0));
    }

}
