package pe.com.hitss.sgp.core.dao.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

//import pe.com.hitss.base.exception.ExceptionCore;
import pe.com.hitss.sgp.core.dao.LoginDao;
import pe.com.hitss.sgp.core.domain.ItemList;
import pe.com.hitss.sgp.core.domain.Notificacion;
import pe.com.hitss.sgp.core.domain.Opciones;
import pe.com.hitss.sgp.core.domain.Permission;
import pe.com.hitss.sgp.core.domain.Usuario;
import pe.com.hitss.sgp.core.util.ConstantesCore;
import pe.com.hitss.sgp.core.util.Resultado;

/**
 * Clase con la implementación de los métodos para la administración del Logeo.
 * 
 */

@SuppressWarnings("all")
@Repository
public class LoginDaoImpl implements LoginDao {

	private static final Logger MENSAJELOG = Logger
			.getLogger(LoginDaoImpl.class);
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall simpleJdbcCall;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.simpleJdbcCall = new SimpleJdbcCall(dataSource);
	}

	@Override
	public Usuario getUserLogin(Long idUsusario, String clave) throws Exception {
		List<Usuario> lstUsuario = null;
		Usuario usuario = null;
		Map<String, Object> ing = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
					.withSchemaName(ConstantesCore.ESQUEMA_SGP)
					.withCatalogName(ConstantesCore.PACKAGE_SGP_SEGURIDAD)
					.withProcedureName("P_SGP_VALIDA_USUARIO")
					.withoutProcedureColumnMetaDataAccess()
					.declareParameters(
							new SqlParameter("an_usuario", Types.NUMERIC),
							new SqlParameter("av_clave", Types.VARCHAR))
					.returningResultSet("CUR_SYS_USUARIO", new UsuarioMapper());

			ing = new HashMap<String, Object>();
			ing.put("an_usuario", idUsusario);
			ing.put("av_clave", clave);

			Map<String, Object> res = simpleJdbcCall.execute(ing);
			lstUsuario = (List<Usuario>) res.get("CUR_SYS_USUARIO");

			if (lstUsuario != null && !lstUsuario.isEmpty()) {
				usuario = lstUsuario.get(0);
			}

			MENSAJELOG.info("Procedimiento: " + simpleJdbcCall.getCallString()
					+ "\n" + "Parámetros: " + ing.toString());

		} catch (DataAccessException da) {
			MENSAJELOG.info("Procedimiento: " + simpleJdbcCall.getCallString()
					+ "\n" + "Parámetros: " + ing.toString());
			MENSAJELOG.error(da);
		}
		return usuario;
	}

	private static final class UsuarioMapper implements RowMapper<Usuario> {

		public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
			Usuario u = new Usuario();
			u.setIdUsuario(rs.getLong("id_usuario"));
			u.setPrimerApellido(rs.getString("primer_apellido"));
			u.setSegundoApellido(rs.getString("segundo_apellido"));
			u.setNombres(rs.getString("nombres"));
			u.setNombreCompleto(rs.getString("nombre_completo"));
			u.setCodigoUsuario(rs.getString("codigo_usuario"));
			u.setNombreUsuario(rs.getString("nombre_usuario"));
			u.setPerfil(rs.getString("perfil"));
			u.setTelefono(rs.getString("telefono"));
			u.setCorreoPersonal(rs.getString("correo_personal"));
            u.setCorreoCliente(rs.getString("correo_cliente"));
            u.setDni(rs.getString("dni"));
            u.setFlagFechaPasada(rs.getInt("flg_fecha_pasada") == ConstantesCore.N1);
			return u;
		}
	}

	@Override
	public List<Opciones> obtenerOpciones(int usuario) {
		Map<String, Object> ing = null;
		List<Opciones> lstOpciones = new ArrayList<>();
		simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withSchemaName(ConstantesCore.ESQUEMA_SGP)
				.withCatalogName(ConstantesCore.PACKAGE_SGP_SEGURIDAD)
				.withProcedureName("P_SGP_OBTENER_OPCIONES")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(
						new SqlParameter("av_usuario", Types.NUMERIC))
				.returningResultSet("CUR_SYS_OPCIONES", new filasOpciones());

		ing = new HashMap<String, Object>();
		ing.put("av_usuario", usuario);

		Map<String, Object> res = simpleJdbcCall.execute(ing);
		lstOpciones = (List<Opciones>) res.get("CUR_SYS_OPCIONES");

		MENSAJELOG.info("Procedimiento: " + simpleJdbcCall.getCallString()
				+ "\n" + "Parámetros: " + ing.toString());

		return lstOpciones;
	}

	private static final class filasOpciones implements RowMapper<Opciones> {

		public Opciones mapRow(ResultSet rs, int rowNum) throws SQLException {
			Opciones u = new Opciones();
			u.setIdOpcion(rs.getInt("ID_OPCION"));
			u.setDescOpcion(rs.getString("DESC_OPCION"));
			u.setInformacion(rs.getString("INFORMACION"));
			u.setNivel(rs.getInt("NIVEL"));
			u.setIdOpcionPadre(rs.getInt("ID_OPCION_PADRE"));
			u.setOrden(rs.getInt("ORDEN"));
			return u;
		}
	}

	@Override
	public List<Notificacion> obtenerNotificaciones(Long idUsuario)
			throws Exception {
		List<Notificacion> listNotificacion = null;
		String query = null;
		try {
			query = " select id_notificacion, notificacion, informacion, "
					+ " id_usuario, id_actividad, nivel "
					+ " from hitss.cfg_notificacion "
					+ " where estado = 1 and id_usuario = ? ";
			listNotificacion = jdbcTemplate.query(query,
					new NotificacionMapper(), idUsuario);

			MENSAJELOG.info("Procedimiento: " + query
					+ "\n" + "Parámetros: {idUsuario : " + idUsuario + "}");
		} catch (DataAccessException da) {
			MENSAJELOG.error(da);
		}
		return listNotificacion;
	}

	private static final class NotificacionMapper implements
			RowMapper<Notificacion> {

		public Notificacion mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			Notificacion obj = new Notificacion();
			obj.setIdNotificacion(rs.getLong("id_notificacion"));
			obj.setNotificacion(rs.getString("notificacion"));
			obj.setInformacion(rs.getString("informacion"));
			obj.setIdUsuario(rs.getLong("id_usuario"));
			obj.setIdActividad(rs.getLong("id_actividad"));
			obj.setNivel(rs.getString("nivel"));
			return obj;
		}
	}

	@Override
	public void eliminarNotificacion(Notificacion notificacion)
			throws Exception {
		String query = null;
		try {
			query = " update hitss.cfg_notificacion set estado = ?, "
					+ "	usumod = ?, fecmod = sysdate "
					+ "where id_notificacion = ? ";
			jdbcTemplate.update(query, 0, notificacion.getUsuMod(),
					notificacion.getIdNotificacion());

			MENSAJELOG.info("Procedimiento: " + query
					+ "\n" + "Parámetros: " + notificacion.toString());
		} catch (DataAccessException da) {
			MENSAJELOG.error(da);
		}
	}

}
