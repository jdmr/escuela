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
package mx.edu.um.escuela.web;

import java.util.List;
import javax.validation.Valid;
import mx.edu.um.escuela.dao.AlumnoDao;
import mx.edu.um.escuela.model.Alumno;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author J. David Mendoza <jdmendoza@um.edu.mx>
 */
@Controller
@RequestMapping("/alumno")
public class AlumnoController {

    private static final Logger log = LoggerFactory.getLogger(AlumnoController.class);
    @Autowired
    private AlumnoDao alumnoDao;

    @RequestMapping
    public String lista(Model modelo) {
        log.info("Mostrando lista de alumnos");

        List<Alumno> alumnos = alumnoDao.lista();
        modelo.addAttribute("alumnos", alumnos);
        return "alumno/lista";
    }

    @RequestMapping("/nuevo")
    public String nuevo(Model modelo) {
        Alumno alumno = new Alumno();
        modelo.addAttribute("alumno", alumno);

        return "alumno/nuevo";
    }

    @RequestMapping("/crea")
    public String crea(@Valid Alumno alumno, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.debug("Creando al alumno {}", alumno);
        if (bindingResult.hasErrors()) {
            return "alumno/nuevo";
        }

        alumno = alumnoDao.crea(alumno);

        redirectAttributes.addFlashAttribute("mensaje", "El alumno "
                + alumno.getMatricula()
                + " ha sido creado");

        return "redirect:/alumno/ver/" + alumno.getMatricula();
    }

    @RequestMapping("/ver/{matricula}")
    public String ver(@PathVariable String matricula, Model modelo) {
        Alumno alumno = alumnoDao.obtiene(matricula);
        modelo.addAttribute("alumno", alumno);
        return "alumno/ver";
    }

    @RequestMapping("/edita/{matricula}")
    public String edita(@PathVariable String matricula, Model modelo) {
        Alumno alumno = alumnoDao.obtiene(matricula);
        modelo.addAttribute("alumno", alumno);
        return "alumno/edita";
    }


    @RequestMapping(value = "/actualiza", method = RequestMethod.POST)
    public String actualiza(@Valid Alumno alumno, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            return "alumno/edita";
        }
        
        alumno = alumnoDao.actualiza(alumno);
        redirectAttributes.addFlashAttribute("mensaje", "El alumno "+ alumno.getMatricula() +" ha sido actualizado.");
        
        return "redirect:/alumno/ver/"+alumno.getMatricula();
    }
    
    @RequestMapping("/elimina/{matricula}")
    public String elimina(@PathVariable String matricula, RedirectAttributes redirectAttributes) {
        Alumno alumno = alumnoDao.obtiene(matricula);
        String nombre = alumnoDao.elimina(alumno);
        redirectAttributes.addFlashAttribute("mensaje", "El alumno "+ nombre +" ha sido dado de baja.");
        return "redirect:/alumno";
    }
}
