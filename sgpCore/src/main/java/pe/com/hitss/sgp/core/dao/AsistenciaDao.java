package pe.com.hitss.sgp.core.dao;

import java.math.BigDecimal;
import java.util.List;

import pe.com.hitss.sgp.core.domain.Asistencia;
import pe.com.hitss.sgp.core.util.Resultado;

public interface AsistenciaDao {

	/**
	 * Lista la marcaci�n por usuario
	 * @return Lista de registro de marcaci�n
	 * @throws Exception
	 */
	List<Asistencia> listarMarcacion(Asistencia asistencia) throws Exception;
	
	/**
	 * Obtiene la hora de marcaci�n desde la base de datos
	 * @param String usuario
	 * @param String clave	
	 * @return String con la fecha y hora de maracaci�n
	 * @throws Exception
	 */
	Resultado grabarMarcacion(BigDecimal usuario, String clave) throws Exception;
	
	/**
	 * Lista la marcaci�n por usuario desde el perfil administrador
	 * @return Lista de registro de marcaci�n
	 * @throws Exception
	 */
	List<Asistencia> listarMarcacionAdministrador(Asistencia asistencia) throws Exception;
}
