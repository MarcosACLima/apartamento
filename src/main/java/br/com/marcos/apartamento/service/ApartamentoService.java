package br.com.marcos.apartamento.service;

import java.util.List;
import java.util.Optional;

import br.com.marcos.apartamento.model.entity.Apartamento;
import br.com.marcos.apartamento.model.enums.EstadoApartamento;

public interface ApartamentoService {
	
	Apartamento salvar(Apartamento apartamento);
	
	Apartamento atualizar(Apartamento apartamento);
	
	List<Apartamento> listar();
	
	void deletar(Apartamento apartamento);
	
	void atualizarEstado(Apartamento apartamento, EstadoApartamento estado);

	Optional<Apartamento> obterPorId(Long id);
	
//	@Autowired
//	private ApartamentoRepository apartamentoRepository;
//	
//	@Transactional(readOnly =  true) // avisar que metodo e somente de leitura
//	public List<ApartamentoDTO> findAll(Pageable pageable) {
//		Page<Movie> result = repository.findAll(pageable);
//		Page<MovieDTO> page = result.map(x -> new MovieDTO(x)); // copnverter Page para um MovieDTO, map aplica a funcao em cada registro da lista
//		return page;
//	}

}
