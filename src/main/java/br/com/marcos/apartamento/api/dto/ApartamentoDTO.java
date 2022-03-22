package br.com.marcos.apartamento.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApartamentoDTO {
	
	private Long id;
	private Integer numero;
	private String estado;
		
}
