package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.entities.Person;
import com.example.demo.entities.Project;

public interface IProjectService extends IService<Project> {
	
	//recupérere tous les project d'une personne
	List<Project> getProjectsByPersonId(long idPers);

	Optional<Project> saveOneProjectByPerson(long idPerson, Project project);
	
	Optional<Project> assignOneProjectByPerson(long idPerson, long idProject);


}
