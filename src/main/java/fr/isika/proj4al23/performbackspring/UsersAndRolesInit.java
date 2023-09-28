package fr.isika.proj4al23.performbackspring;

//import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import fr.isika.proj4al23.performbackspring.models.PerformRole;
import fr.isika.proj4al23.performbackspring.models.PerformUser;
import fr.isika.proj4al23.performbackspring.service.UserAccountService;

@Component
public class UsersAndRolesInit implements ApplicationRunner{

	@Autowired
	public UserAccountService accountService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
//		String texte = "Bonjour, ceci est une chaîne de caractères.";
//        Encoder en base64
//        String chaineBase64 = Base64.getEncoder().encodeToString(texte.getBytes());
//        System.out.println(chaineBase64);
	
//		PerformRole roleUser = new PerformRole();
//		roleUser.setRoleName("USER");
//		accountService.addNewRole(roleUser);
//		
//		PerformRole roleAdmin = new PerformRole();
//		roleAdmin.setRoleName("ADMIN");		
//		accountService.addNewRole(roleAdmin);
//		
//		PerformRole roleMod = new PerformRole();
//		roleMod.setRoleName("MOD");		
//		accountService.addNewRole(roleMod);
//		
//		PerformUser user1 = new PerformUser();
//		user1.setEmail("user1@example.com");
//		user1.setUsername("user1");
//		user1.setPassword("11111");
//		accountService.addNewUser(user1);
//		
//		PerformUser user2 = new PerformUser();
//		user2.setEmail("user2@example.com");
//		user2.setUsername("user2");
//		user2.setPassword("22222");
//		accountService.addNewUser(user2);
//		
//		PerformUser mod = new PerformUser();
//		mod.setEmail("mod@example.com");
//		mod.setUsername("mod");
//		mod.setPassword("mod");
//		accountService.addNewUser(mod);
//		
//		PerformUser admin = new PerformUser();
//		admin.setEmail("admin@example.com");
//		admin.setUsername("admin");
//		admin.setPassword("admin");
//		accountService.addNewUser(admin);
//					
//		accountService.addRoleToUser("user1", "USER");			
//		accountService.addRoleToUser("user2", "USER");
//		
//		accountService.addRoleToUser("mod", "USER");
//		accountService.addRoleToUser("mod", "MOD");
//		
//		accountService.addRoleToUser("admin", "USER");
//		accountService.addRoleToUser("admin", "MOD");
//		accountService.addRoleToUser("admin", "ADMIN");
	}
}
