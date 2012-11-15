package br.com.alexandre.crm.service.entity;

import java.io.Serializable;

public class Endereco implements Serializable {
	
	private static final long serialVersionUID = 3444403979760139560L;
	
	private final String cidade;
	private final String rua;
	private final String cep;

	public Endereco(final String cidade, final String rua, final String cep) {
		this.cidade = cidade;
		this.rua = rua;
		this.cep = cep;
	}
	public String getCidade() {
		return cidade;
	}
	public String getRua() {
		return rua;
	}
	public String getCep() {
		return cep;
	}
	
	
}
