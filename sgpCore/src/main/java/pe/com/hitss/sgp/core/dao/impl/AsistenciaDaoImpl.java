package pe.com.hitss.sgp.core.dao.impl;

import java.math.BigDecimal;
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
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import pe.com.hitss.sgp.core.dao.AsistenciaDao;
import pe.com.hitss.sgp.core.domain.Asistencia;
import pe.com.hitss.sgp.core.domain.Permission;
import pe.com.hitss.sgp.core.domain.Usuario;
import pe.com.hitss.sgp.core.util.ConstantesCore;
import pe.com.hitss.sgp.core.util.Resultado;

@SuppressWarnings("all")
@Repository
public class AsistenciaDaoImpl implements AsistenciaDao {

	private static final Logger MENSAJELOG = Logger
			.getLogger(AsistenciaDaoImpl.class);
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall simpleJdbcCall;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.simpleJdbcCall = new SimpleJdbcCall(dataSource);
	}

	@Override
	public List<Asistencia> listarMarcacion(Asistencia asistencia) throws Exception {
		List<Asistencia> lstAsistencia = null;
		Map<String, Object> ing = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
					.withSchemaName(ConstantesCore.ESQUEMA_SGP)
					.withCatalogName(ConstantesCore.PACKAGE_SGP_SEGURIDAD)
					.withProcedureName("P_SGP_OBTENER_ASISTENCIA")
					.withoutProcedureColumnMetaDataAccess()
					.declareParameters(
							new SqlParameter("an_usuario", Types.NUMERIC),
							new SqlParameter("ad_fecha_inicial", Types.DATE),
							new SqlParameter("ad_fecha_final", Types.DATE))
					.returningResultSet("CUR_SYS_ASISTENCIA",
							new AsistenciaMapper());

			ing = new HashMap<String, Object>();
			ing.put("an_usuario", asistencia.getUsuario().getIdUsuario());
			ing.put("ad_fecha_inicial", asistencia.getFechaInicio());
			ing.put("ad_fecha_final", asistencia.getFechaFin());

			Map<String, Object> res = simpleJdbcCall.execute(ing);
			lstAsistencia = (List<Asistencia>) res.get("CUR_SYS_ASISTENCIA");

			MENSAJELOG.info("Procedimiento: " + simpleJdbcCall.getCallString()
					+ "\n" + "Parámetros: " + ing.toString());
		} catch (DataAccessException da) {
			MENSAJELOG.info("Procedimiento: " + simpleJdbcCall.getCallString()
					+ "\n" + "Parámetros: " + ing.toString());
			MENSAJELOG.error(da);
		}
		return lstAsistencia;
	}
	
	private static final class AsistenciaMapper implements RowMapper<Asistencia> {

		public Asistencia mapRow(ResultSet rs, int rowNum) throws SQLException {
			Asistencia obj = new Asistencia();
			obj.setFecha(rs.getDate("DIA"));
			obj.setHoraIngreso(rs.getString("INGRESO"));
			obj.setHoraSalida(rs.getString("SALIDA"));
			obj.setNombreDia(rs.getString("NOMBRE_DIA"));
			return obj;
		}
	}

	@Override
	public Resultado grabarMarcacion(BigDecimal usuario, String clave)
			throws Exception {
		Map<String, Object> ing = null;
		Resultado resultado = new Resultado();

		simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withSchemaName(ConstantesCore.ESQUEMA_SGP)
				.withCatalogName(ConstantesCore.PACKAGE_SGP_SEGURIDAD)
				.withProcedureName("P_SGP_MARCACION")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(
						new SqlParameter("av_usuario", Types.NUMERIC),
						new SqlParameter("av_clave", Types.VARCHAR),
						new SqlOutParameter("av_mensaje", Types.VARCHAR),
						new SqlOutParameter("an_resultado", Types.NUMERIC));

		ing = new HashMap<String, Object>();
		ing.put("av_usuario", usuario);
		ing.put("av_clave", clave);

		Map out = simpleJdbcCall.execute(ing);

		resultado.setMensaje((String) out.get("av_mensaje"));
		resultado.setResultado((BigDecimal) out.get("an_resultado"));

		MENSAJELOG.info("Procedimiento: " + simpleJdbcCall.getCallString()
				+ "\n" + "Parámetros: " + ing.toString());
		return resultado;
	}

	@Override
	public List<Asistencia> listarMarcacionAdministrador(Asistencia asistencia)
			throws Exception {
		List<Asistencia> lstAsistencia = null;
		Map<String, Object> ing = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
					.withSchemaName(ConstantesCore.ESQUEMA_SGP)
					.withCatalogName(ConstantesCore.PACKAGE_SGP_SEGURIDAD)
					.withProcedureName("P_SGP_BUSCAR_ASISTENCIA")
					.withoutProcedureColumnMetaDataAccess()
					.declareParameters(
							new SqlParameter("an_usuario", Types.NUMERIC),
							new SqlParameter("ad_fecha_inicial", Types.DATE),
							new SqlParameter("ad_fecha_final", Types.DATE),
							new SqlParameter("an_id_proyecto_hitss", Types.NUMERIC))
					.returningResultSet("CUR_SYS_ASISTENCIA",
							new AsistenciaMapper());

			ing = new HashMap<String, Object>();
			ing.put("an_usuario", asistencia.getUsuario().getIdUsuario());
			ing.put("ad_fecha_inicial", asistencia.getFechaInicio());
			ing.put("ad_fecha_final", asistencia.getFechaFin());
			ing.put("an_id_proyecto_hitss", asistencia.getIdProyectoHitss());

			Map<String, Object> res = simpleJdbcCall.execute(ing);
			lstAsistencia = (List<Asistencia>) res.get("CUR_SYS_ASISTENCIA");

			MENSAJELOG.info("Procedimiento: " + simpleJdbcCall.getCallString()
					+ "\n" + "Parámetros: " + ing.toString());
		} catch (DataAccessException da) {
			MENSAJELOG.info("Procedimiento: " + simpleJdbcCall.getCallString()
					+ "\n" + "Parámetros: " + ing.toString());
			MENSAJELOG.error(da);
		}
		return lstAsistencia;
	}

}
