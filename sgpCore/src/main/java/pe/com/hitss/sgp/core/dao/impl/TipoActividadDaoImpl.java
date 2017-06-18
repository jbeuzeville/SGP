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

import pe.com.hitss.sgp.core.dao.TipoActividadDao;
import pe.com.hitss.sgp.core.domain.Asistencia;
import pe.com.hitss.sgp.core.domain.TipoActividad;
import pe.com.hitss.sgp.core.util.ConstantesCore;
import pe.com.hitss.sgp.core.util.Resultado;

@SuppressWarnings("all")
@Repository
public class TipoActividadDaoImpl implements TipoActividadDao {

	private static final Logger MENSAJELOG = Logger
			.getLogger(TipoActividadDaoImpl.class);
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall simpleJdbcCall;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.simpleJdbcCall = new SimpleJdbcCall(dataSource);
	}
	
	@Override
	public List<TipoActividad> listarTipoActividad(int idTipoActividad) throws Exception {
		List<TipoActividad> lstTipoActividad = null;
		Map<String, Object> ing = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
					.withSchemaName(ConstantesCore.ESQUEMA_SGP)
					.withCatalogName(ConstantesCore.PACKAGE_SGP_MANTENIMIENTO)
					.withProcedureName("P_SGP_LISTA_TIPO_ACTIVIDAD")
					.withoutProcedureColumnMetaDataAccess()
					.declareParameters(
							new SqlParameter("AN_ID_TIPO_ACTIVIDAD", Types.NUMERIC))
					.returningResultSet("CUR_SYS_TIPO_ACTIVIDAD",
							new TipoActividadMapper());

			ing = new HashMap<String, Object>();
			ing.put("AN_ID_TIPO_ACTIVIDAD", idTipoActividad);

			Map<String, Object> res = simpleJdbcCall.execute(ing);
			lstTipoActividad = (List<TipoActividad>) res.get("CUR_SYS_TIPO_ACTIVIDAD");

			MENSAJELOG.info("Procedimiento: " + simpleJdbcCall.getCallString()
					+ "\n" + "Parámetros: " + ing.toString());
		} catch (DataAccessException da) {
			MENSAJELOG.info("Procedimiento: " + simpleJdbcCall.getCallString()
					+ "\n" + "Parámetros: " + ing.toString());
			MENSAJELOG.error(da);
		}
		return lstTipoActividad;
	}

	private static final class TipoActividadMapper implements RowMapper<TipoActividad> {

		public TipoActividad mapRow(ResultSet rs, int rowNum) throws SQLException {
			TipoActividad obj = new TipoActividad();
			obj.setIdTipoActividad(rs.getInt("id_tipo_actividad"));
			obj.setDescTipoActividad(rs.getString("desc_tipo_actividad"));
			obj.setInformacion(rs.getString("informacion"));
			return obj;
		}
	}
	
	@Override
	public Resultado grabarTipoActividad(TipoActividad tipoActividad)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
