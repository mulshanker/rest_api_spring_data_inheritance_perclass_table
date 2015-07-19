package com.demo.web.sample;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.demo.web.entity.BirdsAnimal;
import com.demo.web.entity.MammalsAnimal;
import com.demo.web.entity.User;
import com.demo.web.repository.BirdsAnimalRepository;
import com.demo.web.repository.MammalsAnimalRepository;
import com.demo.web.repository.UserRepository;

@Component
@Lazy(false)
public class DatabaseInitializer {

	@Autowired(required = true)
    private UserRepository userRepository;

    @Autowired(required = true)
    private PasswordEncoder passwordEncoder;

    @Autowired(required = true)
    private MammalsAnimalRepository mammalsAnimalRepository;
    

    @Autowired(required = true)
    private BirdsAnimalRepository birdsAnimalRepository;
    
    @PostConstruct
    public void generateData() {
        User userUser = new User("user", this.passwordEncoder.encode("user"));
        userUser.addRole("user");
        this.userRepository.save(userUser);

        User adminUser = new User("admin", this.passwordEncoder.encode("admin"));
        adminUser.addRole("user");
        adminUser.addRole("admin");
        this.userRepository.save(adminUser);
        
        MammalsAnimal animal = new MammalsAnimal();
        animal.setHairColor("BROWN");
        animal.setName("TIGER");
        animal.setAge(5);
        animal.setType(animal.getClass().getSimpleName());
        this.mammalsAnimalRepository.save(animal);
        
        
        BirdsAnimal birds = new BirdsAnimal();
        birds.setFeathersColor("GREEN");
        birds.setName("PEROT");
        birds.setAge(1);
        birds.setType(birds.getClass().getSimpleName());
        this.birdsAnimalRepository.save(birds);
        
        MammalsAnimal dog = new MammalsAnimal();
        dog.setHairColor("BLACK");
        dog.setName("DOC");
        dog.setAge(2);
        dog.setType(dog.getClass().getSimpleName());
        this.mammalsAnimalRepository.save(dog);
       
    }
}
