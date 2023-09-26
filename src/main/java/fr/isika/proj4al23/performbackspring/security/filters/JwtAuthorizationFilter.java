package fr.isika.proj4al23.performbackspring.security.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import fr.isika.proj4al23.performbackspring.utils.JwtUtil;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(
			HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain) throws ServletException, IOException {
		
		if (request.getServletPath().equals("/refreshtoken")) {
			filterChain.doFilter(request, response);
		} else {
			
			String authorizationToken = request.getHeader(JwtUtil.AUTH_HEADER);
			
			if (authorizationToken != null && authorizationToken.startsWith(JwtUtil.PREFIX)) {
				
				try {
					
					String jwt = authorizationToken.substring(JwtUtil.PREFIX.length());
					Algorithm algorithm = Algorithm.HMAC256(JwtUtil.SECRET);
					
					JWTVerifier jwtVerifier = JWT.require(algorithm).build();
					DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
					
					String username = decodedJWT.getSubject();
					String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
					
					Collection<GrantedAuthority> authorithies = new ArrayList<>();
					
					for (String role : roles) {
						authorithies.add(new SimpleGrantedAuthority(role));
					}
					
					UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorithies);
					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
					filterChain.doFilter(request, response);
					
				} catch (Exception e) {
					response.setHeader("error-message", e.getMessage());
					response.sendError(HttpServletResponse.SC_FORBIDDEN);
				}
				
			} else {
				
				filterChain.doFilter(request, response);
			}
		}
	}
}