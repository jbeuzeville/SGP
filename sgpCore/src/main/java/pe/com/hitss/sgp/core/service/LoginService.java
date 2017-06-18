package pe.com.hitss.sgp.core.service;

import java.util.List;

import pe.com.hitss.sgp.core.dao.LoginDao;
import pe.com.hitss.sgp.core.domain.Notificacion;
import pe.com.hitss.sgp.core.domain.Opciones;
import pe.com.hitss.sgp.core.domain.Usuario;

/**
 * Interfaz que contiene los métodos abstractos que serán implementados 
 * por la clase LoginServiceImpl
 * 
 * @author: Cindy Condor
 * @version: 11/01/2016 - 1.0
 */
public interface LoginService extends LoginDao {
	
	/**
	 * Obtiene datos del usuario logeado
	 * @param idUsusario
	 * @param clave
	 * @return Usuario
	 * @exception ExceptionCore
	 */
	Usuario getUserLogin(Long idUsusario, String clave) throws Exception;

	/**
	 * Obtiene la hora de marcación desde la base de datos
	 * @param String usuario	
	 * @return List<Opciones> lista de opciones obtenidas de la bd
	 */
	List<Opciones>obtenerOpciones(int usuario) throws Exception;
	
	/**
	 * Obtiene las notificaciones generadas para el usuario en sesión
	 * 
	 * @param idUsuario
	 * @return List<Opciones> lista de notificaciones
	 */
	List<Notificacion> obtenerNotificaciones(Long idUsuario) throws Exception;
	
	/**
	 * Elimina las notificaciones.
	 * 
	 * @param notificacion
	 */
	void eliminarNotificacion(Notificacion notificacion) throws Exception;
}
