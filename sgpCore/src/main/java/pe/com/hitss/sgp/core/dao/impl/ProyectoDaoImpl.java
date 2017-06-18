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

import pe.com.hitss.sgp.core.dao.ProyectoDao;
import pe.com.hitss.sgp.core.domain.Proyecto;
import pe.com.hitss.sgp.core.util.ConstantesCore;
import pe.com.hitss.sgp.core.util.Resultado;

@SuppressWarnings("all")
@Repository
public class ProyectoDaoImpl implements ProyectoDao {

	private static final Logger MENSAJELOG = Logger
			.getLogger(ProyectoDaoImpl.class);
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall simpleJdbcCall;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.simpleJdbcCall = new SimpleJdbcCall(dataSource);
	}
	
	@Override
	public List<Proyecto> listarProyecto(Proyecto proyecto) throws Exception {
		List<Proyecto> lstProyecto = null;
		Map<String, Object> ing = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
					.withSchemaName(ConstantesCore.ESQUEMA_SGP)
					.withCatalogName(ConstantesCore.PACKAGE_SGP_ACTIVIDAD)
					.withProcedureName("P_SGP_OBTENER_PROYECTOS")
					.withoutProcedureColumnMetaDataAccess()
					.declareParameters(
							new SqlParameter("av_codigo_proyecto", Types.VARCHAR),
							new SqlParameter("av_nombre_proyecto", Types.VARCHAR))
					.returningResultSet("CUR_SYS_PORYECTOS",
							new ProyectoMapper());

			ing = new HashMap<String, Object>();
			ing.put("av_codigo_proyecto", proyecto.getCodigoProyecto());
			ing.put("av_nombre_proyecto", proyecto.getNombreProyecto());

			Map<String, Object> res = simpleJdbcCall.execute(ing);
			lstProyecto = (List<Proyecto>) res.get("CUR_SYS_PORYECTOS");

			MENSAJELOG.info("Procedimiento: " + simpleJdbcCall.getCallString()
					+ "\n" + "Parámetros: " + ing.toString());
		} catch (DataAccessException da) {
			MENSAJELOG.info("Procedimiento: " + simpleJdbcCall.getCallString()
					+ "\n" + "Parámetros: " + ing.toString());
			MENSAJELOG.error(da);
		}
		return lstProyecto;
	}

	private static final class ProyectoMapper implements RowMapper<Proyecto> {

		public Proyecto mapRow(ResultSet rs, int rowNum) throws SQLException {
			Proyecto obj = new Proyecto();
			obj.setIdProyecto(rs.getLong("ID_PROYECTO"));
			obj.setCodigoProyecto(rs.getString("CODIGO_PROYECTO"));
			obj.setNombreProyecto(rs.getString("NOMBRE_PROYECTO"));
			obj.setDescProyecto(rs.getString("desc_proyecto"));
			obj.getIdea().setIdIdea(rs.getLong("ID_IDEA"));
			obj.getIdea().setCodigoIdea(rs.getString("CODIGO_IDEA"));
			obj.setIdEstado(rs.getInt("ID_ESTADO"));
			obj.setDescEstado(rs.getString("DESC_ESTADO"));
			obj.getUsuarioGerente().setIdUsuario(rs.getLong("ID_USUARIO_GERENTE"));
            obj.getUsuarioGerente().setNombreCompleto(rs.getString("GERENTE"));
            obj.getUsuarioLider().setIdUsuario(rs.getLong("ID_USUARIO_LIDER"));
            obj.getUsuarioLider().setNombreCompleto(rs.getString("LIDER"));
			return obj;
		}
	}

	@Override
	public Resultado grabarProyecto(Proyecto proyecto) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Proyecto> listarProyectoHitss(Proyecto proyecto)
			throws Exception {
		List<Proyecto> lstProyecto = null;
		Map<String, Object> ing = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
					.withSchemaName(ConstantesCore.ESQUEMA_SGP)
					.withCatalogName(ConstantesCore.PACKAGE_SGP_MANTENIMIENTO)
					.withProcedureName("P_SGP_LISTA_PROY_HITSS")
					.withoutProcedureColumnMetaDataAccess()
					.declareParameters(
							new SqlParameter("AN_ID_PROYECTO_HITSS", Types.NUMERIC))
					.returningResultSet("CUR_SYS_PROYECTO_HITSS",
							new ProyectoHitssMapper());

			ing = new HashMap<String, Object>();
			ing.put("AN_ID_PROYECTO_HITSS", proyecto.getIdProyecto());

			Map<String, Object> res = simpleJdbcCall.execute(ing);
			lstProyecto = (List<Proyecto>) res.get("CUR_SYS_PROYECTO_HITSS");

			MENSAJELOG.info("Procedimiento: " + simpleJdbcCall.getCallString()
					+ "\n" + "Parámetros: " + ing.toString());
		} catch (DataAccessException da) {
			MENSAJELOG.info("Procedimiento: " + simpleJdbcCall.getCallString()
					+ "\n" + "Parámetros: " + ing.toString());
			MENSAJELOG.error(da);
		}
		return lstProyecto;
	}
	
	private static final class ProyectoHitssMapper implements RowMapper<Proyecto> {

		public Proyecto mapRow(ResultSet rs, int rowNum) throws SQLException {
			Proyecto obj = new Proyecto();
			obj.setIdProyecto(rs.getLong("id_proyecto_hitss"));
			obj.setNombreProyecto(rs.getString("desc_proyecto_hitss"));
			return obj;
		}
	}

}
