package com.financa.financas.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.financa.financas.exception.RegrasExpections;
import com.financa.financas.model.entity.Lancamento;
import com.financa.financas.model.enums.StatusLancamento;
import com.financa.financas.model.enums.TipoLancamento;
import com.financa.financas.model.repository.LancamentoRepository;
import com.financa.financas.service.LancamentoService;

@Service
public class LancamentoServiceImpl implements LancamentoService {

	private LancamentoRepository repository;
	
	public LancamentoServiceImpl(LancamentoRepository repository) {
		this.repository = repository;
	}
	
	@Override
	@Transactional
	public Lancamento salvar(Lancamento novoLancamento) {
		validarLancamento(novoLancamento);
		novoLancamento.setStatus(StatusLancamento.PENDENTE);
		return repository.save(novoLancamento);
	}

	@Override
	@Transactional
	public Lancamento atualizar(Lancamento atualizarLancamento) {
		validarLancamento(atualizarLancamento);
		Objects.requireNonNull(atualizarLancamento.getId());
		return repository.save(atualizarLancamento);
	}

	@Override
	@Transactional
	public void deletar(Lancamento deletarLancamento) {
		Objects.requireNonNull(deletarLancamento.getId());
		repository.delete(deletarLancamento);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Lancamento> buscar(Lancamento lancamentoFiltro) {
		Example queryBuscar = Example.of(lancamentoFiltro, 
				ExampleMatcher.matching().withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING));
		
		return repository.findAll(queryBuscar);
	}

	@Override
	public void atualizarStatus(Lancamento lancamento, StatusLancamento status) {
		lancamento.setStatus(status);
		atualizar(lancamento);
		
	}

	@Override
	public void validarLancamento(Lancamento lancamento) {
		
		if(lancamento.getDescricao() == null | lancamento.getDescricao().trim().equals("")) {
			throw new RegrasExpections("Informe uma descrição");
		}
		
		if(lancamento.getMes() == null | lancamento.getMes() < 1 || lancamento.getMes() > 12) {
			throw new RegrasExpections("Informe um mês válido");
		}
		
		if(lancamento.getAno() == null | lancamento.getAno().toString().length() != 4) {
			throw new RegrasExpections("Informe um ano válido");
		}
		
		if(lancamento.getUsuario() == null | lancamento.getUsuario().getId() == null) {
			throw new RegrasExpections("Informe um Usuário");
		}
		
		if(lancamento.getValor() == null || lancamento.getValor().compareTo(BigDecimal.ZERO) < 1) {
			throw new RegrasExpections("Informe um Valor maior");
		}
		
		if(lancamento.getTipo() == null) {
			throw new RegrasExpections("Informe um tipo de Lancamento");
		}
		
	}

	@Override
	public Optional<Lancamento> obterLancamentoPorId(Long id) {
		return repository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public BigDecimal obterSaldoPorUsuario(Long id) {
		BigDecimal receitas = repository.obterSaldoPorTipoLancamentoEUsuario(id, TipoLancamento.RECEITA);
		BigDecimal despesas = repository.obterSaldoPorTipoLancamentoEUsuario(id, TipoLancamento.DESPESA);
		
		if(receitas == null) {
			receitas = BigDecimal.ZERO;
		}
		if(despesas == null) {
			despesas = BigDecimal.ZERO;
		}
		
		return receitas.subtract(despesas);
	}
	
	

	
}
