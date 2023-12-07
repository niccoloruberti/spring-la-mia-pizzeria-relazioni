package org.java.spring.controller;

import org.java.spring.db.pojo.OffertaSpeciale;
import org.java.spring.db.pojo.Pizza;
import org.java.spring.db.serv.OffertaSpecialeService;
import org.java.spring.db.serv.PizzaService;
import org.java.spring.dto.OffertaSpecialeDTO;
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
           
        model.addAttribute("offerta", offerta);
        
        return "offertaForm";
	}
	
	@PostMapping("/pizzas/{id}/createOfferta")
	public String storeOfferta(@ModelAttribute OffertaSpecialeDTO offertaSpecialeDTO,@PathVariable int id) {
		
		Pizza pizza = pizzaService.findById(id);

		OffertaSpeciale offertaSpeciale = new OffertaSpeciale(offertaSpecialeDTO.getDataInizio(), offertaSpecialeDTO.getDataFine(), offertaSpecialeDTO.getTitolo(), pizza);
		
		offertaSpecialeService.save(offertaSpeciale);
		
		return "redirect:/";
		
		
	}
	
	 @GetMapping("/pizzas/{pizza_id}/offerte/edit/{offerta_id}")
	  public String editOffertaSpeciale(Model model, @PathVariable int pizza_id, @PathVariable int offerta_id) {
	      
	       OffertaSpeciale offerta = offertaSpecialeService.findById(offerta_id);
	       
	       model.addAttribute("offerta", offerta);
	       
	       return "offertaForm";
	   }
	 
	 @PostMapping("/pizzas/{pizza_id}/offerte/edit/{offerta_id}")
	 public String updateOffertaSpeciale(@ModelAttribute OffertaSpecialeDTO offertaSpecialeDTO, @PathVariable int pizza_id, @PathVariable int offerta_id) {
	     
		 OffertaSpeciale offerta = offertaSpecialeService.findById(offerta_id);
		 
		 offerta.setDataInizio(offertaSpecialeDTO.getDataInizio());
		 offerta.setDataFine(offertaSpecialeDTO.getDataFine());
		 offerta.setTitolo(offertaSpecialeDTO.getTitolo());
		 
		offertaSpecialeService.save(offerta);
			
		return "redirect:/";
	 }
}
