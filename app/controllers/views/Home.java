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

	public Result admin(){
		return ok(admin.render());
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
				if(usuario.getUsuActivo()){
					if(!usuario.getAdministradorList().isEmpty()){
						return ok("{\"redirectTo\":\"admin\"}");
					}
					return ok("{\"redirectTo\":\"user\"}");
				}else{
					return Results.badRequest("{\"cuenta\":[\"Tu cuenta todavía no ha sido verificada por favor ingresa a tu correo electronico y sigue las instrucciones.\"]}");
				}
			}
		}
	}

	public Result redirectToHome(String userEmail){
		Usuario usuairo = Usuario.findByEmail(userEmail);
		if(usuairo != null){
			if(usuairo.getAdministradorList() == null){
				return ok(admin.render());
			}
			return ok(home.render());
		}else{
			return badRequest();
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

		public String getEmail(){
			return this.email;
		}

		public void setEmail(String email){
			this.email = email;
		}
	}

	public static class SignUp extends UserForm {
		@Constraints.Required
		@Constraints.MinLength(6)
		public String password;

		public String getPassword(){
			return this.password;
		}

		public void setPassword(String password){
			this.password = password;
		}
	}
}
