package fr.isika.proj4al23.performbackspring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.isika.proj4al23.performbackspring.models.PerformUser;
import fr.isika.proj4al23.performbackspring.models.Profile;

public interface PerformUserRepository extends JpaRepository<PerformUser, Long> {

	PerformUser findByUsername(String username);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
	Boolean existsByIdAndProfilesContains(Long userId, Profile profile);
	
	@Query("SELECT u.profiles FROM PerformUser u WHERE u.id = :userId")
	List<Profile> getAllUserProfiles(@Param("userId") Long userId);
}
