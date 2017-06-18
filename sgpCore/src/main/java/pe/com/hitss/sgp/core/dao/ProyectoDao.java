package pe.com.hitss.sgp.core.dao;

import java.util.List;

import pe.com.hitss.sgp.core.domain.Proyecto;
import pe.com.hitss.sgp.core.util.Resultado;

public interface ProyectoDao {
	
	/**
	 * Lista los proyectos existentes
	 * @return Lista de proyectos
	 * @throws Exception
	 */
	List<Proyecto> listarProyecto(Proyecto proyecto) throws Exception;
	
	/**
	 * Registra o actualiza proyecto
	 * @param proyecto
	 * @return Resultado de operación
	 * @throws Exception
	 */
	Resultado grabarProyecto(Proyecto proyecto) throws Exception;
	
	/**
	 * Lista los proyectos hitss existentes
	 * @return Lista de proyectos hitss
	 * @throws Exception
	 */
	List<Proyecto> listarProyectoHitss(Proyecto proyecto) throws Exception;
}
