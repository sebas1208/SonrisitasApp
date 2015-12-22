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
        Form<Odontologo> userForm = Form.form(Odontologo.class).bindFromRequest();
        // Map<String,String> anyData = new HashMap();
        // anyData.put("usuFechaRegistro", "22/12/2015");
        // userForm.bind(anyData);
        DynamicForm dynamicForm = Form.form().bindFromRequest();

        // if(userForm.hasErrors()){
        //     Logger.error(userForm.errorsAsJson().toString());
        //     return Results.badRequest(userForm.errorsAsJson());
        // }
        Logger.error(userForm.get().getUsuId().getUsuFechaRegistro().toString());
        final Odontologo odontologo = userForm.get();
        odontologo.setOdoActivo(false);
        odontologo.setOdoFechaRegistro(Calendar.getInstance().getTime());
        Ebean.execute(new TxRunnable() {
            public void run() {                                
                Ebean.save(odontologo);
            }
        });
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
        return Results.noContent();
    }
}
