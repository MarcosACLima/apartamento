package br.com.marcos.apartamento.model.entity;

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

import br.com.marcos.apartamento.model.enums.EstadoApartamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Validated
@Entity
@Table(name = "apartamento")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Data // Getter, Setter, toString, EqualsAndHashCode
@NoArgsConstructor // construtor sem argumentos
@AllArgsConstructor
@Builder // para usar Builder tem q existir um construtor para todos os argumentos
public class Apartamento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer numero;
	
	@Enumerated(value = EnumType.STRING) // Enumeracao por tipo String
	private EstadoApartamento estado;
	
}
