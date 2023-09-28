package fr.isika.proj4al23.performbackspring.service;

import java.util.List;

import fr.isika.proj4al23.performbackspring.models.PerformRole;
import fr.isika.proj4al23.performbackspring.models.PerformUser;

public interface UserAccountService {

	PerformUser addNewUser(PerformUser performUser);
	PerformRole addNewRole(PerformRole performRole);
	void addRoleToUser(String username, String roleName);
	PerformUser loadUserByUsername(String username);
	List<PerformUser> listUsers();
}
