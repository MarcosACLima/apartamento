package br.com.marcos.apartamento.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.validation.annotation.Validated;




@Validated
@Entity
@Table(name = "apartamento")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Apartamento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer numero;
	
	@Enumerated(value = EnumType.STRING) // Enumeracao por tipo String
	private EstadoApartamento estado;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public EstadoApartamento getEstado() {
		return estado;
	}

	public void setEstado(EstadoApartamento estado) {
		this.estado = estado;
	}

	

}
