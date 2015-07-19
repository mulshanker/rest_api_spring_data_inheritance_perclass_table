package com.demo.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "birds_animal")
@PrimaryKeyJoinColumn(name = "animal_id", referencedColumnName = "id")
public class BirdsAnimal extends Animal{


	@GeneratedValue
	private Long id;
	
    @Column
    private String feathersColor;


    @Column
    private String numberOfWings;
    

    public BirdsAnimal() {
    }

    
    public Long getId() {
		return id;
	}
    

	public String getFeathersColor() {
		return feathersColor;
	}


	public void setFeathersColor(String feathersColor) {
		this.feathersColor = feathersColor;
	}


	public String getNumberOfWings() {
		return numberOfWings;
	}


	public void setNumberOfWings(String numberOfWings) {
		this.numberOfWings = numberOfWings;
	}




}
