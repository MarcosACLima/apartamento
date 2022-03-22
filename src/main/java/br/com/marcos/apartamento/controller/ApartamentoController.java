package br.com.marcos.apartamento.controller;

import java.io.Serializable;
import java.util.List;

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

import br.com.marcos.apartamento.entity.Apartamento;
import br.com.marcos.apartamento.exception.RegraNegocioException;
import br.com.marcos.apartamento.repository.ApartamentoRepository;
import br.com.marcos.apartamento.service.ApartamentoService;

@RestController
public class ApartamentoController {
	
	@Autowired /* IC/CD ou CDI - Injeção de dependencias */
	private ApartamentoRepository apartamentoRepository;
	
	private ApartamentoService apartamentoService;
	
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
	public ResponseEntity salvar(@RequestBody Apartamento apartamento) { // Recebe os dados para salvar
		try {
			Apartamento apartamentoSalvo = apartamentoService.salvar(apartamento);
			return new ResponseEntity(apartamentoSalvo, HttpStatus.CREATED);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}	
	}
	
	@PutMapping(value = "produto") // mapeia a URL
	@ResponseBody // descriçao da resposta
	public ResponseEntity<?> atualizar(@RequestBody Apartamento apartamento) { // Recebe os dados para salvar
		
		if (apartamento.getId() == null) {
			return new ResponseEntity<String>("ID não foi informado para atualizacao", HttpStatus.OK);
		}
		
		Apartamento apartamento2 = apartamentoRepository.saveAndFlush(apartamento);
		
		return new ResponseEntity<Apartamento>(apartamento2, HttpStatus.OK);
	}
	
	
	@DeleteMapping(value = "produto") // mapeia a URL
	@ResponseBody // descriçao da resposta
	public ResponseEntity<String> delete(@RequestParam Long id) { // Recebe os dados para deletar
		apartamentoRepository.deleteById(id);
		
		return new ResponseEntity<String>("Apartamento deletado com sucesso!", HttpStatus.OK);
	}

}
