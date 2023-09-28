package fr.isika.proj4al23.performbackspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.isika.proj4al23.performbackspring.models.PerformRole;
import fr.isika.proj4al23.performbackspring.models.PerformUser;
import fr.isika.proj4al23.performbackspring.service.UserAccountService;
import fr.isika.proj4al23.performbackspring.viewmodels.RoleToUserViewModel;

@RestController
@RequestMapping(path="/performmma")
public class UserAccountController {

	@Autowired
	private UserAccountService accountService;
	
	@GetMapping(path = "/users")
	@PostAuthorize("hasAnyAuthority('ADMIN', 'MOD')")
	public List<PerformUser> performUsers(){
		return accountService.listUsers();
	}
	
	
	@PostMapping(path = "/role")
	@PostAuthorize("hasAuthority('ADMIN')")
	public PerformRole savePerformRole(@RequestBody PerformRole performRole) {
		return accountService.addNewRole(performRole);
	}
	
	@PostMapping(path = "/addroletouser")
	@PostAuthorize("hasAuthority('ADMIN')")
	public void addRoleToUser(@RequestBody RoleToUserViewModel roleToUserViewModel) {
		accountService.addRoleToUser(roleToUserViewModel.getUsername(), roleToUserViewModel.getRoleName());
	}
}
