package fr.isika.proj4al23.performbackspring.controller;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.isika.proj4al23.performbackspring.models.PerformUser;
import fr.isika.proj4al23.performbackspring.service.UserAccountServiceImpl;

@RestController
@RequestMapping("/auth")
public class AuthLoginController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserAccountServiceImpl userAccountService;

	@Autowired
	private JwtEncoder jwtEncoder;

	@GetMapping("/profile")
	public Authentication authentication(Authentication authentication) {
		return authentication;
	}

	@PostMapping("/login")
	public Map<String, String> login(String username, String password) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(username, password));

		Instant instant = Instant.now();
		String scope = authentication.getAuthorities().stream().map(a -> a.getAuthority())
				.collect(Collectors.joining(" "));

		JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder().issuedAt(instant)
				.expiresAt(instant.plus(10, ChronoUnit.MINUTES)).subject(username).claim("scope", scope).build();

		JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters
				.from(JwsHeader.with(MacAlgorithm.HS256).build(), jwtClaimsSet);

		String jwt = jwtEncoder.encode(jwtEncoderParameters).getTokenValue();

		return Map.of("access-token", jwt);
	}

	@PostMapping("/register")
	public Map<String, String> register(String email, String username, String password) {

		if (userAccountService.isExistsByUsername(username)) {

			return Map.of("user-exist", "Le nom d'utilisateur est déjà pris.");

		} else if (userAccountService.isExistsByEmail(email)) {

			return Map.of("email-exist", "L'adresse e-mail est déjà utilisée.");

		} else {

			PerformUser performUser = new PerformUser();
			performUser.setEmail(email);
			performUser.setUsername(username);
			performUser.setPassword(password);

			userAccountService.addNewUser(performUser);
			userAccountService.addRoleToUser(username, "USER");
			return login(username, password);
		}
	}
}
