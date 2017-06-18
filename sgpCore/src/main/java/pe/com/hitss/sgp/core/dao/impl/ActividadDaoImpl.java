package pe.com.hitss.sgp.core.dao.impl;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import pe.com.hitss.sgp.core.dao.ActividadDao;
import pe.com.hitss.sgp.core.domain.Actividad;
import pe.com.hitss.sgp.core.domain.DocAdjuntosRegAct;
import pe.com.hitss.sgp.core.util.ConstantesCore;
import pe.com.hitss.sgp.core.util.Resultado;

@SuppressWarnings("all")
@Repository
public class ActividadDaoImpl implements ActividadDao {

	private static final Logger MENSAJELOG = Logger
			.getLogger(ActividadDaoImpl.class);
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall simpleJdbcCall;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.simpleJdbcCall = new SimpleJdbcCall(dataSource);
	}

	@Override
	public List<Actividad> listarActividad(Actividad actividad)
			throws Exception {
		List<Actividad> lstAsistencia = null;
		Map<String, Object> ing = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
					.withSchemaName(ConstantesCore.ESQUEMA_SGP)
					.withCatalogName(ConstantesCore.PACKAGE_SGP_ACTIVIDAD)
					.withProcedureName("P_SGP_OBTENER_REG_ACT")
					.withoutProcedureColumnMetaDataAccess()
					.declareParameters(
							new SqlParameter("an_id_usuario", Types.NUMERIC),
							new SqlParameter("av_nombre_usuario", Types.VARCHAR),
							new SqlParameter("AD_FECHA", Types.DATE),
							new SqlParameter("AD_FECHA_FINAL", Types.DATE))
					.returningResultSet("CUR_SYS_REG_ACT",
							new ActividadMapper());

			ing = new HashMap<String, Object>();
			ing.put("an_id_usuario", actividad.getRecurso().getIdUsuario());
			ing.put("av_nombre_usuario", actividad.getRecurso()
					.getNombreUsuario());
			ing.put("AD_FECHA", actividad.getFecha());
			ing.put("AD_FECHA_FINAL", actividad.getFechaFin());

			Map<String, Object> res = simpleJdbcCall.execute(ing);
			lstAsistencia = (List<Actividad>) res.get("CUR_SYS_REG_ACT");

			MENSAJELOG.info("Procedimiento: " + simpleJdbcCall.getCallString()
					+ "\n" + "Parámetros: " + ing.toString());
		} catch (DataAccessException da) {
			MENSAJELOG.info("Procedimiento: " + simpleJdbcCall.getCallString()
					+ "\n" + "Parámetros: " + ing.toString());
			MENSAJELOG.error(da);
		}
		return lstAsistencia;
	}

	private static final class ActividadMapper implements RowMapper<Actividad> {

		public Actividad mapRow(ResultSet rs, int rowNum) throws SQLException {
			Actividad obj = new Actividad();
			obj.setIdRegistroActividad(rs.getLong("id_registro_actividad"));
			obj.setSemanaMes(rs.getString("semana"));
			obj.setSemana(rs.getInt("semana_mes"));
			obj.setFecha(rs.getDate("dia"));
			obj.getProyecto().setIdProyecto(rs.getLong("id_proyecto"));
			obj.getProyecto()
					.setCodigoProyecto(rs.getString("codigo_proyecto"));
			obj.getProyecto()
					.setNombreProyecto(rs.getString("nombre_proyecto"));
			obj.setCodigoSDfalla(rs.getString("codigo_sd_falla"));
			obj.getIdea().setIdIdea(rs.getLong("id_idea"));
			obj.getIdea().setCodigoIdea(rs.getString("codigo_idea"));
			obj.getTipoActividad().setIdTipoActividad(
					rs.getInt("id_tipo_actividad"));
			obj.getTipoActividad().setDescTipoActividad(
					rs.getString("desc_tipo_actividad"));
			obj.setActividad(rs.getString("actividad"));
			obj.setHorasEjecutadas(rs.getInt("horas_ejecutadas"));
			obj.getUsuAnalistaEjec().setIdUsuario(
					rs.getLong("id_usu_analista_ejec"));
			obj.getTipoHorario().setIdTipoHorario(rs.getInt("id_tipo_horario"));
			obj.getTipoHorario().setDescTipoHorario(
					rs.getString("desc_tipo_horario"));
			obj.getRecurso().setIdUsuario(rs.getLong("id_recurso"));
			obj.getRecurso().setNombreCompleto(rs.getString("nombre_recurso"));
			return obj;
		}
	}

	@Override
	public Resultado grabarActividad(Actividad actividad) throws Exception {
		Map<String, Object> ing = null;
		Resultado resultado = new Resultado();
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
					.withSchemaName(ConstantesCore.ESQUEMA_SGP)
					.withCatalogName(ConstantesCore.PACKAGE_SGP_ACTIVIDAD)
					.withProcedureName("P_SGP_REGISTRAR_ACTIVIDAD")
					.withoutProcedureColumnMetaDataAccess()
					.declareParameters(
							new SqlParameter("an_id_registro_actividad",
									Types.NUMERIC),
							new SqlParameter("an_semana_mes", Types.NUMERIC),
							new SqlParameter("ad_fecha", Types.DATE),
							new SqlParameter("an_id_proyecto", Types.NUMERIC),
							new SqlParameter("av_codigo_sd_falla",
									Types.VARCHAR),
							new SqlParameter("an_id_tipo_actividad",
									Types.NUMERIC),
							new SqlParameter("av_actividad", Types.VARCHAR),
							new SqlParameter("an_horas_ejecutadas",
									Types.NUMERIC),
							new SqlParameter("an_id_usu_analista_ejec",
									Types.NUMERIC),
							new SqlParameter("an_id_tipo_horario",
									Types.NUMERIC),
							new SqlParameter("an_id_recurso", Types.NUMERIC),
							new SqlParameter("an_id_estado_proyecto",
									Types.NUMERIC),
							new SqlParameter("an_id_usuario_sesion",
									Types.VARCHAR),
							new SqlParameter("an_id_idea", Types.NUMERIC),
							new SqlOutParameter("an_out_id_registro_actividad",
									Types.NUMERIC),
							new SqlOutParameter("an_mesaje_validacion",
									Types.VARCHAR));

			ing = new HashMap<String, Object>();
			ing.put("an_id_registro_actividad",
					actividad.getIdRegistroActividad());
			ing.put("an_semana_mes", actividad.getSemana());
			ing.put("ad_fecha", actividad.getFecha());
			ing.put("an_id_proyecto", actividad.getProyecto().getIdProyecto());
			ing.put("av_codigo_sd_falla", actividad.getCodigoSDfalla());
			ing.put("an_id_tipo_actividad", actividad.getTipoActividad()
					.getIdTipoActividad());
			ing.put("av_actividad", actividad.getActividad());
			ing.put("an_horas_ejecutadas", actividad.getHorasEjecutadas());
			ing.put("an_id_usu_analista_ejec", actividad.getUsuAnalistaEjec()
					.getIdUsuario());
			ing.put("an_id_tipo_horario", actividad.getTipoHorario()
					.getIdTipoHorario());
			ing.put("an_id_recurso", actividad.getRecurso().getIdUsuario());
			ing.put("an_id_estado_proyecto", actividad.getEstado());
			ing.put("an_id_usuario_sesion", actividad.getUsuReg());
			ing.put("an_id_idea", actividad.getIdea().getIdIdea());

			Map out = simpleJdbcCall.execute(ing);

			resultado.setResNumeric(out.get("an_out_id_registro_actividad"));
			resultado.setResString(out.get("an_mesaje_validacion"));

			MENSAJELOG.info("Procedimiento: " + simpleJdbcCall.getCallString()
					+ "\n" + "Parámetros: " + ing.toString());
		} catch (DataAccessException da) {
			MENSAJELOG.info("Procedimiento: " + simpleJdbcCall.getCallString()
					+ "\n" + "Parámetros: " + ing.toString());
			MENSAJELOG.error(da);
		}
		return resultado;
	}

	@Override
	public void grabarDocumento(DocAdjuntosRegAct documento) throws Exception {
		Map<String, Object> ing = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
					.withSchemaName(ConstantesCore.ESQUEMA_SGP)
					.withCatalogName(ConstantesCore.PACKAGE_SGP_ACTIVIDAD)
					.withProcedureName("P_SGP_DOC_INSERT_ACT_HHEE")
					.withoutProcedureColumnMetaDataAccess()
					.declareParameters(
							new SqlParameter("an_id_registro_actividad",
									Types.NUMERIC),
							new SqlParameter("av_nombre_documento",
									Types.VARCHAR),
							new SqlParameter("ab_documento", Types.BINARY),
							new SqlParameter("av_tipo_documento", Types.VARCHAR),
							new SqlParameter("av_usureg", Types.VARCHAR),
							new SqlParameter("ad_fecreg", Types.DATE));

			ing = new HashMap<String, Object>();
			ing.put("an_id_registro_actividad",
					documento.getIdRegistroActividad());
			ing.put("av_nombre_documento", documento.getNombreDocumento());
			ing.put("ab_documento", documento.getDocumento());
			ing.put("av_tipo_documento", documento.getTipoDocumento());
			ing.put("av_usureg", documento.getUsuReg());
			ing.put("ad_fecreg", documento.getFecReg());

			Map out = simpleJdbcCall.execute(ing);

			MENSAJELOG.info("Procedimiento: " + simpleJdbcCall.getCallString()
					+ "\n" + "Parámetros: " + ing.toString());
		} catch (DataAccessException da) {
			MENSAJELOG.info("Procedimiento: " + simpleJdbcCall.getCallString()
					+ "\n" + "Parámetros: " + ing.toString());
			MENSAJELOG.error(da);
		}
	}

	@Override
	public void eliminarActividad(Actividad actividad) throws Exception {
		Map<String, Object> ing = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
					.withSchemaName(ConstantesCore.ESQUEMA_SGP)
					.withCatalogName(ConstantesCore.PACKAGE_SGP_ACTIVIDAD)
					.withProcedureName("P_SGP_ELIMINAR_REG_ACTIVIDAD")
					.withoutProcedureColumnMetaDataAccess()
					.declareParameters(
							new SqlParameter("an_id_registro_actividad",
									Types.NUMERIC),
							new SqlParameter("AV_ID_USUARIO", Types.VARCHAR));

			ing = new HashMap<String, Object>();
			ing.put("an_id_registro_actividad",
					actividad.getIdRegistroActividad());
			ing.put("AV_ID_USUARIO", actividad.getUsuMod());

			Map out = simpleJdbcCall.execute(ing);

			MENSAJELOG.info("Procedimiento: " + simpleJdbcCall.getCallString()
					+ "\n" + "Parámetros: " + ing.toString());
		} catch (DataAccessException da) {
			MENSAJELOG.info("Procedimiento: " + simpleJdbcCall.getCallString()
					+ "\n" + "Parámetros: " + ing.toString());
			MENSAJELOG.error(da);
		}
	}

	@Override
	public List<DocAdjuntosRegAct> listarDocumentos(Long idActividad)
			throws Exception {
		List<DocAdjuntosRegAct> lstDocAdjuntosRegAct = null;
		Map<String, Object> ing = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
					.withSchemaName(ConstantesCore.ESQUEMA_SGP)
					.withCatalogName(ConstantesCore.PACKAGE_SGP_ACTIVIDAD)
					.withProcedureName("P_SGP_OBTENER_DOC_ACT_HHEE")
					.withoutProcedureColumnMetaDataAccess()
					.declareParameters(
							new SqlParameter("an_id_registro_actividad",
									Types.NUMERIC))
					.returningResultSet("CUR_SYS_DOC",
							new DocAdjuntosRegActMapper());

			ing = new HashMap<String, Object>();
			ing.put("an_id_registro_actividad", idActividad);

			Map<String, Object> res = simpleJdbcCall.execute(ing);
			lstDocAdjuntosRegAct = (List<DocAdjuntosRegAct>) res
					.get("CUR_SYS_DOC");

			MENSAJELOG.info("Procedimiento: " + simpleJdbcCall.getCallString()
					+ "\n" + "Parámetros: " + ing.toString());
		} catch (DataAccessException da) {
			MENSAJELOG.info("Procedimiento: " + simpleJdbcCall.getCallString()
					+ "\n" + "Parámetros: " + ing.toString());
			MENSAJELOG.error(da);
		}
		return lstDocAdjuntosRegAct;
	}

	private static final class DocAdjuntosRegActMapper implements
			RowMapper<DocAdjuntosRegAct> {

		public DocAdjuntosRegAct mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			DocAdjuntosRegAct obj = new DocAdjuntosRegAct();
			Blob doc = null;
			obj.setIdDocumento(rs.getLong("id_documento"));
			obj.setIdRegistroActividad(rs.getLong("id_registro_actividad"));
			obj.setNombreDocumento(rs.getString("nombre_documento"));
			doc = rs.getBlob("documento");
			if (doc != null) {
				obj.setDocumento(doc.getBytes(1, (int) doc.length()));
			}
			obj.setTipoDocumento(rs.getString("tipo_documento"));
			obj.setEstado(rs.getInt("estado"));
			obj.setUsuReg(rs.getString("usureg"));
			obj.setFecReg(rs.getDate("fecreg"));
			obj.setUsuMod(rs.getString("usumod"));
			obj.setFecMod(rs.getDate("fecmod"));
			return obj;
		}
	}

	@Override
	public void eliminarDocumento(DocAdjuntosRegAct documento) throws Exception {
		Map<String, Object> ing = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
					.withSchemaName(ConstantesCore.ESQUEMA_SGP)
					.withCatalogName(ConstantesCore.PACKAGE_SGP_ACTIVIDAD)
					.withProcedureName("P_SGP_ELIMINAR_DOC_ACT_HHEE")
					.withoutProcedureColumnMetaDataAccess()
					.declareParameters(
							new SqlParameter("an_id_documento", Types.NUMERIC),
							new SqlParameter("AV_ID_USUARIO", Types.VARCHAR));

			ing = new HashMap<String, Object>();
			ing.put("an_id_documento", documento.getIdDocumento());
			ing.put("AV_ID_USUARIO", documento.getUsuMod());

			Map out = simpleJdbcCall.execute(ing);

			MENSAJELOG.info("Procedimiento: " + simpleJdbcCall.getCallString()
					+ "\n" + "Parámetros: " + ing.toString());
		} catch (DataAccessException da) {
			MENSAJELOG.info("Procedimiento: " + simpleJdbcCall.getCallString()
					+ "\n" + "Parámetros: " + ing.toString());
			MENSAJELOG.error(da);
		}
	}

	@Override
	public List<Actividad> listarActividadAdministrador(Actividad actividad)
			throws Exception {
		List<Actividad> lstActividad = null;
		Map<String, Object> ing = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
					.withSchemaName(ConstantesCore.ESQUEMA_SGP)
					.withCatalogName(ConstantesCore.PACKAGE_SGP_ACTIVIDAD)
					.withProcedureName("P_SGP_BUSCAR_REG_ACT")
					.withoutProcedureColumnMetaDataAccess()
					.declareParameters(
							new SqlParameter("an_usuario", Types.NUMERIC),
							new SqlParameter("ad_fecha_inicial", Types.DATE),
							new SqlParameter("ad_fecha_final", Types.DATE),
							new SqlParameter("an_id_proyecto_hitss",
									Types.NUMERIC))
					.returningResultSet("CUR_SYS_REG_ACT",
							new ActividadMapper());

			ing = new HashMap<String, Object>();
			ing.put("an_usuario", actividad.getRecurso().getIdUsuario());
			ing.put("ad_fecha_inicial", actividad.getFecha());
			ing.put("ad_fecha_final", actividad.getFechaFin());
			ing.put("an_id_proyecto_hitss", actividad.getIdProyectoHitss());

			Map<String, Object> res = simpleJdbcCall.execute(ing);
			lstActividad = (List<Actividad>) res.get("CUR_SYS_REG_ACT");

			MENSAJELOG.info("Procedimiento: " + simpleJdbcCall.getCallString()
					+ "\n" + "Parámetros: " + ing.toString());
		} catch (DataAccessException da) {
			MENSAJELOG.info("Procedimiento: " + simpleJdbcCall.getCallString()
					+ "\n" + "Parámetros: " + ing.toString());
			MENSAJELOG.error(da);
		}
		return lstActividad;
	}
}
