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

import pe.com.hitss.sgp.core.dao.IdeaDao;
import pe.com.hitss.sgp.core.domain.Idea;
import pe.com.hitss.sgp.core.domain.Proyecto;
import pe.com.hitss.sgp.core.util.ConstantesCore;
import pe.com.hitss.sgp.core.util.Resultado;

@SuppressWarnings("all")
@Repository
public class IdeaDaoImpl implements IdeaDao {

	private static final Logger MENSAJELOG = Logger
			.getLogger(IdeaDaoImpl.class);
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall simpleJdbcCall;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.simpleJdbcCall = new SimpleJdbcCall(dataSource);
	}
	
	@Override
	public List<Idea> listarIdea(Idea idea) throws Exception {
		List<Idea> lstIdea = null;
		Map<String, Object> ing = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
					.withSchemaName(ConstantesCore.ESQUEMA_SGP)
					.withCatalogName(ConstantesCore.PACKAGE_SGP_ACTIVIDAD)
					.withProcedureName("P_SGP_OBTENER_IDEAS")
					.withoutProcedureColumnMetaDataAccess()
					.declareParameters(
							new SqlParameter("av_codigo_idea", Types.VARCHAR),
							new SqlParameter("av_desc_idea", Types.VARCHAR))
					.returningResultSet("CUR_SYS_PORYECTOS",
							new IdeaMapper());

			ing = new HashMap<String, Object>();
			ing.put("av_codigo_idea", idea.getCodigoIdea());
			ing.put("av_desc_idea", idea.getDescIdea());

			Map<String, Object> res = simpleJdbcCall.execute(ing);
			lstIdea = (List<Idea>) res.get("CUR_SYS_PORYECTOS");

			MENSAJELOG.info("Procedimiento: " + simpleJdbcCall.getCallString()
					+ "\n" + "Parámetros: " + ing.toString());
		} catch (DataAccessException da) {
			MENSAJELOG.info("Procedimiento: " + simpleJdbcCall.getCallString()
					+ "\n" + "Parámetros: " + ing.toString());
			MENSAJELOG.error(da);
		}
		return lstIdea;
	}

	private static final class IdeaMapper implements RowMapper<Idea> {

		public Idea mapRow(ResultSet rs, int rowNum) throws SQLException {
			Idea obj = new Idea();
			obj.setIdIdea(rs.getLong("id_idea"));
			obj.setCodigoIdea(rs.getString("codigo_idea"));
	        obj.setDescIdea(rs.getString("desc_idea"));
	        obj.setInformacion(rs.getString("informacion"));
	        obj.setEstado(rs.getInt("estado"));
	        obj.setUsuReg(rs.getString("usureg"));
	        obj.setFecReg(rs.getDate("fecreg"));
	        obj.setUsuMod(rs.getString("usumod"));
	        obj.setFecMod(rs.getDate("fecmod"));
			return obj;
		}
	}

	@Override
	public Resultado grabarIdea(Idea idea) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
