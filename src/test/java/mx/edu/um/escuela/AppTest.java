package mx.edu.um.escuela;

import mx.edu.um.escuela.util.App;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {


    @Test
    public void debieraDecirHola() {
        App app = new App();
        String mensaje = app.mensaje();
        Assert.assertEquals("Hola mundo", mensaje);
    }
}
