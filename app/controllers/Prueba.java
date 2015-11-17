package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Prueba extends Controller {

    public Result prueba() {
        return ok(prueba.render("ALgo"));
    }

}
