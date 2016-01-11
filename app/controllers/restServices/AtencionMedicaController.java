package controllers.restServices;

import play.*;
import play.mvc.*;
import models.AtencionMedica;
import models.HistoriaClinicaDetalle;
import models.Odontologo;
import models.Usuario;
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
        // Form<AtencionMedica> atmForm = Form.form(AtencionMedica.class).bindFromRequest();               
        // if(atmForm.hasErrors()){
        //     Logger.error(atmForm.errorsAsJson().toString());
        //     return Results.badRequest(atmForm.errorsAsJson());
        // }
        // final AtencionMedica atm = atmForm.get();
        // atm.setAtmActivo(true);
        // atm.setAtmFechaRegistro(Calendar.getInstance().getTime());
        // Ebean.execute(new TxRunnable() {
        //     public void run() {                                
        //         Ebean.save(atm);
        //     }
        // });
        // return Results.ok(Json.toJson(atm));


        DynamicForm dynamicForm = Form.form().bindFromRequest();
        final AtencionMedica atencionMedica = new AtencionMedica();
        Logger.error(dynamicForm.get("odoId"));
        Ebean.execute(new TxRunnable() {
            public void run() {                
                atencionMedica.setAtmFecha(Fechas.stringToDate(dynamicForm.get("atmFecha")));
                atencionMedica.setAtmHoraInicio(Fechas.stringToTime(dynamicForm.get("atmHoraInicio")));
                atencionMedica.setAtmHoraFin(Fechas.stringToTime(dynamicForm.get("atmHoraFin")));
                atencionMedica.setOdoId(Ebean.find(Odontologo.class,Long.parseLong(dynamicForm.get("odoId"))));
                atencionMedica.setUsuId(Ebean.find(Usuario.class,Long.parseLong(dynamicForm.get("usuId"))));
                atencionMedica.setTamId(Ebean.find(TipoAtencionMedica.class,Long.parseLong(dynamicForm.get("tamId"))));
                atencionMedica.setAtmActivo(true);
                atencionMedica.setAtmFechaRegistro(Calendar.getInstance().getTime());
                Ebean.save(atencionMedica);
            }
        });
        return Results.ok(Json.toJson(atencionMedica));
    }

    public Result buscarPorUsuario(Long idUsuario){
        List<AtencionMedica> atencionMedicaList = AtencionMedica.findByUser(idUsuario);
        return Results.ok(Json.toJson(atencionMedicaList));
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
