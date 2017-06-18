package pe.com.hitss.sgp.web.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import pe.com.hitss.sgp.core.domain.Actividad;
import pe.com.hitss.sgp.core.domain.DocAdjuntosRegAct;
import pe.com.hitss.sgp.core.domain.Idea;
import pe.com.hitss.sgp.core.domain.Proyecto;
import pe.com.hitss.sgp.core.domain.TipoActividad;
import pe.com.hitss.sgp.core.domain.TipoHorario;
import pe.com.hitss.sgp.core.domain.Usuario;
import pe.com.hitss.sgp.core.service.ActividadService;
import pe.com.hitss.sgp.core.service.IdeaService;
import pe.com.hitss.sgp.core.service.ProyectoService;
import pe.com.hitss.sgp.core.service.TipoActividadService;
import pe.com.hitss.sgp.core.service.TipoHorarioService;
import pe.com.hitss.sgp.core.service.UsuarioService;
import pe.com.hitss.sgp.core.util.MessageException;
import pe.com.hitss.sgp.core.util.Resultado;
import pe.com.hitss.sgp.web.base.controller.BaseController;
import pe.com.hitss.sgp.web.util.ConstantesWeb;
import pe.com.hitss.sgp.web.util.UtilWeb;

@Controller
@Scope("session")
public class ActividadController extends BaseController {

	private static final long serialVersionUID = 1L;

	private static final Logger MENSAJELOG = Logger
			.getLogger(ActividadController.class);

	@Autowired
	private ActividadService actividadService;
	@Autowired
	private TipoActividadService tipoActividadService;
	@Autowired
	private TipoHorarioService tipoHorarioService;
	@Autowired
	private ProyectoService proyectoService;
	@Autowired
	private IdeaService ideaService;
	@Autowired
	private UsuarioService usuarioService;

	private String iniMain;

	private Actividad actividad;
	private Actividad filtroActividad;
	private Proyecto filtroProyecto;
	private Idea filtroIdea;
	private Usuario restriccionUsuario;

	private UploadedFile documento;
	private StreamedContent file;

	private List<Actividad> listActividad;
	private List<TipoActividad> listTipoActividad;
	private List<TipoHorario> listTipoHorario;
	private List<Proyecto> listProyecto;
	private List<Idea> listIdea;
	private List<Usuario> listUsuario;	

	@PostConstruct
	private void init() {
		MENSAJELOG.info("INI ActividadController");
		this.actividad = new Actividad();
		this.listActividad = new ArrayList<Actividad>();
		this.filtroActividad = new Actividad();
		this.filtroProyecto = new Proyecto();
		this.filtroIdea = new Idea();
		this.restriccionUsuario = new Usuario();
		this.listTipoActividad = new ArrayList<TipoActividad>();
		this.listTipoHorario = new ArrayList<TipoHorario>();
		this.listProyecto = new ArrayList<Proyecto>();
		this.listIdea = new ArrayList<Idea>();
		this.listUsuario = new ArrayList<Usuario>();
	}

	public String getIniMain() {
		this.restriccionUsuario = getUsuarioActual();
		this.filtroActividad.setFecha(new GregorianCalendar().getTime());
		this.filtroActividad.setFechaFin(new GregorianCalendar().getTime());
		this.listarTipoActividad();
		this.listarTipoHorario();
		this.listarActividad();
		this.listarUsuario();
		this.nuevaActividad();
		return iniMain;
	}

	public void setIniMain(String iniMain) {
		this.iniMain = iniMain;
	}
	
	public void cancelarOperacion() {
		this.actividad = new Actividad();
		nuevaActividad();
	}

	public void nuevaActividad() {
		this.actividad = new Actividad();
		this.actividad.getRecurso().setIdUsuario(
				this.restriccionUsuario.getIdUsuario());
		this.actividad.getRecurso().setNombreCompleto(
				this.restriccionUsuario.getNombreCompleto());
		this.actividad.setFecha(new GregorianCalendar().getTime());
		this.obtenerSemanaDelMes();
	}

	public void editarActividad(Actividad actividad) {
		this.actividad = actividad;
		this.seleccionaHorario();
		this.listarDocumentos();
	}

	public void clonarActividad(Actividad actividad) {
		this.actividad = actividad;
		this.seleccionaHorario();
		this.listarDocumentos();
		for (DocAdjuntosRegAct doc : this.actividad.getListDocumento()) {
			doc.setIdDocumento(null);
			doc.setIdRegistroActividad(null);
			doc.setIdAux(UtilWeb.getFechaExport());
		}
		this.actividad.setFecha(new GregorianCalendar().getTime());
		this.actividad.getRecurso().setIdUsuario(
				this.restriccionUsuario.getIdUsuario());
		this.actividad.setIdRegistroActividad(null);
	}

