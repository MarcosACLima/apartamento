package br.com.marcos.apartamento.dto;

import br.com.marcos.apartamento.model.entity.Apartamento;

public class ApartamentoDTO {
	
	private Long id;
	private Integer numero;
	private String estado;
	
	public ApartamentoDTO() {
		
	}
	
	public ApartamentoDTO(Long id, Integer numero, String estado) {
		this.id = id;
		this.numero = numero;
		this.estado = estado;
	}
	
	public ApartamentoDTO(Apartamento apartamento) {
		id = apartamento.getId();
		numero = apartamento.getNumero();
		estado = apartamento.getEstado().toString();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

}
