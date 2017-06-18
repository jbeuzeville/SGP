package pe.com.hitss.sgp.web.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import pe.com.hitss.sgp.core.domain.Asistencia;
import pe.com.hitss.sgp.core.domain.CampoGenerico;
import pe.com.hitss.sgp.core.domain.Proyecto;
import pe.com.hitss.sgp.core.domain.Usuario;
import pe.com.hitss.sgp.core.service.AsistenciaService;
import pe.com.hitss.sgp.core.service.ProyectoService;
import pe.com.hitss.sgp.core.service.UsuarioService;
import pe.com.hitss.sgp.core.util.MessageException;
import pe.com.hitss.sgp.core.util.Resultado;
import pe.com.hitss.sgp.web.base.controller.BaseController;
import pe.com.hitss.sgp.web.util.ConstantesWeb;
import pe.com.hitss.sgp.web.util.UtilWeb;

@Controller
@Scope("session")
public class GestionarAsistenciaController extends BaseController {

	private static final long serialVersionUID = 1L;

	private static final Logger MENSAJELOG = Logger
			.getLogger(GestionarAsistenciaController.class);

	@Autowired
	private AsistenciaService asistenciaService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private ProyectoService proyectoService;

	private String iniMain;
	private String clave;

	private CampoGenerico filtroAsistencia;

	private List<Asistencia> listAsistencias;
	private List<Usuario> listUsuario;
	private List<Proyecto> listProyectoHitss;

	@PostConstruct
	private void init() {
		MENSAJELOG.info("INI AsistenciaController");
		this.filtroAsistencia = new CampoGenerico();
		this.listAsistencias = new ArrayList<Asistencia>();
		this.listUsuario = new ArrayList<Usuario>();
		this.listProyectoHitss = new ArrayList<Proyecto>();
	}

	public String getIniMain() {
		listarUsuario();
		this.listarProyectoHitss();
		return iniMain;
	}

	public void setIniMain(String iniMain) {
		this.iniMain = iniMain;
	}

	public void buscarAsistencias() {
		try {
			Asistencia asistencia = new Asistencia();
			if (!UtilWeb.esNuloOrVacio(this.filtroAsistencia.getIdRecurso())) {
				asistencia.getUsuario().setIdUsuario(
						Long.parseLong(this.filtroAsistencia.getIdRecurso()));
			} else {
				asistencia.getUsuario().setIdUsuario(null);
			}
			if (!UtilWeb.esNuloOrVacio(this.filtroAsistencia
					.getIdProyectoHitss())) {
				asistencia.setIdProyectoHitss(Long
						.parseLong(this.filtroAsistencia.getIdProyectoHitss()));
			} else {
				asistencia.setIdProyectoHitss(null);
			}
			if (!UtilWeb.esNuloOrVacio(this.filtroAsistencia.getFechaInicio())
					&& UtilWeb.esNuloOrVacio(this.filtroAsistencia
							.getFechaFin())) {
				mensajeError("Por favor, seleccionar Fecha Fin.");
				return;
			}
			if (!UtilWeb.esNuloOrVacio(this.filtroAsistencia.getFechaFin())
					&& UtilWeb.esNuloOrVacio(this.filtroAsistencia
							.getFechaInicio())) {
				mensajeError("Por favor, seleccionar Fecha Inicio.");
				return;
			}
			asistencia.setFechaInicio(this.filtroAsistencia.getFechaInicio());
			asistencia.setFechaFin(this.filtroAsistencia.getFechaFin());
			this.listAsistencias = asistenciaService
					.listarMarcacionAdministrador(asistencia);
		} catch (Exception e) {
			String[] errores = MessageException.getMessageExceptionPrintAop(e,
					e.getMessage());
			MENSAJELOG.error(errores[ConstantesWeb.N1]);
			ocurrioError(errores[ConstantesWeb.N0]);
		}
	}

	public void marcar() {

		Usuario usuario = (Usuario) UtilWeb
				.getObjetoSesion(ConstantesWeb.USUARIO_LOGUEADO);

		Resultado resultado = null;
		try {
			BigDecimal idUsuario = new BigDecimal(usuario.getIdUsuario()
					.toString());
			resultado = asistenciaService
					.grabarMarcacion(idUsuario, this.clave);
			this.clave = "";
			mensajeExito(resultado.getMensaje());
		} catch (Exception e) {
			String[] errores = MessageException.getMessageExceptionPrintAop(e,
					e.getMessage());
			MENSAJELOG.error(errores[ConstantesWeb.N1]);
			ocurrioError(errores[ConstantesWeb.N0]);
		}
	}
	
	public void listarUsuario() {
		try {
			Usuario usuario = new Usuario();
			usuario.setIdUsuario(0L);
			usuario.setCodigoUsuario("0");
			this.listUsuario = this.usuarioService.listarUsuario(usuario);
		} catch (Exception e) {
			String[] errores = MessageException.getMessageExceptionPrintAop(e,
					e.getMessage());
			MENSAJELOG.error(errores[ConstantesWeb.N1]);
			ocurrioError(errores[ConstantesWeb.N0]);
		}
	}
	
	public void listarProyectoHitss() {
		try {
			Proyecto proyecto = new Proyecto();
			proyecto.setIdProyecto(0L);
			this.listProyectoHitss = this.proyectoService
					.listarProyectoHitss(proyecto);
		} catch (Exception e) {
			String[] errores = MessageException.getMessageExceptionPrintAop(e,
					e.getMessage());
			MENSAJELOG.error(errores[ConstantesWeb.N1]);
			ocurrioError(errores[ConstantesWeb.N0]);
		}
	}

	public CampoGenerico getFiltroAsistencia() {
		return filtroAsistencia;
	}

	public void setFiltroAsistencia(CampoGenerico filtroAsistencia) {
		this.filtroAsistencia = filtroAsistencia;
	}

	public List<Asistencia> getListAsistencias() {
		return listAsistencias;
	}

	public void setListAsistencias(List<Asistencia> listAsistencias) {
		this.listAsistencias = listAsistencias;
	}

	public List<Usuario> getListUsuario() {
		return listUsuario;
	}

	public void setListUsuario(List<Usuario> listUsuario) {
		this.listUsuario = listUsuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public List<Proyecto> getListProyectoHitss() {
		return listProyectoHitss;
	}

	public void setListProyectoHitss(List<Proyecto> listProyectoHitss) {
		this.listProyectoHitss = listProyectoHitss;
	}
}