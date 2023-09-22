package fr.isika.proj4al23.performbackspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.isika.proj4al23.performbackspring.models.PerformUser;
import fr.isika.proj4al23.performbackspring.service.AccountService;

@RestController
@RequestMapping(path="/performmma")
public class AccountRestController {

	@Autowired
	private AccountService accountService;
	
	@GetMapping(path = "/users")
	public List<PerformUser> performUsers(){
		return accountService.listUsers();
	}
}