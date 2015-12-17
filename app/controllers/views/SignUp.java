package controllers.views;

import play.*;
import play.mvc.*;

import views.html.*;

public class SignUp extends Controller {
	
	public Result signUp(){
		return ok(signUp.render());
	}

}