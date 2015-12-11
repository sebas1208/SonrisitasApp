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
import com.avaje.ebean.TxRunnable;
import com.avaje.ebean.Ebean;
import play.data.DynamicForm;
import play.data.Form;

public class OdontologoController extends Controller {

    //READ OPERATION
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
        DynamicForm dynamicForm = Form.form().bindFromRequest();
        Ebean.execute(new TxRunnable() {
            public void run() {
                Odontologo odontologo = new Odontologo();
                odontologo.setOdoNombres(dynamicForm.get("nombres"));
                odontologo.setOdoApellidos(dynamicForm.get("apellidos"));
                odontologo.setOdoDireccion(dynamicForm.get("direccion"));
                odontologo.setOdoTelefono(dynamicForm.get("telefono"));
                odontologo.setOdoEmail(dynamicForm.get("email"));
                odontologo.setOdoCedula(dynamicForm.get("cedula"));
                odontologo.setUsuId(Ebean.find(Usuario.class,Long.parseLong(dynamicForm.get("usuario"))));
                odontologo.setOdoActivo(true);
                odontologo.setOdoFechaRegistro(new Date());
                Ebean.save(odontologo);
            }
        });
        return Results.ok("Usuario " + dynamicForm.get("usuario"));
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
