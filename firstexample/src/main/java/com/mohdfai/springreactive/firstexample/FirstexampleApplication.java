package com.mohdfai.springreactive.firstexample;

import com.mohdfai.springreactive.firstexample.repository.EmpolyeeRepostiry;
import com.mohdfai.springreactive.firstexample.resource.Empolyee;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class FirstexampleApplication {

	@Bean
	CommandLineRunner employees(EmpolyeeRepostiry empolyeeRepostiry)
	{
		return args -> {
			empolyeeRepostiry
					.deleteAll()
					.subscribe(null, null, () -> {

						Stream.of(new Empolyee(UUID.randomUUID().toString(),
								"Imdad", 23000L),new Empolyee(UUID.randomUUID().toString(),
								"Faiz", 13000L),new Empolyee(UUID.randomUUID().toString(),
								"Aman", 20000L),new Empolyee(UUID.randomUUID().toString(),
								"Miyan", 53000L)
						)
								.forEach(employee -> {
									empolyeeRepostiry
											.save(employee)
											.subscribe(System.out::println);

								});

					})
			;
		};

	}


	public static void main(String[] args) {
		SpringApplication.run(FirstexampleApplication.class, args);
	}

}

