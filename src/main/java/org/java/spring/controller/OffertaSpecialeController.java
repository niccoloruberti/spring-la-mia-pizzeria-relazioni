package org.java.spring.controller;

import java.util.List;

import org.java.spring.db.pojo.OffertaSpeciale;
import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.serv.OffertaSpecialeService;
import org.java.spring.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class OffertaSpecialeController {
	
	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private OffertaSpecialeService offertaSpecialeService;
	
	@GetMapping("/pizzas/{id}/createOfferta")
	public String getOffertaForm(Model model, @PathVariable int id) {
		
        Pizza pizza = pizzaService.findById(id);
        
        OffertaSpeciale offerta = new OffertaSpeciale();
        
        offerta.setPizza(pizza);
        
        List<OffertaSpeciale> listaOfferte = pizza.getOfferte();
        
        model.addAttribute("offerta", offerta);
        
        model.addAttribute("listaOfferte", listaOfferte);
        
        return "offertaForm";
	}
	
	@PostMapping("/pizzas/{id}/createOfferta")
	public String storeOfferta(@Valid @ModelAttribute("offerta") OffertaSpeciale formOfferta, Model model ,@PathVariable int id) {
		
		Pizza pizza = pizzaService.findById(id);
		
		OffertaSpeciale offertaSpeciale = new OffertaSpeciale(formOfferta.getDataInizio(), formOfferta.getDataFine(), formOfferta.getTitolo(), pizza);
		
		offertaSpecialeService.save(offertaSpeciale);
		
		return "redirect:/";
		
	}
}
