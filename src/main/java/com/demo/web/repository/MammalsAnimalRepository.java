package com.demo.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.web.entity.MammalsAnimal;

@Repository("mammalsAnimalRepository")
@Transactional
public interface MammalsAnimalRepository extends JpaRepository<MammalsAnimal, Long> {


}
