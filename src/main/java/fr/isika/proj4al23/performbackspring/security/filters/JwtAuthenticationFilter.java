package fr.isika.proj4al23.performbackspring.security.filters;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.isika.proj4al23.performbackspring.utils.JwtUtil;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private AuthenticationManager authenticationManager;
	
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(
			HttpServletRequest request, 
			HttpServletResponse response) throws AuthenticationException {
		
		System.out.println("attemptAuthentication");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		System.out.println(username);
		System.out.println(password);
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		
		return authenticationManager.authenticate(authenticationToken);
	}
	
	@Override
	protected void successfulAuthentication(
			HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain chain, 
			Authentication authResult) throws IOException, ServletException {
		
		System.out.println("successfullAuthentication");
		
		User user = (User) authResult.getPrincipal();
		Algorithm algorithm = Algorithm.HMAC256(JwtUtil.SECRET);
		
		String jwtAccessToken = JWT.create()
				.withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + JwtUtil.EXPIRE_ACCESS))
				.withIssuer(request.getRequestURL().toString())
				.withClaim("roles", user.getAuthorities().stream().map(gAuth -> gAuth.getAuthority()).collect(Collectors.toList()))
				.sign(algorithm);
		
		String jwtRefreshToken = JWT.create()
				.withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + JwtUtil.EXPIRE_REFRESH))
				.withIssuer(request.getRequestURL().toString())
				.sign(algorithm);
		
		Map<String, String> idToken = new HashMap<>();
		idToken.put("access-token", jwtAccessToken);
		idToken.put("refresh-token", jwtRefreshToken);
		
		response.setContentType("application/json");
		new ObjectMapper().writeValue(response.getOutputStream(), idToken);
	}
}