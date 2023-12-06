package fr.isika.proj4al23.performbackspring.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.isika.proj4al23.performbackspring.models.PerformUser;
import fr.isika.proj4al23.performbackspring.models.Profile;
import fr.isika.proj4al23.performbackspring.repository.PerformUserRepository;
import fr.isika.proj4al23.performbackspring.repository.ProfileRepository;

@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {
	
	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private PerformUserRepository performUserRepository;

	@Override
	public Profile addProfile(Long userId, Profile profile) {
		List<Profile> profiles = performUserRepository.getAllUserProfiles(userId);
		for(Profile eltProfile : profiles) {
			if (eltProfile.getDescription().equals(profile.getDescription())) {
				return null;
			}
		}
		return profileRepository.save(profile);				
	}
	
	@Override
	public Profile getUserProfile(Long userId, Long profileId) {
		List<Profile> profiles = performUserRepository.getAllUserProfiles(userId);
		Optional<Profile> profile = profileRepository.findById(profileId);
		for(Profile fighterProfile : profiles) {
			if (fighterProfile.getId() == profileId) {
				return profile.get();
			}
		}
		return null;
	}

	@Override
	public List<Profile> listUserProfiles(Long userId) {
		return performUserRepository.getAllUserProfiles(userId);
	}

	@Override
	public Profile addProfileToUser(Long userId, Profile profile) {
		Optional<PerformUser> performUser = performUserRepository.findById(userId);
		if (profile != null) {
			performUser.get().getProfiles().add(profile);
			return profile;
		}
		return null;
	}


	@Override
	public void deleteProfile(Long userId, Long profileId) {
		Optional<Profile> profile = profileRepository.findById(profileId);
		Optional<PerformUser> performUser = performUserRepository.findById(userId);
		performUser.get().getProfiles().remove(profile.get());
		profileRepository.delete(profile.get());
	}

}
