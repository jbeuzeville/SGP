package pe.com.hitss.sgp.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.hitss.sgp.core.dao.UsuarioDao;
import pe.com.hitss.sgp.core.domain.Usuario;
import pe.com.hitss.sgp.core.service.UsuarioService;
import pe.com.hitss.sgp.core.util.Resultado;

@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioDao usuarioDao;
	
	@Override
	public List<Usuario> listarUsuario(Usuario usuario) throws Exception {
		return usuarioDao.listarUsuario(usuario);
	}

	@Override
	public Resultado grabarUsuario(Usuario usuario) throws Exception {
		return usuarioDao.grabarUsuario(usuario);
	}

}
