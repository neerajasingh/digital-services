/**

The ErrorResponseHandler class is responsible for handling HTTP errors returned from REST services.
It implements the ResponseErrorHandler interface to handle errors for RestTemplate instances.
*/
package com.test.images.exception.handler;

import java.io.IOException;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import com.test.images.error.ServiceException;

public class ErrorResponseHandler implements ResponseErrorHandler {
	/**
	 * The hasError method checks if the response from a REST service contains an
	 * error code. If an error code is present, it is handled by the handleError
	 * method.
	 *
	 * @param response the ClientHttpResponse from a REST service
	 * @return true if the response contains an error code, false otherwise
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		HttpStatusCode status = response.getStatusCode();
		return status.is4xxClientError() || status.is5xxServerError();
	}

	/**
	 * The handleError method handles any error codes returned by a REST service.
	 * This method is called if hasError returns true.
	 *
	 * @param response the ClientHttpResponse from a REST service
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	public void handleError(ClientHttpResponse response) throws IOException {

		if (response.getStatusCode().is4xxClientError()) {

			if (response.getStatusCode().value() == 400) {
				throw new ServiceException(ErrorCodes.REQUEST_INVALID, response.getStatusText(),
						response.getStatusCode());

			} else if (response.getStatusCode().value() == 401) {
				throw new ServiceException(ErrorCodes.REQUEST_UNAUTHORIZED, response.getStatusText(),
						response.getStatusCode());
			} else if (response.getStatusCode().value() == 403) {
				throw new ServiceException(ErrorCodes.REQUEST_UNAUTHORIZED, response.getStatusText(),
						response.getStatusCode());
			} else if ((response.getStatusCode().value() == 404)) {
				throw new ServiceException(ErrorCodes.NOT_FOUND, response.getStatusText(), response.getStatusCode());
			}
		}

		if (response.getStatusCode().is5xxServerError()) {
			if (response.getStatusCode().value() == 500) {
				throw new ServiceException(ErrorCodes.INTERNAL_SERVER_ERROR, response.getStatusText(),
						response.getStatusCode());
			} else if (response.getStatusCode().value() == 503) {
				throw new ServiceException(ErrorCodes.SYSTEM_UNAVAILABLE, response.getStatusText(),
						response.getStatusCode());
			}
		}

	}

}
