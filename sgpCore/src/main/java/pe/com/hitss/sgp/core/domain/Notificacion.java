package pe.com.hitss.sgp.core.domain;

import java.io.Serializable;
import java.util.Date;

public class Notificacion implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idNotificacion;
	private String notificacion;
	private String informacion;
	private Long idUsuario;
	private String codigoUsuario;
	private Long idActividad;
	private String nivel;
	private int estado;
	private String usuReg;
	private Date fecReg;
	private String usuMod;
	private Date fecMod;

	public Long getIdNotificacion() {
		return idNotificacion;
	}

	public void setIdNotificacion(Long idNotificacion) {
		this.idNotificacion = idNotificacion;
	}

	public String getNotificacion() {
		return notificacion;
	}

	public void setNotificacion(String notificacion) {
		this.notificacion = notificacion;
	}

	public String getInformacion() {
		return informacion;
	}

	public void setInformacion(String informacion) {
		this.informacion = informacion;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public Long getIdActividad() {
		return idActividad;
	}

	public void setIdActividad(Long idActividad) {
		this.idActividad = idActividad;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
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

	@Override
	public String toString() {
		return "Notificacion [idNotificacion=" + idNotificacion
				+ ", notificacion=" + notificacion + ", informacion="
				+ informacion + ", idUsuario=" + idUsuario + ", codigoUsuario="
				+ codigoUsuario + ", idActividad=" + idActividad + ", nivel="
				+ nivel + ", estado=" + estado + ", usuReg=" + usuReg
				+ ", fecReg=" + fecReg + ", usuMod=" + usuMod + ", fecMod="
				+ fecMod + "]";
	}

}
