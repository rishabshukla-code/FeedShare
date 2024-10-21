package com.mycompany.feedshare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(scanBasePackages = {"com.mycompany.controller", "com.mycompany.model.pojo", "com.mycompany.DAO", "com.mycompany.validator"})
public class FeedShareApplication implements WebMvcConfigurer {
	
	 @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("/FS-Uploads/**")
	                .addResourceLocations("file:" + System.getProperty("user.home") + "/Desktop/FS-Uploads/");
	    }

	public static void main(String[] args) {
		SpringApplication.run(FeedShareApplication.class, args);
	}

}
