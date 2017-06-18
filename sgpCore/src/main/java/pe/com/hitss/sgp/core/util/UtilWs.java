package pe.com.hitss.sgp.core.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//import pe.com.jpahibernateprime.demo.ws.ws.SeguridadTypeProxy;

@Component
public class UtilWs {

	@Autowired
	private CoreMessageUtil messageUtil;

	/*private SeguridadTypeProxy seguridadTypeProxy;

	public SeguridadTypeProxy getSeguridadTypeProxy() {
		if (seguridadTypeProxy == null) {
			seguridadTypeProxy = new SeguridadTypeProxy();
		}
		String endPoint = obtenerUrlWebServices()
				+ messageUtil.getMessage("proxy.urlSeguridadService", null);
		seguridadTypeProxy.setEndpoint(endPoint);
		return seguridadTypeProxy;
	}*/
	
	@SuppressWarnings("all")
	private String obtenerUrlWebServices() {
		String ip = messageUtil.getMessage("proxy.ip", null);
		String puerto = messageUtil.getMessage("proxy.puerto", null);
		String protocolo = messageUtil.getMessage("proxy.protocolo", null);
		return (protocolo + "://" + ip + ":" + puerto + "/");
	}
}