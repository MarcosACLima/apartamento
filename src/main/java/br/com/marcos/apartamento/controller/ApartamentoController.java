package br.com.marcos.apartamento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.marcos.apartamento.entity.Apartamento;
import br.com.marcos.apartamento.repository.ApartamentoRepository;

@RestController
public class ApartamentoController {
	
	@Autowired /* IC/CD ou CDI - Injeção de dependencias */
	private ApartamentoRepository apartamentoRepository;
	
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
	@GetMapping(value = "listatodos")
	@ResponseBody // Retorna os dados para o corpo 
	public ResponseEntity<List<Apartamento>> listaApartamentos() {
		List<Apartamento> apartamentos = apartamentoRepository.findAll(); // executar consulta no banco de dados
				
		return new ResponseEntity<List<Apartamento>>(apartamentos, HttpStatus.OK); // Retornar a lista em JSON
	}
	
	
	@PostMapping(value = "salvar") // mapeia a URL
	@ResponseBody // descriçao da resposta
	public ResponseEntity<Apartamento> salvar(@RequestBody Apartamento apartamento) { // Recebe os dados para salvar
		Apartamento apartamento2 = apartamentoRepository.save(apartamento);
		
		return new ResponseEntity<Apartamento>(apartamento2, HttpStatus.CREATED);
		
	}
	
	

}
