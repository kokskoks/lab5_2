package edu.iis.mto.integrationtest.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;

import edu.iis.mto.integrationtest.utils.ModeUtils;

@Configuration //wskazuje, �e klasa zawiera konfiguracj� bean��w Spring
@ComponentScan("edu.iis.mto.integrationtest.repository") //ustala kontekst poszukiwania komponent�w
@Import(value = { PersistenceConfig.class }) //klasa zwi�zana z konfiguracj� bean��w, kt�ra importujemy
public class ApplicationConfig {
	
	@Bean
	public static PropertyPlaceholderConfigurer getPropertyPlaceHolderConfigurer(){
		PropertyPlaceholderConfigurer resolver = new PropertyPlaceholderConfigurer();
		resolver.setLocation(new ClassPathResource(ModeUtils.getMode().getModeName() + 
				"-persistence.properties")); 
		return resolver;
	}
	
}
