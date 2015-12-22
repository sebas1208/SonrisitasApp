package controllers;

import play.*;
import play.mvc.*;
import util.GoogleMail;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import play.Logger;
import views.html.*;

public class SignUp extends Controller {
	
	public Result signUp(){
		return ok(signUp.render());
	}

	public Result signUpOdontologo(){
		return ok(signUpOdontologo.render());
	}

	public Result emailMessage(String email){
		try{
			GoogleMail.Send("sebas1208.avalos","goog1208",email,"Clinica Sonrisitas Confirmacion de Correo", "Querido Usuario:\nPara activar tu cuenta has clic en el siguiente enlace: " + "enlace");
		}catch(Exception e){
			Logger.error("El correo no se ha podido enviar");
			e.printStackTrace();
		}
		return ok(emailMessage.render(email));
	}

}