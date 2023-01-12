package com.example.demo.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor  // constructeur vide
@AllArgsConstructor // constructeur plein

public class PersonDto {

	@NotEmpty()   //interdir les champs nulls pour les string
	@Size(min = 2 , message = "Nom doit contenir minimum 2 caractères")
	private String firstName;
	@NotEmpty()   //interdir les champs nulls
	@Size(min = 2 , message = "Prenom doit contenir minimum 2 caractères")
	private String lasttName;
	@NotNull()  //interdir les champs nulls pour les entiers
	@Digits(integer = 3, fraction = 0, message = "age doit contenir au maximum 3 entiers")
	private Integer age;
}
