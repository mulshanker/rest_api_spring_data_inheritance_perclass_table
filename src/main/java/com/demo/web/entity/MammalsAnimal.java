package com.demo.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name = "mammals_animal")
@PrimaryKeyJoinColumn(name = "animal_id", referencedColumnName = "id")
@JsonIgnoreProperties
public class MammalsAnimal extends Animal{


	@GeneratedValue
	private Long id;
	
	@Column
	private String hairColor;


	@Column
	private String vertebratesType;

	public MammalsAnimal() {
	}


	public Long getId() {
		return id;
	}


	public String getHairColor() {
		return hairColor;
	}


	public void setHairColor(String hairColor) {
		this.hairColor = hairColor;
	}


	public String getVertebratesType() {
		return vertebratesType;
	}


	public void setVertebratesType(String vertebratesType) {
		this.vertebratesType = vertebratesType;
	}



}
