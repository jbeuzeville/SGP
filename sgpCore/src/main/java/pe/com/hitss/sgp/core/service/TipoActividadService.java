package pe.com.hitss.sgp.core.service;

import java.util.List;

import pe.com.hitss.sgp.core.domain.TipoActividad;
import pe.com.hitss.sgp.core.util.Resultado;

public interface TipoActividadService {

	/**
	 * Lista los tipos de actividad existentes
	 * @return Lista de tipos
	 * @throws Exception
	 */
	List<TipoActividad> listarTipoActividad(int idTipoActividad) throws Exception;
	
	/**
	 * Registra o actualiza el tipo de actividad
	 * @param actividad
	 * @return Resultado de operación
	 * @throws Exception
	 */
	Resultado grabarTipoActividad(TipoActividad tipoActividad) throws Exception;
}
