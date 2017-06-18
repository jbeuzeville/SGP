package pe.com.hitss.sgp.core.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import pe.com.hitss.sgp.core.dao.UsuarioDao;
import pe.com.hitss.sgp.core.domain.Actividad;
import pe.com.hitss.sgp.core.domain.Usuario;
import pe.com.hitss.sgp.core.util.ConstantesCore;
import pe.com.hitss.sgp.core.util.Resultado;

@SuppressWarnings("all")
@Repository
public class UsuarioDaoImpl implements UsuarioDao {

	private static final Logger MENSAJELOG = Logger
			.getLogger(UsuarioDaoImpl.class);
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall simpleJdbcCall;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.simpleJdbcCall = new SimpleJdbcCall(dataSource);
	}

	@Override
	public List<Usuario> listarUsuario(Usuario usuario) throws Exception {
		List<Usuario> lstUsuario = null;
		Map<String, Object> ing = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
					.withSchemaName(ConstantesCore.ESQUEMA_SGP)
					.withCatalogName(ConstantesCore.PACKAGE_SGP_MANTENIMIENTO)
					.withProcedureName("P_SGP_LISTA_USUARIO")
					.withoutProcedureColumnMetaDataAccess()
					.declareParameters(
							new SqlParameter("AN_ID_USUARIO", Types.NUMERIC),
							new SqlParameter("AN_CODIGO_USUARIO", Types.NUMERIC))
					.returningResultSet("CUR_SYS_USUARIO", new UsuarioMapper());

			ing = new HashMap<String, Object>();
			ing.put("AN_ID_USUARIO", usuario.getIdUsuario());
			ing.put("AN_CODIGO_USUARIO", usuario.getIdUsuario());

			Map<String, Object> res = simpleJdbcCall.execute(ing);
			lstUsuario = (List<Usuario>) res.get("CUR_SYS_USUARIO");

			MENSAJELOG.info("Procedimiento: " + simpleJdbcCall.getCallString()
					+ "\n" + "Parámetros: " + ing.toString());
		} catch (DataAccessException da) {
			MENSAJELOG.info("Procedimiento: " + simpleJdbcCall.getCallString()
					+ "\n" + "Parámetros: " + ing.toString());
			MENSAJELOG.error(da);
		}
		return lstUsuario;
	}

	private static final class UsuarioMapper implements RowMapper<Usuario> {

		public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
			Usuario obj = new Usuario();
			obj.setIdUsuario(rs.getLong("id_usuario"));
			obj.setPrimerApellido(rs.getString("primer_apellido"));
			obj.setSegundoApellido(rs.getString("segundo_apellido"));
			obj.setNombres(rs.getString("nombres"));
			obj.setNombreCompleto(obj.getNombres() + " "
					+ obj.getPrimerApellido() + " " + obj.getSegundoApellido());
			obj.setCodigoUsuario(rs.getString("codigo_usuario"));
			obj.setNombreUsuario(rs.getString("nombre_usuario"));
			obj.setCorreoPersonal(rs.getString("correo"));
			return obj;
		}
	}

	@Override
	public Resultado grabarUsuario(Usuario usuario) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
