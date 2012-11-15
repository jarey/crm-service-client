package br.com.alexandre.crm.service.entity;

import java.io.Serializable;
import java.util.Calendar;

public class Cliente implements Serializable {

	private static final long serialVersionUID = 955517037688361903L;
	
	private final String nome;
	private final String sexo;
	private final Calendar dataNascimento;
	private final String telefone;
	private final Endereco endereco;
	private final String email;
	
	public Cliente(final String nome, final String sexo, final Calendar dataNascimento, final String telefone, final Endereco endereco, final String email) {
		this.nome = nome;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
		this.telefone = telefone;
		this.endereco = endereco;
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public String getSexo() {
		return sexo;
	}
	public Calendar getDataNascimento() {
		return dataNascimento;
	}
	public String getTelefone() {
		return telefone;
	}
	
	public Integer getIdade() {
		Integer idade = null;
		if (dataNascimento != null) {
			final Calendar agora = Calendar.getInstance();
			long subtracao = agora.get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR);
			if ((agora.get(Calendar.MONTH) > dataNascimento.get(Calendar.MONTH)) ||
				(agora.get(Calendar.MONTH) == dataNascimento.get(Calendar.MONTH) &&
				(agora.get(Calendar.DATE) >= dataNascimento.get(Calendar.DATE)))) { 
				idade = new Integer((int) subtracao);
			} else {
				idade = new Integer((int) (subtracao-1));
			}			
		}
		return idade;
	}
	
	public Endereco getEndereco() {
		return this.endereco;
	}
	
	public String getEmail() {
		return this.email;
	}
}
