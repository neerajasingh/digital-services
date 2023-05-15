/**

The ApplicationConfig class is responsible for configuring and initializing the application.
It defines beans for RestTemplate instances and sets up connection parameters for HTTP clients.
*/
package com.test.images.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import com.test.images.exception.handler.ErrorResponseHandler;

@Configuration
@EnableJpaRepositories(basePackages = "com.test.images.repository")
@EntityScan(basePackages = { "com.test.images.domainmodel" })
public class ApplicationConfig {

	@Value("${rest.connectionTimeout:5000}")
	private int connectionTimeout;

	@Value("${rest.readTimeout:15000}")
	private int readTimeout;

	@Value("${rest.connectionTimeout:15000}")
	private int maxConnections;

	@Value("${rest.readTimeout:15000}")
	private int maxConnectionsPerRoute;


	/**
	 * The imageRestTemplate method initializes a RestTemplate instance with a
	 * custom error handler.
	 *
	 * @return a RestTemplate instance configured with a custom ErrorResponseHandler
	 */
	@Bean
	public RestTemplate imageRestTemplate() {

		RestTemplate template = new RestTemplate();
		template.setErrorHandler(new ErrorResponseHandler());
		return template;
	}

}
