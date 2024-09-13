package org.zerock.myapp.domain;

import java.util.Arrays;

import lombok.Builder;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
@Log4j2

@Builder
@ToString
public class Person {
	private String name;
	private Integer age;
	private Double weight;
	private Double heigt;
	
	private Boolean gender;
	
	
	public static void main(String...args) {
		log.trace("main({}) invoked.", Arrays.toString(args));
		
//		PersonBuilder builder = Person.builder();
//		Person person = builder.build();
		
		Person person = 
			Person.builder() // (1) 건설사를 만들고
				.name("Yoon")
				.age(23)
				.weight(81.0)
				.heigt(175.0)
				.gender(true)
				.build();	// (2) 건설사에게 Person을 만들어라.
		
		log.info("\t+ person : {}", person);
	} // main
} // end class

