package pe.com.hitss.sgp.core.domain;

import java.io.Serializable;
import java.util.Date;

public class CampoGenerico implements Serializable {

	private static final long serialVersionUID = 1L;

	private String idProyectoHitss;
	private String idRecurso;
	private Date fechaInicio;
	private Date fechaFin;

	public String getIdProyectoHitss() {
		return idProyectoHitss;
	}

	public void setIdProyectoHitss(String idProyectoHitss) {
		this.idProyectoHitss = idProyectoHitss;
	}

	public String getIdRecurso() {
		return idRecurso;
	}

	public void setIdRecurso(String idRecurso) {
		this.idRecurso = idRecurso;
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

}
