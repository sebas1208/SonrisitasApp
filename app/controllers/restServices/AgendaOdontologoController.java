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
        List<AgendaOdontologo> agendaOdontologoList = AgendaOdontologo.find.all();
        return Results.ok(Json.toJson(agendaOdontologoList));
    }

    public Result uno(Long id){
        AgendaOdontologo agendaOdontologo = AgendaOdontologo.find.byId(id);
        if(agendaOdontologo == null) return Results.ok("{\"error\":\"No existe el agendaOdontologo\"}");
        return Results.ok(Json.toJson(agendaOdontologo));
    }

    public Result buscarPorOdontologo(Long idOdontologo){
        List<AgendaOdontologo> agendaOdontologoList = AgendaOdontologo.findByOdontologo(idOdontologo);
        return Results.ok(Json.toJson(agendaOdontologoList));
    }

    public Result nuevo(){
        DynamicForm dynamicForm = Form.form().bindFromRequest();
        final AgendaOdontologo agendaOdontologo = new AgendaOdontologo();
        agendaOdontologo.setAgeDia(Short.parseShort(dynamicForm.get("ageDia")));
        agendaOdontologo.setAgeHoraInicio(Fechas.stringToTime(dynamicForm.get("ageHoraInicio")));
        agendaOdontologo.setAgeHoraFin(Fechas.stringToTime(dynamicForm.get("ageHoraFin")));
        agendaOdontologo.setAgeDiaNombre(dynamicForm.get("ageDiaNombre"));
        agendaOdontologo.setAgeActivo(true);
        agendaOdontologo.setAgeFechaRegistro(Calendar.getInstance().getTime());
        agendaOdontologo.setOdoId(Ebean.find(Odontologo.class,Long.parseLong(dynamicForm.get("odoId"))));
        Ebean.execute(new TxRunnable() {
            public void run() {                
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
