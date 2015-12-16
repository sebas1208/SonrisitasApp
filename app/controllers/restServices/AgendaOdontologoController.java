package controllers.restServices;

import play.*;
import play.mvc.*;
import models.AgendaOdontologo;
import models.Odontologo;
import play.db.ebean.*;
import play.mvc.Result;
import play.mvc.Results;
import play.api.libs.ws.WS;
import play.libs.Json;

import java.util.List;
import java.util.Date;
import java.util.Calendar;
import java.util.ArrayList;
import com.avaje.ebean.TxRunnable;
import com.avaje.ebean.Ebean;
import play.data.DynamicForm;
import play.data.Form;
import util.Fechas;

public class AgendaOdontologoController extends Controller {

    public Result todos() {
        List<AgendaOdontologo> agendaOdontologos = AgendaOdontologo.find.all();
        return Results.ok(Json.toJson(agendaOdontologos));
    }

    public Result uno(Long id){
        AgendaOdontologo agendaOdontologo = AgendaOdontologo.find.byId(id);
        if(agendaOdontologo == null) return Results.ok("{\"error\":\"No existe el agendaOdontologo\"}");
        return Results.ok(Json.toJson(agendaOdontologo));
    }

    public Result nuevo(){
        DynamicForm dynamicForm = Form.form().bindFromRequest();
        final AgendaOdontologo agendaOdontologo = new AgendaOdontologo();
        Ebean.execute(new TxRunnable() {
            public void run() {                
                agendaOdontologo.setAgeDia(Short.parseShort(dynamicForm.get("dia")));
                agendaOdontologo.setAgeHoraInicio(Fechas.stringToTime(dynamicForm.get("horaInicio")));
                agendaOdontologo.setAgeHoraFin(Fechas.stringToTime(dynamicForm.get("horaFin")));
                agendaOdontologo.setAgeDiaNombre(dynamicForm.get("nombreDia"));
                agendaOdontologo.setAgeActivo(true);
                agendaOdontologo.setAgeFechaRegistro(Calendar.getInstance().getTime());
                agendaOdontologo.setOdoId(Ebean.find(Odontologo.class,Long.parseLong(dynamicForm.get("odontologo"))));
                Ebean.save(agendaOdontologo);
            }
        });
        return Results.ok(Json.toJson(agendaOdontologo));
    }

    public Result borrar(Long id){
        Ebean.execute(new TxRunnable() {
          public void run() {
            AgendaOdontologo agendaOdontologo = Ebean.find(AgendaOdontologo.class, id);
            if(agendaOdontologo != null){
                Ebean.delete(agendaOdontologo);
            }
        }});
        return Results.ok("Borrado: " + id);
    }
}
