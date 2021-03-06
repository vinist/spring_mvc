package com.algaworks.cobranca.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.cobranca.enuns.StatusTituloEnum;
import com.algaworks.cobranca.model.Titulo;
import com.algaworks.cobranca.repository.TituloFilter;
import com.algaworks.cobranca.service.CadastroTituloService;

@Controller
@RequestMapping("/titulos")
public class TituloController {
	
	private static final String CADASTRO_VIEW = "cadastroTitulo";
	
	@Autowired
	private CadastroTituloService service;
	
	@RequestMapping("/novo")
	public ModelAndView novo(){
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Titulo());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Titulo titulo, Errors errors, RedirectAttributes attributes){
		if(errors.hasErrors())
			return CADASTRO_VIEW;
		
		try {
			service.salvar(titulo);
			attributes.addFlashAttribute("mensagem", "Título salvo com sucesso!");
			return "redirect:/titulos/novo";
		} catch (IllegalArgumentException e) {
			errors.rejectValue("dataVencimento", null, "Formato de data inválido!");
			return CADASTRO_VIEW;
		}
	}
	
	@RequestMapping
	public ModelAndView pesquisar(@ModelAttribute("filtro") TituloFilter filtro){
		List<Titulo> titulos = service.filtrar(filtro);
		ModelAndView mv = new ModelAndView("pesquisaTitulos");
		mv.addObject("titulos", titulos);
		return mv;
	}
	
	@RequestMapping("{id}")
	public ModelAndView edidar(@PathVariable("id") Titulo titulo){
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(titulo);
		return mv;
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long id, RedirectAttributes attributes){
		service.excluir(id);
		attributes.addFlashAttribute("mensagem", "Título excluído com sucesso!");
		return "redirect:/titulos";
	}
	
	@RequestMapping(value = "/{id}/receber", method = RequestMethod.PUT)
	public @ResponseBody String receber(@PathVariable Long id){
		return service.receber(id);
	}
	
	@ModelAttribute("listStatusTitulo")
	public List<StatusTituloEnum> getListStatusTitulo(){
		return Arrays.asList(StatusTituloEnum.values());
	}

}
