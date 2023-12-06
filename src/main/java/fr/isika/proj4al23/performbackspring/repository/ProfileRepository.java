package fr.isika.proj4al23.performbackspring.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import fr.isika.proj4al23.performbackspring.models.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

}