package fr.isika.proj4al23.performbackspring.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Preference {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
    private String fights;
    private String wins;
    private String kowins;
    private String submissionwins;
    private String strikes;
    private String strikesratio;
    private String takedowns;
    private String takedowndefense;
    private String takedownsratio;
    private String ipsg;
    private Boolean isActive;
}
