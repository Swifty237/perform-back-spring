package fr.isika.proj4al23.performbackspring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.isika.proj4al23.performbackspring.models.PerformRole;
import fr.isika.proj4al23.performbackspring.models.PerformUser;
import fr.isika.proj4al23.performbackspring.repository.PerformRoleRepository;
import fr.isika.proj4al23.performbackspring.repository.PerformUserRepository;

@Service
@Transactional
public class UserAccountServiceImpl implements UserAccountService {
	
	@Autowired
	private PerformUserRepository performUserRepository;
	
	@Autowired
	private PerformRoleRepository performRoleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public PerformUser addNewUser(PerformUser performUser) {
		String pw = performUser.getPassword();
		performUser.setPassword(passwordEncoder.encode(pw));
		return performUserRepository.save(performUser);
	}

	@Override
	public PerformRole addNewRole(PerformRole performRole) {
		return performRoleRepository.save(performRole);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		PerformUser performUser = performUserRepository.findByUsername(username);
		PerformRole performRole = performRoleRepository.findByRoleName(roleName);
		performUser.getPerformRoles().add(performRole);
	}

	@Override
	public PerformUser loadUserByUsername(String username) {
		return performUserRepository.findByUsername(username);
	}

	@Override
	public List<PerformUser> listUsers() {
		return performUserRepository.findAll();
	}

	@Override
	public List<PerformRole> listRoles() {
		return performRoleRepository.findAll();
	}

	@Override
	public Boolean isExistsByUsername(String username) {
		return performUserRepository.existsByUsername(username);
	}

	@Override
	public Boolean isExistsByEmail(String email) {
		return performUserRepository.existsByEmail(email);
	}

	@Override
	public Boolean isExistsByRoleName(String roleName) {
		return performRoleRepository.existsByRoleName(roleName);
	}

}
