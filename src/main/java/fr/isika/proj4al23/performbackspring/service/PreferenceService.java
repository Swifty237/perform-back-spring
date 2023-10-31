package fr.isika.proj4al23.performbackspring.service;

import java.util.List;

import fr.isika.proj4al23.performbackspring.models.Preference;

public interface PreferenceService {

	Preference getUserPreference(Long userId, Long preferenceId);
	List<Preference> listUserPreferences(Long userId);
	Preference addPreference(Long userId, Preference preference);
	Preference addPreferenceToUser(Long userId, Preference preference);
	void deletePreference(Long userId, Long preferenceId);
//	Preference modifyPreference(Long userId, Long preferenceId, Preference modifiedPreference);
}