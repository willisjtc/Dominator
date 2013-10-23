package dominion.application;

import dominion.application.profile.ProfileViewDTO;

public class LoginManager {
	
	private ProfileViewDTO profileSelected;
	
	public LoginManager() {
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
