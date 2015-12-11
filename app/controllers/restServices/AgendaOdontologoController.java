package controllers.restServices;

import play.*;
import play.mvc.*;
import models.AgendaOdontologo;
import models.Usuario;
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

public class AgendaOdontologoController extends Controller {


    public Result todos() {
        return Results.ok();
    }

    public Result uno(Long id) {
        return Results.ok();
    }

    public Result nuevo() {
        return Results.ok();
    }

    public Result borrar(Long id) {
        return Results.ok();
    }
}
