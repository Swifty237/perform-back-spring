package fr.isika.proj4al23.performbackspring.utils;

public class JwtUtil {

	public static final String SECRET = "password78";
	public static final String AUTH_HEADER = "Authorization";
	public static final String CONTENT_TYPE = "application/json";
	public static final String PREFIX = "Bearer ";
	public static final long EXPIRE_ACCESS = 120000;
	public static final long EXPIRE_REFRESH = 900000;
}
