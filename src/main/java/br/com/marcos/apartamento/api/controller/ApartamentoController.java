package br.com.marcos.apartamento.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.marcos.apartamento.api.dto.ApartamentoDTO;
import br.com.marcos.apartamento.api.dto.AtualizarEstadoDTO;
import br.com.marcos.apartamento.exception.RegraNegocioException;
import br.com.marcos.apartamento.model.entity.Apartamento;
import br.com.marcos.apartamento.model.enums.EstadoApartamento;
import br.com.marcos.apartamento.service.ApartamentoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/api")
public class ApartamentoController {
	
	private final ApartamentoService apartamentoService;
	
	/* Primeiro metodo de API */
	@GetMapping(value = "apartamento")
	@ResponseBody // Retorna os dados para o corpo 
	public ResponseEntity<List<Apartamento>> listaApartamentos() {
		List<Apartamento> apartamentos = apartamentoService.listar(); // executar consulta no banco de dados
				
		return ResponseEntity.ok(apartamentos); // Retornar a lista em JSON
	}
	
	
	@PostMapping(value = "apartamento") // mapeia a URL
	@ResponseBody // descriçao da resposta
	public ResponseEntity salvar(@RequestBody ApartamentoDTO apartamentoDTO) { // Recebe os dados para salvar
		try {
			Apartamento apartamento = converter(apartamentoDTO);
			apartamento = apartamentoService.salvar(apartamento);
			return new ResponseEntity(apartamento, HttpStatus.CREATED); 
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}	
	}
	
	@PutMapping(value = "produto/{id}") // mapeia a URL
	@ResponseBody // descriçao da resposta
	public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody ApartamentoDTO apartamentoDTO) { // Recebe os dados para salvar
		
		return apartamentoService.obterPorId(id).map( entity -> {
			try {
				Apartamento apartamento = converter(apartamentoDTO);
				apartamento.setId(entity.getId());
				apartamentoService.atualizar(apartamento);
				return ResponseEntity.ok(apartamento);
			} catch (RegraNegocioException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}	
		}).orElseGet( () -> 
			new ResponseEntity("Apartamento não encontrado na base de Dados.", HttpStatus.BAD_REQUEST) );
	}
	
	
	@DeleteMapping(value = "produto/{id}") // mapeia a URL
	@ResponseBody // descriçao da resposta
	public ResponseEntity<String> delete(@PathVariable("id") Long id) { // Recebe os dados para deletar
		return apartamentoService.obterPorId(id).map( entidade -> {
			apartamentoService.deletar(entidade);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}).orElseGet( () -> 
			new ResponseEntity("Apartamento deletado com sucesso!", HttpStatus.BAD_REQUEST) );
	}
	
	@PutMapping("produto/{id}/atualizar-estado")
	public ResponseEntity atualizarEstado( @PathVariable("id") Long id, @RequestBody AtualizarEstadoDTO dto ) {
		return apartamentoService.obterPorId(id).map( entity -> {
			EstadoApartamento estadoSelecionado = EstadoApartamento.valueOf(dto.getEstado()); // valueOf buscar a String do ENUM referente ao argumento passado 
			
			if(estadoSelecionado == null) {
				return ResponseEntity.badRequest().body("Não foi possível atualizar o estado do Apartamento, envie um estado válido.");
			}
			
			try {
				entity.setEstado(estadoSelecionado);
				apartamentoService.atualizar(entity);
				return ResponseEntity.ok(entity);
			} catch (Exception e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
			
		}).orElseGet( () -> 
			new ResponseEntity("Apartamento não encontrado na base de Dados.", HttpStatus.BAD_REQUEST) );	
	}
	
	private Apartamento converter(ApartamentoDTO apartamentoDTO) {
		Apartamento apartamento = new Apartamento();
		apartamento.setId(apartamentoDTO.getId());
		apartamento.setNumero(apartamentoDTO.getNumero());
		
		if(apartamentoDTO.getEstado() != null) {
			apartamento.setEstado(EstadoApartamento.valueOf(apartamentoDTO.getEstado())); // valeOf recebe uma String que vai representar uma corresponde da ENUM 
		}
		
		return apartamento;
	}
	

}
