package com.example.demo.services;

import java.util.List;
import java.util.Optional;

public interface IService<T>  {
	
	List<T> findAll();
	
	T saveOrUpdate(T o);
	
	Optional<T> findById(long id);
	// valeur recupérée est optional cad soit valeur soit nulle et si elle recupère null on retourne une exception
	
    boolean delete(long id);
	

}
