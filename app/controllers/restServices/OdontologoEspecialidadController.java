package controllers.restServices;

import play.*;
import play.mvc.*;
import models.OdontologoEspecialidad;
import models.Odontologo;
import models.Especialidad;
import play.mvc.Result;
import play.mvc.Results;
import play.api.libs.ws.WS;
import play.libs.Json;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import com.avaje.ebean.TxRunnable;
import com.avaje.ebean.Ebean;
import play.data.DynamicForm;
import play.data.Form;

public class OdontologoEspecialidadController extends Controller {

     public Result todos() {
        List<OdontologoEspecialidad> odontologosEspecialidad = OdontologoEspecialidad.find.all();
        return Results.ok(Json.toJson(odontologosEspecialidad));
    }

    public Result uno(Long id){
        OdontologoEspecialidad odontologoEspecialidad = OdontologoEspecialidad.find.byId(id);
        if(odontologoEspecialidad == null) return Results.ok("{\"error\":\"No existe el odontologoEspecialidad\"}");
        return Results.ok(Json.toJson(odontologoEspecialidad));
    }

    public Result nuevo(){
        DynamicForm dynamicForm = Form.form().bindFromRequest();
        final OdontologoEspecialidad odontologoEspecialidad = new OdontologoEspecialidad();
        Ebean.execute(new TxRunnable() {
            public void run() {
                odontologoEspecialidad.setOdoId(Ebean.find(Odontologo.class,Long.parseLong(dynamicForm.get("odontologo"))));
                odontologoEspecialidad.setEspId(Ebean.find(Especialidad.class,Long.parseLong(dynamicForm.get("especialidad"))));
                Ebean.save(odontologoEspecialidad);
            }
        });
        return Results.ok(Json.toJson(odontologoEspecialidad));
    }

    public Result borrar(Long id){
        Ebean.execute(new TxRunnable() {
          public void run() {
            OdontologoEspecialidad odontologoEspecialidad = Ebean.find(OdontologoEspecialidad.class, id);
            if(odontologoEspecialidad != null){
                Ebean.delete(odontologoEspecialidad);
            }
        }});
        return Results.ok("Borrado: " + id);
    }
}
