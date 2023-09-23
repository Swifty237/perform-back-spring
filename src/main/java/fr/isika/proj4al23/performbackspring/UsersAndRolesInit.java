package fr.isika.proj4al23.performbackspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import fr.isika.proj4al23.performbackspring.models.PerformRole;
import fr.isika.proj4al23.performbackspring.models.PerformUser;
import fr.isika.proj4al23.performbackspring.service.AccountService;

@Component
public class UsersAndRolesInit implements ApplicationRunner{

	@Autowired
	public AccountService accountService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
	
		PerformRole roleUser = new PerformRole();
		roleUser.setRoleName("USER");
		accountService.addNewRole(roleUser);
		
		PerformRole roleAdmin = new PerformRole();
		roleAdmin.setRoleName("ADMIN");		
		accountService.addNewRole(roleAdmin);
		
		PerformUser user1 = new PerformUser();
		user1.setEmail("user1@example.com");
		user1.setUsername("user1");
		user1.setPassword("1234");
		accountService.addNewUser(user1);
		
		PerformUser user2 = new PerformUser();
		user2.setEmail("user2@example.com");
		user2.setUsername("user2");
		user2.setPassword("5678");
		accountService.addNewUser(user2);
		
		PerformUser user3 = new PerformUser();
		user3.setEmail("user3@example.com");
		user3.setUsername("user3");
		user3.setPassword("9012");
		accountService.addNewUser(user3);
					
		accountService.addRoleToUser("user1", "USER");
		accountService.addRoleToUser("user1", "ADMIN");
			
		accountService.addRoleToUser("user2", "USER");
		accountService.addRoleToUser("user3", "USER");
	}
}
