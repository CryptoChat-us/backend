package br.com.api.crypto_chat.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "br.com.api.crypto_chat.integration")
public class FeignConfig {
}
