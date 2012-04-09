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
import org.springframework.stereotype.Repository;

/**
 *
 * @author J. David Mendoza <jdmendoza@um.edu.mx>
 */
@Repository
public class MaestroDao {

    private List<Maestro> maestros = new ArrayList<>();

    public MaestroDao() {
        maestros.add(new Maestro("0001", "David", "Mendoza", new Date(), true, "david.mendoza@um.edu.mx"));
        maestros.add(new Maestro("0002", "Dulce", "Alvarado", new Date(), true, "dulce.alvarado@um.edu.mx"));
    }

    public List<Maestro> lista() {
        return maestros;
    }

    public Maestro crea(Maestro maestro) {
        maestros.add(maestro);
        return maestro;
    }

    public Maestro actualiza(Maestro maestro) {
        for (int pos = 0; pos < maestros.size(); pos++) {
            Maestro a = maestros.get(pos);
            if (a.getNomina().equals(maestro.getNomina())) {
                maestros.set(pos, maestro);
                break;
            }
        }
        return maestro;
    }

    public String elimina(Maestro maestro) {
        String nomina = maestro.getNomina();
        for (int pos = 0; pos < maestros.size(); pos++) {
            Maestro a = maestros.get(pos);
            if (a.getNomina().equals(maestro.getNomina())) {
                maestros.remove(pos);
                break;
            }
        }
        return nomina;
    }
    
    public Maestro obtiene(String nomina) {
        Maestro maestro = null;
        for (int pos = 0; pos < maestros.size(); pos++) {
            Maestro a = maestros.get(pos);
            if (a.getNomina().equals(nomina)) {
                maestro = a;
                break;
            }
        }
        return maestro;
    }
}
