package com.springSemester.ComponentAnnotation;

import com.springSemester.ComponentAnnotation.petStore.Person;
import com.springSemester.ComponentAnnotation.petStore.Pet;
import com.springSemester.ComponentAnnotation.petStore.Snail;
import com.springSemester.ComponentAnnotation.petStore.Spider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class ComponentAnnotationApplicationTests {

    @Autowired
    private ConfigurableApplicationContext context;

    @Test
    void contextLoads() {
		assertDoesNotThrow(()->context.getBean(Person.class));
		assertDoesNotThrow(()->context.getBean(Spider.class));
		assertDoesNotThrow(()->context.getBean(Snail.class));

		Person person = context.getBean(Person.class);
        List<Pet> pets = person.getPets();
		assertEquals(2, pets.size());
	}

}
