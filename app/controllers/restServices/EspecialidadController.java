package controllers.restServices;

import play.*;
import play.mvc.*;
import models.Especialidad;
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

public class EspecialidadController extends Controller {

    public Result todos() {
        List<Especialidad> especialidades = Especialidad.find.all();
        return Results.ok(Json.toJson(especialidades));
    }

    public Result uno(Long id){
        Especialidad especialidad = Especialidad.find.byId(id);
        if(especialidad == null) return Results.ok("{\"error\":\"No existe el Especialidad\"}");
        return Results.ok(Json.toJson(especialidad));
    }

    public Result nuevo(){
        DynamicForm dynamicForm = Form.form().bindFromRequest();
        final Especialidad especialidad = new Especialidad();
        Ebean.execute(new TxRunnable() {
            public void run() {
                especialidad.setEspNombre(dynamicForm.get("espNombre"));
                especialidad.setEspArea(dynamicForm.get("espArea"));
                especialidad.setEspActivo(true);
                especialidad.setEspFechaRegistro(Calendar.getInstance().getTime());
                Ebean.save(especialidad);
            }
        });
        return Results.ok(Json.toJson(especialidad));
    }

    public Result editar(Long id){
        DynamicForm dynamicForm = Form.form().bindFromRequest();
        Especialidad especialidad = Especialidad.find.byId(id);
        especialidad.setEspNombre(dynamicForm.get("espNombre"));
        especialidad.setEspArea(dynamicForm.get("espArea"));
        especialidad.setEspActivo(Boolean.valueOf(dynamicForm.get("espActivo")));
        Ebean.save(especialidad);
        return Results.ok(Json.toJson(especialidad));
    }

    public Result borrar(Long id){
        Ebean.execute(new TxRunnable() {
          public void run() {
            Especialidad especialidad = Ebean.find(Especialidad.class, id);
            if(especialidad != null){
                Ebean.delete(especialidad);
            }
        }});
        return Results.ok("Borrado: " + id);
    }
}
