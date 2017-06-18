package pe.com.hitss.sgp.core.domain;

import java.io.Serializable;

public class TipoActividad implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idTipoActividad;
	private String descTipoActividad;
	private String informacion;
	private int estado;
	private String usuReg;
	private String fecReg;
	private String usuMod;
	private String fecMod;

	public int getIdTipoActividad() {
		return idTipoActividad;
	}

	public void setIdTipoActividad(int idTipoActividad) {
		this.idTipoActividad = idTipoActividad;
	}

	public String getDescTipoActividad() {
		return descTipoActividad;
	}

	public void setDescTipoActividad(String descTipoActividad) {
		this.descTipoActividad = descTipoActividad;
	}

	public String getInformacion() {
		return informacion;
	}

	public void setInformacion(String informacion) {
		this.informacion = informacion;
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

	public String getFecReg() {
		return fecReg;
	}

	public void setFecReg(String fecReg) {
		this.fecReg = fecReg;
	}

	public String getUsuMod() {
		return usuMod;
	}

	public void setUsuMod(String usuMod) {
		this.usuMod = usuMod;
	}

	public String getFecMod() {
		return fecMod;
	}

	public void setFecMod(String fecMod) {
		this.fecMod = fecMod;
	}

}
