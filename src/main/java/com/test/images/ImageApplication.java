/**

The ImageApplication class is the starting point of the Image Application.
It uses the Spring Boot framework to initialize and configure the application.
*/
package com.test.images;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImageApplication {
	/**
	 * The main method initializes and runs the Image Application. It starts the
	 * Spring Boot application context and launches the application.
	 *
	 * @param args the command line arguments passed to the application
	 */
	public static void main(String[] args) {
		SpringApplication.run(ImageApplication.class);
	}
}
