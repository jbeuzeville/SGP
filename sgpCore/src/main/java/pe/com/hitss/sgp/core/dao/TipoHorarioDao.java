package pe.com.hitss.sgp.core.dao;

import java.util.List;

import pe.com.hitss.sgp.core.domain.TipoHorario;
import pe.com.hitss.sgp.core.util.Resultado;

public interface TipoHorarioDao {

	/**
	 * Lista los tipos de horario existentes
	 * @return Lista de tipos
	 * @throws Exception
	 */
	List<TipoHorario> listarTipoHorario(int idTipoHorario) throws Exception;
	
	/**
	 * Registra o actualiza el tipo de horario
	 * @param horario
	 * @return Resultado de operación
	 * @throws Exception
	 */
	Resultado grabarTipoHorario(TipoHorario tipoHorario) throws Exception;
}
