package fr.isika.proj4al23.performbackspring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.isika.proj4al23.performbackspring.models.PerformUser;
import fr.isika.proj4al23.performbackspring.models.Preference;

public interface PerformUserRepository extends JpaRepository<PerformUser, Long> {

	PerformUser findByUsername(String username);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
	Boolean existsByIdAndPreferencesContains(Long userId, Preference preference);
	
	@Query("SELECT u.preferences FROM PerformUser u WHERE u.id = :userId")
	List<Preference> getAllUserPreferences(@Param("userId") Long userId);
}
