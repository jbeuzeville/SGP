package pe.com.hitss.sgp.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.hitss.sgp.core.dao.TipoHorarioDao;
import pe.com.hitss.sgp.core.domain.TipoActividad;
import pe.com.hitss.sgp.core.domain.TipoHorario;
import pe.com.hitss.sgp.core.service.TipoHorarioService;
import pe.com.hitss.sgp.core.util.Resultado;

@Service("tipoHorarioService")
public class TipoHorarioServiceImpl implements TipoHorarioService {

	@Autowired
	private TipoHorarioDao tipoHorarioDao;
	
	@Override
	public List<TipoHorario> listarTipoHorario(int idTipoHorario) throws Exception {
		return tipoHorarioDao.listarTipoHorario(idTipoHorario);
	}

	@Override
	public Resultado grabarTipoHorario(TipoHorario tipoHorario)
			throws Exception {
		return tipoHorarioDao.grabarTipoHorario(tipoHorario);
	}

}
