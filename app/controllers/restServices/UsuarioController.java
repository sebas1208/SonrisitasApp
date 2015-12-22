package controllers.restServices;

import play.*;
import play.mvc.*;
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
import play.Logger;

public class UsuarioController extends Controller {

    public Result todos() {
        List<Usuario> usuarios = Usuario.find.all();
        return Results.ok(Json.toJson(usuarios));
    }

    public Result uno(Long id){
        Usuario usuario = Usuario.find.byId(id);
        if(usuario == null) return Results.badRequest("{\"error\":\"No existe el usuario\"}");
        return Results.ok(Json.toJson(usuario));
    }

    public Result nuevo(){
        Form<Usuario> userForm = Form.form(Usuario.class).bindFromRequest();               
        if(userForm.hasErrors()){
            Logger.error(userForm.errorsAsJson().toString());
            return Results.badRequest(userForm.errorsAsJson());
        }
        final Usuario usuario = userForm.get();
        usuario.setUsuActivo(false);
        usuario.setUsuFechaRegistro(Calendar.getInstance().getTime());
        Ebean.execute(new TxRunnable() {
            public void run() {                                
                Ebean.save(usuario);
            }
        });
        return Results.ok(Json.toJson(usuario));        
    }

    public Result borrar(Long id){
        Ebean.execute(new TxRunnable() {
          public void run() {
            Usuario usuario = Ebean.find(Usuario.class, id);
            if(usuario != null){
                Ebean.delete(usuario);
            }
        }});
        return Results.ok("Borrado: " + id);
    }
}
