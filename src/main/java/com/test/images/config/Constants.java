/**
The Constants class contains constant values used throughout the application.
It is abstract to prevent instantiation of the class and has a private constructor to prevent subclassing.
*/
package com.test.images.config;

public abstract class Constants {

	private Constants() {
	}

	public static final String AUTHORIZATION = "Authorization";
	public static final String BEARER = "Bearer ";
	public static final String CLIENT_ID_KEY = "Client-ID ";
	public static final String IMAGE_KEY = "image";
	public static final String ACCESS_TOKEN = "access_token";
	public static final String GRANT_TYPE_KEY = "grant_type";
	public static final String CLIENT_SECRET_KEY = "client_secret";
	public static final String CLIENT_ID_KEY2 = "client_id";
	public static final String REFRESH_TOKEN_KEY = "refresh_token";

}
