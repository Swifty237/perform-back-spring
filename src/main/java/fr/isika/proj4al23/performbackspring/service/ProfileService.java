package fr.isika.proj4al23.performbackspring.service;

import java.util.List;

import fr.isika.proj4al23.performbackspring.models.Profile;

public interface ProfileService {

	Profile getUserProfile(Long userId, Long profileId);
	List<Profile> listUserProfiles(Long userId);
	Profile addProfile(Long userId, Profile profile);
	Profile addProfileToUser(Long userId, Profile profile);
	void deleteProfile(Long userId, Long profileId);
//	Preference modifyPreference(Long userId, Long preferenceId, Preference modifiedPreference);
}