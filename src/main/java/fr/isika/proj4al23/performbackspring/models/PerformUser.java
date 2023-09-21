package fr.isika.proj4al23.performbackspring.models;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerformUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String email;
	private String username;
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER) // fetch = FetchType.EAGER => permet de charger directement les roles à l'inverse de LAZY qui est par défaut
	private Collection<PerformRole> roles = new ArrayList<>();
}
