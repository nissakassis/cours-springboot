package com.example.demo.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.services.IPersonService;

@Controller
@RequestMapping("/home") // chemin propre au coontroleur
// dc si on veut retourner home on fait /home/chemins ci-dessous
public class HomeController {

	@Autowired
	private IPersonService personService;
	
	//http://localhost:8080/home/index
	@GetMapping(value = "/index")
	public String goHome() {
		return "home";
	}
	
	// http://localhost:8080/home/showAll
	@GetMapping(value = "/showAll")
	public ModelAndView showAll() { // retourner des données dans  Model et ds view on retourne des vues
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home"); // donner la vue home
		mv.addObject("persons", personService.findAll()); // donner le modèle
		return mv; // retourne la vue home et en meme temps je retouirne une liste qui s'appelent personnnes
	}
	
	/// http://localhost:8080/home/showsome avec requ false et ds ca cas retourne la defaultValue
	// http://localhost:8080/home/showsome?firstName=
	@GetMapping(value = "/showSome")
	public ModelAndView showSome(@RequestParam(value = "firstName", required = false , defaultValue = "nissa") String firstName) { // retourner des données dans  Model et ds view on retourne des vues
		// on peut faire value=chaussette
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home"); // donner la vue home
		mv.addObject("persons", personService.findByFirstName(firstName)); // donner le modèle
		return mv;// retourne la vue home et en meme temps je retouirne une liste qui s'appelent personnnes
	}

	
	// http://localhost:8080/home/showAny
	// http://localhost:8080/home/showAny/...
	// http://localhost:8080/home/showAny/Jhon
		@GetMapping(value = { "/showAny/{firstName}" , "/showAny" }) // deux chemins possibles ac confitions en bas
		// declarer un argument ca l'initialise donc on peut mettre ModelAndView dans showAny() 
		// et ca evite de l'initialiser comme en haut avec new
		public ModelAndView showAny(@PathVariable Optional<String> firstName, ModelAndView modelAndView) { // retourner des données dans  Model et ds view on retourne des vues
			if (firstName.isPresent()) {
				modelAndView.addObject("firstName", firstName.get()); // jhon val par defaut
			} else {
				modelAndView.addObject("firstName", "Jhon");
			}
			modelAndView.setViewName("home");
			return modelAndView;
	
		}
	
		
		// http://localhost:8080/home/showAny2
		// http://localhost:8080/home/showAny2/...
		@GetMapping(value = {"/showAny2/{firstName}", "/showAny2"})
		// ici quand on met /showAny2 ca retourne page vide car pas possible de valeur par defaut 
	    public String showAny2(@PathVariable(value = "firstName", required = false) String firstName,
	    		ModelAndView modelAndView){
			modelAndView.addObject("firstName", firstName);
	        return "home";
	    }
		
}
