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
import pe.com.hitss.sgp.core.domain.Usuario;
import pe.com.hitss.sgp.core.service.AsistenciaService;
import pe.com.hitss.sgp.core.util.MessageException;
import pe.com.hitss.sgp.core.util.Resultado;
import pe.com.hitss.sgp.web.base.controller.BaseController;
import pe.com.hitss.sgp.web.util.ConstantesWeb;
import pe.com.hitss.sgp.web.util.UtilWeb;

@Controller
@Scope("session")
public class AsistenciaController extends BaseController {

	private static final long serialVersionUID = 1L;

	private static final Logger MENSAJELOG = Logger
			.getLogger(AsistenciaController.class);

	@Autowired
	private AsistenciaService asistenciaService;

	private String iniMain;
	private String clave;

	private Asistencia asistencia;

	private List<Asistencia> listAsistencias;

	@PostConstruct
	private void init() {
		MENSAJELOG.info("INI AsistenciaController");
		this.asistencia = new Asistencia();
		this.listAsistencias = new ArrayList<Asistencia>();
	}

	public String getIniMain() {
		this.asistencia.getUsuario().setIdUsuario(getUsuarioActual().getIdUsuario());
		buscarAsistencias();
		return iniMain;
	}

	public void setIniMain(String iniMain) {
		this.iniMain = iniMain;
	}

	public void buscarAsistencias() {
		try {
			this.listAsistencias = asistenciaService
					.listarMarcacion(this.asistencia);
		} catch (Exception e) {
			String[] errores = MessageException.getMessageExceptionPrintAop(e,
					e.getMessage());
			MENSAJELOG.error(errores[ConstantesWeb.N1]);
			ocurrioError(errores[ConstantesWeb.N0]);
		}
	}
	
	public void marcar() {
		
		Usuario usuario = (Usuario) UtilWeb.getObjetoSesion(ConstantesWeb.USUARIO_LOGUEADO);
		
		Resultado resultado = null;
		try {
			BigDecimal idUsuario = new BigDecimal(usuario.getIdUsuario().toString());
			resultado = asistenciaService.grabarMarcacion(idUsuario,
					this.clave);
			this.clave="";
			mensajeExito(resultado.getMensaje());
		} catch (Exception e) {
			String[] errores = MessageException.getMessageExceptionPrintAop(e,
					e.getMessage());
			MENSAJELOG.error(errores[ConstantesWeb.N1]);
			ocurrioError(errores[ConstantesWeb.N0]);
		}
	}

	public Asistencia getAsistencia() {
		return asistencia;
	}

	public void setAsistencia(Asistencia asistencia) {
		this.asistencia = asistencia;
	}

	public List<Asistencia> getListAsistencias() {
		return listAsistencias;
	}

	public void setListAsistencias(List<Asistencia> listAsistencias) {
		this.listAsistencias = listAsistencias;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

}
