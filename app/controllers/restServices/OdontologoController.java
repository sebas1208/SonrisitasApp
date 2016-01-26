package controllers.restServices;

import play.*;
import play.mvc.*;
import models.Odontologo;
import models.Usuario;
import play.mvc.Result;
import play.mvc.Results;
import play.api.libs.ws.WS;
import play.libs.Json;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import com.avaje.ebean.TxRunnable;
import com.avaje.ebean.Ebean;
import play.data.DynamicForm;
import play.data.Form;

public class OdontologoController extends Controller {

    public Result todos() {
        List<Odontologo> odontologos = Odontologo.find.all();
        return Results.ok(Json.toJson(odontologos));
    }

    public Result uno(Long id){
        Odontologo odontologo = Odontologo.find.byId(id);
        if(odontologo == null) return Results.ok("{\"error\":\"No existe el usuario\"}");
        return Results.ok(Json.toJson(odontologo));
    }

    public Result nuevo(){
        Form<Odontologo> odontologoForm = Form.form(Odontologo.class).bindFromRequest();
        if(odontologoForm.hasErrors()){
            Logger.error(odontologoForm.errorsAsJson().toString());
            return Results.badRequest(odontologoForm.errorsAsJson());
        }
        final Odontologo odontologo = odontologoForm.get();
        odontologo.setOdoActivo(true);
        odontologo.setOdoFechaRegistro(Calendar.getInstance().getTime());
        Ebean.execute(new TxRunnable() {
            public void run() {
                Ebean.save(odontologo);
            }
        });
        return Results.ok(Json.toJson(odontologo));
    }

    public Result editar(Long id){
        DynamicForm dynamicForm = Form.form().bindFromRequest();
        Odontologo odontologo = Odontologo.find.byId(id);
        odontologo.setOdoNombres(dynamicForm.get("odoNombres"));
        odontologo.setOdoApellidos(dynamicForm.get("odoApellidos"));
        odontologo.setOdoDireccion(dynamicForm.get("odoDireccion"));
        odontologo.setOdoTelefono(dynamicForm.get("odoTelefono"));
        odontologo.setOdoEmail(dynamicForm.get("odoEmail"));
        odontologo.setOdoCedula(dynamicForm.get("odoCedula"));
        odontologo.setOdoActivo(Boolean.valueOf(dynamicForm.get("odoActivo")));
        Ebean.save(odontologo);
        return Results.ok(Json.toJson(odontologo));
    }

    public Result borrar(Long id){
        Ebean.execute(new TxRunnable() {
          public void run() {
            Odontologo odontologo = Ebean.find(Odontologo.class, id);
            if(odontologo != null){
                Ebean.delete(odontologo);
            }
        }});
        return Results.ok("Borrado: " + id);
    }
}
