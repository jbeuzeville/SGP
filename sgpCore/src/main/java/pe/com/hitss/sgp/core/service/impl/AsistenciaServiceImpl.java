package pe.com.hitss.sgp.core.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.hitss.sgp.core.dao.AsistenciaDao;
import pe.com.hitss.sgp.core.domain.Asistencia;
import pe.com.hitss.sgp.core.service.AsistenciaService;
import pe.com.hitss.sgp.core.util.Resultado;

@Service("asistenciaService")
public class AsistenciaServiceImpl implements AsistenciaService {

	@Autowired
	private AsistenciaDao asistenciaDao;
	
	@Override
	public List<Asistencia> listarMarcacion(Asistencia asistencia) throws Exception {
		return asistenciaDao.listarMarcacion(asistencia);
	}

	@Override
	public Resultado grabarMarcacion(BigDecimal usuario, String clave)
			throws Exception {
		return asistenciaDao.grabarMarcacion(usuario, clave);
	}

	@Override
	public List<Asistencia> listarMarcacionAdministrador(Asistencia asistencia)
			throws Exception {
		return asistenciaDao.listarMarcacionAdministrador(asistencia);
	}

}
