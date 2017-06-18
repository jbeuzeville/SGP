package pe.com.hitss.sgp.core.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Actividad implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idRegistroActividad;
	private int semana;
	private String semanaMes;
	private Date fecha;
	private String fechaString;
	private Proyecto proyecto;
	private Idea idea;
	private String codigoSDfalla;
	private TipoActividad tipoActividad;
	private String actividad;
	private int horasEjecutadas;
	private Usuario usuAnalistaEjec;
	private TipoHorario tipoHorario;
	private Usuario recurso;
	private int estado;
	private String usuReg;
	private Date fecReg;
	private String usuMod;
	private Date fecMod;
	private List<DocAdjuntosRegAct> listDocumento;
	private List<DocAdjuntosRegAct> listDocumentoEliminar;

	private boolean flgDocumentos;
	private Date fechaFin;
	private Long idProyectoHitss;
	
	public Actividad() {
		super();
		this.proyecto = new Proyecto();
		this.idea = new Idea();
		this.tipoActividad = new TipoActividad();
		this.usuAnalistaEjec = new Usuario();
		this.tipoHorario = new TipoHorario();
		this.recurso = new Usuario();
		this.listDocumento = new ArrayList<DocAdjuntosRegAct>();
		this.listDocumentoEliminar = new ArrayList<DocAdjuntosRegAct>();
		this.flgDocumentos = false;
	}

	public Long getIdRegistroActividad() {
		return idRegistroActividad;
	}

	public void setIdRegistroActividad(Long idRegistroActividad) {
		this.idRegistroActividad = idRegistroActividad;
	}

	public int getSemana() {
		return semana;
	}

	public void setSemana(int semana) {
		this.semana = semana;
	}

	public String getSemanaMes() {
		return semanaMes;
	}

	public void setSemanaMes(String semanaMes) {
		this.semanaMes = semanaMes;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getFechaString() {
		return fechaString;
	}

	public void setFechaString(String fechaString) {
		this.fechaString = fechaString;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public Idea getIdea() {
		return idea;
	}

	public void setIdea(Idea idea) {
		this.idea = idea;
	}

	public String getCodigoSDfalla() {
		return codigoSDfalla;
	}

	public void setCodigoSDfalla(String codigoSDfalla) {
		this.codigoSDfalla = codigoSDfalla;
	}

	public TipoActividad getTipoActividad() {
		return tipoActividad;
	}

	public void setTipoActividad(TipoActividad tipoActividad) {
		this.tipoActividad = tipoActividad;
	}

	public String getActividad() {
		return actividad;
	}

	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	public int getHorasEjecutadas() {
		return horasEjecutadas;
	}

	public void setHorasEjecutadas(int horasEjecutadas) {
		this.horasEjecutadas = horasEjecutadas;
	}

	public Usuario getUsuAnalistaEjec() {
		return usuAnalistaEjec;
	}

	public void setUsuAnalistaEjec(Usuario usuAnalistaEjec) {
		this.usuAnalistaEjec = usuAnalistaEjec;
	}

	public TipoHorario getTipoHorario() {
		return tipoHorario;
	}

	public void setTipoHorario(TipoHorario tipoHorario) {
		this.tipoHorario = tipoHorario;
	}

	public Usuario getRecurso() {
		return recurso;
	}

	public void setRecurso(Usuario recurso) {
		this.recurso = recurso;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getUsuReg() {
		return usuReg;
	}

	public void setUsuReg(String usuReg) {
		this.usuReg = usuReg;
	}

	public Date getFecReg() {
		return fecReg;
	}

	public void setFecReg(Date fecReg) {
		this.fecReg = fecReg;
	}

	public String getUsuMod() {
		return usuMod;
	}

	public void setUsuMod(String usuMod) {
		this.usuMod = usuMod;
	}

	public Date getFecMod() {
		return fecMod;
	}

	public void setFecMod(Date fecMod) {
		this.fecMod = fecMod;
	}

	public List<DocAdjuntosRegAct> getListDocumento() {
		return listDocumento;
	}

	public void setListDocumento(List<DocAdjuntosRegAct> listDocumento) {
		this.listDocumento = listDocumento;
	}

	public List<DocAdjuntosRegAct> getListDocumentoEliminar() {
		return listDocumentoEliminar;
	}

	public void setListDocumentoEliminar(
			List<DocAdjuntosRegAct> listDocumentoEliminar) {
		this.listDocumentoEliminar = listDocumentoEliminar;
	}

	public boolean isFlgDocumentos() {
		return flgDocumentos;
	}

	public void setFlgDocumentos(boolean flgDocumentos) {
		this.flgDocumentos = flgDocumentos;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Long getIdProyectoHitss() {
		return idProyectoHitss;
	}

	public void setIdProyectoHitss(Long idProyectoHitss) {
		this.idProyectoHitss = idProyectoHitss;
	}

}
