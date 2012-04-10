/*
 * The MIT License
 *
 * Copyright 2012 Universidad de Montemorelos A. C.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package mx.edu.um.escuela;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author J. David Mendoza <jdmendoza@um.edu.mx>
 */
@Repository
public class AlumnoDao {
    
    private static final Logger log = LoggerFactory.getLogger(AlumnoDao.class);

    private List<Alumno> alumnos = new ArrayList<>();

    public AlumnoDao() {
        log.info("Creando una nueva instancia de AlumnoDao");
        alumnos.add(new Alumno("0001", "David", "Mendoza", new Date(), true, "david.mendoza@um.edu.mx"));
        alumnos.add(new Alumno("0002", "Dulce", "Alvarado", new Date(), true, "dulce.alvarado@um.edu.mx"));
    }

    public List<Alumno> lista() {
        log.debug("Obteniendo lista de usuarios");
        return alumnos;
    }

    public Alumno crea(Alumno alumno) {
        log.debug("Creando al alumno {}", alumno);
        alumnos.add(alumno);
        return alumno;
    }

    public Alumno actualiza(Alumno alumno) {
        log.debug("Actualizando al alumno {}", alumno);
        for (int pos = 0; pos < alumnos.size(); pos++) {
            Alumno a = alumnos.get(pos);
            if (a.getMatricula().equals(alumno.getMatricula())) {
                alumnos.set(pos, alumno);
                break;
            }
        }
        return alumno;
    }

    public String elimina(Alumno alumno) {
        log.debug("Eliminando al alumno {}", alumno);
        String matricula = alumno.getMatricula();
        for (int pos = 0; pos < alumnos.size(); pos++) {
            Alumno a = alumnos.get(pos);
            if (a.getMatricula().equals(alumno.getMatricula())) {
                alumnos.remove(pos);
                break;
            }
        }
        return matricula;
    }
    
    public Alumno obtiene(String matricula) {
        log.debug("Obteniendo al alumno con la matricula {}", matricula);
        Alumno alumno = null;
        for (int pos = 0; pos < alumnos.size(); pos++) {
            Alumno a = alumnos.get(pos);
            if (a.getMatricula().equals(matricula)) {
                alumno = a;
                break;
            }
        }
        return alumno;
    }
}
