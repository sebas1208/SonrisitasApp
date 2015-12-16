package controllers.restServices;

import play.*;
import play.mvc.*;
import models.Administrador;
import models.Usuario;
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

public class AdministradorController extends Controller {

    public Result todos() {
        List<Administrador> administradores = Administrador.find.all();
        return Results.ok(Json.toJson(administradores));
    }

    public Result uno(Long id){
        Administrador administrador = Administrador.find.byId(id);
        if(administrador == null) return Results.ok("{\"error\":\"No existe el usuario\"}");
        return Results.ok(Json.toJson(administrador));
    }

    public Result nuevo(){
        DynamicForm dynamicForm = Form.form().bindFromRequest();
        final Administrador administrador = new Administrador();
        Ebean.execute(new TxRunnable() {
            public void run() {                
                administrador.setAdmNombre(dynamicForm.get("nombre"));
                administrador.setAdmApellido(dynamicForm.get("apellido"));
                administrador.setAdmDireccion(dynamicForm.get("direccion"));
                administrador.setAdmTelefono(dynamicForm.get("telefono"));
                administrador.setAdmEmail(dynamicForm.get("email"));
                administrador.setUsuId(Ebean.find(Usuario.class,Long.parseLong(dynamicForm.get("usuario"))));
                administrador.setAdmActivo(true);
                administrador.setAdmFechaRegistro(Calendar.getInstance().getTime());
                Ebean.save(administrador);
            }
        });
        return Results.ok(Json.toJson(administrador));
    }

    public Result borrar(Long id){
        Ebean.execute(new TxRunnable() {
          public void run() {
            Administrador administrador = Ebean.find(Administrador.class, id);
            if(administrador != null){
                Ebean.delete(administrador);
            }
        }});
        return Results.noContent();
    }
}
