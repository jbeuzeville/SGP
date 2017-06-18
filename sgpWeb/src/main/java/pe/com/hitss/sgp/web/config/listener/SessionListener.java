package pe.com.hitss.sgp.web.config.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

//import us.com.telmexusa.ctp.web.bean.UsuarioBean;

import pe.com.hitss.sgp.web.util.ConstantesWeb;

public class SessionListener implements HttpSessionListener {

	@Autowired
	private static final Logger LOGGER = Logger.getLogger(SessionListener.class);

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		if (session.getAttribute(ConstantesWeb.USUARIO_LOGUEADO) != null) {
			LOGGER.info("SE LIBERO DE LISTA SESSION: " + session.getAttribute(ConstantesWeb.USUARIO_LOGUEADO));
			//ListaSession.listaUsuariosSesion.remove(((UsuarioBean) session
			//				.getAttribute(ConstantesWeb.USUARIO_LOGUEADO))
			//				.getUsuario());
			//UtilWeb.sendRedirect(ConstantesWeb.LOGIN);
		}

	}

}
