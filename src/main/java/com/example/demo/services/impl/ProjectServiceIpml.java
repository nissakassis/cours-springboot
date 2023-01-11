package com.example.demo.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Project;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.services.IProjectService;

@Service("projectService")
public class ProjectServiceIpml implements IProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private PersonRepository personRepository;
	
	@Override
	public List<Project> findAll() {
		return projectRepository.findAll();
	}

	@Override
	public Project saveOrUpdate(Project o) {
		return projectRepository.save(o);
	}

	@Override
	public Optional<Project> findById(long id) {
		return projectRepository.findById(id);
	}

	@Override
	public boolean delete(long id) {
	 projectRepository.deleteById(id);
	 return true;
	}

	@Override
	public List<Project> getProjectsByPersonId(long idPers) {
		return projectRepository.findProjectsByPersonsId(idPers);
	}

	@Override
	public Optional<Project> saveOneProjectByPerson(long idPerson, Project project) {
		 //Optional<Project> projectToSave = personRepository.findById(idPerson).map(person -> {
		return personRepository.findById(idPerson).map(person -> {
			Project p = new Project();
			p.setTitle(project.getTitle());
			person.getProjects().add(project);
			projectRepository.save(project);
			return p;
		 });
	}

	
	@Override
	public Optional<Project> assignOneProjectByPerson(long idPerson, long idProject) {
		return personRepository.findById(idPerson).map(person -> {
			Project p = projectRepository.findById(idProject).get();
			person.getProjects().add(p);
			return projectRepository.save(p);
		});
	}

	

}
