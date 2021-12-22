package com.rubencarmona.blog.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@SuppressWarnings("deprecation")
public class ConfiguracionWeb extends WebMvcConfigurerAdapter {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		/*
		 * Todas las peticiones a las webjars le indicaremos la ruta a seguir.
		 * classpath:/META-INF/resources/webjars/
		 * ruta que se encuentra dentro de las dependencias de /Maven Dependencies/jquery-x.x.x.jar
		 */
		registry.addResourceHandler("/webjars/**")
		.addResourceLocations("classpath:/META-INF/resources/webjars/");
		
		
		/**
		 * Ruta para los recursos
		 */
		registry.addResourceHandler("/assets/**")
		.addResourceLocations("/assets/");
	}
	
	
	/**
	 * Ruta para poder trabajar con JSP.
	 * El viewResolver resolvera las vistas que estamos llamando en el controlador, en este caso es de JSP.
	 */
	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

}
