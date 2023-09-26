package fr.isika.proj4al23.performbackspring.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.isika.proj4al23.performbackspring.models.PerformRole;
import fr.isika.proj4al23.performbackspring.models.PerformUser;
import fr.isika.proj4al23.performbackspring.service.AccountService;
import fr.isika.proj4al23.performbackspring.utils.JwtUtil;
import fr.isika.proj4al23.performbackspring.viewmodels.RoleToUserViewModel;

@RestController
@RequestMapping(path="/performmma")
public class AccountRestController {

	@Autowired
	private AccountService accountService;
	
	@GetMapping(path = "/users")
	@PostAuthorize("hasAuthority('USER')")
	public List<PerformUser> performUsers(){
		return accountService.listUsers();
	}
	
	@PostMapping(path = "/user")
	@PostAuthorize("hasAuthority('SUPER ADMIN', 'ADMIN')")
	public PerformUser savePerformUser(@RequestBody PerformUser performUser) {
		return accountService.addNewUser(performUser);
	}
	
	@PostMapping(path = "/role")
	@PostAuthorize("hasAuthority('SUPER ADMIN')")
	public PerformRole savePerformRole(@RequestBody PerformRole performRole) {
		return accountService.addNewRole(performRole);
	}
	
	@PostMapping(path = "/addroletouser")
	@PostAuthorize("hasAuthority('SUPER ADMIN')")
	public void addRoleToUser(@RequestBody RoleToUserViewModel roleToUserViewModel) {
		accountService.addRoleToUser(roleToUserViewModel.getUsername(), roleToUserViewModel.getRoleName());
	}
	
	@GetMapping(path="/refreshtoken")
	public void refreshToken (HttpServletRequest request, HttpServletResponse response) throws IOException {		
		
		String authorizationToken = request.getHeader(JwtUtil.AUTH_HEADER);
		
		if (authorizationToken != null && authorizationToken.startsWith(JwtUtil.PREFIX)) {
			
			try {
				
				String RefreshJwt = authorizationToken.substring(JwtUtil.PREFIX.length());
				Algorithm algorithm = Algorithm.HMAC256(JwtUtil.SECRET);
				
				JWTVerifier jwtVerifier = JWT.require(algorithm).build();
				DecodedJWT decodedJWT = jwtVerifier.verify(RefreshJwt);
				
				String username = decodedJWT.getSubject();
				
				PerformUser performUser = accountService.loadUserByUsername(username);
				
				String jwtAccessToken = JWT.create()
						.withSubject(performUser.getUsername())
						.withExpiresAt(new Date(System.currentTimeMillis() + JwtUtil.EXPIRE_ACCESS))
						.withIssuer(request.getRequestURL().toString())
						.withClaim("roles", performUser.getPerformRoles().stream().map(gAuth -> gAuth.getRoleName()).collect(Collectors.toList()))
						.sign(algorithm);
				
				Map<String, String> idToken = new HashMap<>();
				idToken.put("access-token", jwtAccessToken);
				idToken.put("refresh-token", RefreshJwt);
				
				response.setContentType(JwtUtil.CONTENT_TYPE);
				new ObjectMapper().writeValue(response.getOutputStream(), idToken);
								
			} catch (Exception e) {
				
				throw e;
			}
		} else {
			
			throw new RuntimeException("RefreshToken required !!!");
		}
	}
	
	@GetMapping(path = "/profile")
	public PerformUser profile(Principal principal) {
		return accountService.loadUserByUsername(principal.getName());
	}
}