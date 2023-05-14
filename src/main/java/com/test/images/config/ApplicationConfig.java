/**

The ApplicationConfig class is responsible for configuring and initializing the application.
It defines beans for RestTemplate instances and sets up connection parameters for HTTP clients.
*/
package com.test.images.config;

import java.util.concurrent.TimeUnit;

import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.core5.util.Timeout;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
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
	 * The restTemplate method initializes a RestTemplate instance with custom HTTP
	 * connection settings.
	 *
	 * @return a RestTemplate instance configured with a custom
	 *         HttpComponentsClientHttpRequestFactory and
	 *         MappingJackson2HttpMessageConverter
	 */
	@Bean
	public RestTemplate restTemplate() {
		PoolingHttpClientConnectionManagerBuilder builder = PoolingHttpClientConnectionManagerBuilder.create();
		builder.setMaxConnTotal(maxConnections);
		builder.setMaxConnPerRoute(maxConnectionsPerRoute);
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create().setConnectionManager(builder.build());
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectTimeout(Timeout.of(connectionTimeout, TimeUnit.MICROSECONDS))
				.setResponseTimeout(Timeout.of(readTimeout, TimeUnit.MICROSECONDS)).build();
		httpClientBuilder.setDefaultRequestConfig(requestConfig);
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(
				httpClientBuilder.build());

		RestTemplate restTemplate = new RestTemplate(factory);

		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

		return restTemplate;
	}

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
