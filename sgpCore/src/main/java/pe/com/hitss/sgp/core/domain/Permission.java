package pe.com.hitss.sgp.core.domain;

import java.io.Serializable;

public class Permission implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer codPer;
	private String desPer;
	private Integer codOpc;
	private String key;
	private Integer nvl1;
	private Integer nvl2;
	private Integer nvl3;
	
	
	public Integer getCodPer() {
		return codPer;
	}
	public void setCodPer(Integer codPer) {
		this.codPer = codPer;
	}
	public String getDesPer() {
		return desPer;
	}
	public void setDesPer(String desPer) {
		this.desPer = desPer;
	}
	public Integer getCodOpc() {
		return codOpc;
	}
	public void setCodOpc(Integer codOpc) {
		this.codOpc = codOpc;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Integer getNvl1() {
		return nvl1;
	}
	public void setNvl1(Integer nvl1) {
		this.nvl1 = nvl1;
	}
	public Integer getNvl2() {
		return nvl2;
	}
	public void setNvl2(Integer nvl2) {
		this.nvl2 = nvl2;
	}
	public Integer getNvl3() {
		return nvl3;
	}
	public void setNvl3(Integer nvl3) {
		this.nvl3 = nvl3;
	}
	
	

	

}
