package pe.com.hitss.sgp.core.service;

import java.util.List;

import pe.com.hitss.sgp.core.domain.Actividad;
import pe.com.hitss.sgp.core.domain.DocAdjuntosRegAct;
import pe.com.hitss.sgp.core.util.Resultado;

public interface ActividadService {

	/**
	 * Lista las actividades existentes
	 * @param actividad
	 * @return Lista de Actividades
	 * @throws Exception
	 */
	List<Actividad> listarActividad(Actividad actividad) throws Exception;
	
	/**
	 * Registra o actualiza actividades
	 * @param actividad
	 * @return Resultado de operación
	 * @throws Exception
	 */
	Resultado grabarActividad(Actividad actividad) throws Exception;
	
	/**
	 * Desactivar Actividad
	 * @param actividad
	 * @throws Exception
	 */
	void eliminarActividad(Actividad actividad) throws Exception;
	
	/**
	 * Registra documento
	 * @param documento
	 * @return Resultado de operación
	 * @throws Exception
	 */
	void grabarDocumento(DocAdjuntosRegAct documento) throws Exception;
	
	/**
	 * Lista los documentos
	 * @param actividad
	 * @return Lista de documentos por actividad
	 * @throws Exception
	 */
	List<DocAdjuntosRegAct> listarDocumentos(Long idActividad) throws Exception;
	
	/**
	 * Desactivar Documento
	 * @param documento
	 * @throws Exception
	 */
	void eliminarDocumento(DocAdjuntosRegAct documento) throws Exception;
	
	/**
	 * Lista las actividades existentes desde el perfil Administrador
	 * @param actividad
	 * @return Lista de Actividades
	 * @throws Exception
	 */
	List<Actividad> listarActividadAdministrador(Actividad actividad) throws Exception;
}
