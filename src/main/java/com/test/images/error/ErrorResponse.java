package com.test.images.error;

/**
 * The ErrorResponse class represents an error response that can be returned by
 * a REST API endpoint. It contains a code and a message describing the error.
 */
public class ErrorResponse {

	/**
	 * The error code associated with the error.
	 */
	private String code;

	/**
	 * The error message associated with the error.
	 */
	private String message;

	/**
	 * Default constructor for ErrorResponse.
	 */
	public ErrorResponse() {
	}

	/**
	 * Constructor for ErrorResponse that sets the error code and message.
	 * 
	 * @param code    The error code to set.
	 * @param message The error message to set.
	 */
	public ErrorResponse(String code, String message) {
		this.code = code;
		this.message = message;
	}

	/**
	 * Getter for the error code.
	 * 
	 * @return The error code associated with the error.
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Setter for the error code.
	 * 
	 * @param code The error code to set.
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Getter for the error message.
	 * 
	 * @return The error message associated with the error.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Setter for the error message.
	 * 
	 * @param message The error message to set.
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
