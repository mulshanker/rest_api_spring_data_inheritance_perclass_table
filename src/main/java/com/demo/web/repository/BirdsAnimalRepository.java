package com.demo.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.web.entity.BirdsAnimal;

@Repository("birdsAnimalRepository")
@Transactional
public interface BirdsAnimalRepository extends JpaRepository<BirdsAnimal, Long> {


}
