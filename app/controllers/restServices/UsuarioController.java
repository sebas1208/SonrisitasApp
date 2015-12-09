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
import java.util.ArrayList;
import com.avaje.ebean.TxRunnable;
import com.avaje.ebean.Ebean;
import play.data.DynamicForm;
import play.data.Form;

public class UsuarioController extends Controller {

    public Result all() {
        List<Usuario> usuarios = Usuario.find.all();
        return Results.ok(Json.toJson(usuarios));
    }

    public Result one(Long id){
        Usuario usuario = Usuario.find.byId(id);
        if(usuario == null) return Results.ok("{\"error\":\"No existe el usuario\"}");
        return Results.ok(Json.toJson(usuario));
    }

    public Result nuevo(){
        DynamicForm dynamicForm = Form.form().bindFromRequest();
        Ebean.execute(new TxRunnable() {
            public void run() {
                Usuario usuario = new Usuario();
                usuario.setUsuUser(dynamicForm.get("usuario"));
                usuario.setUsuPassword(dynamicForm.get("password"));
                usuario.setUsuPreguntaRecuperacion(dynamicForm.get("preguntaRecuperacion"));
                usuario.setUsuRespuestaRecuperacion(dynamicForm.get("respuestaRecuperacion"));
                usuario.setUsuEmail(dynamicForm.get("email"));
                usuario.setUsuActivo(true);
                usuario.setUsuFechaRegistro(new Date());
                Ebean.save(usuario);
            }
        });
        return Results.ok("Creado: " + dynamicForm.get("usuario"));
    }

    public Result delete(Long id){
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