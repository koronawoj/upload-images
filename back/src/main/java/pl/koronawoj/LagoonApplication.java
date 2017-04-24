package pl.koronawoj;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import pl.koronawoj.config.JwtFilter;
import pl.koronawoj.model.Photo;
import pl.koronawoj.service.PhotoService;
import pl.koronawoj.service.impl.PhotoServiceImpl;
import pl.koronawoj.controller.*;

@SpringBootApplication
public class LagoonApplication {

	@Bean
	public FilterRegistrationBean JwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/rest/");
		return registrationBean;
	}

	public static void main(String[] args) {
		SpringApplication.run(LagoonApplication.class, args);
		
		

	}

}
