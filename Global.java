import play.*;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Calendar;
import play.Logger;
import java.util.Formatter;
import play.data.format.Formatters;
import java.util.Locale;


public class Global extends GlobalSettings {

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
	private static final SimpleDateFormat DATE_FORMAT_SIMPLE = new SimpleDateFormat("dd/MM/yyyy");    

	@Override
	public void onStart(Application application) {
		Logger.info("Application started...");
		Formatters.register(Date.class, new Formatters.SimpleFormatter<Date>() {
			@Override
			public Date parse(String input, Locale l) throws ParseException {
				try{
					Logger.info("Application started...");
					Logger.info(input);
					Long millis=Long.valueOf(input);
					Logger.info(DATE_FORMAT_SIMPLE.parse(input).toString());
					return DATE_FORMAT_SIMPLE.parse(input);         
				}	
				catch (ParseException ex){
					return DATE_FORMAT_SIMPLE.parse(input);         
				}
			}   

			@Override
			public String print(Date date, Locale l) {
				return DATE_FORMAT.format(date);
			}
		});     
		super.onStart(application);
	}

	public void onStop(Application app) {
		Logger.info("Application shutdown...");
	}

}