package controllers.restServices;

import play.*;
import play.mvc.*;
import models.TipoAtencionMedica;
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

    private String obtenerNemotecnico(int horas, int minutos){
        String respuesta = "";
        if(horas != 0){
            if(horas == 1){
                respuesta = "" + horas + " hora";
            }else{
                respuesta = "" + horas + " horas";
            }
        }
        if(minutos != 0){
            if (minutos == 1) {
                respuesta += " " + minutos + " minuto";
            }else{
                respuesta += " " + minutos + " minutos";
            }
        }
        return respuesta;
    }

    public Result nuevo(){
        DynamicForm dynamicForm = Form.form().bindFromRequest();
        final TipoAtencionMedica tipoAtencionMedica = new TipoAtencionMedica();
        tipoAtencionMedica.setTamNombre(dynamicForm.get("tamNombre"));
        tipoAtencionMedica.setTamDuracionHoras(Integer.parseInt(dynamicForm.get("tamDuracionHoras")));
        tipoAtencionMedica.setTamDuracionMinutos(Integer.parseInt(dynamicForm.get("tamDuracionMinutos")));
        tipoAtencionMedica.setTamDuracionNem(obtenerNemotecnico(tipoAtencionMedica.getTamDuracionHoras(),tipoAtencionMedica.getTamDuracionMinutos()));
        tipoAtencionMedica.setEspId(Especialidad.find.byId(Long.valueOf(dynamicForm.get("espId"))));
        tipoAtencionMedica.setTamActivo(true);
        tipoAtencionMedica.setTamFechaRegistro(Calendar.getInstance().getTime());
        Ebean.execute(new TxRunnable() {
            public void run() {
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
