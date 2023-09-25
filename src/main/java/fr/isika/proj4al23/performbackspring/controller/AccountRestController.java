package fr.isika.proj4al23.performbackspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.isika.proj4al23.performbackspring.models.PerformRole;
import fr.isika.proj4al23.performbackspring.models.PerformUser;
import fr.isika.proj4al23.performbackspring.service.AccountService;
import fr.isika.proj4al23.performbackspring.viewmodels.RoleToUserViewModel;

@RestController
@RequestMapping(path="/performmma")
public class AccountRestController {

	@Autowired
	private AccountService accountService;
	
	@GetMapping(path = "/users")
	public List<PerformUser> performUsers(){
		return accountService.listUsers();
	}
	
	@PostMapping(path = "/user")
	public PerformUser savePerformUser(@RequestBody PerformUser performUser) {
		return accountService.addNewUser(performUser);
	}
	
	@PostMapping(path = "/role")
	public PerformRole savePerformRole(@RequestBody PerformRole performRole) {
		return accountService.addNewRole(performRole);
	}
	
	@PostMapping(path = "/addroletouser")
	public void addRoleToUser(@RequestBody RoleToUserViewModel roleToUserViewModel) {
		accountService.addRoleToUser(roleToUserViewModel.getUsername(), roleToUserViewModel.getRoleName());
	}
}