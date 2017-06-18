package pe.com.hitss.sgp.core.domain;

import java.io.Serializable;

import pe.com.hitss.sgp.core.util.Resultado;

public class Opciones implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer idOpcion;
	private String descOpcion;
	private String informacion;
	private Integer nivel;
	private Integer idOpcionPadre;
	private Integer orden;
	
	private Resultado resultado;
	
	public Integer getIdOpcion() {
		return idOpcion;
	}
	public void setIdOpcion(Integer idOpcion) {
		this.idOpcion = idOpcion;
	}
	public String getDescOpcion() {
		return descOpcion;
	}
	public void setDescOpcion(String descOpcion) {
		this.descOpcion = descOpcion;
	}
	public String getInformacion() {
		return informacion;
	}
	public void setInformacion(String informacion) {
		this.informacion = informacion;
	}
	public Integer getNivel() {
		return nivel;
	}
	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}
	
	public Integer getOrden() {
		return orden;
	}
	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	public Resultado getResultado() {
		return resultado;
	}
	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}
	public Integer getIdOpcionPadre() {
		return idOpcionPadre;
	}
	public void setIdOpcionPadre(Integer idOpcionPadre) {
		this.idOpcionPadre = idOpcionPadre;
	}
	
	
	

}
