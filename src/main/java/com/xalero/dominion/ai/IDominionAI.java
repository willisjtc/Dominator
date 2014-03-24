package com.xalero.dominion.ai;

import com.xalero.dominion.server.model.DominionEventHandler;


public interface IDominionAI {

	public void setDominionEventHandler(DominionEventHandler dominionEventHandler);
	public void update(String event);
}
