package fr.isika.proj4al23.performbackspring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import fr.isika.proj4al23.performbackspring.security.filters.JwtAuthenticationFilter;
import fr.isika.proj4al23.performbackspring.security.filters.JwtAuthorizationFilter;
import fr.isika.proj4al23.performbackspring.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // les cookies de session ne sont plus enregistré
		http.authorizeRequests().antMatchers("/refreshtoken/**", "/login/**").permitAll();
		http.authorizeRequests().anyRequest().authenticated();
//		http.authorizeRequests().antMatchers(HttpMethod.POST, "/user/**").hasAnyAuthority("SUPER ADMIN");
//		http.authorizeRequests().antMatchers(HttpMethod.POST, "/user/**").hasAnyAuthority("ADMIN");
//		http.authorizeRequests().antMatchers(HttpMethod.GET, "/users/**").hasAnyAuthority("USER");
		http.addFilter(new JwtAuthenticationFilter(authenticationManagerBean()));
		http.addFilterBefore(new JwtAuthorizationFilter(),  UsernamePasswordAuthenticationFilter.class);
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}
}