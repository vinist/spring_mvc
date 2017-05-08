package com.algaworks.cobranca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.algaworks.cobranca.enuns.StatusTituloEnum;
import com.algaworks.cobranca.model.Titulo;
import com.algaworks.cobranca.repository.ITitulos;
import com.algaworks.cobranca.repository.TituloFilter;

@Service
public class CadastroTituloService {
	
	@Autowired
	private ITitulos iTitulos;
	
	public void salvar(Titulo titulo) {
		try {
			iTitulos.save(titulo);
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Formato de data inválido!");
		}
	}

	public void excluir(Long id) {
		iTitulos.delete(id);
		
	}

	public String receber(Long id) {
		Titulo titulo = iTitulos.findOne(id);
		titulo.setStatus(StatusTituloEnum.RECEBIDO);
		iTitulos.save(titulo);
		return titulo.getStatus().getLabel();
	}

	public List<Titulo> filtrar(TituloFilter filtro) {
		String descricao = filtro.getDescricao() == null ? "%" : filtro.getDescricao();
		return iTitulos.findByDescricaoContaining(descricao);
	}

}
