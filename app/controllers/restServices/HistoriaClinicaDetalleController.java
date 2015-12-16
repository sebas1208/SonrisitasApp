package controllers.restServices;

import play.*;
import play.mvc.*;
import models.HistoriaClinicaDetalle;
import models.HistoriaClinicaCabecera;
import models.Paciente;
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

public class HistoriaClinicaDetalleController extends Controller {
    
     public Result todos() {
        List<HistoriaClinicaDetalle> historiasClinicasDetalle = HistoriaClinicaDetalle.find.all();
        return Results.ok(Json.toJson(historiasClinicasDetalle));
    }

    public Result uno(Long id){
        HistoriaClinicaDetalle historiaClinicaDetalle = HistoriaClinicaDetalle.find.byId(id);
        if(historiaClinicaDetalle == null) return Results.ok("{\"error\":\"No existe el HistoriaClinicaDetalle\"}");
        return Results.ok(Json.toJson(historiaClinicaDetalle));
    }

    public Result nuevo(){
        DynamicForm dynamicForm = Form.form().bindFromRequest();
        final HistoriaClinicaDetalle HistoriaClinicaDetalle = new HistoriaClinicaDetalle();
        Ebean.execute(new TxRunnable() {
            public void run() {                
                HistoriaClinicaDetalle.setHcdExploracionFisica(dynamicForm.get("exploracionFisica"));
                HistoriaClinicaDetalle.setHcdDiagnostico(dynamicForm.get("diagnostico"));
                HistoriaClinicaDetalle.setHcdEvolucion(dynamicForm.get("evolucion"));
                HistoriaClinicaDetalle.setHcdExamenesComplementarios(dynamicForm.get("examenesComplementarios"));
                HistoriaClinicaDetalle.setHcdRecetaMedica(dynamicForm.get("recetaMedica"));
                HistoriaClinicaDetalle.setHcdActivo(true);
                HistoriaClinicaDetalle.setHcdFechaRegistro(Calendar.getInstance().getTime());
                HistoriaClinicaDetalle.setHccId(Ebean.find(HistoriaClinicaCabecera.class,Long.parseLong(dynamicForm.get("historiaClinicaCabecera"))));
                Ebean.save(HistoriaClinicaDetalle);
            }
        });
        return Results.ok(Json.toJson(HistoriaClinicaDetalle));
    }

    public Result borrar(Long id){
        Ebean.execute(new TxRunnable() {
          public void run() {
            HistoriaClinicaDetalle HistoriaClinicaDetalle = Ebean.find(HistoriaClinicaDetalle.class, id);
            if(HistoriaClinicaDetalle != null){
                Ebean.delete(HistoriaClinicaDetalle);
            }
        }});
        return Results.ok("Borrado: " + id);
    }
}
