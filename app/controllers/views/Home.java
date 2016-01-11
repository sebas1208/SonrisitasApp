package controllers;

import models.Usuario;
import play.*;
import play.mvc.*;

import views.html.*;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;
import play.data.validation.Constraints;
import play.data.Form;
import play.Logger;


public class Home extends Controller {
	
	public Result index(){
		return ok(index.render());
	}

	public Result home(){
		return ok(home.render());
	}

	public Result authUser(){
		Form<SignUp> signUpForm = Form.form(SignUp.class).bindFromRequest();

		if(signUpForm.hasErrors()){
			Logger.error(signUpForm.errorsAsJson().toString());
			return Results.badRequest(signUpForm.errorsAsJson());
		}
		SignUp newUser =  signUpForm.get();
		Usuario usuario = Usuario.findByEmail(newUser.email);
		if(usuario == null){
			return Results.badRequest("{\"email\":[\"El email no se encuentra asociado a ninguna cuenta\"]}");
		}else{
			usuario = Usuario.findByEmailAndPassword(newUser.email, newUser.password);
			if(usuario == null){
				return Results.badRequest("{\"password\":[\"El password que ingresaste no es el correcto\"]}");
			}else{
				Logger.info(String.valueOf(usuario.getUsuId()));
				//session("userId",String.valueOf(usuario.getUsuId()));
				return ok();
			}
		}		
	}

	private static ObjectNode buildJsonResponse(String type, String message) {
		ObjectNode wrapper = Json.newObject();
		ObjectNode msg = Json.newObject();
		msg.put("message", message);
		wrapper.put(type, msg);
		return wrapper;
	}

	public static class UserForm {
		@Constraints.Required
		@Constraints.Email
		public String email;
	}

	public static class SignUp extends UserForm {
		@Constraints.Required
		@Constraints.MinLength(6)
		public String password;
	}

	
}
