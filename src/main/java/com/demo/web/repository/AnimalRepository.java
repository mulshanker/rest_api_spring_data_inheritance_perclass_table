package com.demo.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.web.entity.Animal;
import com.demo.web.entity.MammalsAnimal;

@Repository("animalRepository")
@Transactional
public interface AnimalRepository extends JpaRepository<Animal, Long> {


}
