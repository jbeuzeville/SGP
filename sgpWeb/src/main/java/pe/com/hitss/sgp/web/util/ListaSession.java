package pe.com.hitss.sgp.web.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("listaSession")
@Scope("application")
public class ListaSession {

	public static Map<String, HttpSession> listaUsuariosSesion = new HashMap<String, HttpSession>();
	public static List<Boolean> listaQuartz = new ArrayList<Boolean>();
	public static Stack<String> listaUrl = new Stack<String>();
	
	static{
		listaQuartz.add(true);
	}
	
}
