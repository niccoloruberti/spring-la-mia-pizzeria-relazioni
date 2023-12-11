package org.java.spring.controller;

import java.util.List;

import org.java.spring.db.pojo.Ingrediente;
import org.java.spring.db.pojo.OffertaSpeciale;
import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.serv.IngredienteService;
import org.java.spring.db.serv.OffertaSpecialeService;
import org.java.spring.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class MainController {
	
	@Autowired
	private OffertaSpecialeService offertaSpecialeService;

	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private IngredienteService ingredienteService;
	
	@GetMapping
	public String getPizzas(Model model, @RequestParam(required = false) String ricercaStr) {
		
		
		List<Pizza> pizzas = 
				ricercaStr == null ? pizzaService.findAll() : pizzaService.findByNome(ricercaStr);
		
		model.addAttribute("pizzas", pizzas);
		
		model.addAttribute("ricercaStr", ricercaStr);
		
		return "pizzas";
	}
	
	@GetMapping("/pizzas/{id}")
	public String getPizza(Model model, @PathVariable int id) {
		
		Pizza pizza = pizzaService.findById(id);
		model.addAttribute("pizza", pizza);
		
		return "showPizza";
	}
	
	@GetMapping("/pizzas/create")
	public String createPizza(Model model) {
		
		Pizza pizza = new Pizza();
		
		model.addAttribute("pizza", pizza);
		
		List<Ingrediente> ingredienti = ingredienteService.findAll();
		
		model.addAttribute("ingredienti", ingredienti);
		
		return "createPizza";
	}
	
	@PostMapping("/pizzas/create")
	public String store(@Valid @ModelAttribute("pizza") Pizza formPizza,
		BindingResult bindingResult,
		Model model) {
		
		if(bindingResult.hasErrors()) {
			return "createPizza";
		}
		
		pizzaService.save(formPizza);
		
		return "redirect:/";
		
	}
	
	@GetMapping("/pizzas/edit/{id}")
	public String editPizza(@PathVariable("id") Integer id, Model model) {
		
		Pizza pizza = pizzaService.findById(id);
		
		model.addAttribute("pizza", pizza);
		
		List<Ingrediente> ingredienti = ingredienteService.findAll();
		
		model.addAttribute("ingredienti", ingredienti);
		
		return "createPizza";
	}
	
	@PostMapping("/pizzas/edit/{id}")
	public String updatePizza(@Valid @ModelAttribute Pizza pizza, BindingResult bindingResult, Model model) {
		
		return store(pizza, bindingResult, model);
	}
	
	@PostMapping("/pizzas/delete/{id}")
	public String deletePizza(@PathVariable int id) {
		
		Pizza pizza = pizzaService.findById(id);
		
        List<OffertaSpeciale> offerte = pizza.getOfferte();
        offerte.forEach(offertaSpecialeService::delete);
		
		pizzaService.delete(pizza);
		
		return "redirect:/";
	}
	
}
