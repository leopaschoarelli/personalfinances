package br.com.personal.finances;

import br.com.personal.finances.core.io.Base64ProtocolResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class PersonalFinances {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

		var app = new SpringApplication(PersonalFinances.class);
		app.addListeners(new Base64ProtocolResolver());
		app.run(args);
	}

}