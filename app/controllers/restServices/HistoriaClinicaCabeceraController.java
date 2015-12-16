package controllers.restServices;

import play.*;
import play.mvc.*;
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

public class HistoriaClinicaCabeceraController extends Controller {

    public Result todos() {
        List<HistoriaClinicaCabecera> historiasClinicasCabecera = HistoriaClinicaCabecera.find.all();
        return Results.ok(Json.toJson(historiasClinicasCabecera));
    }

    public Result uno(Long id){
        HistoriaClinicaCabecera historiaClinicaCabecera = HistoriaClinicaCabecera.find.byId(id);
        if(historiaClinicaCabecera == null) return Results.ok("{\"error\":\"No existe el HistoriaClinicaCabecera\"}");
        return Results.ok(Json.toJson(historiaClinicaCabecera));
    }

    public Result nuevo(){
        DynamicForm dynamicForm = Form.form().bindFromRequest();
        final HistoriaClinicaCabecera historiaClinicaCabecera = new HistoriaClinicaCabecera();
        Ebean.execute(new TxRunnable() {
            public void run() {                
                historiaClinicaCabecera.setHccAntecedentesFamiliares(dynamicForm.get("antecedentesFamiliares"));
                historiaClinicaCabecera.setHccAntecedentesPersonales(dynamicForm.get("antecedentesPersonales"));
                historiaClinicaCabecera.setHccEnfermedadActual(dynamicForm.get("enfermedadActual"));
                historiaClinicaCabecera.setHccActivo(true);
                historiaClinicaCabecera.setHccFechaRegistro(Calendar.getInstance().getTime());
                historiaClinicaCabecera.setPacId(Ebean.find(Paciente.class,Long.parseLong(dynamicForm.get("paciente"))));
                Ebean.save(historiaClinicaCabecera);
            }
        });
        return Results.ok(Json.toJson(historiaClinicaCabecera));
    }

    public Result borrar(Long id){
        Ebean.execute(new TxRunnable() {
          public void run() {
            HistoriaClinicaCabecera historiaClinicaCabecera = Ebean.find(HistoriaClinicaCabecera.class, id);
            if(historiaClinicaCabecera != null){
                Ebean.delete(historiaClinicaCabecera);
            }
        }});
        return Results.ok("Borrado: " + id);
    }
}
