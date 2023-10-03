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
import fr.isika.proj4al23.performbackspring.service.UserAccountServiceImpl;
import fr.isika.proj4al23.performbackspring.viewmodels.RoleToUserViewModel;

@RestController
@RequestMapping(path="/performmma")
public class UserAccountController {

	@Autowired
	private UserAccountServiceImpl userAccountService;
	
	@GetMapping(path = "/users")
	@PostAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_MOD')")
	public List<PerformUser> performUsers(){
		return userAccountService.listUsers();
	}
	
	
	@PostMapping(path = "/role")
	@PostAuthorize("hasAuthority('SCOPE_ADMIN')")
	public PerformRole savePerformRole(@RequestBody PerformRole performRole) {
		return userAccountService.addNewRole(performRole);
	}
	
	@GetMapping(path = "/roles")
	@PostAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_MOD')")
	public List<PerformRole> performRoles(){
		return userAccountService.listRoles();
	}
	
	@PostMapping(path = "/addroletouser")
	@PostAuthorize("hasAuthority('SCOPE_ADMIN')")
	public void addRoleToUser(@RequestBody RoleToUserViewModel roleToUserViewModel) {
		userAccountService.addRoleToUser(roleToUserViewModel.getUsername(), roleToUserViewModel.getRoleName());
	}
}
