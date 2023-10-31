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
//		user1.setEmail("jean_dupont@example.com");
//		user1.setUsername("Jean");
//		user1.setPassword("jean");
//		userAccountService.addNewUser(user1);
//		
//		PerformUser user2 = new PerformUser();
//		user2.setEmail("fabricekouakou@example.com");
//		user2.setUsername("Fabrice");
//		user2.setPassword("fabrice");
//		userAccountService.addNewUser(user2);
//		
//		PerformUser mod = new PerformUser();
//		mod.setEmail("terencehill@example.com");
//		mod.setUsername("Terence");
//		mod.setPassword("terence");
//		userAccountService.addNewUser(mod);
//		
//		PerformUser admin = new PerformUser();
//		admin.setEmail("admin@example.com");
//		admin.setUsername("Yannick");
//		admin.setPassword("admin");
//		userAccountService.addNewUser(admin);
//					
//		userAccountService.addRoleToUser("Jean", "USER");			
//		userAccountService.addRoleToUser("Fabrice", "USER");
//		
//		userAccountService.addRoleToUser("Terence", "USER");
//		userAccountService.addRoleToUser("Terence", "MOD");
//		
//		userAccountService.addRoleToUser("Yannick", "USER");
//		userAccountService.addRoleToUser("Yannick", "MOD");
//		userAccountService.addRoleToUser("yannick", "ADMIN");
	}
}
