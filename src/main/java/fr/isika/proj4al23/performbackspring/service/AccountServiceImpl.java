package fr.isika.proj4al23.performbackspring.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.isika.proj4al23.performbackspring.models.PerformRole;
import fr.isika.proj4al23.performbackspring.models.PerformUser;
import fr.isika.proj4al23.performbackspring.repository.PerformRoleRepository;
import fr.isika.proj4al23.performbackspring.repository.PerformUserRepository;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private PerformUserRepository performUserRepository;
	
	@Autowired
	private PerformRoleRepository performRoleRepository;

	@Override
	public PerformUser addNewUser(PerformUser performUser) {
		return performUserRepository.save(performUser);
	}

	@Override
	public PerformRole addNewRole(PerformRole performRole) {
		return performRoleRepository.save(performRole);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		PerformUser performUser = this.performUserRepository.findByUsername(username);
		PerformRole performRole = this.performRoleRepository.findByRoleName(roleName);
		performUser.getRoles().add(performRole);
	}

	@Override
	public PerformUser loadUserByUsername(String username) {
		return performUserRepository.findByUsername(username);
	}

	@Override
	public List<PerformUser> listUsers() {
		return performUserRepository.findAll();
	}

}
