package pe.com.hitss.sgp.core.domain;

import java.io.Serializable;
import java.util.Date;

public class DocAdjuntosRegAct implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idDocumento;
	private Long idRegistroActividad;
	private String nombreDocumento;
	private byte[] documento;
	private String tipoDocumento;
	private int estado;
	private String usuReg;
	private Date fecReg;
	private String usuMod;
	private Date fecMod;
	
	private String idAux;

	public Long getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(Long idDocumento) {
		this.idDocumento = idDocumento;
	}

	public Long getIdRegistroActividad() {
		return idRegistroActividad;
	}

	public void setIdRegistroActividad(Long idRegistroActividad) {
		this.idRegistroActividad = idRegistroActividad;
	}

	public String getNombreDocumento() {
		return nombreDocumento;
	}

	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}

	public byte[] getDocumento() {
		return documento;
	}

	public void setDocumento(byte[] documento) {
		this.documento = documento;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
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

	public String getIdAux() {
		return idAux;
	}

	public void setIdAux(String idAux) {
		this.idAux = idAux;
	}
}
