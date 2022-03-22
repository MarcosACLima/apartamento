package br.com.marcos.apartamento.api.controller;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.marcos.apartamento.api.dto.ApartamentoDTO;
import br.com.marcos.apartamento.exception.RegraNegocioException;
import br.com.marcos.apartamento.model.entity.Apartamento;
import br.com.marcos.apartamento.model.enums.EstadoApartamento;
import br.com.marcos.apartamento.model.repository.ApartamentoRepository;
import br.com.marcos.apartamento.service.ApartamentoService;

@RestController
//@RequestMapping("/api")
public class ApartamentoController {
	
	@Autowired /* IC/CD ou CDI - Injeção de dependencias */
	private ApartamentoRepository apartamentoRepository;
	
	private ApartamentoService apartamentoService;
	
	private ApartamentoController(ApartamentoService apartamentoService) {
		this.apartamentoService = apartamentoService;
	}
	
	// http://localhost:8080/crud/mostrarnome/digitar string name
	@RequestMapping(value = "/mostrarnome/{name}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String greetingsText(@PathVariable String name) {
		return "CRUD Spring Boot API " + name + "!";
	}
	
	@RequestMapping(value = "/olamundo/{msg}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String retornarOlaMundo(@PathVariable int numero) {
		
		Apartamento apartamento = new Apartamento();
		apartamento.setNumero(numero); // set nome
		
		apartamentoRepository.save(apartamento); // gravar no banco de dados
		
		return "Ola mundo " + numero;
	}
	
	/* Primeiro metodo de API */
	@GetMapping(value = "apartamento")
	@ResponseBody // Retorna os dados para o corpo 
	public ResponseEntity<List<Apartamento>> listaApartamentos() {
		List<Apartamento> apartamentos = apartamentoRepository.findAll(); // executar consulta no banco de dados
				
		return new ResponseEntity<List<Apartamento>>(apartamentos, HttpStatus.OK); // Retornar a lista em JSON
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
	
	
	@DeleteMapping(value = "produto") // mapeia a URL
	@ResponseBody // descriçao da resposta
	public ResponseEntity<String> delete(@RequestParam Long id) { // Recebe os dados para deletar
		apartamentoRepository.deleteById(id);
		
		return new ResponseEntity<String>("Apartamento deletado com sucesso!", HttpStatus.OK);
	}
	
	private Apartamento converter(ApartamentoDTO apartamentoDTO) {
		Apartamento apartamento = new Apartamento();
		apartamento.setId(apartamentoDTO.getId());
		apartamento.setNumero(apartamentoDTO.getNumero());
		apartamento.setEstado(EstadoApartamento.valueOf(apartamentoDTO.getEstado())); // valeOf recebe uma String que vai representar uma corresponde da ENUM 
	
		return apartamento;
	}
	

}
