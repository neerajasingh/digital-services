package com.test.images.error;

import org.springframework.http.HttpStatusCode;

import com.test.images.exception.handler.ErrorCodes;

/**
 * ServiceException represents a runtime exception that is thrown when a service
 * layer encounters an error or exception. It holds an error code, message, and
 * HTTP status code that can be used to provide detailed information about the
 * error to the client.
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * The error code associated with the error that occurred.
	 */
	private final ErrorCodes code;

	/**
	 * The message associated with the error that occurred.
	 */
	private final String message;

	/**
	 * The HTTP status code associated with the error that occurred.
	 */
	private final HttpStatusCode statusCode;

	/**
	 * Constructs a new ServiceException object with the specified error code,
	 * message, and HTTP status code.
	 *
	 * @param code       the error code associated with the error that occurred.
	 * @param message    the message associated with the error that occurred.
	 * @param statusCode the HTTP status code associated with the error that
	 *                   occurred.
	 */
	public ServiceException(final ErrorCodes code, final String message, final HttpStatusCode statusCode) {
		this.code = code;
		this.message = message;
		this.statusCode = statusCode;
	}

	/**
	 * Returns the error code associated with the error that occurred.
	 *
	 * @return the error code associated with the error that occurred.
	 */
	public ErrorCodes getCode() {
		return code;
	}

	/**
	 * Returns the message associated with the error that occurred.
	 *
	 * @return the message associated with the error that occurred.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Returns the HTTP status code associated with the error that occurred.
	 *
	 * @return the HTTP status code associated with the error that occurred.
	 */
	public HttpStatusCode getStatusCode() {
		return statusCode;
	}

}