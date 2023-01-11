package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Person;

//@Repository  cette annotation existe implicitement dans JpaRepository, dc pas besoin de la metttre
// ci dessous Long est le type de l'attribut id dans Person 

public interface PersonRepository extends JpaRepository<Person, Long> {

	// Méthodes Customisées ne se trouvent pas dans le JpaRepository comme dans les méthodes 
	// finfAll() ... alors il faut les crééer ici
	List<Person> findByFirstName(String firstName);

	List<Person> findByFirstNameAndLastName(String firstName, String lastName);

	List<Person> findByFirstNameContaining(String firstName);

	@Query("select p from Person p where p.lastName =:lastName")
	List<Person> chercherSelonLeNom(@Param("lastName") String lastName);

	// OU
	@Query("select p from Person p where p.firstName =?1")
	List<Person> chercherSelonLePrenom(String firstName);// met la valeur p.firstName ici à la première position
	// si y avait
//	@Query("select p from Person p where p.firstName =:1 and p.lastName =: 2")
//	List<Person> chercherSelonLePrenom( String firstName, String lastName);

}
