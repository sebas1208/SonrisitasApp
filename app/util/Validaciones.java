package util;

import java.text.SimpleDateFormat;
import models.Usuario;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Calendar;
import play.Logger;

final public class Validaciones {
	String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{5,50}$";
	String userMessage;

	private Validaciones(){

	}

	//Las fechas en string tienen el formato dia/mes/año
	public boolean usuarioValido(Usuario user){
		boolean retorno = true;
		String userName = user.getUsuUser();
		String userPassword = user.getUsuPassword();
		String userPreguntaRecuperacion = user.getUsuPreguntaRecuperacion();
		String userEmail = user.getUsuEmail();

		if(nombreUsuarioValido(userName)){
			retorno = false;
			userMessage = "El nombre de usuario debe ser mayor a 5 y menor a 50";
		}
		if(passwordUsuarioValido(userPassword)){
			retorno = false;
			userMessage = "El password de usuario debe ser mayor a 5 y menor a 50, debe tener una letra mayuscula y un digito";
		}
		return retorno;

	}

	public boolean nombreUsuarioValido(String userName){
		return (userName.length() > 50 && userName.length() < 5);
	}

	public boolean passwordUsuarioValido(String userPassword){
		return (userPassword.length() > 50 && userPassword.length() < 8 && userPassword.matches(passwordRegex));
	}
}