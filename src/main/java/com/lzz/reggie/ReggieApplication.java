package com.lzz.reggie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableTransactionManagement
public class ReggieApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReggieApplication.class, args);
	}

}
