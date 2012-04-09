package mx.edu.um.escuela;

import java.util.Scanner;
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
        
        System.out.println("Menu:");
        System.out.println("1. Lista de Alumnos");
        System.out.println("2. Crea alumno");
        System.out.println("3. Obtiene alumno");
        System.out.println("X. Salir");
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            Alumno alumno;
            String line = scanner.nextLine();
            switch(line) {
                case "1" : 
                    for(Alumno a : alumnoDao.lista()) {
                        System.out.println(a);
                    }
                    break;
                case "2" :
                    alumno = new Alumno();
                    System.out.println("Matricula:");
                    alumno.setMatricula(scanner.nextLine());
                    System.out.println("Nombre:");
                    alumno.setNombre(scanner.nextLine());
                    System.out.println("Apellido:");
                    alumno.setApellido(scanner.nextLine());
                    alumnoDao.crea(alumno);
                    System.out.println("Alumno CREADO!!!");
                    break;
                case "3":
                    System.out.println("Matricula:");
                    alumno = alumnoDao.obtiene(scanner.nextLine());
                    System.out.println(alumno);
                    break;
                case "X":
                    System.out.println("Adios...");
                    System.exit(0);
            }
            System.out.println("Menu:");
            System.out.println("1. Lista de Alumnos");
            System.out.println("2. Crea alumno");
            System.out.println("3. Obtiene alumno");
            System.out.println("X. Salir");
        }
        
        App app = new App();
        System.out.println(app.mensaje());
    }

    public String mensaje() {
        return "Hola mundo";
    }
}
