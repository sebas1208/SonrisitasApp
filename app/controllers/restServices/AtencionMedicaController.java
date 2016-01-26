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
import util.Gmail;

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
        atencionMedica.setAtmFecha(Fechas.stringToDate(dynamicForm.get("atmFecha")));
        atencionMedica.setAtmHoraInicio(Fechas.stringToTime(dynamicForm.get("atmHoraInicio")));
        atencionMedica.setAtmHoraFin(Fechas.stringToTime(dynamicForm.get("atmHoraFin")));
        atencionMedica.setOdoId(Ebean.find(Odontologo.class,Long.parseLong(dynamicForm.get("odoId"))));
        atencionMedica.setUsuId(Ebean.find(Usuario.class,Long.parseLong(dynamicForm.get("usuId"))));
        atencionMedica.setTamId(Ebean.find(TipoAtencionMedica.class,Long.parseLong(dynamicForm.get("tamId"))));
        atencionMedica.setAtmActivo(true);
        atencionMedica.setAtmFechaRegistro(Calendar.getInstance().getTime());
        if(AtencionMedica.findByUserDayAndHour(atencionMedica.getUsuId().getUsuId(),atencionMedica.getAtmFecha(),atencionMedica.getAtmHoraInicio()) != null){
            return Results.badRequest("{\"error\":[\"Ya tienes agendada una cita en este horario.\"]}");
        }
        if(AtencionMedica.findByOdoDayAndHour(atencionMedica.getOdoId().getOdoId(),atencionMedica.getAtmFecha(),atencionMedica.getAtmHoraInicio()) != null){
            return Results.badRequest("{\"error\":[\"El odontologo ya tiene agendado una cita en este horario.\"]}");
        }
        Ebean.execute(new TxRunnable() {
            public void run() {
                Ebean.save(atencionMedica);
            }
        });
        String message = "Saludos de la Clinica Dental Sonrisitas.\nUsted ha agendado una cita con los siguientes datos.\nFecha: " + Fechas.dateToString(atencionMedica.getAtmFecha()) + "\n" +
        "Hora: " + Fechas.timeToString(atencionMedica.getAtmHoraInicio()) + "\n" +
        "Duracion: " + atencionMedica.getTamId().getTamDuracionNem() + "\n" +
        "Doctor: " + atencionMedica.getOdoId().getOdoNombres() + " " + atencionMedica.getOdoId().getOdoApellidos() + "\n" +
        "Tipo Atencion: " + atencionMedica.getTamId().getTamNombre() + "\n";
        Gmail.send(atencionMedica.getUsuId().getUsuEmail(),"Sonrisitas Informacion de Cita",message);
        return Results.ok(Json.toJson(atencionMedica));
    }

    public Result buscarPorUsuario(Long idUsuario){
        List<AtencionMedica> atencionMedicaList = AtencionMedica.findByUser(idUsuario);
        return Results.ok(Json.toJson(atencionMedicaList));
    }

    public Result borrar(Long id){
        final AtencionMedica atencionMedica = Ebean.find(AtencionMedica.class, id);
        String message = "Saludos de la Clinica Dental Sonrisitas.\nUsted ha eliminado una cita con los siguientes datos.\nFecha: " + Fechas.dateToString(atencionMedica.getAtmFecha()) + "\n" +
        "Hora: " + Fechas.timeToString(atencionMedica.getAtmHoraInicio()) + "\n" +
        "Duracion: " + atencionMedica.getTamId().getTamDuracionNem() + "\n" +
        "Doctor: " + atencionMedica.getOdoId().getOdoNombres() + " " + atencionMedica.getOdoId().getOdoApellidos() + "\n" +
        "Tipo Atencion: " + atencionMedica.getTamId().getTamNombre() + "\n" +
        "Si usted no eliminó esta cita por favor contactese con la clinica para solucionar el inconveniente.";
        Ebean.execute(new TxRunnable() {
          public void run() {
            if(atencionMedica != null){
                Ebean.delete(atencionMedica);
            }
        }});
        Gmail.send(atencionMedica.getUsuId().getUsuEmail(),"Sonrisitas Cita Eliminada",message);
        return Results.ok("Borrado: " + id);
    }

    public Result editar(Long id){
        DynamicForm dynamicForm = Form.form().bindFromRequest();
        AtencionMedica atencionMedica = AtencionMedica.find.byId(id);
        atencionMedica.setAtmFecha(Fechas.stringToDate(dynamicForm.get("atmFecha")));
        atencionMedica.setAtmHoraInicio(Fechas.stringToTime(dynamicForm.get("atmHoraInicio")));
        atencionMedica.setAtmHoraFin(Fechas.stringToTime(dynamicForm.get("atmHoraFin")));
        atencionMedica.setOdoId(Ebean.find(Odontologo.class,Long.parseLong(dynamicForm.get("odoId"))));
        atencionMedica.setTamId(Ebean.find(TipoAtencionMedica.class,Long.parseLong(dynamicForm.get("tamId"))));
        if(AtencionMedica.findByUserDayAndHour(atencionMedica.getUsuId().getUsuId(),atencionMedica.getAtmFecha(),atencionMedica.getAtmHoraInicio()) != null){
            return Results.badRequest("{\"error\":[\"Ya tienes agendada una cita en este horario.\"]}");
        }
        if(AtencionMedica.findByOdoDayAndHour(atencionMedica.getOdoId().getOdoId(),atencionMedica.getAtmFecha(),atencionMedica.getAtmHoraInicio()) != null){
            return Results.badRequest("{\"error\":[\"El odontologo ya tiene agendado una cita en este horario.\"]}");
        }
        Ebean.save(atencionMedica);
        return Results.ok(Json.toJson(atencionMedica));
    }
}
