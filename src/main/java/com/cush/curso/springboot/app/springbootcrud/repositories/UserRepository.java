package com.cush.curso.springboot.app.springbootcrud.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.cush.curso.springboot.app.springbootcrud.entities.User;

public interface UserRepository extends CrudRepository<User, Long>  {

    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
    
} 
