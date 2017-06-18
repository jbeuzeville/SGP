package pe.com.hitss.sgp.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.hitss.sgp.core.dao.TipoActividadDao;
import pe.com.hitss.sgp.core.domain.TipoActividad;
import pe.com.hitss.sgp.core.service.TipoActividadService;
import pe.com.hitss.sgp.core.util.Resultado;

@Service("tipoActividadService")
public class TipoActividadServiceImpl implements TipoActividadService {

	@Autowired
	private TipoActividadDao tipoActividadDao;
	
	@Override
	public List<TipoActividad> listarTipoActividad(int idTipoActividad) throws Exception {
		return tipoActividadDao.listarTipoActividad(idTipoActividad);
	}

	@Override
	public Resultado grabarTipoActividad(TipoActividad tipoActividad)
			throws Exception {
		return tipoActividadDao.grabarTipoActividad(tipoActividad);
	}

}
