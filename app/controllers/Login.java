package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Login extends Controller {

    public Result login() {
        return ok(login.render("Your new application is ready."));
    }

}
