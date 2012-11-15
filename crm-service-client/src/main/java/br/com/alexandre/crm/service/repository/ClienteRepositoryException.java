package br.com.alexandre.crm.service.repository;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.cxf.transport.http.HTTPException;

import br.com.alexandre.crm.service.client.CustomerServiceException;

public class ClienteRepositoryException extends RuntimeException {

	private static final long serialVersionUID = -4580740310798680535L;

	private Integer code;
	
	public ClienteRepositoryException(final Integer code, final String message) {
		super(message);
		this.code = code;		
	}
	
	public ClienteRepositoryException(final Integer code, final String message, final Throwable cause) {
		super(message, cause);
		this.code = code;
	}
	
	public Integer getCode() {
		return this.code;
	}
 
	public static ClienteRepositoryException build(final Exception e) {
		if (e instanceof CustomerServiceException) {
			return new ClienteRepositoryException(2001, e.getMessage(), e);
		}

		final Throwable rootCause = ExceptionUtils.getRootCause(e);
		
		if (rootCause instanceof ConnectException) {
			return new ClienteRepositoryException(2002,"Erro de conexao com o WebService", e);
		}
		
		if (rootCause instanceof SocketTimeoutException) {
			return new ClienteRepositoryException(2003, "Timeout", e);
		}
		if (rootCause instanceof HTTPException) {
			return new ClienteRepositoryException(2004, "Nao foi possivel se comunicar com o recurso", e);
		}
		
		return new ClienteRepositoryException(2005, "Erro generico", e);
	}
}
