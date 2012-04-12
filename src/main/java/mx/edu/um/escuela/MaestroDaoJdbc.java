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
@Repository("maestroDao")
@Transactional
public class MaestroDaoJdbc extends JdbcDaoSupport implements MaestroDao {

    private static final Logger log = LoggerFactory.getLogger(MaestroDaoJdbc.class);
    private static final String CREAR_TABLA = "CREATE TABLE MAESTROS("
            + "ID SERIAL, "
            + "NOMINA VARCHAR(10) NOT NULL UNIQUE, "
            + "NOMBRE VARCHAR(64) NOT NULL, "
            + "APELLIDO VARCHAR(64) NOT NULL, "
            + "FECHA_NACIMIENTO DATE, "
            + "ES_HOMBRE BOOLEAN DEFAULT TRUE, "
            + "CORREO VARCHAR(128), "
            + "PRIMARY KEY(ID)"
            + ")";
    private static final String ELIMINA_TABLA = "DROP TABLE IF EXISTS MAESTROS";
    private static final String CREAR_MAESTRO = "INSERT INTO MAESTROS(NOMINA, NOMBRE, APELLIDO, FECHA_NACIMIENTO, ES_HOMBRE, CORREO) VALUES(?,?,?,?,?,?)";
    private static final String ACTUALIZAR_MAESTRO = "UPDATE maestros SET nombre = ?, apellido = ?, fecha_nacimiento = ?, es_hombre = ?, correo = ? WHERE nomina = ?";
    private static final String OBTIENE_MAESTRO = "SELECT id, nomina, nombre, apellido, fecha_nacimiento, es_hombre, correo FROM maestros WHERE nomina = ?";
    private static final String ELIMINA_MAESTRO = "DELETE FROM maestros WHERE nomina = ?";
    private static final String LISTA_MAESTROS = "SELECT id, nomina, nombre, apellido, fecha_nacimiento, es_hombre, correo FROM maestros";
    private List<Maestro> maestros = new ArrayList<>();

    @Autowired
    public MaestroDaoJdbc(DataSource dataSource) {
        this.setDataSource(dataSource);
        this.inicializa();
    }

    private void inicializa() {
        getJdbcTemplate().update(ELIMINA_TABLA);
        getJdbcTemplate().update(CREAR_TABLA);

        this.crea(new Maestro("0001", "David", "Mendoza", new Date(), true, "david.mendoza@um.edu.mx"));
        this.crea(new Maestro("0002", "Dulce", "Alvarado", new Date(), false, "dulce.alvarado@um.edu.mx"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Maestro> lista() {
        log.debug("Obteniendo lista de maestros");
        List<Maestro> lista = getJdbcTemplate().query(LISTA_MAESTROS, new MaestroMapper());
        return lista;
    }

    @Override
    public Maestro crea(final Maestro maestro) {
        log.debug("Creando al maestro {}", maestro);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(
                    Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(
                        CREAR_MAESTRO, new String[]{"id"});
                ps.setString(1, maestro.getNomina());
                ps.setString(2, maestro.getNombre());
                ps.setString(3, maestro.getApellido());
                if (maestro.getFechaNacimiento() != null) {
                    ps.setDate(4, new java.sql.Date(maestro.getFechaNacimiento().getTime()));
                } else {
                    ps.setNull(4, Types.DATE);
                }
                ps.setBoolean(5, maestro.getEsHombre());
                if (maestro.getCorreo() != null) {
                    ps.setString(6, maestro.getCorreo());
                } else {
                    ps.setNull(6, Types.VARCHAR);
                }
                return ps;
            }
        }, keyHolder);
        maestro.setId(keyHolder.getKey().longValue());

        maestros.add(maestro);
        return maestro;
    }

    @Override
    public Maestro actualiza(Maestro maestro) {
        log.debug("Actualizando al maestro {}", maestro);
        getJdbcTemplate().update(ACTUALIZAR_MAESTRO, maestro.getNombre(), maestro.getApellido(), maestro.getFechaNacimiento(), maestro.getEsHombre(), maestro.getCorreo(), maestro.getNomina());
        for (int pos = 0; pos < maestros.size(); pos++) {
            Maestro a = maestros.get(pos);
            if (a.getNomina().equals(maestro.getNomina())) {
                maestros.set(pos, maestro);
                break;
            }
        }
        return maestro;
    }

    @Override
    public String elimina(Maestro maestro) {
        log.debug("Eliminando al maestro {}", maestro);
        String nomina = maestro.getNomina();
        getJdbcTemplate().update(ELIMINA_MAESTRO, nomina);

        for (int pos = 0; pos < maestros.size(); pos++) {
            Maestro a = maestros.get(pos);
            if (a.getNomina().equals(maestro.getNomina())) {
                maestros.remove(pos);
                break;
            }
        }
        return nomina;
    }

    @Override
    public Maestro obtiene(String nomina) {
        log.debug("Obteniendo al maestro con la nomina {}", nomina);
        return getJdbcTemplate().queryForObject(OBTIENE_MAESTRO, new String[]{nomina}, new MaestroMapper());
    }
}

class MaestroMapper implements RowMapper<Maestro> {

    @Override
    public Maestro mapRow(ResultSet rs, int i) throws SQLException {
        Maestro maestro = new Maestro();
        maestro.setId(rs.getLong("id"));
        maestro.setNomina(rs.getString("nomina"));
        maestro.setNombre(rs.getString("nombre"));
        maestro.setApellido(rs.getString("apellido"));
        if (rs.getDate("fecha_nacimiento") != null) {
            maestro.setFechaNacimiento(new Date(rs.getDate("fecha_nacimiento").getTime()));
        }
        maestro.setEsHombre(rs.getBoolean("es_hombre"));
        if (rs.getString("correo") != null) {
            maestro.setCorreo(rs.getString("correo"));
        }
        return maestro;
    }
}
