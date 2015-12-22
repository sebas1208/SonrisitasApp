package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Home extends Controller {
	
	public Result home(){
		return ok(index.render());
	}
}