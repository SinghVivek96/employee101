package com.vivek.employee101;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@SpringBootApplication
public class Employee101Application {

	public static void main(String[] args) {
		SpringApplication.run(Employee101Application.class, args);
		System.out.println("Runnning");
	}

	@Bean
	public CorsFilter corsFilter() { //Filter to handle CORS pre-flight requests and intercept CORS simple and actual requests
		CorsConfiguration corsConfiguration = new CorsConfiguration(); //Here we are instantiating new corsConfiguration to pull in the details wrt cors
		corsConfiguration.setAllowCredentials(true); //Whether user credentials are supported.
		corsConfiguration.addAllowedOrigin("http://localhost:4200/"); //For basically adding the origin like what will pe intercepting our API
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin","Access-Control-Allow-Origin","Content-Type",
				"Accept","Authorization","Origin Accept","X-Requested-With",
				"Access-Control-Request-Method","Access-Control-Request-Header"));//Set the list of headers that a pre-flight request
		// can list as allowed for use during an actual request.
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin","Content-Type","Accept","Authorization","Access-Control-Allow-Origin",
				"Access-Control-Allow-Credential"));//Set the list of response headers other than simple headers
		corsConfiguration.setAllowedMethods(Arrays.asList("PATCH","GET","POST","PUT","DELETE","OPTIONS"));//what type of methods are actually allowed
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		//CorsConfigurationSource that uses URL path patterns to select the CorsConfiguration for a request.
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}

}
