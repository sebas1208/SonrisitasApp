package controllers.restServices;

import play.*;
import play.mvc.*;
import models.Paciente;
import models.Usuario;
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
import util.Fechas;

public class PacienteController extends Controller {

    public Result todos() {
        List<Paciente> pacientes = Paciente.find.all();
        return Results.ok(Json.toJson(pacientes));
    }

    public Result uno(Long id){
        Paciente paciente = Paciente.find.byId(id);
        if(paciente == null) return Results.ok("{\"error\":\"No existe el paciente\"}");
        return Results.ok(Json.toJson(paciente));
    }

    public Result nuevo(){
        DynamicForm dynamicForm = Form.form().bindFromRequest();
        final Paciente paciente = new Paciente();
        Ebean.execute(new TxRunnable() {
            public void run() {
                paciente.setPacNombres(dynamicForm.get("nombres"));
                paciente.setPacApellidos(dynamicForm.get("apellidos"));
                paciente.setPacDireccion(dynamicForm.get("direccion"));
                paciente.setPacTelefono(dynamicForm.get("telefono"));
                paciente.setPacEmail(dynamicForm.get("email"));
                paciente.setPacFechaNacimiento(Fechas.stringToDate(dynamicForm.get("fechaNacimiento")));
                paciente.setPacCedula(dynamicForm.get("cedula"));
                paciente.setPacSeguroMedico(dynamicForm.get("seguroMedico"));
                paciente.setPacSexo(dynamicForm.get("sexo"));
                paciente.setPacEstadoCivil(dynamicForm.get("extadoCivil"));
                paciente.setPacLugarNacimiento(dynamicForm.get("lugarNacimiento"));
                paciente.setPacOcupacion(dynamicForm.get("ocupacion"));
                paciente.setPacActivo(true);
                paciente.setPacFechaRegistro(Calendar.getInstance().getTime());
                paciente.setUsuId(Ebean.find(Usuario.class,Long.parseLong(dynamicForm.get("usuario"))));
                Ebean.save(paciente);
            }
        });
        return Results.ok(Json.toJson(paciente));
    }

    public Result borrar(Long id){
        Ebean.execute(new TxRunnable() {
          public void run() {
            Paciente paciente = Ebean.find(Paciente.class, id);
            if(paciente != null){
                Ebean.delete(paciente);
            }
        }});
        return Results.noContent();
    }
}
