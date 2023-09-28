package fr.isika.proj4al23.performbackspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.isika.proj4al23.performbackspring.models.PerformUser;

public interface PerformUserRepository extends JpaRepository<PerformUser, Long> {

	PerformUser findByUsername(String username);
	
	Boolean existsByUsername(String username);
	
	Boolean existsByEmail(String email);
}
