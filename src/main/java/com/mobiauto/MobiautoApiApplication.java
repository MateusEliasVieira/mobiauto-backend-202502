package com.mobiauto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class MobiautoApiApplication {

	public static void main(String[] args) {
//		PasswordEncoder encriptadorDeSenha = new BCryptPasswordEncoder();
//		System.out.println("Senha: "+encriptadorDeSenha.encode("Ma25!!"));
		SpringApplication.run(MobiautoApiApplication.class, args);
	}

}
