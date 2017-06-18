package pe.com.hitss.sgp.web.base.controller;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import pe.com.hitss.sgp.web.config.WebMessageUtil;
import pe.com.hitss.sgp.web.util.ConstantesWeb;
import pe.com.hitss.sgp.web.util.MessageFactory;
import pe.com.hitss.sgp.web.util.UtilWeb;
import pe.com.hitss.sgp.core.domain.Usuario;

@Controller
@Scope("session")
public class BaseController implements Serializable {

	private static final long serialVersionUID = 1L;
	@Autowired
	private WebMessageUtil webMessageUtil;
	protected String idioma;

	public BaseController(){
		FacesContext context = FacesContext.getCurrentInstance();
		context.getViewRoot().setLocale((Locale)UtilWeb.getObjetoSesion(ConstantesWeb.LOCALIDAD));
	}
	
	public void exitoRegistra(Object entidad, Object id) {
		UtilWeb.mensajeInformacion(MessageFactory.getMessage(
				ConstantesWeb.EXITO_REGISTRO, ""));
	}
	
	public void exitoRegistraSales(Object entidad, Object id) {
		UtilWeb.mensajeInformacion(MessageFactory.getMessage(
				ConstantesWeb.EXITO_REGISTRO_SALES, ""));
	}

	public void exitoActualizar(Object entidad, Object id) {
		UtilWeb.mensajeInformacion(MessageFactory.getMessage(
				ConstantesWeb.EXITO_ACTUALIZO, ""));
	}

	public void exitoEliminar(Object entidad, Object id) {
		UtilWeb.mensajeInformacion(MessageFactory.getMessage(
				ConstantesWeb.EXITO_ELIMINO, ""));
	}
	
	/*private String obtenerNombreClase(Object entidad){
		int posicion = entidad.getClass().getName().lastIndexOf(".");
		return entidad.getClass().getName().substring(posicion+1);
	}*/
	
	public void advertenciaNoSeleccionado(String mensaje) {
		UtilWeb.mensajeAdvertencia(MessageFactory.getMessage(
				mensaje, ""));
	}
	
	public void ocurrioError(String cod) {
		UtilWeb.mensajeFatal(MessageFactory.getMessage(
				ConstantesWeb.OCURRIO_ERROR, cod));
	}
	
	public void mensajeError(String mensaje) {
		UtilWeb.mensajeError(MessageFactory.getMessage(
				mensaje, ""));
	}
	
	public void mensajeAdvertencia(String mensaje) {
		UtilWeb.mensajeAdvertencia(mensaje);
	}
	
	public void mensajeExito(String mensaje) {
		UtilWeb.mensajeInformacion(MessageFactory.getMessage(
				mensaje, ""));
	}
	
	protected Usuario getUsuarioActual(){
		Usuario usuarioEnSesion = new Usuario();
		usuarioEnSesion = (Usuario) UtilWeb.getObjetoSesion(ConstantesWeb.USUARIO_LOGUEADO);
		return usuarioEnSesion;
	}
	
	public void getLenguajeActual(){
		idioma = UtilWeb.getObjetoSesion(ConstantesWeb.LNG).toString().toUpperCase();
	}

}
