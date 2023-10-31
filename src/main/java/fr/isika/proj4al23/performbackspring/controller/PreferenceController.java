package fr.isika.proj4al23.performbackspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.isika.proj4al23.performbackspring.models.Preference;
import fr.isika.proj4al23.performbackspring.service.PreferenceServiceImpl;

@RestController
@RequestMapping(path="/preference")
public class PreferenceController {

	@Autowired
	private PreferenceServiceImpl preferenceService;
	
	@PostMapping(path="/newpreference/{userId}")
	public Preference saveUserPreference(@PathVariable Long userId, @RequestBody Preference preference) {
		Preference newPreference = preferenceService.addPreference(userId, preference);
		if (newPreference != null) {
			return preferenceService.addPreferenceToUser(userId, newPreference);
		}
		return null;
	}
	
	
	@GetMapping(path="/preferences/{userId}")
	public List<Preference> loadAllPreferences(@PathVariable Long userId){
		return preferenceService.listUserPreferences(userId);
	}
	
	@GetMapping(path="/userpreference/{userId}/{preferenceId}")
	public Preference loadUserPreference(@PathVariable Long userId, @PathVariable Long preferenceId) {
		return preferenceService.getUserPreference(userId, preferenceId);
	}
	
	@DeleteMapping(path="delete/{userId}/{preferenceId}")
	public void deletePreference(@PathVariable Long userId, @PathVariable Long preferenceId) {
		preferenceService.deletePreference(userId, preferenceId);
	}
}
