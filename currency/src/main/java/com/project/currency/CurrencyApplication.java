package com.project.currency;

import com.project.currency.arbs.MainArbs;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CurrencyApplication {

	public static void main(String[] args) {
		MainArbs mainArbs = new MainArbs();
	}

}