	public void grabarActividad() {
		Resultado resultado = null;
		try {
			if (UtilWeb.esNuloOrVacio(this.actividad.getFecha())) {
				mensajeAdvertencia("Por favor seleccione Fecha");
				return;
			}
			if (UtilWeb.esNuloOrVacio(this.actividad.getProyecto()
					.getIdProyecto())
					&& UtilWeb.esNuloOrVacio(this.actividad.getCodigoSDfalla())
					&& UtilWeb.esNuloOrVacio(this.actividad.getIdea()
							.getIdIdea())) {
				mensajeAdvertencia("Por favor seleccione Proyecto, Idea o ingrese Codigo DS");
				return;
			}
			if (!UtilWeb.esNuloOrVacio(this.actividad.getProyecto()
					.getIdProyecto())
					&& !UtilWeb
							.esNuloOrVacio(this.actividad.getCodigoSDfalla())
					&& !UtilWeb.esNuloOrVacio(this.actividad.getIdea()
							.getIdIdea())) {
				mensajeAdvertencia("No se permite Proyecto, Idea y Codigo SD a la vez");
				return;
			}
			if (UtilWeb.esNuloOrVacio(this.actividad.getHorasEjecutadas())
					|| this.actividad.getHorasEjecutadas() == 0) {
				mensajeAdvertencia("Por favor ingrese Horas ejecutadas, debe ser diferente de cero");
				return;
			}
			if (UtilWeb.esNuloOrVacio(this.actividad.getTipoActividad()
					.getIdTipoActividad())
					|| this.actividad.getTipoActividad().getIdTipoActividad() == 0) {
				mensajeAdvertencia("Por favor seleccione Tipo de Actividad");
				return;
			}
			if (UtilWeb.esNuloOrVacio(this.actividad.getTipoHorario()
					.getIdTipoHorario())
					|| this.actividad.getTipoHorario().getIdTipoHorario() == 0) {
				mensajeAdvertencia("Por favor seleccione Tipo de Horario");
				return;
			}
			if (UtilWeb.esNuloOrVacio(this.actividad.getActividad())) {
				mensajeAdvertencia("Por favor ingrese Actividad");
				return;
			}
			if (this.actividad.isFlgDocumentos()
					&& this.actividad.getListDocumento() != null
					&& this.actividad.getListDocumento().isEmpty()) {
				mensajeAdvertencia("Por favor adjunte al menos un documento");
				return;
			} else if (!this.actividad.isFlgDocumentos()) {
				this.actividad
						.setListDocumento(new ArrayList<DocAdjuntosRegAct>());
			}
			this.actividad.setCodigoSDfalla(this.actividad.getCodigoSDfalla()
					.toUpperCase());
			this.actividad
					.setUsuReg(this.restriccionUsuario.getNombreUsuario());
			resultado = this.actividadService.grabarActividad(this.actividad);
			if (!UtilWeb.esNuloOrVacio(resultado.getResString())) {
				mensajeError(resultado.getResString().toString());
				return;
			}
			mensajeExito("Actividad se registró con éxito!..");
			nuevaActividad();
			listarActividad();
			MENSAJELOG.info("ID DE ACTIVIDAD GENERADO: "
					+ Long.parseLong(resultado.getResNumeric().toString()));
		} catch (Exception e) {
			String[] errores = MessageException.getMessageExceptionPrintAop(e,
					e.getMessage());
			MENSAJELOG.error(errores[ConstantesWeb.N1]);
			ocurrioError(errores[ConstantesWeb.N0]);
		}
	}

	public void eliminarActividad(Actividad actividad) {
		try {
			actividad.setUsuMod(this.restriccionUsuario.getNombreUsuario());
			this.actividadService.eliminarActividad(actividad);
			mensajeExito("Actividad se eliminó con éxito!..");
			nuevaActividad();
			listarActividad();
		} catch (Exception e) {
			String[] errores = MessageException.getMessageExceptionPrintAop(e,
					e.getMessage());
			MENSAJELOG.error(errores[ConstantesWeb.N1]);
			ocurrioError(errores[ConstantesWeb.N0]);
		}
	}

	public void listarActividad() {
		try {
			this.filtroActividad.getRecurso().setIdUsuario(
					this.restriccionUsuario.getIdUsuario());
			this.filtroActividad.getRecurso().setNombreUsuario(
					this.restriccionUsuario.getNombreUsuario());
			this.listActividad = this.actividadService
					.listarActividad(this.filtroActividad);
		} catch (Exception e) {
			String[] errores = MessageException.getMessageExceptionPrintAop(e,
					e.getMessage());
			MENSAJELOG.error(errores[ConstantesWeb.N1]);
			ocurrioError(errores[ConstantesWeb.N0]);
		}
	}

