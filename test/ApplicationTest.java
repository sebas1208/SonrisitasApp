import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Calendar;
import controllers.restServices.EspecialidadController;
import models.Especialidad;
import play.db.ebean.*;
import com.avaje.ebean.Ebean;


import com.fasterxml.jackson.databind.JsonNode;
import org.junit.*;

import play.mvc.*;
import play.test.*;
import play.data.DynamicForm;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.RequiredValidator;
import play.i18n.Lang;
import play.libs.F;
import play.libs.F.*;
import play.twirl.api.Content;
import views.html.*;

import static play.test.Helpers.*;
import static org.junit.Assert.*;

import play.test.*;

import play.Logger;
/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest {

    // @Test
    // public void renderTemplate() {
    //     Content html = views.html.index.render("Your new application is ready.");
    //     assertEquals("text/html", contentType(html));
    //     assertTrue(contentAsString(html).contains("Your new application is ready."));
    // }

    // @Test
    // public void crearEspecialidad(){
    //     Especialidad especialidadPrueba = new Especialidad();
    //     especialidadPrueba.setEspNombre("Especialidad Prueba");
    //     especialidadPrueba.setEspArea("Especialidad Prueba");
    //     especialidadPrueba.setEspActivo(true);
    //     especialidadPrueba.setEspFechaRegistro(Calendar.getInstance().getTime());
    //     Ebean.save(especialidadPrueba);
    //     especialidadPrueba = Especialidad.findByEspNombre("Especialidad Prueba");
    //     assertEquals("Especialidad Prueba",especialidadPrueba.getEspNombre());
    // }
    @Inject Application application;

    @Test
    public void eliminarEspecialidad(){
        Especialidad especialidad = Especialidad.findByEspNombre("Especialidad Prueba");
        Ebean.delete(especialidad);
        especialidad = Especialidad.findByEspNombre("Especialidad Prueba");
        assertEquals(null,especialidad);
    }

    @Test
    public void renderHome() {
        Content html = home.render();
        assertEquals("text/html", contentType(html));
        assertTrue(contentAsString(html).contains("Sonrisitas"));
    }

    @Test
    public void renderAdmin() {
        Content html = admin.render();
        assertEquals("text/html", contentType(html));
        assertTrue(contentAsString(html).contains("Admin"));
    }

}
