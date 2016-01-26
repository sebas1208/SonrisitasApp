package controllers;

import play.*;
import play.mvc.*;
import util.Gmail;
import models.Usuario;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import play.Logger;
import com.avaje.ebean.Ebean;
import views.html.*;

public class SignUp extends Controller {

	public Result confirmEmail(String random, String email){
		Usuario usuario = Usuario.findByEmail(email);
		if(usuario != null){
			if(usuario.getUsuConfirmEmailRandom().equals(random)){
				usuario.setUsuActivo(true);
				Ebean.save(usuario);
				return ok(emailConfirmed.render());
			}else{
				return badRequest(pageNotFound.render());
			}
		}else{
			return badRequest(pageNotFound.render());
		}
	}

	public Result emailMessage(String email){
		try{
			Usuario usuario = Usuario.findByEmail(email);
			if(usuario != null){
				String message = "Saludos de la Clinica Dental Sonrisitas.\nPara confirmar su cuenta por favor haz click en el siguiente enlace: https://obscure-atoll-1131.herokuapp.com/confirmEmail/" + usuario.getUsuConfirmEmailRandom() + "/" + email;
				Gmail.send(email,"Sonrisitas Confirmacion de Cuenta",message);
			}else{
				return badRequest();
			}
		}catch(Exception e){
			Logger.error("El correo no se ha podido enviar");
			e.printStackTrace();
			return badRequest();
		}
		return ok(emailMessage.render(email));
	}

}