package pe.com.hitss.sgp.core.domain;

import java.io.Serializable;
import java.util.Date;

public class Idea implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idIdea;
	private String codigoIdea;
	private String descIdea;
	private String informacion;
	private int estado;
	private String usuReg;
	private Date fecReg;
	private String usuMod;
	private Date fecMod;

	public Long getIdIdea() {
		return idIdea;
	}

	public void setIdIdea(Long idIdea) {
		this.idIdea = idIdea;
	}

	public String getCodigoIdea() {
		return codigoIdea;
	}

	public void setCodigoIdea(String codigoIdea) {
		this.codigoIdea = codigoIdea;
	}

	public String getDescIdea() {
		return descIdea;
	}

	public void setDescIdea(String descIdea) {
		this.descIdea = descIdea;
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

}
