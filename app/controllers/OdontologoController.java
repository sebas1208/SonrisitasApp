package controllers;

import play.*;
import play.mvc.*;
import models.Odontologo;
import play.db.ebean.Model;
import play.mvc.Result;
import play.mvc.Results;
import com.fasterxml.jackson.databind.JsonNode;
import play.api.libs.ws.WS;
import play.db.ebean.Model;
import play.libs.Json;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class OdontologoController extends Controller {

    public Result all() {
    	List<Odontologo> odontologos = new Model.Finder<String,Odontologo>(String.class,Odontologo.class).all();
    	return Results.ok(Json.toJson(odontologos));
    }

    public Result one(String id) {
        Odontologo odontologo = new Model.Finder<String,Odontologo>(String.class,Odontologo.class).byId(id);
        if(odontologo == null) return Results.ok("{\nerror: \"No hay datos\n\"}");
        return Results.ok(Json.toJson(odontologo));
    }
    /*@BodyParser.Of(BodyParser.Json.class)
    public Result submitOdontologo() {
    	JsonNode jsonNode = Controller.request().body().asJson();
        String url = jsonNode.findPath("url").asText();
        String fullname = jsonNode.findPath("fullname").asText();
        JsonNode response = fetchInformation(url);
        Odontologo odontologo = null;
        if(response == null){
            return Results.badRequest();
        }else{
            String odoCedula = response.findPath("cedula").textValue();
            String odoNombres = response.findPath("nombres").textValue();
            String odoApellidos = response.findPath("apellidos").textValue();
            String odoDireccion = response.findPath("direccion").textValue();
            String odoTelefono = response.findPath("telefono").textValue();
            String odoEmail = response.findPath("email").textValue();
            odontologo = new Odontologo(odoCedula, odoNombres, odoApellidos, odoDireccion, odoTelefono, odoEmail);
        }
        odontologo.save();
        return Results.created();
    }

    private static JsonNode fetchInformation(String url){
        String restServiceUrl = "http://localhost/api/v1/extract?url="+url;
        Future<Response> future = WS.url(restServiceUrl).get();
        try {
            Response result = Await.result(future, Duration.apply(30, TimeUnit.SECONDS));
            JsonNode jsonNode = Json.parse(result.json().toString());
            return jsonNode;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }*/
}
