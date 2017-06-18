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

import pe.com.hitss.sgp.core.dao.TipoHorarioDao;
import pe.com.hitss.sgp.core.domain.TipoActividad;
import pe.com.hitss.sgp.core.domain.TipoHorario;
import pe.com.hitss.sgp.core.util.ConstantesCore;
import pe.com.hitss.sgp.core.util.Resultado;

@SuppressWarnings("all")
@Repository
public class TipoHorarioDaoImpl implements TipoHorarioDao {

	private static final Logger MENSAJELOG = Logger
			.getLogger(TipoHorarioDaoImpl.class);
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall simpleJdbcCall;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.simpleJdbcCall = new SimpleJdbcCall(dataSource);
	}
	
	@Override
	public List<TipoHorario> listarTipoHorario(int idTipoHorario) throws Exception {
		List<TipoHorario> lstTipoHorario = null;
		Map<String, Object> ing = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
					.withSchemaName(ConstantesCore.ESQUEMA_SGP)
					.withCatalogName(ConstantesCore.PACKAGE_SGP_MANTENIMIENTO)
					.withProcedureName("P_SGP_LISTA_TIPO_HORARIO")
					.withoutProcedureColumnMetaDataAccess()
					.declareParameters(
							new SqlParameter("AN_ID_TIPO_HORARIO", Types.NUMERIC))
					.returningResultSet("CUR_SYS_TIPO_HORARIO",
							new TipoHorarioMapper());

			ing = new HashMap<String, Object>();
			ing.put("AN_ID_TIPO_HORARIO", idTipoHorario);

			Map<String, Object> res = simpleJdbcCall.execute(ing);
			lstTipoHorario = (List<TipoHorario>) res.get("CUR_SYS_TIPO_HORARIO");

			MENSAJELOG.info("Procedimiento: " + simpleJdbcCall.getCallString()
					+ "\n" + "Parámetros: " + ing.toString());
		} catch (DataAccessException da) {
			MENSAJELOG.info("Procedimiento: " + simpleJdbcCall.getCallString()
					+ "\n" + "Parámetros: " + ing.toString());
			MENSAJELOG.error(da);
		}
		return lstTipoHorario;
	}

	private static final class TipoHorarioMapper implements RowMapper<TipoHorario> {

		public TipoHorario mapRow(ResultSet rs, int rowNum) throws SQLException {
			TipoHorario obj = new TipoHorario();
			obj.setIdTipoHorario(rs.getInt("id_tipo_horario"));
			obj.setDescTipoHorario(rs.getString("desc_tipo_horario"));
			obj.setInformacion(rs.getString("informacion"));
			obj.setFlgDocumentos(obj.getIdTipoHorario() == 3 ? 1 : 0);
			return obj;
		}
	}

	@Override
	public Resultado grabarTipoHorario(TipoHorario tipoHorario)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
