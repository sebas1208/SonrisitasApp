package util;

import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Calendar;
import play.Logger;

final public class Fechas {
	private Fechas(){

	}

	//Las fechas en string tienen el formato dia/mes/año
	public static Date stringToDate(String fecha){
		try{
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Date dateFecha = format.parse(fecha);
			return dateFecha;
		}
		catch(ParseException e){
			Logger.error("Fecha mal formateada" + e);
			return Calendar.getInstance().getTime();
		}
	}

	//Las horas tienen el formato hh:mm
	public static Date stringToTime(String hora){
		try{
			DateFormat format = new SimpleDateFormat("HH:mm");
			Date dateFecha = format.parse(hora);
			return dateFecha;
		}
		catch(ParseException e){
			Logger.error("Hora mal formateada" + e);
			return Calendar.getInstance().getTime();
		}	
	}

}