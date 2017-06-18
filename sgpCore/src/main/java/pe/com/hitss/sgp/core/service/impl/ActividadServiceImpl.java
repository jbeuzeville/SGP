package pe.com.hitss.sgp.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pe.com.hitss.sgp.core.dao.ActividadDao;
import pe.com.hitss.sgp.core.domain.Actividad;
import pe.com.hitss.sgp.core.domain.DocAdjuntosRegAct;
import pe.com.hitss.sgp.core.service.ActividadService;
import pe.com.hitss.sgp.core.util.Resultado;

@Service("actividadService")
public class ActividadServiceImpl implements ActividadService {

	@Autowired
	private ActividadDao actividadDao;

	@Override
	public List<Actividad> listarActividad(Actividad actividad)
			throws Exception {
		return actividadDao.listarActividad(actividad);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Resultado grabarActividad(Actividad actividad) throws Exception {
		Resultado resultado = actividadDao.grabarActividad(actividad);

		if (resultado.getResString() == null) {
			resultado.setId(Long
					.parseLong(resultado.getResNumeric().toString()));

			for (DocAdjuntosRegAct doc : actividad.getListDocumento()) {
				if (doc.getIdDocumento() == null) {
					doc.setIdRegistroActividad(resultado.getId());
					this.grabarDocumento(doc);
				}
			}

			for (DocAdjuntosRegAct doc : actividad.getListDocumentoEliminar()) {
				if (doc.getIdDocumento() != null) {
					this.eliminarDocumento(doc);
				}
			}
		}
		return resultado;
	}

	@Override
	public void eliminarActividad(Actividad actividad) throws Exception {
		actividadDao.eliminarActividad(actividad);
	}

	@Override
	public void grabarDocumento(DocAdjuntosRegAct documento) throws Exception {
		actividadDao.grabarDocumento(documento);
	}

	@Override
	public List<DocAdjuntosRegAct> listarDocumentos(Long idActividad)
			throws Exception {
		return actividadDao.listarDocumentos(idActividad);
	}

	@Override
	public void eliminarDocumento(DocAdjuntosRegAct documento) throws Exception {
		actividadDao.eliminarDocumento(documento);
	}

	@Override
	public List<Actividad> listarActividadAdministrador(Actividad actividad)
			throws Exception {
		return actividadDao.listarActividadAdministrador(actividad);
	}

}
