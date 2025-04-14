package br.com.api.crypto_chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("br.com.api.crypto_chat.data.entity")
@EnableJpaRepositories("br.com.api.crypto_chat.data.repository")
@ComponentScan("br.com.api.crypto_chat")
public class CryptoChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptoChatApplication.class, args);
	}

}
