package pe.com.hitss.sgp.core.domain;

import java.io.Serializable;

public class TipoHorario implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idTipoHorario;
	private String descTipoHorario;
	private String informacion;
	private int estado;
	private String usuReg;
	private String fecReg;
	private String usuMod;
	private String fecMod;
	
	private int flgDocumentos;

	public int getIdTipoHorario() {
		return idTipoHorario;
	}

	public void setIdTipoHorario(int idTipoHorario) {
		this.idTipoHorario = idTipoHorario;
	}

	public String getDescTipoHorario() {
		return descTipoHorario;
	}

	public void setDescTipoHorario(String descTipoHorario) {
		this.descTipoHorario = descTipoHorario;
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

	public int getFlgDocumentos() {
		return flgDocumentos;
	}

	public void setFlgDocumentos(int flgDocumentos) {
		this.flgDocumentos = flgDocumentos;
	}

}
