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

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author J. David Mendoza <jdmendoza@um.edu.mx>
 */
@Repository("alumnoDao")
@Transactional
public class AlumnoDaoJdbc extends JdbcDaoSupport implements AlumnoDao {
    
    private static final Logger log = LoggerFactory.getLogger(AlumnoDaoJdbc.class);
    private static final String CREAR_TABLA = "CREATE TABLE ALUMNOS("
            + "ID SERIAL, "
            + "MATRICULA VARCHAR(10) NOT NULL UNIQUE, "
            + "NOMBRE VARCHAR(64) NOT NULL, "
            + "APELLIDO VARCHAR(64) NOT NULL, "
            + "FECHA_NACIMIENTO DATE, "
            + "ES_HOMBRE BOOLEAN DEFAULT TRUE, "
            + "CORREO VARCHAR(128), "
            + "PRIMARY KEY(ID)"
            + ")";
    private static final String ELIMINA_TABLA = "DROP TABLE IF EXISTS ALUMNOS";
    private static final String CREAR_ALUMNO = "INSERT INTO ALUMNOS(MATRICULA, NOMBRE, APELLIDO, FECHA_NACIMIENTO, ES_HOMBRE, CORREO) VALUES(?,?,?,?,?,?)";
    private static final String ACTUALIZAR_ALUMNO = "UPDATE alumnos SET nombre = ?, apellido = ?, fecha_nacimiento = ?, es_hombre = ?, correo = ? WHERE matricula = ?";
    private static final String OBTIENE_ALUMNO = "SELECT id, matricula, nombre, apellido, fecha_nacimiento, es_hombre, correo FROM alumnos WHERE matricula = ?";
    private static final String ELIMINA_ALUMNO = "DELETE FROM alumnos WHERE matricula = ?";
    private static final String LISTA_ALUMNOS = "SELECT id, matricula, nombre, apellido, fecha_nacimiento, es_hombre, correo FROM alumnos";
    private List<Alumno> alumnos = new ArrayList<>();
    
    @Autowired
    public AlumnoDaoJdbc(DataSource dataSource) {
        setDataSource(dataSource);
        inicializa();
        log.info("Creando una nueva instancia de AlumnoDao");
    }
    
    private void inicializa() {
        log.info("Inicializando tablas de alumnos...");
        getJdbcTemplate().update(ELIMINA_TABLA);
        getJdbcTemplate().update(CREAR_TABLA);
        this.crea(new Alumno("0001", "David", "Mendoza", new Date(), true, "david.mendoza@um.edu.mx"));
        this.crea(new Alumno("0002", "Dulce", "Alvarado", new Date(), false, "dulce.alvarado@um.edu.mx"));
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Alumno> lista() {
        log.debug("Obteniendo lista de usuarios");
        RowMapper<Alumno> mapper = new RowMapper<Alumno>() {
            
            @Override
            public Alumno mapRow(ResultSet rs, int rowNum) throws SQLException {
                Alumno alumno = new Alumno();
                alumno.setId(rs.getLong("id"));
                alumno.setMatricula(rs.getString("matricula"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setApellido(rs.getString("apellido"));
                if (rs.getDate("fecha_nacimiento") != null) {
                    alumno.setFechaNacimiento(new Date(rs.getDate("fecha_nacimiento").getTime()));
                }
                alumno.setEsHombre(rs.getBoolean("es_hombre"));
                if (rs.getString("correo") != null) {
                    alumno.setCorreo(rs.getString("correo"));
                }
                return alumno;
            }
        };
        List<Alumno> lista = getJdbcTemplate().query(LISTA_ALUMNOS, mapper);
        
        return lista;
    }
    
    @Override
    public Alumno crea(final Alumno alumno) {
        log.debug("Creando al alumno {}", alumno);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(new PreparedStatementCreator() {
            
            @Override
            public PreparedStatement createPreparedStatement(
                    Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(
                        CREAR_ALUMNO, new String[]{"id"});
                ps.setString(1, alumno.getMatricula());
                ps.setString(2, alumno.getNombre());
                ps.setString(3, alumno.getApellido());
                if (alumno.getFechaNacimiento() != null) {
                    ps.setDate(4, new java.sql.Date(alumno.getFechaNacimiento().getTime()));
                } else {
                    ps.setNull(4, Types.DATE);
                }
                ps.setBoolean(5, alumno.getEsHombre());
                if (alumno.getCorreo() != null) {
                    ps.setString(6, alumno.getCorreo());
                } else {
                    ps.setNull(6, Types.VARCHAR);
                }
                return ps;
            }
        }, keyHolder);
        alumno.setId(keyHolder.getKey().longValue());
        
        alumnos.add(alumno);
        return alumno;
    }
    
    @Override
    public Alumno actualiza(Alumno alumno) {
        log.debug("Actualizando al alumno {}", alumno);
        getJdbcTemplate().update(ACTUALIZAR_ALUMNO, alumno.getNombre(), alumno.getApellido(), alumno.getFechaNacimiento(), alumno.getEsHombre(), alumno.getCorreo(), alumno.getMatricula());
        
        for (int pos = 0; pos < alumnos.size(); pos++) {
            Alumno a = alumnos.get(pos);
            if (a.getMatricula().equals(alumno.getMatricula())) {
                alumnos.set(pos, alumno);
                break;
            }
        }
        return alumno;
    }
    
    @Override
    public String elimina(Alumno alumno) {
        log.debug("Eliminando al alumno {}", alumno);
        String matricula = alumno.getMatricula();
        getJdbcTemplate().update(ELIMINA_ALUMNO, matricula);
        
        for (int pos = 0; pos < alumnos.size(); pos++) {
            Alumno a = alumnos.get(pos);
            if (a.getMatricula().equals(alumno.getMatricula())) {
                alumnos.remove(pos);
                break;
            }
        }
        return matricula;
    }
    
    @Override
    public Alumno obtiene(String matricula) {
        log.debug("Obteniendo al alumno con la matricula {}", matricula);
        RowMapper<Alumno> mapper = new RowMapper<Alumno>() {
            
            @Override
            public Alumno mapRow(ResultSet rs, int rowNum) throws SQLException {
                Alumno alumno = new Alumno();
                alumno.setId(rs.getLong("id"));
                alumno.setMatricula(rs.getString("matricula"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setFechaNacimiento(new Date(rs.getDate("fecha_nacimiento").getTime()));
                alumno.setEsHombre(rs.getBoolean("es_hombre"));
                alumno.setCorreo(rs.getString("correo"));
                return alumno;
            }
        };
        return getJdbcTemplate().queryForObject(OBTIENE_ALUMNO, new String[]{matricula}, mapper);
//        Alumno alumno = null;
//        for (int pos = 0; pos < alumnos.size(); pos++) {
//            Alumno a = alumnos.get(pos);
//            if (a.getMatricula().equals(matricula)) {
//                alumno = a;
//                break;
//            }
//        }
//        return alumno;
    }
}
