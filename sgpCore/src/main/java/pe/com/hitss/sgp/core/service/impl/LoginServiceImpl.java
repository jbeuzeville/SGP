package pe.com.hitss.sgp.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.hitss.sgp.core.dao.LoginDao;
import pe.com.hitss.sgp.core.domain.Notificacion;
import pe.com.hitss.sgp.core.domain.Opciones;
import pe.com.hitss.sgp.core.domain.Usuario;
import pe.com.hitss.sgp.core.service.LoginService;

@Service("loginService")
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private LoginDao loginDao;

	@Override
	public Usuario getUserLogin(Long idUsusario, String clave) throws Exception {
		return loginDao.getUserLogin(idUsusario, clave);
	}

	@Override
	public List<Opciones> obtenerOpciones(int usuario) throws Exception {
		return loginDao.obtenerOpciones(usuario);
	}

	@Override
	public List<Notificacion> obtenerNotificaciones(Long idUsuario)
			throws Exception {
		return loginDao.obtenerNotificaciones(idUsuario);
	}

	@Override
	public void eliminarNotificacion(Notificacion notificacion)
			throws Exception {
		loginDao.eliminarNotificacion(notificacion);
	}

}
