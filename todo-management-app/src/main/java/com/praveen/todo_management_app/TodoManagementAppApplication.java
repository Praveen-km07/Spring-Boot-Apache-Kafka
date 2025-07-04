package com.praveen.todo_management_app;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@OpenAPIDefinition(
		info =@Info(
		title = "Todo Management REST API  Documentation",
		description = "Provide REST API documentation for APIs",
		version ="v1.0",
		contact =  @Contact(
				name="praveen",
				email = "praveenhsn.gowda@gmail.com",
				url="www.praveen.com"
		),
		license = @License(
				name="Apache 2.0",
				url="www.praveen.com/license"
		)
     ),
		externalDocs = @ExternalDocumentation(
				description = "Todo Management REST API for developers",
				url ="www.praveen.com/externaldocs"
		)
)
@SpringBootApplication
public class TodoManagementAppApplication {


	public static void main(String[] args) {
		SpringApplication.run(TodoManagementAppApplication.class, args);
	}

}
