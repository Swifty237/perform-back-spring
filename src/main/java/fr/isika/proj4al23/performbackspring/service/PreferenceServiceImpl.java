package fr.isika.proj4al23.performbackspring.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.isika.proj4al23.performbackspring.models.PerformUser;
import fr.isika.proj4al23.performbackspring.models.Preference;
import fr.isika.proj4al23.performbackspring.repository.PerformUserRepository;
import fr.isika.proj4al23.performbackspring.repository.PreferenceRepository;

@Service
@Transactional
public class PreferenceServiceImpl implements PreferenceService {
	
	@Autowired
	private PreferenceRepository preferenceRepository;
	
	@Autowired
	private PerformUserRepository performUserRepository;

	@Override
	public Preference addPreference(Long userId, Preference preference) {
		List<Preference> preferences = performUserRepository.getAllUserPreferences(userId);
		for(Preference eltPreference : preferences) {
			if (eltPreference.getName().equals(preference.getName())) {
				return null;
			}
		}
		return preferenceRepository.save(preference);				
	}
	
	@Override
	public Preference getUserPreference(Long userId, Long preferenceId) {
		List<Preference> preferences = performUserRepository.getAllUserPreferences(userId);
		Optional<Preference> preference = preferenceRepository.findById(preferenceId);
		for(Preference userPreference : preferences) {
			if (userPreference.getId() == preferenceId) {
				return preference.get();
			}
		}
		return null;
	}

	@Override
	public List<Preference> listUserPreferences(Long userId) {
		return performUserRepository.getAllUserPreferences(userId);
	}

	@Override
	public Preference addPreferenceToUser(Long userId, Preference preference) {
		Optional<PerformUser> performUser = performUserRepository.findById(userId);
		if (preference != null) {
			performUser.get().getPreferences().add(preference);
			return preference;
		}
		return null;
	}


	@Override
	public void deletePreference(Long userId, Long preferenceId) {
		Optional<Preference> preference = preferenceRepository.findById(preferenceId);
		Optional<PerformUser> performUser = performUserRepository.findById(userId);
		performUser.get().getPreferences().remove(preference.get());
		preferenceRepository.delete(preference.get());
	}

}
