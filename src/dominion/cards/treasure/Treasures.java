package dominion.cards.treasure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import dominion.cards.Card;

public class Treasures {
	List<Card> platinum;
	List<Card> gold;
	List<Card> silver;
	List<Card> copper;
	
	public Treasures(List<Card> p,
					 List<Card> g,
					 List<Card> s,
					 List<Card> c) {
		platinum = p;
		gold = g;
		silver = s;
		copper = c;
		// TODO
	}

	public Card getPlatinum() {
		if (platinum.size() > 0) {
			return platinum.remove(0);	
		}
		return null;
	}
	
	public Card getGold() {
		// TODO 
		return null;
	}
	
	public Card getSilver() {
		// TODO
		return null;
	}
	
	public Card getCopper() {
		// TODO
		return null;
	}
	
	public Collection<Card> getPlatinums() {
		return platinum;
	}

	public Collection<Card> getGolds() {
		return gold;
	}

	public Collection<Card> getSilvers() {
		return silver;
	}

	public Collection<Card> getCoppers() {
		return copper;
	}
}
