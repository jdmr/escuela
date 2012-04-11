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
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author J. David Mendoza <jdmendoza@um.edu.mx>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:escuela.xml"})
@Transactional
public class AlumnoDaoTest {
    
    private static final Logger log = LoggerFactory.getLogger(AlumnoDaoTest.class);
    
    @Autowired
    private AlumnoDao instance;

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
     * Test of lista method, of class AlumnoDaoJdbc.
     */
    @Test
    public void testLista() {
        log.debug("lista");
        List<Alumno> result = instance.lista();
        assertNotNull(result);
        assertTrue(instance.lista().size() >= 1);
    }

    /**
     * Test of crea method, of class AlumnoDaoJdbc.
     */
    @Test
    public void testCrea() {
        log.debug("crea");
        Alumno alumno = new Alumno("0003", "Daniel", "Mendoza", new Date(), true, "dama@um.edu.mx");

        Alumno result = instance.crea(alumno);
        assertNotNull(result);
        assertTrue(instance.lista().size() > 1);
        assertEquals("Daniel", instance.lista().get(instance.lista().size() - 1).getNombre());
    }

    /**
     * Test of actualiza method, of class AlumnoDaoJdbc.
     */
    @Test
    public void testActualiza() {
        log.debug("actualiza");
        Alumno alumno = instance.lista().get(0);
        alumno.setNombre("Jorge David");

        Alumno result = instance.actualiza(alumno);
        assertNotNull(result);
        assertEquals("Jorge David", result.getNombre());
        assertEquals("Jorge David", instance.obtiene("0001").getNombre());
    }

    /**
     * Test of elimina method, of class AlumnoDaoJdbc.
     */
    @Test(expected = EmptyResultDataAccessException.class)
    public void testElimina() {
        log.debug("elimina");
        Alumno alumno = instance.obtiene("0001");
        String matricula = alumno.getMatricula();

        String result = instance.elimina(alumno);
        assertEquals(matricula, result);
        instance.obtiene(matricula);
        fail("Debio lanzar una excepcion de alumno no encontrado");
    }

    /**
     * Test of obtiene method, of class AlumnoDaoJdbc.
     */
    @Test
    public void testObtiene() {
        log.debug("obtiene");
        String matricula = "0002";
        Alumno result = instance.obtiene(matricula);
        assertEquals("Dulce", result.getNombre());
    }
}
