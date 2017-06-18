package pe.com.hitss.sgp.core.domain;

import java.io.Serializable;

public class ItemList implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer fatherId;
	private String codigo;
	private String descripcion;

	public ItemList() {

	}

	public ItemList(Integer id) {
		super();
		this.id = id;
	}

	public ItemList(Integer id, String codigo, String descripcion) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getFatherId() {
		return fatherId;
	}

	public void setFatherId(Integer fatherId) {
		this.fatherId = fatherId;
	}

	@Override
	public String toString() {
		return "ItemList [id=" + id + ", fatherId=" + fatherId + ", codigo="
				+ codigo + ", descripcion=" + descripcion + "]";
	}

}
