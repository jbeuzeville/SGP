package pe.com.hitss.sgp.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.hitss.sgp.core.dao.ProyectoDao;
import pe.com.hitss.sgp.core.domain.Proyecto;
import pe.com.hitss.sgp.core.service.ProyectoService;
import pe.com.hitss.sgp.core.util.Resultado;

@Service("proyectoService")
public class ProyectoServiceImpl implements ProyectoService {

	@Autowired
	private ProyectoDao proyectoDao;
	
	@Override
	public List<Proyecto> listarProyecto(Proyecto proyecto) throws Exception {
		return proyectoDao.listarProyecto(proyecto);
	}

	@Override
	public Resultado grabarProyecto(Proyecto proyecto) throws Exception {
		return proyectoDao.grabarProyecto(proyecto);
	}

	@Override
	public List<Proyecto> listarProyectoHitss(Proyecto proyecto)
			throws Exception {
		return proyectoDao.listarProyectoHitss(proyecto);
	}

}
