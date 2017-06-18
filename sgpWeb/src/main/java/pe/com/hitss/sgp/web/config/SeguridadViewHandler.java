package pe.com.hitss.sgp.web.config;

import java.io.IOException;

import javax.faces.application.ViewHandler;
import javax.faces.application.ViewHandlerWrapper;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import pe.com.hitss.sgp.web.util.ConstantesWeb;

public class SeguridadViewHandler extends ViewHandlerWrapper {

	@Autowired
	private static final Logger LOGGER = Logger.getLogger(SeguridadViewHandler.class);
	private ViewHandler parent;

	public SeguridadViewHandler(ViewHandler parent) {
		this.parent = parent;
	}

	@Override
	public UIViewRoot createView(FacesContext context, String viewId) {
		LOGGER.info(":::::CREATE VIEW:::::::::::::" + viewId);
		this.validaVista(context, viewId);
		return super.createView(context, viewId);
	}

	private void validaVista(FacesContext context, String viewId) {
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		String vista = viewId.substring(1, viewId.lastIndexOf('.'));
		if (!vista.equalsIgnoreCase("index") && !vista.equalsIgnoreCase("pages/login")){
			boolean permitido = true;
			if ((session == null)) {
				permitido = false;
			} else if (session.getAttribute(ConstantesWeb.USUARIO_LOGUEADO) == null) {
				permitido = false;
			}
			if (!permitido) {
				LOGGER.error("El usuario no cuenta con los premisos para entrar a la pagina");
				sendRedirect(context);
			}
		}
	}

	private void sendRedirect(FacesContext context) {
		HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();
		try {
			context.responseComplete();
			response.sendRedirect(context.getExternalContext().getRequestContextPath());
		} catch (IOException ioe) {
			LOGGER.error(ioe.getMessage());
		}
	}

	@Override
	public ViewHandler getWrapped() {
		return parent;
	}
}
