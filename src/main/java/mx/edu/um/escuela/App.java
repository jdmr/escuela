package mx.edu.um.escuela;

import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App {
    
    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("escuela.xml");
        AlumnoDao alumnoDao = (AlumnoDao) context.getBean("alumnoDao");
        MaestroDao maestroDao = (MaestroDao) context.getBean("maestroDao");
        
        log.info("Menu:");
        log.info("1. Lista de Alumnos");
        log.info("2. Crea alumno");
        log.info("3. Obtiene alumno");
        log.info("4. Lista de Maestros");
        log.info("5. Crea maestro");
        log.info("6. Obtiene maestro");
        log.info("X. Salir");
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            Alumno alumno;
            Maestro maestro;
            String line = scanner.nextLine();
            switch(line) {
                case "1" : 
                    for(Alumno a : alumnoDao.lista()) {
                        log.info("{}",a);
                    }
                    break;
                case "2" :
                    alumno = new Alumno();
                    log.info("Matricula:");
                    alumno.setMatricula(scanner.nextLine());
                    log.info("Nombre:");
                    alumno.setNombre(scanner.nextLine());
                    log.info("Apellido:");
                    alumno.setApellido(scanner.nextLine());
                    alumnoDao.crea(alumno);
                    log.info("Alumno CREADO!!!");
                    break;
                case "3":
                    log.info("Matricula:");
                    alumno = alumnoDao.obtiene(scanner.nextLine());
                    log.info("{}", alumno);
                    break;
                case "4" : 
                    for(Maestro a : maestroDao.lista()) {
                        log.info("{}",a);
                    }
                    break;
                case "5" :
                    maestro = new Maestro();
                    log.info("Nomina:");
                    maestro.setNomina(scanner.nextLine());
                    log.info("Nombre:");
                    maestro.setNombre(scanner.nextLine());
                    log.info("Apellido:");
                    maestro.setApellido(scanner.nextLine());
                    maestroDao.crea(maestro);
                    log.info("Maestro CREADO!!!");
                    break;
                case "6":
                    log.info("Matricula:");
                    maestro = maestroDao.obtiene(scanner.nextLine());
                    log.info("{}", maestro);
                    break;
                case "X":
                    log.info("Adios...");
                    System.exit(0);
            }
            log.info("Menu:");
            log.info("1. Lista de Alumnos");
            log.info("2. Crea alumno");
            log.info("3. Obtiene alumno");
            log.info("X. Salir");
        }
        
        App app = new App();
        log.info(app.mensaje());
    }

    public String mensaje() {
        return "Hola mundo";
    }
}
