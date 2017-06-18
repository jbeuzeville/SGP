package pe.com.hitss.sgp.web.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import pe.com.hitss.sgp.web.base.controller.BaseController;
import pe.com.hitss.sgp.web.util.ConstantesWeb;
import pe.com.hitss.sgp.web.util.UtilWeb;
import pe.com.hitss.sgp.core.domain.Notificacion;
import pe.com.hitss.sgp.core.domain.Opciones;
import pe.com.hitss.sgp.core.domain.Usuario;
import pe.com.hitss.sgp.core.service.AsistenciaService;
import pe.com.hitss.sgp.core.service.LoginService;
import pe.com.hitss.sgp.core.util.MessageException;
import pe.com.hitss.sgp.core.util.Resultado;

@Controller
@Scope("session")
public class LoginController extends BaseController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger MENSAJELOG = Logger
			.getLogger(LoginController.class);

	@Autowired
	private LoginService loginService;

	@Autowired
	private AsistenciaService asistenciaService;

	private Usuario userLogin;
	private String limpiar;
	private Long usuario;
	private String clave;
	private String init;

	private List<Notificacion> listNotificacion;

	private MenuModel model;

	private Integer resolucion;

	public LoginController() {

	}

	public String getInit() {
		userLogin = new Usuario();
		listNotificacion = new ArrayList<Notificacion>();
		return init;
	}

	public void setInit(String init) {
		this.init = init;
	}

	public void iniciarSesion() {
		try {
			this.userLogin = loginService
					.getUserLogin(this.usuario, this.clave);

			if (this.userLogin != null) {
				UtilWeb.setObjetoSesion(ConstantesWeb.USUARIO_LOGUEADO,
						userLogin);

				crearMenu();

				listarNotificaciones();

				MENSAJELOG.info("Usuario Logueado: "
						+ this.userLogin.toString());
				UtilWeb.sendRedirect(ConstantesWeb.PAGE_BIENVENIDO);
			} else {
				mensajeError("Usuario y/o clave incorrecta");
			}

		} catch (Exception e) {
			String[] errores = MessageException.getMessageExceptionPrintAop(e,
					e.getMessage());
			MENSAJELOG.error(errores[ConstantesWeb.N1]);
			ocurrioError(errores[ConstantesWeb.N0]);
		}
	}

	public void marcar() {
		Resultado resultado = null;
		try {
			BigDecimal idUsuario = new BigDecimal(this.usuario.toString());
			resultado = asistenciaService
					.grabarMarcacion(idUsuario, this.clave);
			mensajeExito(resultado.getMensaje());
		} catch (Exception e) {
			String[] errores = MessageException.getMessageExceptionPrintAop(e,
					e.getMessage());
			MENSAJELOG.error(errores[ConstantesWeb.N1]);
			ocurrioError(errores[ConstantesWeb.N0]);
		}
	}

	public void crearMenu() throws Exception {
		model = new DefaultMenuModel();
		List<Opciones> lstOpciones = loginService.obtenerOpciones(Integer
				.parseInt(this.usuario.toString()));
		for (Opciones opciones : lstOpciones) {
			if (opciones.getIdOpcionPadre() == 0) {
				DefaultSubMenu submenu = new DefaultSubMenu(
						opciones.getDescOpcion());
				for (Opciones opciones2 : lstOpciones) {
					if (opciones2.getIdOpcionPadre() == opciones.getIdOpcion()) {
						DefaultMenuItem menuItem = new DefaultMenuItem(
								opciones2.getDescOpcion());

						menuItem.setAjax(false);
						menuItem.setUrl(opciones2.getInformacion() != null ? opciones2
								.getInformacion() : "");
						submenu.addElement(menuItem);
					}
				}
				model.addElement(submenu);
			}
		}
	}

	public void cerrarSesion() {
		userLogin = null;
		UtilWeb.removeObjetoSesion(ConstantesWeb.USUARIO_LOGUEADO);
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		session.invalidate();
	}

	public void listarNotificaciones() {
		try {
			this.listNotificacion.clear();
			this.listNotificacion = loginService
					.obtenerNotificaciones(this.userLogin.getIdUsuario());
		} catch (Exception e) {
			String[] errores = MessageException.getMessageExceptionPrintAop(e,
					e.getMessage());
			MENSAJELOG.error(errores[ConstantesWeb.N1]);
			ocurrioError(errores[ConstantesWeb.N0]);
		}
	}

	public void eliminarNotificacion(Notificacion nota) {
		try {
			nota.setUsuMod(this.userLogin.getNombreUsuario());
			loginService.eliminarNotificacion(nota);
			listarNotificaciones();
		} catch (Exception e) {
			String[] errores = MessageException.getMessageExceptionPrintAop(e,
					e.getMessage());
			MENSAJELOG.error(errores[ConstantesWeb.N1]);
			ocurrioError(errores[ConstantesWeb.N0]);
		}
	}

	public Integer getAncho(Integer add) {
		Integer ancho = 0;
		this.resolucion = this.resolucion < 1366 ? 1366 : this.resolucion;
		ancho = Math.round(this.resolucion * 1010 / 1366)
				+ Math.round(add * 1010 / 1366);
		return ancho;
	}

	public String getLimpiar() {
		return limpiar;
	}

	public void setLimpiar(String limpiar) {
		this.limpiar = limpiar;
	}

	public Long getUsuario() {
		return usuario;
	}

	public void setUsuario(Long usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Usuario getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(Usuario userLogin) {
		this.userLogin = userLogin;
	}

	public Integer getResolucion() {
		return resolucion;
	}

	public void setResolucion(Integer resolucion) {
		this.resolucion = resolucion;
	}

	public MenuModel getModel() {
		return model;
	}

	public void setModel(MenuModel model) {
		this.model = model;
	}

	public List<Notificacion> getListNotificacion() {
		return listNotificacion;
	}

	public void setListNotificacion(List<Notificacion> listNotificacion) {
		this.listNotificacion = listNotificacion;
	}
}
