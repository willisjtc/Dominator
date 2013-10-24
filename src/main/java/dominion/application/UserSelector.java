package dominion.application;

import dominion.application.profile.ProfileViewDTO;

public class UserSelector {
	
	private ProfileViewDTO profileSelected;
	
	public UserSelector() {
		profileSelected = null;
	}

	public ProfileViewDTO getProfileSelected() {
		return profileSelected;
	}

	public void setProfileSelected(ProfileViewDTO profileSelected) {
		this.profileSelected = profileSelected;
	}
	
	public void login(String username, String password) {
		
	}
}
