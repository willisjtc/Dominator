package dominion.cards.expansion;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;

public enum Expansion {
	base (1, "base.jpg", "Base"),
	intrigue (2, "intrigue.jpg", "Intrigue"),
	seaside (3, "seaside.jpg", "Seaside"),
	alchemy (4, "alchemy.jpg", "Alchemy"),
	prosperity (4, "prosperity.jpg", "Prosperity"),
	cornucopia (5, "cornucopia.jpg", "Cornucopia"),
	hinterlands (6, "hinterlands.jpg", "Hinterlands"),
	dark_ages (7, "dark_ages.jpg", "Dark Ages"),
	guilds (8, "guilds.jpg", "Guilds"),
	promo (9, "promo.jpg", "Promos");

	
	private int id;
	private Image expansionImage;
	private static final String baseCardsPath = "dominion/application/expansions/";
	private final String displayName;
	private final SimpleBooleanProperty selected = new SimpleBooleanProperty(false);
	
	Expansion (int id, String imageName, String displayName) {
		this.id = id;
		this.expansionImage = new Image(baseCardsPath + imageName, true);
		this.displayName = displayName;
	}
	
	public int getId() {
		return id;
	}

	public Expansion getById(int id) {
		for (int i = 0; i < values().length; i++) {
			if (values()[i].id == id) {
				return values()[i];
			}
		}
		return null;
	}
	
	public Image getExpansionImage() {
		return expansionImage;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	
	public boolean getSelected() {
        return selected.get();
	}

	public void setSelected(boolean selected) {
		this.selected.set(selected);
	}	

	public SimpleBooleanProperty selectedProperty() {
        return selected;
	}

}
