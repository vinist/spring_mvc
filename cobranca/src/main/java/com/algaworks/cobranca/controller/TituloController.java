package com.algaworks.cobranca.controller;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.algaworks.cobranca.enuns.StatusTituloEnum;
import com.algaworks.cobranca.model.Titulo;
import com.algaworks.cobranca.repository.ITitulos;

@Controller
@RequestMapping("/titulos")
public class TituloController {
	
	@Autowired
	private ITitulos iTitulos;
	
	@RequestMapping("/novo")
	public ModelAndView novo(){
		ModelAndView mv = new ModelAndView("cadastroTitulo");
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(Titulo titulo){
		iTitulos.save(titulo);
		ModelAndView mv = new ModelAndView("cadastroTitulo");
		mv.addObject("mensagem", "Título salvo com sucesso!");
		return mv;
	}
	
	@ModelAttribute("listStatusTitulo")
	public List<StatusTituloEnum> getListStatusTitulo(){
		return Arrays.asList(StatusTituloEnum.values());
	}

}
