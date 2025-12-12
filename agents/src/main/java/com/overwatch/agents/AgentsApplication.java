package com.overwatch.agents;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class AgentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgentsApplication.class, args);
	}

//    @Bean
//    public CommandLineRunner commandLineRunner ( KafkaTemplate<String,String> kafkaTemplate ){
//        return args -> kafkaTemplate.send("overwatch.published-supers","data","Hello World!.");
//    }

}
