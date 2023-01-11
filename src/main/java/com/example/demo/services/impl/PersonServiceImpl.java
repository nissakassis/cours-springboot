package com.example.demo.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Person;
import com.example.demo.repository.PersonRepository;
import com.example.demo.services.IPersonService;
import com.example.demo.services.IService;

@Service("personService")
//public class PersonServiceImpl implements IService<Person>{
public class PersonServiceImpl implements IPersonService{
	
	
	@Autowired // utilisee pr l'injection de dependances personRepository
	private PersonRepository personRepository;

	@Override
	// on applique un traitement et modifie la methode findall qui se trouve dans couche de métier
	public List<Person> findAll() {
		return personRepository.findAll()
				.stream()
				.filter((x) -> x.getAge() >= 18)
				.collect(Collectors.toList());
		//return personRepository.findAll();
	}

	@Override
	public Person saveOrUpdate(Person o) {
		return personRepository.save(o);
	}

	@Override
	public Optional<Person> findById(long id) {
		return personRepository.findById(id);
		// si on n'a pas mis Optional<Person> mais Person alors on ajoute .get() qui retourne l'optional 
	}

	@Override
	public boolean delete(long id) {
		personRepository.deleteById(id);
		return true;
	}

	@Override
	public List<Person> findByFirstName(String firstName) {
		return personRepository.findByFirstName(firstName);
	}

	@Override
	public List<Person> findByFirstNameAndLastName(String firstName, String lastName) {
		return personRepository.findByFirstNameAndLastName(firstName, lastName);
	}

	@Override
	public List<Person> findByFirstNameContaining(String firstName) {
		return personRepository.findByFirstNameContaining(firstName);
	}

	@Override
	public List<Person> chercherSelonLeNom(String lastName) {
		return personRepository.chercherSelonLeNom(lastName);
	}

	@Override
	public List<Person> chercherSelonLePrenom(String firstName) {
		return personRepository.chercherSelonLePrenom(firstName);
	}

}
