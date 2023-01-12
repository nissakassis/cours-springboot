package com.example.demo.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue; // 	avec version 3.0.0 tous les imports st jakarta.persis....
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Getter
//@Setter
//@ToString

@Entity
@RequiredArgsConstructor
@Data  // avec ca on a getter, setter, toString et RequiredArgsConstructor
@NoArgsConstructor

public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonNull
	private Long id;
	@Column(name = "first_name") // nom de la colonne dans workbench
	@NonNull // necc pour ajouter  des valeurs de RequiredArgsConstructor
	// pour le constructeur non vide
	private String firstName;
	@Column(name = "last_name")
	@NonNull
	private String lastName;
	@Column(name = "age")
	@NonNull
	private Integer age;
	
	// CascadeType.PERSIST : lors de la persistance d'une personne, conserve
	// également ses projets.
	// CascadeType.REMOVE : lors de la suppression d'une personne, il supprime
	// également ses projets
	// les entités voitures.
	// CascadeType.REFRESH : lors de l'actualisation d'une personne, actualise
	// également ses projets
	// CascadeType.MERGE : lors de la fusion de l'état de l'entité personne,
	// fusionne également les entités contenues dans projets.	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "persons_projects", joinColumns = { @JoinColumn(name = "id_person") }, inverseJoinColumns = {
			@JoinColumn(name = "id_project") })
	private List<Project> projects = new ArrayList<>();

//
//	public Person(String firstName, String lastName, Integer age) {
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.age = age;
//	}
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getFirstName() {
//		return firstName;
//	}
//
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//
//	public String getLastName() {
//		return lastName;
//	}
//
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
//
//	public Integer getAge() {
//		return age;
//	}
//
//	public void setAge(Integer age) {
//		this.age = age;
//	}
//
//	@Override
//	public String toString() {
//		return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + "]";
//	}
}
