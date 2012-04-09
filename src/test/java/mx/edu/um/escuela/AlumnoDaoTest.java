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

import java.util.Date;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.*;

/**
 *
 * @author J. David Mendoza <jdmendoza@um.edu.mx>
 */
public class AlumnoDaoTest {

    public AlumnoDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of lista method, of class AlumnoDao.
     */
    @Test
    public void testLista() {
        System.out.println("lista");
        AlumnoDao instance = new AlumnoDao();
        List<Alumno> result = instance.lista();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("David", result.get(0).getNombre());
    }

    /**
     * Test of crea method, of class AlumnoDao.
     */
    @Test
    public void testCrea() {
        System.out.println("crea");
        Alumno alumno = new Alumno("0003", "Daniel", "Mendoza", new Date(), true, "dama@um.edu.mx");

        AlumnoDao instance = new AlumnoDao();
        Alumno result = instance.crea(alumno);
        assertNotNull(result);
        assertEquals(3, instance.lista().size());
        assertEquals("Daniel", instance.lista().get(2).getNombre());
    }

    /**
     * Test of actualiza method, of class AlumnoDao.
     */
    @Test
    public void testActualiza() {
        System.out.println("actualiza");
        AlumnoDao instance = new AlumnoDao();
        Alumno alumno = instance.lista().get(0);
        alumno.setNombre("Jorge David");

        Alumno result = instance.actualiza(alumno);
        assertNotNull(result);
        assertEquals("Jorge David", result.getNombre());
        assertEquals("Jorge David", instance.lista().get(0).getNombre());
    }

    /**
     * Test of elimina method, of class AlumnoDao.
     */
    @Test
    public void testElimina() {
        System.out.println("elimina");
        AlumnoDao instance = new AlumnoDao();
        Alumno alumno = instance.lista().get(0);

        String result = instance.elimina(alumno);
        assertEquals("0001", result);
        assertEquals(1, instance.lista().size());
    }

    /**
     * Test of obtiene method, of class AlumnoDao.
     */
    @Test
    public void testObtiene() {
        System.out.println("obtiene");
        String matricula = "0001";
        AlumnoDao instance = new AlumnoDao();
        Alumno result = instance.obtiene(matricula);
        assertEquals("David", result.getNombre());
    }
}
