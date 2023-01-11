package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entities.Person;
import com.example.demo.repository.PersonRepository;
import com.example.demo.services.IPersonService;
import com.example.demo.services.IService;

// Fichier qui mappe sur des chemins et qui va nous renvoyer
// des reponses au format JSon, XML
@RestController
@CrossOrigin("*") // Autorise la communication entre application (Backend <=> frontend)
//et "*" autorise touttttt
// @CrossOrigin("http://localhost:4200/")  alors autoriqse ce chemin seulement

@RequestMapping("api/persons") // chemin pour atteindre le controlleur , chemin qui redirige vers le controlleur

public class PersonRestController {

	//avec Autowired on recupère le bean (classe) injecté
	@Autowired // utilisee pr l'injection de dependances personRepository
	//private PersonRepository personRepository;
	private IPersonService personService;  // nom dans PersonServiceImpl @Service("personService")
	
	// api/persons
	@GetMapping() // "/persons" car on a rajouté ds le chemin en haut
	public ResponseEntity<List<Person>> getAll() {
		return new ResponseEntity<List<Person>>(personService.findAll(), HttpStatus.OK);
	}

	// api/persons
	@PostMapping() 
	public ResponseEntity<Person> create(@RequestBody Person person) {
		return new ResponseEntity<Person>(personService.saveOrUpdate(person), HttpStatus.CREATED);
	}

	// api/persons
	@GetMapping("/{id}") // /persons
	public ResponseEntity<Person> getById(@PathVariable long id) {
		return personService.findById(id).map((p) -> {
			return new ResponseEntity<Person>(p, HttpStatus.OK);
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				"La personne avec l'id " + id + "n'existe pas"));
	}

	// api/persons/id
	@PutMapping("/{id}")
	public ResponseEntity<Person> editById(@PathVariable long id, @RequestBody Person person) {
		return personService.findById(id).map((p) -> {
			p.setFirstName(person.getFirstName());
			p.setLastName(person.getLastName());
			p.setAge(person.getAge());
			personService.saveOrUpdate(person);
			return new ResponseEntity<Person>(p, HttpStatus.OK);
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				"La personne avec l'id " + id + "n'existe pas"));
	}

	// api/persons/id
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable long id)  {
		return personService.findById(id).map((p) -> {
			personService.delete(p.getId());
			return new ResponseEntity<>(true, HttpStatus.OK);
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				"La personne avec l'id " + id + "n'existe pas"));
	}


	// api/persons/showSome?firstName=...&lastName=...
		@GetMapping("/showSome")
		public ResponseEntity<List<Person>> getAll(
				@RequestParam(value = "firstName") String firstName, 
				@RequestParam(value = "lastName") String lastName) { 
			return new ResponseEntity<List<Person>>(personService.findByFirstNameAndLastName(firstName, lastName), HttpStatus.OK);
		}
		
		// api/persons/showSome2/nom2/prenom2
		@GetMapping("/showSome2/{firstName}/{lastName}")
		public ResponseEntity<List<Person>> getAll2(
				@PathVariable(value = "firstName") String firstName, 
				@PathVariable(value = "lastName") String lastName) {
			return new ResponseEntity<List<Person>>(personService.findByFirstNameAndLastName(firstName, lastName), HttpStatus.OK);
		}

	
//	@GetMapping("/persons")
//	public ResponseEntity<List<Person>> getAll() {
//		return new ResponseEntity<List<Person>>(personRepository.findAll(), HttpStatus.OK);
//	}
//
//	@PostMapping("/persons")
//	public ResponseEntity<Person> create(@RequestBody Person person) {
//		return new ResponseEntity<Person>(personRepository.save(person), HttpStatus.CREATED);
//	}
//
//	@GetMapping("/persons/{id}")
//	public ResponseEntity<Person> getById(@PathVariable long id) {
//		return personRepository.findById(id).map((p) -> {
//			return new ResponseEntity<Person>(p, HttpStatus.OK);
//		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
//				"La personne avec l'id " + id + "n'existe pas"));
//	}
//
//	@PutMapping("/persons/{id}")
//	public ResponseEntity<Person> editById(@PathVariable long id, @RequestBody Person person) {
//		return personRepository.findById(id).map((p) -> {
//			p.setFirstName(person.getFirstName());
//			p.setLastName(person.getLastName());
//			p.setAge(person.getAge());
//			personRepository.save(p);
//			return new ResponseEntity<Person>(p, HttpStatus.OK);
//		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
//				"La personne avec l'id " + id + "n'existe pas"));
//	}
//
//	@DeleteMapping("/persons/{id}")
//	public ResponseEntity<Boolean> delete(@PathVariable long id)  {
//		return personRepository.findById(id).map((p) -> {
//			personRepository.delete(p);
//			return new ResponseEntity<>(true, HttpStatus.OK);
//		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
//				"La personne avec l'id " + id + "n'existe pas"));
//	}


	
	//	@PostMapping // Enregistre une donnee
