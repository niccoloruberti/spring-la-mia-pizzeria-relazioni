package org.java.spring.controller;

import java.util.List;
import org.java.spring.db.pojo.Ingrediente;
import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.serv.IngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class IngredienteController {

	@Autowired
	private IngredienteService ingredienteService;
	
	@GetMapping ("/ingredienti")
	public String getIngredienti(Model model) {
		
		List<Ingrediente> listaIngredienti  = ingredienteService.findAll();
		
		model.addAttribute("listaIngredienti", listaIngredienti);
		
		return "ingredienti";
	}
	
	@GetMapping("/ingredienti/create")
	public String createIngrediente(Model model) {
		
		Ingrediente ingrediente = new Ingrediente();
		
		model.addAttribute("ingrediente", ingrediente);
		
		return "ingredienteForm";
	}
	
	@PostMapping("/ingredienti/create")
	public String store(@ModelAttribute Ingrediente formIngrediente,
		Model model) {
	
		ingredienteService.save(formIngrediente);
		
		return "redirect:/ingredienti";
		
	}
	
	@PostMapping("/ingredienti/delete/{id}")
	public String deleteIngrediente(@PathVariable int id) {
		
		Ingrediente ingrediente = ingredienteService.findById(id);
		
	    for (Pizza pizza : ingrediente.getPizzas()) {
	        pizza.rimuoviIngrediente(ingrediente);
	    }
		
		ingredienteService.delete(ingrediente);
		
		return "redirect:/ingredienti";
	}
	
}
