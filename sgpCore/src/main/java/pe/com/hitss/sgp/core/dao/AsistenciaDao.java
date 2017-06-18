package pe.com.hitss.sgp.core.dao;

import java.math.BigDecimal;
import java.util.List;

import pe.com.hitss.sgp.core.domain.Asistencia;
import pe.com.hitss.sgp.core.util.Resultado;

public interface AsistenciaDao {

	/**
	 * Lista la marcación por usuario
	 * @return Lista de registro de marcación
	 * @throws Exception
	 */
	List<Asistencia> listarMarcacion(Asistencia asistencia) throws Exception;
	
	/**
	 * Obtiene la hora de marcación desde la base de datos
	 * @param String usuario
	 * @param String clave	
	 * @return String con la fecha y hora de maracación
	 * @throws Exception
	 */
	Resultado grabarMarcacion(BigDecimal usuario, String clave) throws Exception;
	
	/**
	 * Lista la marcación por usuario desde el perfil administrador
	 * @return Lista de registro de marcación
	 * @throws Exception
	 */
	List<Asistencia> listarMarcacionAdministrador(Asistencia asistencia) throws Exception;
}
