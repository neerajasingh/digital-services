# Image Uploader

Image Uploader is a RESTful web application that allows users to upload and share images. It provides a simple and user-friendly interface for uploading images and generates a link to share the uploaded images with others.

## Requirements

To run the Image Uploader application, you need to have the following:

- Java 11 or later
- Maven 3.6.3 or later
- Spring Boot 2.5.0 or later
- An internet connection to upload and download images

## Getting started

To get started with Image Uploader, follow these steps:

1. Clone the repository:
```
TODO:
```

2. Navigate to the project directory:
```
cd user-image-ms
```

3. Build the application:
```
mvn clean install
```

4. Start the application:
```
mvn spring-boot:run
```

5. Open your web browser and navigate to `http://localhost:8080` to access the Image Uploader application.

## Usage

### Uploading images

TODO:

### Error handling

The application is designed to handle errors in a user-friendly way. If an error occurs during the upload process, a descriptive error message will be displayed on the screen.

## Architecture

The Image Uploader application is built using the following technologies:

- Spring Boot: Provides a robust framework for building web applications.
- Spring MVC: Used to implement the RESTful web service API.
- Spring Security
- OAuth2
## Directory structure

The directory structure of the Image Uploader application is as follows:

- `src/main/java/com.test.images`: Contains the Java source code for the application.
  - `config`: Contains configuration classes.
  - `controller`: Contains RESTful web service controllers.
  - `error`: Contains error response classes.
  - `exception/handler`: Contains exception handler classes.
  - `service`: Contains service classes.
  - `service/helper`: Contains helper classes for the service layer.
  - `validator`: Contains classes for validating the request parameters.
- `src/main/resources`: Contains the application properties files and HTML templates.
- `src/test/java/com.test.images`: Contains the unit and integration tests for the application.
- `target`: Contains the compiled class files and packaged JAR file.

## Contributing

If you would like to contribute to the Image Uploader project, please follow these steps:

1. Fork the repository.
2. Create a new branch for your changes.
3. Make your changes and commit them.
4. Push your changes to your fork.
5. Submit a pull request to the main repository.

## License
Open source.Feel free to use and modify the source code as you see fit.