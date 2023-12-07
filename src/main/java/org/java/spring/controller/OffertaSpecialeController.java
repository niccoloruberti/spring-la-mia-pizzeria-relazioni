package org.java.spring.controller;

import org.java.spring.db.pojo.OffertaSpeciale;
import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.serv.OffertaSpecialeService;
import org.java.spring.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
        
        model.addAttribute("offerta", offerta);
        
        return "offertaForm";
	}
}
