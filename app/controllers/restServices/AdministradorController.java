package controllers.restServices;

import play.*;
import play.mvc.*;
import models.Administrador;
//import play.db.ebean.Model;
import play.mvc.Result;
import play.mvc.Results;
import com.fasterxml.jackson.databind.JsonNode;
import play.api.libs.ws.WS;
import play.libs.Json;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import javax.persistence.Query;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class AdministradorController extends Controller {

    public Result all() {
        List<Administrador> administradores = Administrador.find.all();
        //System.out.println(odontologos.get(0).getOdoNombres());
        return Results.ok(Json.toJson(administradores));
    }

/*    public Result one(Long id){
        Administrador administrador = Administrador.find.byId(id);
        if(administrador == null) return Results.ok("{\"error\":\"No existe el usuario\"}");
        return Results.ok(Json.toJson(usuario));
    }*/
}
