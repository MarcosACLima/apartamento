package br.com.marcos.apartamento.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.marcos.apartamento.entity.Apartamento;
import br.com.marcos.apartamento.exception.RegraNegocioException;
import br.com.marcos.apartamento.repository.ApartamentoRepository;

@Service
public class ApartamentoService {

	@Autowired
	private ApartamentoRepository apartamentoRepository;

	public void numeroUnico(Integer numero) {
		boolean existe = apartamentoRepository.existsByNumero(numero);

		if (existe) {
			throw new RegraNegocioException("Já existe um usuário cadastro com este email.");
		}
	}
	
	@Transactional
	public Apartamento salvar(Apartamento apartamento) {
		numeroUnico(apartamento.getNumero());
		return apartamentoRepository.save(apartamento);
	}

}
