package com.project.currency;

import com.project.currency.arbs.MainArbs;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


//@ComponentScan({"com.project.currency"})
//@EntityScan("com.project.currency")
//@EnableJpaRepositories("com.project.currency.repositories")
@SpringBootApplication
public class CurrencyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyApplication.class, args);
		MainArbs.getInstance().run();
	}

}