//	@DeleteMapping // Supprimer une donnee
//	@PutMapping // Mettre a jour une donnee totalement
//	@PatchMapping // Mettre a jour une donne partiellement

	// Une api utilise les methodes du protocole HTTP (GET, POST, PUT, PATCH,
	// DELETE)

	// /hello
	@GetMapping("/hello")
	public String sayHello() {
		return "hello";
	}

	@PostMapping("/hello")
	public String sayHello(String msg) {
		return msg;
	}

}


//package com.example.demo.controllers;
//
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.demo.entities.Person;
//import com.example.demo.repository.PersonRepository;
//
////Fichier qui mappe sur des chemins et qui va nous renvoyer
////des reponses au format JSon, XML
//@RestController
//public class PersonRestController {
//
//	// annotation utilisée injection de dépendance : cad qd un methode de classe A
//	// veut utiliser
//	// methode de la classe B
//	@Autowired
//	private PersonRepository personRepository;
//
////	// /hello
////	@GetMapping("/persons")
////	public List<Person> getAll() {
////		return personRepository.findAll(); // spring data jpa qui cherche le methodes...
////	}
////
////	// enregistrer une personne dans les bases de données
////	@PostMapping("/persons")
////	public Person create(@RequestBody Person person) {
////		return personRepository.save(person); // retourner la personne enregistrée en base de données
////		// OU
////		// Person personToSave = personRepository.save(person);  icic on encapsule la valeur dans cet objet de type personne
////		// et dans ce cas on aurait retourner return personToSAve
////	}
////	
////	@GetMapping("/persons/{id}")
////	public Person getById(@PathVariable Long id) {
////		return personRepository.findById(id).get();
//       .get interdit les null
////	}
////	
//////	@DeleteMapping("/persons/{id}")
//////	public boolean deleteById(@PathVariable Long id) {
//////	     personRepository.deleteById(id);
//////		 return true;
//////	}
//////	
////	
////	@DeleteMapping("/persons/{id}")
////	public boolean deleteById(@PathVariable Long id) throws Exception {		
////		if (personRepository.findById(id).get() == null) {
////			throw new Exception("l'utilisateur n'existe pas");
////		} else {
////			personRepository.deleteById(id);
////			return true;
////		}
////	}
////	
////	
////	@PutMapping("/persons/{id}")
////	public Person modifyById(@PathVariable Long id, @RequestBody Person person){
////		Person p = personRepository.findById(id).get();
////		p.setFirstName(person.getFirstName());
////		p.setLastName(person.getLastName());
////		p.setAge(person.getAge());
////		p.setId(id);
////		personRepository.save(p);
////	//	personRepository.saveAndFlush(p);
////		return p;
////	}
////	
//////	@PutMapping("/persons/{id}")
//////	public Person modifyById(@PathVariable Long id) {
//////		Person p = personRepository.findById(id).get();
//////		p.setFirstName("nissa");
//////		p.setLastName("Kassis");
//////		p.setAge(28);
//////		return p;
//////	}
////	
//////	@PostMapping // Enregistre une donnee
//////	@DeleteMapping // Supprimer une donnee
//////	@PutMapping // Mettre a jour une donnee totalement
//////	@PatchMapping // Mettre a jour une donne partiellement
////
////	// Une api utilise les methodes du protocole HTTP (GET, POST, PUT, PATCH,
////	// DELETE)
////
//////	// /hello 
//////	@GetMapping("/hello")
//////	public String sayHello() {
//////		return "hello";
//////	}
//////	
////	@PostMapping("/hello")
////	public String sayHello(String msg) {
////		return msg;
////	}
//
//}