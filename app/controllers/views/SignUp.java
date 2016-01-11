package controllers;

import play.*;
import play.mvc.*;
import util.Gmail;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import play.Logger;
import views.html.*;

public class SignUp extends Controller {
	
	public Result signUp(){
		return ok(signUp.render());
	}

	public Result emailMessage(String email){
		try{
			Gmail.send(email,"Sonrisitas","Holi");
		}catch(Exception e){
			Logger.error("El correo no se ha podido enviar");
			e.printStackTrace();
			return badRequest();
		}
		return ok(emailMessage.render(email));
	}

}