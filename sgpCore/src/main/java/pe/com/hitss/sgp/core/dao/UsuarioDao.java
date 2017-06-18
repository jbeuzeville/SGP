package pe.com.hitss.sgp.core.dao;

import java.util.List;

import pe.com.hitss.sgp.core.domain.Usuario;
import pe.com.hitss.sgp.core.util.Resultado;

public interface UsuarioDao {

	/**
	 * Lista los usuarios existentes
	 * @return Lista de usuarios
	 * @throws Exception
	 */
	List<Usuario> listarUsuario(Usuario usuario) throws Exception;
	
	/**
	 * Registra o actualiza usuario
	 * @param usuario
	 * @return Resultado de operación
	 * @throws Exception
	 */
	Resultado grabarUsuario(Usuario usuario) throws Exception;
}
