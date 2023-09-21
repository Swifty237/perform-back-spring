package fr.isika.proj4al23.performbackspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.isika.proj4al23.performbackspring.models.PerformRole;

public interface PerformRoleRepository extends JpaRepository<PerformRole, Long> {

	PerformRole findByRoleName(String roleName);
}
