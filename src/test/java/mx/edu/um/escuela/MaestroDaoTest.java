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
public class MaestroDaoTest {

    private static final Logger log = LoggerFactory.getLogger(MaestroDaoTest.class);
    @Autowired
    private MaestroDao instance;

    public MaestroDaoTest() {
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
     * Test of lista method, of class MaestroDaoJdbc.
     */
    @Test
    public void testLista() {
        log.debug("lista");
        List<Maestro> result = instance.lista();
        assertNotNull(result);
        assertTrue(instance.lista().size() >= 1);
    }

    /**
     * Test of crea method, of class MaestroDaoJdbc.
     */
    @Test
    public void testCrea() {
        log.debug("crea");
        Maestro maestro = new Maestro("0003", "Daniel", "Mendoza", new Date(), true, "dama@um.edu.mx");

        Maestro result = instance.crea(maestro);
        assertNotNull(result);
        assertTrue(instance.lista().size() > 1);
        assertEquals("Daniel", instance.lista().get(instance.lista().size() - 1).getNombre());
    }

    /**
     * Test of actualiza method, of class MaestroDaoJdbc.
     */
    @Test
    public void testActualiza() {
        log.debug("actualiza");
        Maestro maestro = instance.obtiene("0001");
        maestro.setNombre("Jorge David");

        Maestro result = instance.actualiza(maestro);
        assertNotNull(result);
        assertEquals("Jorge David", result.getNombre());
        assertEquals("Jorge David", instance.obtiene("0001").getNombre());
    }

    /**
     * Test of elimina method, of class MaestroDaoJdbc.
     */
    @Test(expected = EmptyResultDataAccessException.class)
    public void testElimina() {
        log.debug("elimina");
        Maestro maestro = instance.obtiene("0001");

        String result = instance.elimina(maestro);
        assertEquals("0001", result);
        instance.obtiene("0001");
        fail("Debio lanzar excepcion de maestro no encontrado");
    }

    /**
     * Test of obtiene method, of class MaestroDaoJdbc.
     */
    @Test
    public void testObtiene() {
        log.debug("obtiene");
        String nomina = "0002";
        Maestro result = instance.obtiene(nomina);
        assertEquals("Dulce", result.getNombre());
    }
}
