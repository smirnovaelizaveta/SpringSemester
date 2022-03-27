package com.springSemester.ComponentAnnotation;

import com.springSemester.ComponentAnnotation.petStore.Person;
import com.springSemester.ComponentAnnotation.petStore.Pet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class ComponentAnnotationApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ComponentAnnotationApplication.class, args);

		Person person = context.getBean("person", Person.class);
		List<Pet> pets = person.getPets();
		System.out.println("pets = " + pets);
	}

}
