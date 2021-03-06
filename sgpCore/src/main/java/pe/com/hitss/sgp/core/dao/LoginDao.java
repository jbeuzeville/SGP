package pe.com.hitss.sgp.core.dao;

import java.util.List;

import pe.com.hitss.sgp.core.domain.Notificacion;
import pe.com.hitss.sgp.core.domain.Opciones;
import pe.com.hitss.sgp.core.domain.Usuario;

/**
 * Interfaz que contiene los m�todos abstractos que ser�n implementados por la
 * clase LoginDaoImpl
 */

public interface LoginDao {

	/**
	 * Obtiene datos del usuario logeado
	 * 
	 * @param idUsusario
	 * @param clave
	 * @return Usuario
	 * @exception ExceptionCore
	 */
	Usuario getUserLogin(Long idUsusario, String clave) throws Exception;

	/**
	 * Obtiene la hora de marcaci�n desde la base de datos
	 * 
	 * @param usuario
	 * @return List<Opciones> lista de opciones obtenidas de la bd
	 */
	List<Opciones> obtenerOpciones(int usuario) throws Exception;

	/**
	 * Obtiene las notificaciones generadas para el usuario en sesi�n
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
