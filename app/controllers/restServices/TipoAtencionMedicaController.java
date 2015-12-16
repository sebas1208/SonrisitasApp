package controllers.restServices;

import play.*;
import play.mvc.*;
import models.TipoAtencionMedica;
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


public class TipoAtencionMedicaController extends Controller {

    public Result todos() {
        List<TipoAtencionMedica> tipoAtencionesMedicas = TipoAtencionMedica.find.all();
        return Results.ok(Json.toJson(tipoAtencionesMedicas));
    }

    public Result uno(Long id){
        TipoAtencionMedica tipoAtencionMedica = TipoAtencionMedica.find.byId(id);
        if(tipoAtencionMedica == null) return Results.ok("{\"error\":\"No existe el TipoAtencionMedica\"}");
        return Results.ok(Json.toJson(tipoAtencionMedica));
    }

    public Result nuevo(){
        DynamicForm dynamicForm = Form.form().bindFromRequest();
        final TipoAtencionMedica tipoAtencionMedica = new TipoAtencionMedica();
        Ebean.execute(new TxRunnable() {
            public void run() {                
                tipoAtencionMedica.setTamNombre(dynamicForm.get("nombre"));
                tipoAtencionMedica.setTamDuracionHoras(Integer.parseInt(dynamicForm.get("duracionHoras")));
                tipoAtencionMedica.setTamDuracionMinutos(Integer.parseInt(dynamicForm.get("duracionMinutos")));
                tipoAtencionMedica.setTamActivo(true);
                tipoAtencionMedica.setTamFechaRegistro(Calendar.getInstance().getTime());
                Ebean.save(tipoAtencionMedica);
            }
        });
        return Results.ok(Json.toJson(tipoAtencionMedica));
    }

    public Result borrar(Long id){
        Ebean.execute(new TxRunnable() {
          public void run() {
            TipoAtencionMedica tipoAtencionMedica = Ebean.find(TipoAtencionMedica.class, id);
            if(tipoAtencionMedica != null){
                Ebean.delete(tipoAtencionMedica);
            }
        }});
        return Results.ok("Borrado: " + id);
    }
}
