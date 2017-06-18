package pe.com.hitss.sgp.core.util;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//import pe.com.hitss.base.exception.ExceptionCore;

@Component("exceptionAdvice")
public class ManagerExceptionAdvice implements ThrowsAdvice {

	@Autowired
	private static final Logger LOGGER = Logger
			.getLogger(ManagerExceptionAdvice.class);

	public void afterThrowing(Method method, Object[] args, Object target,
			Exception ex) {// throws ExceptionCore {
		LOGGER.error("Traza de Métodos: " + method.toString());
	}

}
