package pe.com.hitss.sgp.core.domain;

import java.io.Serializable;
import java.util.Date;

public class Asistencia implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idAsistencia;
	private Usuario usuario;
	private Date fecha;
	private String horaIngreso;
	private String horaSalida;
	private String nombreDia;
	private int estado;
	
	private Date fechaInicio;
	private Date fechaFin;
	private Long idProyectoHitss;
	
	public Asistencia() {
		super();
		this.usuario = new Usuario();
	}
	
	public Long getIdAsistencia() {
		return idAsistencia;
	}

	public void setIdAsistencia(Long idAsistencia) {
		this.idAsistencia = idAsistencia;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getHoraIngreso() {
		return horaIngreso;
	}

	public void setHoraIngreso(String horaIngreso) {
		this.horaIngreso = horaIngreso;
	}

	public String getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(String horaSalida) {
		this.horaSalida = horaSalida;
	}

	public String getNombreDia() {
		return nombreDia;
	}

	public void setNombreDia(String nombreDia) {
		this.nombreDia = nombreDia;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
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
