package fr.isika.proj4al23.performbackspring;

//import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import fr.isika.proj4al23.performbackspring.models.PerformRole;
import fr.isika.proj4al23.performbackspring.models.PerformUser;
import fr.isika.proj4al23.performbackspring.service.UserAccountServiceImpl;

@Component
public class UsersAndRolesInit implements ApplicationRunner{

	@Autowired
	public UserAccountServiceImpl userAccountService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
			
//		PerformRole roleUser = new PerformRole();
//		roleUser.setRoleName("USER");
//		userAccountService.addNewRole(roleUser);
//		
//		PerformRole roleAdmin = new PerformRole();
//		roleAdmin.setRoleName("ADMIN");		
//		userAccountService.addNewRole(roleAdmin);
//		
//		PerformRole roleMod = new PerformRole();
//		roleMod.setRoleName("MOD");		
//		userAccountService.addNewRole(roleMod);
//		
//		PerformUser user1 = new PerformUser();
//		user1.setEmail("user1@example.com");
//		user1.setUsername("user1");
//		user1.setPassword("11111");
//		userAccountService.addNewUser(user1);
//		
//		PerformUser user2 = new PerformUser();
//		user2.setEmail("user2@example.com");
//		user2.setUsername("user2");
//		user2.setPassword("22222");
//		userAccountService.addNewUser(user2);
//		
//		PerformUser mod = new PerformUser();
//		mod.setEmail("mod@example.com");
//		mod.setUsername("mod");
//		mod.setPassword("mod");
//		userAccountService.addNewUser(mod);
//		
//		PerformUser admin = new PerformUser();
//		admin.setEmail("admin@example.com");
//		admin.setUsername("admin");
//		admin.setPassword("admin");
//		userAccountService.addNewUser(admin);
//					
//		userAccountService.addRoleToUser("user1", "USER");			
//		userAccountService.addRoleToUser("user2", "USER");
//		
//		userAccountService.addRoleToUser("mod", "USER");
//		userAccountService.addRoleToUser("mod", "MOD");
//		
//		userAccountService.addRoleToUser("admin", "USER");
//		userAccountService.addRoleToUser("admin", "MOD");
//		userAccountService.addRoleToUser("admin", "ADMIN");
	}
}
