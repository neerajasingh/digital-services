package com.test.images.exception.handler;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.test.images.error.ErrorResponse;
import com.test.images.error.ServiceException;

/**
 * This class represents the global exception handler for the application. It is
 * responsible for catching and handling any exceptions that occur during the
 * request processing. It contains three exception handling methods that handle
 * the runtime exceptions, method argument not valid exceptions, and service
 * exceptions, respectively. The appropriate HTTP status code and error response
 * are returned from these methods depending on the type of exception that
 * occurred.
 */
@ControllerAdvice
public class ExceptionHandler {

	/**
	 * This method handles runtime exceptions that occur during the request
	 * processing and returns an HTTP 500 Internal Server Error response with an
	 * error code and message. The error code is set to INTERNAL_SERVER_ERROR, and
	 * the message is set to the exception message.
	 *
	 * @param exception the runtime exception that occurred
	 * @return a ResponseEntity containing an ErrorResponse object and the specified
	 *         HTTP status
	 */
	@org.springframework.web.bind.annotation.ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<ErrorResponse> handle(final DataIntegrityViolationException exception) {
		return new ResponseEntity<ErrorResponse>(
				new ErrorResponse(ErrorCodes.INTERNAL_SERVER_ERROR.name(), exception.getMessage()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * This method handles runtime exceptions that occur during the request
	 * processing and returns an HTTP 500 Internal Server Error response with an
	 * error code and message. The error code is set to INTERNAL_SERVER_ERROR, and
	 * the message is set to the exception message.
	 *
	 * @param exception  the runtime exception that occurred
	 * @param httpStatus the HTTP status to return in the response
	 * @return a ResponseEntity containing an ErrorResponse object and the specified
	 *         HTTP status
	 */
	@org.springframework.web.bind.annotation.ExceptionHandler(value = RuntimeException.class)
	public ResponseEntity<ErrorResponse> handle(final RuntimeException exception, final HttpStatus httpStatus) {
		return new ResponseEntity<ErrorResponse>(
				new ErrorResponse(ErrorCodes.INTERNAL_SERVER_ERROR.name(), exception.getMessage()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * This method handles method argument not valid exceptions that occur during
	 * the request processing and returns an HTTP 400 Bad Request response with an
	 * error code and message. The error code is set to REQUEST_INVALID, and the
	 * message is set to the exception message.
	 *
	 * @param exception the method argument not valid exception that occurred
	 * @return a ResponseEntity containing an ErrorResponse object and HTTP 400 Bad
	 *         Request status
	 */
	@org.springframework.web.bind.annotation.ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handle(final MethodArgumentNotValidException exception) {
		return new ResponseEntity<ErrorResponse>(
				new ErrorResponse(ErrorCodes.REQUEST_INVALID.name(), exception.getMessage()), HttpStatus.BAD_REQUEST);
	}

	/**
	 * This method handles service exceptions that occur during the request
	 * processing and returns an HTTP status code and error response based on the
	 * type of service exception that occurred. The error response contains an error
	 * code and message.
	 *
	 * @param exception the service exception that occurred
	 * @return a ResponseEntity containing an ErrorResponse object and the HTTP
	 *         status code specified in the exception
	 */
	@org.springframework.web.bind.annotation.ExceptionHandler(value = ServiceException.class)
	public ResponseEntity<ErrorResponse> handle(final ServiceException exception) {
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(exception.getCode().name(), exception.getMessage()),
				exception.getStatusCode());
	}
	
	/**
	 * This method handles runtime exceptions that occur during the request
	 * processing and returns an HTTP 400 Bad Request Error response with an
	 * error code and message. The error code is set to INTERNAL_SERVER_ERROR, and
	 * the message is set to the exception message.
	 *
	 * @param exception the runtime exception that occurred
	 * @return a ResponseEntity containing an ErrorResponse object and the specified
	 *         HTTP status
	 */
	@org.springframework.web.bind.annotation.ExceptionHandler(value = MissingServletRequestParameterException.class)
	public ResponseEntity<ErrorResponse> handle(final MissingServletRequestParameterException exception) {
		return new ResponseEntity<ErrorResponse>(
				new ErrorResponse(ErrorCodes.REQUEST_INVALID.name(), exception.getMessage()),
				HttpStatus.BAD_REQUEST);
	}
	
}
