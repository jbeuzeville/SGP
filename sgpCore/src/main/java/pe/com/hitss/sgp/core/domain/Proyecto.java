package pe.com.hitss.sgp.core.domain;

import java.io.Serializable;
import java.util.Date;

public class Proyecto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idProyecto;
	private String codigoProyecto;
	private String descProyecto;
	private String nombreProyecto;
	private String informacion;
	private Idea idea;
	private int idEstado;
	private String descEstado;
	private int flgActividad;
	private int estado;
	private String usuReg;
	private Date fecReg;
	private String usuMod;
	private Date fecMod;
	private Usuario usuarioGerente;
	private Usuario usuarioLider;

	
	public Proyecto() {
		super();
		this.idea = new Idea();
		this.usuarioGerente = new Usuario();
		this.usuarioLider = new Usuario();
	}

	public Long getIdProyecto() {
		return idProyecto;
	}

	public void setIdProyecto(Long idProyecto) {
		this.idProyecto = idProyecto;
	}

	public String getCodigoProyecto() {
		return codigoProyecto;
	}

	public void setCodigoProyecto(String codigoProyecto) {
		this.codigoProyecto = codigoProyecto;
	}

	public String getDescProyecto() {
		return descProyecto;
	}

	public void setDescProyecto(String descProyecto) {
		this.descProyecto = descProyecto;
	}

	public String getNombreProyecto() {
		return nombreProyecto;
	}

	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}

	public String getInformacion() {
		return informacion;
	}

	public void setInformacion(String informacion) {
		this.informacion = informacion;
	}

	public Idea getIdea() {
		return idea;
	}

	public void setIdea(Idea idea) {
		this.idea = idea;
	}

	public int getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	public String getDescEstado() {
		return descEstado;
	}

	public void setDescEstado(String descEstado) {
		this.descEstado = descEstado;
	}

	public int getFlgActividad() {
		return flgActividad;
	}

	public void setFlgActividad(int flgActividad) {
		this.flgActividad = flgActividad;
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

	public Usuario getUsuarioGerente() {
		return usuarioGerente;
	}

	public void setUsuarioGerente(Usuario usuarioGerente) {
		this.usuarioGerente = usuarioGerente;
	}

	public Usuario getUsuarioLider() {
		return usuarioLider;
	}

	public void setUsuarioLider(Usuario usuarioLider) {
		this.usuarioLider = usuarioLider;
	}

}