	public void listarTipoActividad() {
		try {
			this.listTipoActividad = this.tipoActividadService
					.listarTipoActividad(ConstantesWeb.N0);
		} catch (Exception e) {
			String[] errores = MessageException.getMessageExceptionPrintAop(e,
					e.getMessage());
			MENSAJELOG.error(errores[ConstantesWeb.N1]);
			ocurrioError(errores[ConstantesWeb.N0]);
		}
	}

	public void listarTipoHorario() {
		try {
			this.listTipoHorario = this.tipoHorarioService
					.listarTipoHorario(ConstantesWeb.N0);
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

	public void obtenerSemanaDelMes() {
		Calendar calendar = Calendar.getInstance();
		calendar.setMinimalDaysInFirstWeek(1);
		calendar.setFirstDayOfWeek(Calendar.SUNDAY);
		calendar.setTime(this.actividad.getFecha());
		int semanaMes = calendar.get(Calendar.WEEK_OF_MONTH);
		MENSAJELOG.info("Fecha: " + calendar.getTime().toString() + " Semana "
				+ semanaMes);
		this.actividad.setSemana(semanaMes);
		this.actividad.setSemanaMes(String.valueOf(ConstantesWeb.SEMANA)
				.concat(" ").concat(String.valueOf(semanaMes)));
	}

	public void blurEditaCodigoSD() {
		if (!UtilWeb.esNuloOrVacio(this.actividad.getCodigoSDfalla())) {
			this.actividad.setProyecto(new Proyecto());
			this.actividad.setIdea(new Idea());
		}
	}

	public void listarProyecto() {
		try {
			this.listProyecto = this.proyectoService
					.listarProyecto(this.filtroProyecto);
		} catch (Exception e) {
			String[] errores = MessageException.getMessageExceptionPrintAop(e,
					e.getMessage());
			MENSAJELOG.error(errores[ConstantesWeb.N1]);
			ocurrioError(errores[ConstantesWeb.N0]);
		}
	}

	public void seleccionarProyecto(Proyecto proyecto) {
		if (proyecto != null && proyecto.getIdProyecto() != null) {
			this.actividad.getProyecto()
					.setIdProyecto(proyecto.getIdProyecto());
			this.actividad.getProyecto().setNombreProyecto(
					proyecto.getNombreProyecto());
			this.actividad.setIdea(new Idea());
			this.actividad.setCodigoSDfalla(null);
		}
	}

	public void listarIdea() {
		try {
			this.listIdea = this.ideaService.listarIdea(this.filtroIdea);
		} catch (Exception e) {
			String[] errores = MessageException.getMessageExceptionPrintAop(e,
					e.getMessage());
			MENSAJELOG.error(errores[ConstantesWeb.N1]);
			ocurrioError(errores[ConstantesWeb.N0]);
		}
	}

	public void seleccionarIdea(Idea idea) {
		if (idea != null && idea.getIdIdea() != null) {
			this.actividad.getIdea().setIdIdea(idea.getIdIdea());
			this.actividad.getIdea().setDescIdea(idea.getDescIdea());
			this.actividad.setProyecto(new Proyecto());
			this.actividad.setCodigoSDfalla(null);
		}
	}

	public void seleccionaHorario() {
		this.actividad.setFlgDocumentos(false);
		for (TipoHorario t : this.listTipoHorario) {
			if (this.actividad.getTipoHorario().getIdTipoHorario() == t
					.getIdTipoHorario()) {
				if (t.getFlgDocumentos() == ConstantesWeb.N1) {
					this.actividad.setFlgDocumentos(true);
				}
				return;
			}
		}
	}

	public void handleFileUpload(FileUploadEvent event) {
		UploadedFile doc = null;
		DocAdjuntosRegAct archivo = null;
		try {
			doc = event.getFile();
			if (doc != null) {
				if (doc.getContents().length != 0) {
					archivo = new DocAdjuntosRegAct();
					archivo.setIdRegistroActividad(this.actividad
							.getIdRegistroActividad());
					archivo.setNombreDocumento(doc.getFileName());
					archivo.setDocumento(doc.getContents());
					archivo.setTipoDocumento(doc.getContentType());
					archivo.setIdAux(UtilWeb.getFechaExport());
					archivo.setUsuReg(this.restriccionUsuario
							.getNombreUsuario());
					archivo.setFecReg(new GregorianCalendar().getTime());
					this.actividad.getListDocumento().add(archivo);
					mensajeExito("Archivo se cargó con éxito.");
				} else {
					UtilWeb.mensajeAdvertencia("Archivo no válido, no tiene contenido.");
				}
			}
		} catch (Exception e) {
			String[] errores = MessageException.getMessageExceptionPrintAop(e,
					e.getMessage());
			MENSAJELOG.error(errores[ConstantesWeb.N1]);
			ocurrioError(errores[ConstantesWeb.N0]);
		}
	}

	public void descargarDocumento(DocAdjuntosRegAct docAdjunto) {
		try {
			InputStream is = new ByteArrayInputStream(docAdjunto.getDocumento());
			setFile(new DefaultStreamedContent(is,
					docAdjunto.getTipoDocumento(),
					docAdjunto.getNombreDocumento()));
		} catch (Exception e) {
			String[] errores = MessageException.getMessageExceptionPrintAop(e,
					e.getMessage());
			MENSAJELOG.error(errores[ConstantesWeb.N1]);
			ocurrioError(errores[ConstantesWeb.N0]);
		}
	}

	public void listarDocumentos() {
		List<DocAdjuntosRegAct> list = null;
		try {
			list = this.actividadService.listarDocumentos(this.actividad
					.getIdRegistroActividad());
			this.actividad.setListDocumento(list);
		} catch (Exception e) {
			String[] errores = MessageException.getMessageExceptionPrintAop(e,
					e.getMessage());
			MENSAJELOG.error(errores[ConstantesWeb.N1]);
			ocurrioError(errores[ConstantesWeb.N0]);
		}
	}

	public void eliminarDocumento(DocAdjuntosRegAct docAdjunto) {
		List<DocAdjuntosRegAct> lista = null;
		try {
			if (docAdjunto.getIdDocumento() != null
					|| !UtilWeb.esNuloOrVacio(docAdjunto.getIdDocumento())) {
				docAdjunto
						.setUsuMod(this.restriccionUsuario.getNombreUsuario());
				this.actividad.getListDocumentoEliminar().add(docAdjunto);
				lista = new ArrayList<DocAdjuntosRegAct>();
				for (DocAdjuntosRegAct doc : this.actividad.getListDocumento()) {
					if (!doc.getIdDocumento().equals(
							docAdjunto.getIdDocumento())) {
						lista.add(doc);
					}
				}
				this.actividad.setListDocumento(lista);
			} else {
				lista = new ArrayList<DocAdjuntosRegAct>();
				for (DocAdjuntosRegAct doc : this.actividad.getListDocumento()) {
					if (doc.getIdAux().compareToIgnoreCase(
							docAdjunto.getIdAux()) != 0) {
						lista.add(doc);
					}
				}
				this.actividad.setListDocumento(lista);
			}
			mensajeExito("Archivo se eliminó con éxito.");
		} catch (Exception e) {
			String[] errores = MessageException.getMessageExceptionPrintAop(e,
					e.getMessage());
			MENSAJELOG.error(errores[ConstantesWeb.N1]);
			ocurrioError(errores[ConstantesWeb.N0]);
		}
	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public Actividad getFiltroActividad() {
		return filtroActividad;
	}

	public void setFiltroActividad(Actividad filtroActividad) {
		this.filtroActividad = filtroActividad;
	}

	public Proyecto getFiltroProyecto() {
		return filtroProyecto;
	}

	public void setFiltroProyecto(Proyecto filtroProyecto) {
		this.filtroProyecto = filtroProyecto;
	}

	public Idea getFiltroIdea() {
		return filtroIdea;
	}

	public void setFiltroIdea(Idea filtroIdea) {
		this.filtroIdea = filtroIdea;
	}

	public Usuario getRestriccionUsuario() {
		return restriccionUsuario;
	}

	public void setRestriccionUsuario(Usuario restriccionUsuario) {
		this.restriccionUsuario = restriccionUsuario;
	}

	public UploadedFile getDocumento() {
		return documento;
	}

	public void setDocumento(UploadedFile documento) {
		this.documento = documento;
	}

	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	public List<Actividad> getListActividad() {
		return listActividad;
	}

	public void setListActividad(List<Actividad> listActividad) {
		this.listActividad = listActividad;
	}

	public List<TipoActividad> getListTipoActividad() {
		return listTipoActividad;
	}

	public void setListTipoActividad(List<TipoActividad> listTipoActividad) {
		this.listTipoActividad = listTipoActividad;
	}

	public List<TipoHorario> getListTipoHorario() {
		return listTipoHorario;
	}

	public void setListTipoHorario(List<TipoHorario> listTipoHorario) {
		this.listTipoHorario = listTipoHorario;
	}

	public List<Proyecto> getListProyecto() {
		return listProyecto;
	}

	public void setListProyecto(List<Proyecto> listProyecto) {
		this.listProyecto = listProyecto;
	}

	public List<Idea> getListIdea() {
		return listIdea;
	}

	public void setListIdea(List<Idea> listIdea) {
		this.listIdea = listIdea;
	}

	public List<Usuario> getListUsuario() {
		return listUsuario;
	}

	public void setListUsuario(List<Usuario> listUsuario) {
		this.listUsuario = listUsuario;
	}
}
