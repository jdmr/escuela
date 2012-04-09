package mx.edu.um.escuela;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("escuela.xml");
        AlumnoDao alumnoDao = (AlumnoDao) context.getBean("alumnoDao");
        List<Alumno> alumnos = alumnoDao.lista();
        for(Alumno alumno : alumnos) {
            System.out.println(alumno);
        }
        
        App app = new App();
        System.out.println(app.mensaje());
    }

    public String mensaje() {
        return "Hola mundo";
    }
}
