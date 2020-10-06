package com.financa.financas.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.financa.financas.model.entity.Lancamento;
import com.financa.financas.model.enums.StatusLancamento;

public interface LancamentoService {

	Lancamento salvar(Lancamento novoLancamento);
	
	Lancamento atualizar(Lancamento atualizarLancamento);
	
	void deletar(Lancamento deletarLancamento);
	
	List<Lancamento> buscar(Lancamento lancamentoFiltro);
	
	void atualizarStatus(Lancamento lancamento, StatusLancamento status);
	
	void validarLancamento(Lancamento lancamento);
	
	Optional<Lancamento> obterLancamentoPorId(Long id);
	
	BigDecimal obterSaldoPorUsuario(Long id);
}
