package com.demo.web.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.DiscriminatorOptions;

@Entity
@Table(name = "animal")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Animal extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(unique = true, length = 16, nullable = false)
    private String name;
    
	@Column(nullable = false)
    private String type;
    
	
    @Column
    private Integer age;


    public Animal() {
    }

    public Animal(String name) {

        this.name = name;
    }

    public String getName() {

        return this.name;
    }

    public void setName(String name) {

        this.name = name;
    }

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}



 
}
