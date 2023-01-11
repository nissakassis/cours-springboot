package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

	//Methode customisées
	// recupére ts les projets selon l'id d'une personne
	List<Project> findProjectsByPersonsId(long id); // persons obligatoire comme son nom dans project
	
	
}
