package br.com.alexandre.crm.service.entity;

import java.io.Serializable;

public class Erro implements Serializable {

	private static final long serialVersionUID = -6919556437912690402L;

	private Integer code;

	private String message;
	
	public Erro() { }

	public Erro(final Integer code, final String message) {
		this.code = code;
		this.message = message;
	}
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Erro [code=" + code + ", message=" + message + "]";
	}
	
	
}
