package dominion.application;

import com.google.inject.AbstractModule;

import dominion.application.model.SingleGameSettings;

public class GameSettingsModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(SingleGameSettings.class).toInstance(SingleGameSettings.INSTANCE);
	}

}
