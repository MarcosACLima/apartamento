package br.com.marcos.apartamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
@EnableWebMvc 
/* 
 * Habilita CORS
 * Permiter integração com bean validation, 
 * suporte geração de RSS, suporte a serialização de objetos para JSON, XML, etc
 */
public class ApartamentoApplication implements WebMvcConfigurer {

	@Override
		public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**")
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
		}
	
	public static void main(String[] args) {
		SpringApplication.run(ApartamentoApplication.class, args);
	}

}
