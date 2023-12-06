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

import fr.isika.proj4al23.performbackspring.models.Profile;
import fr.isika.proj4al23.performbackspring.service.ProfileServiceImpl;

@RestController
@RequestMapping(path="/profile")
public class ProfileController {

	@Autowired
	private ProfileServiceImpl profileService;
	
	@PostMapping(path="/newprofile/{userId}")
	public Profile saveProfile(@PathVariable Long userId, @RequestBody Profile profile) {
		Profile newProfile = profileService.addProfile(userId, profile);
		if (newProfile != null) {
			return profileService.addProfileToUser(userId, newProfile);
		}
		return null;
	}
	
	
	@GetMapping(path="/profiles/{userId}")
	public List<Profile> loadAllProfiles(@PathVariable Long userId){
		return profileService.listUserProfiles(userId);
	}
	
	@GetMapping(path="/loadprofile/{userId}/{profileId}")
	public Profile loadProfile(@PathVariable Long userId, @PathVariable Long profileId) {
		return profileService.getUserProfile(userId, profileId);
	}
	
	@DeleteMapping(path="delete/{userId}/{profileId}")
	public void deleteProfile(@PathVariable Long userId, @PathVariable Long profileId) {
		profileService.deleteProfile(userId, profileId);
	}
}
