package controllers.restServices;

import play.*;
import play.mvc.*;
import models.AtencionMedica;
import models.HistoriaClinicaDetalle;
import models.Odontologo;
import models.Paciente;
import models.TipoAtencionMedica;
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

public class AtencionMedicaController extends Controller {

    public Result todos() {
        List<AtencionMedica> atencionesMedicas = AtencionMedica.find.all();
        return Results.ok(Json.toJson(atencionesMedicas));
    }

    public Result uno(Long id){
        AtencionMedica atencionMedica = AtencionMedica.find.byId(id);
        if(atencionMedica == null) return Results.ok("{\"error\":\"No existe el atencionMedica\"}");
        return Results.ok(Json.toJson(atencionMedica));
    }

    public Result nuevo(){
        DynamicForm dynamicForm = Form.form().bindFromRequest();
        final AtencionMedica atencionMedica = new AtencionMedica();
        Ebean.execute(new TxRunnable() {
            public void run() {                
                atencionMedica.setAtmFecha(Fechas.stringToDate(dynamicForm.get("fecha")));
                atencionMedica.setAtmHoraInicio(Fechas.stringToTime(dynamicForm.get("horaInicio")));
                atencionMedica.setAtmHoraFin(Fechas.stringToTime(dynamicForm.get("horaFin")));
                atencionMedica.setHcdId(Ebean.find(HistoriaClinicaDetalle.class,Long.parseLong(dynamicForm.get("historiaClinicaDetalle"))));
                atencionMedica.setOdoId(Ebean.find(Odontologo.class,Long.parseLong(dynamicForm.get("odontologo"))));
                atencionMedica.setPacId(Ebean.find(Paciente.class,Long.parseLong(dynamicForm.get("paciente"))));
                atencionMedica.setTamId(Ebean.find(TipoAtencionMedica.class,Long.parseLong(dynamicForm.get("tipoAtencionMedica"))));
                atencionMedica.setAtmActivo(true);
                atencionMedica.setAtmFechaRegistro(Calendar.getInstance().getTime());
                Ebean.save(atencionMedica);
            }
        });
        return Results.ok(Json.toJson(atencionMedica));
    }

    public Result borrar(Long id){
        Ebean.execute(new TxRunnable() {
          public void run() {
            AtencionMedica atencionMedica = Ebean.find(AtencionMedica.class, id);
            if(atencionMedica != null){
                Ebean.delete(atencionMedica);
            }
        }});
        return Results.ok("Borrado: " + id);
    }
}
