package br.com.marcos.apartamento.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.marcos.apartamento.exception.RegraNegocioException;
import br.com.marcos.apartamento.model.entity.Apartamento;
import br.com.marcos.apartamento.model.enums.EstadoApartamento;
import br.com.marcos.apartamento.model.repository.ApartamentoRepository;
import br.com.marcos.apartamento.service.ApartamentoService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ApartamentoServiceImpl implements ApartamentoService {

	private ApartamentoRepository apartamentoRepository;
	
	@Override
	@Transactional
	public Apartamento salvar(Apartamento apartamento) {	
		validar(apartamento);
		apartamento.setEstado(EstadoApartamento.LIVRE);
		return apartamentoRepository.save(apartamento);
	}

	@Override
	@Transactional
	public Apartamento atualizar(Apartamento apartamento) {
		Objects.requireNonNull(apartamento.getId());  // tem q passar um apartamento com Id
		return apartamentoRepository.save(apartamento);
	}

	@Override
	@Transactional
	public List<Apartamento> listar() {
		return apartamentoRepository.findAll();
	}

	@Override
	@Transactional
	public void deletar(Apartamento apartamento) {
		Objects.requireNonNull(apartamento.getId());
		apartamentoRepository.delete(apartamento);
	}

	@Override
	public void atualizarEstado(Apartamento apartamento, EstadoApartamento estado) {
		apartamento.setEstado(estado);
		atualizar(apartamento);
	}

	@Override
	public Optional<Apartamento> obterPorId(Long id) {
		return apartamentoRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Apartamento> buscar(Apartamento apartamentoFiltro) {
		Example example = Example.of( apartamentoFiltro, 
				ExampleMatcher.matching()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING ) );
		
		return apartamentoRepository.findAll(example);
	}

	@Override
	public void validar(Apartamento apartamento) {
		
		Apartamento numeroAp = apartamentoRepository.findByNumero(apartamento.getNumero());
		
		if (apartamento == null && apartamento.getNumero() != numeroAp.getNumero() ) {
			throw new RegraNegocioException("Informe um Número de apartamento valido.");
		}
		
	}

}
