package pe.com.hitss.sgp.core.dao;

import java.util.List;

import pe.com.hitss.sgp.core.domain.Idea;
import pe.com.hitss.sgp.core.util.Resultado;

public interface IdeaDao {

	/**
	 * Lista los ideas existentes
	 * @return Lista de ideas
	 * @throws Exception
	 */
	List<Idea> listarIdea(Idea idea) throws Exception;
	
	/**
	 * Registra o actualiza idea
	 * @param idea
	 * @return Resultado de operación
	 * @throws Exception
	 */
	Resultado grabarIdea(Idea idea) throws Exception;
}
