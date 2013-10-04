package dominion.cards;

import dominion.cards.action.ActionCard;
import dominion.cards.treasure.TreasureCard;
import dominion.cards.victory.VictoryCard;
import dominion.game.DominionConstants;

public class CardFactory {
	private static CardFactory cardFactory = new CardFactory();
	
	// Original Dominion Cards
	public final static Card adventurer = cardFactory.new Adventurer();
	public final static Card cellar = cardFactory.new Cellar();
	public final static Card bureaucrat = cardFactory.new Bureaucrat();
	public final static Card chancellor = cardFactory.new Chancellor();
	public final static Card chapel = cardFactory.new Chapel();
	public final static Card councilRoom = cardFactory.new CouncilRoom();
	public final static Card feast = cardFactory.new Feast();
	public final static Card festival = cardFactory.new Festival();
	public final static Card laboratory = cardFactory.new Laboratory();
	public final static Card library = cardFactory.new Library();
	public final static Card market = cardFactory.new Market();
	public final static Card militia = cardFactory.new Militia();
	public final static Card mine = cardFactory.new Mine();
	public final static Card moat = cardFactory.new Moat();
	public final static Card moneylender = cardFactory.new Moneylender();
	public final static Card remodel = cardFactory.new Remodel();
	public final static Card smithy = cardFactory.new Smithy();
	public final static Card spy = cardFactory.new Spy();
	public final static Card thief = cardFactory.new Thief();
	public final static Card throneRoom = cardFactory.new ThroneRoom();
	public final static Card village = cardFactory.new Village();
	public final static Card witch = cardFactory.new Witch();
	public final static Card woodcutter = cardFactory.new Woodcutter();
	public final static Card workshop = cardFactory.new Workshop();
	public final static Card copper = cardFactory.new Copper();
	public final static Card silver = cardFactory.new Silver();
	public final static Card gold = cardFactory.new Gold();	

	public final static Card estate = cardFactory.new Gold();
	public final static Card duchy = cardFactory.new Gold();
	public final static Card province = cardFactory.new Gold();
	public final static Card gardens = cardFactory.new Gold();
	public final static Card curse = cardFactory.new Gold();
	
	// Original Dominion Cards
	public class Adventurer extends ActionCard {
		public int getCost() {
			return DominionConstants.SIX;
		}
		@Override
		public int getPlusBuys() {
			return 0;
		}
		@Override
		public int getPlusDraws() {
			return 0;
		}
		@Override
		public int getPlusTreasures() {
			return 0;
		}
		@Override
		public int getPlusActions() {
			return 0;
		}
	}
	public class Bureaucrat extends ActionCard {
		public int getCost() {
			return DominionConstants.FOUR;
		}
		@Override
		public int getPlusBuys() {
			return 0;
		}
		@Override
		public int getPlusDraws() {
			return 0;
		}
		@Override
		public int getPlusTreasures() {
			return 0;
		}
		@Override
		public int getPlusActions() {
			return 0;
		}
	}
	public class Cellar extends ActionCard {
		@Override
		public int getCost() {
			return DominionConstants.TWO;
		}
		@Override
		public int getPlusBuys() {
			return 0;
		}
		@Override
		public int getPlusDraws() {
			return 0;
		}
		@Override
		public int getPlusTreasures() {
			return 0;
		}
		@Override
		public int getPlusActions() {
			return 1;
		}
	}
	public class Chancellor extends ActionCard {
		public int getCost() {
			return DominionConstants.THREE;
		}
		@Override
		public int getPlusBuys() {
			return 0;
		}
		@Override
		public int getPlusDraws() {
			return 0;
		}
		@Override
		public int getPlusTreasures() {
			return DominionConstants.TWO;
		}
		@Override
		public int getPlusActions() {
			return 0;
		}
	}
	public class Chapel extends ActionCard {
		public int getCost() {
			return DominionConstants.TWO;
		}
		@Override
		public int getPlusBuys() {
			return 0;
		}
		@Override
		public int getPlusDraws() {
			return 0;
		}
		@Override
		public int getPlusTreasures() {
			return 0;
		}
		@Override
		public int getPlusActions() {
			return 0;
		}
	}
	public class CouncilRoom extends ActionCard {
		public int getCost() {
			return DominionConstants.FIVE;
		}
		@Override
		public int getPlusBuys() {
			return DominionConstants.ONE;
		}
		@Override
		public int getPlusDraws() {
			return DominionConstants.FOUR;
		}
		@Override
		public int getPlusTreasures() {
			return 0;
		}
		@Override
		public int getPlusActions() {
			return 0;
		}
	}
	public class Feast extends ActionCard {
		public int getCost() {
			return DominionConstants.FOUR;
		}
		@Override
		public int getPlusBuys() {
			return 0;
		}
		@Override
		public int getPlusDraws() {
			return 0;
		}
		@Override
		public int getPlusTreasures() {
			return 0;
		}
		@Override
		public int getPlusActions() {
			return 0;
		}
	}
	public class Festival extends ActionCard {
		public int getCost() {
			return DominionConstants.FIVE;
		}
		@Override
		public int getPlusBuys() {
			return DominionConstants.ONE;
		}
		@Override
		public int getPlusDraws() {
			return 0;
		}
		@Override
		public int getPlusTreasures() {
			return DominionConstants.TWO;
		}
		@Override
		public int getPlusActions() {
			return DominionConstants.FIVE;
		}
	}
	public class Laboratory extends ActionCard {
		public int getCost() {
			return DominionConstants.FIVE;
		}
		@Override
		public int getPlusBuys() {
			return 0;
		}
		@Override
		public int getPlusDraws() {
			return DominionConstants.TWO;
		}
		@Override
		public int getPlusTreasures() {
			return 0;
		}
		@Override
		public int getPlusActions() {
			return DominionConstants.ONE;
		}
	}
	public class Library extends ActionCard {
		public int getCost() {
			return DominionConstants.FIVE;
		}
		@Override
		public int getPlusBuys() {
			return 0;
		}
		@Override
		public int getPlusDraws() {
			return 0;
		}
		@Override
		public int getPlusTreasures() {
			return 0;
		}
		@Override
		public int getPlusActions() {
			return 0;
		}
	}
	public class Market extends ActionCard {
		public int getCost() {
			return DominionConstants.FIVE;
		}
		@Override
		public int getPlusBuys() {
			return DominionConstants.ONE;
		}
		@Override
		public int getPlusDraws() {
			return DominionConstants.ONE;
		}
		@Override
		public int getPlusTreasures() {
			return DominionConstants.ONE;
		}
		@Override
		public int getPlusActions() {
			return DominionConstants.ONE;
		}
	}
	public class Militia extends ActionCard {
		public int getCost() {
			return DominionConstants.FOUR;
		}
		@Override
		public int getPlusBuys() {
			return 0;
		}
		@Override
		public int getPlusDraws() {
			return 0;
		}
		@Override
		public int getPlusTreasures() {
			return DominionConstants.TWO;
		}
		@Override
		public int getPlusActions() {
			return 0;
		}
	}
	public class Mine extends ActionCard {
		public int getCost() {
			return DominionConstants.FIVE;
		}
		@Override
		public int getPlusBuys() {
			return 0;
		}
		@Override
		public int getPlusDraws() {
			return 0;
		}
		@Override
		public int getPlusTreasures() {
			return 0;
		}
		@Override
		public int getPlusActions() {
			return 0;
		}
	}
	public class Moat extends ActionCard {
		public int getCost() {
			return DominionConstants.TWO;
		}
		@Override
		public boolean isReaction() {
			return true;
		}
		@Override
		public int getPlusBuys() {
			return 0;
		}
		@Override
		public int getPlusDraws() {
			return DominionConstants.TWO;
		}
		@Override
		public int getPlusTreasures() {
			return 0;
		}
		@Override
		public int getPlusActions() {
			return 0;
		}
	}
	public class Moneylender extends ActionCard {

		public int getCost() {
			return DominionConstants.FOUR;
		}
		
		@Override
		public int getPlusBuys() {
			return 0;
		}

		@Override
		public int getPlusDraws() {
			return 0;
		}

		@Override
		public int getPlusTreasures() {
			return 0;
		}

		@Override
		public int getPlusActions() {
			return 0;
		}
	}
	public class Remodel extends ActionCard {
		public int getCost() {
			return DominionConstants.FOUR;
		}
		@Override
		public int getPlusBuys() {
			return 0;
		}
		@Override
		public int getPlusDraws() {
			return 0;
		}
		@Override
		public int getPlusTreasures() {
			return 0;
		}
		@Override
		public int getPlusActions() {
			return 0;
		}
	}
	public class Smithy extends ActionCard {

		public int getCost() {
			return DominionConstants.FOUR;
		}
		
		@Override
		public int getPlusBuys() {
			return 0;
		}

		@Override
		public int getPlusDraws() {
			return DominionConstants.THREE;
		}

		@Override
		public int getPlusTreasures() {
			return 0;
		}

		@Override
		public int getPlusActions() {
			return 0;
		}
	}
	public class Spy extends ActionCard {
		public int getCost() {
			return DominionConstants.FOUR;
		}
		@Override
		public int getPlusBuys() {
			return 0;
		}
		@Override
		public int getPlusDraws() {
			return DominionConstants.ONE;
		}
		@Override
		public int getPlusTreasures() {
			return 0;
		}
		@Override
		public int getPlusActions() {
			return DominionConstants.ONE;
		}
	}
	public class Thief extends ActionCard {
		public int getCost() {
			return DominionConstants.FOUR;
		}
		@Override
		public int getPlusBuys() {
			return 0;
		}
		@Override
		public int getPlusDraws() {
			return 0;
		}
		@Override
		public int getPlusTreasures() {
			return 0;
		}
		@Override
		public int getPlusActions() {
			return 0;
		}
	}
	public class ThroneRoom extends ActionCard {
		public int getCost() {
			return DominionConstants.FOUR;
		}
		@Override
		public int getPlusBuys() {
			return 0;
		}
		@Override
		public int getPlusDraws() {
			return 0;
		}
		@Override
		public int getPlusTreasures() {
			return 0;
		}
		@Override
		public int getPlusActions() {
			return 0;
		}
	}
	public class Village extends ActionCard {
		public int getCost() {
			return DominionConstants.THREE;
		}
		@Override
		public int getPlusBuys() {
			return 0;
		}
		@Override
		public int getPlusDraws() {
			return DominionConstants.ONE;
		}
		@Override
		public int getPlusTreasures() {
			return 0;
		}
		@Override
		public int getPlusActions() {
			return DominionConstants.TWO;
		}
	}
	public class Witch extends ActionCard {
		public int getCost() {
			return DominionConstants.FIVE;
		}
		@Override
		public int getPlusBuys() {
			return 0;
		}
		@Override
		public int getPlusDraws() {
			return DominionConstants.TWO;
		}
		@Override
		public int getPlusTreasures() {
			return 0;
		}
		@Override
		public int getPlusActions() {
			return 0;
		}
	}
	public class Woodcutter extends ActionCard {
		public int getCost() {
			return DominionConstants.THREE;
		}
		@Override
		public int getPlusBuys() {
			return 0;
		}
		@Override
		public int getPlusDraws() {
			return 0;
		}
		@Override
		public int getPlusTreasures() {
			return 0;
		}
		@Override
		public int getPlusActions() {
			return 0;
		}
	}
	public class Workshop extends ActionCard {
		public int getCost() {
			return DominionConstants.THREE;
		}
		@Override
		public int getPlusBuys() {
			return 0;
		}
		@Override
		public int getPlusDraws() {
			return 0;
		}
		@Override
		public int getPlusTreasures() {
			return 0;
		}
		@Override
		public int getPlusActions() {
			return 0;
		}
	}
	public class Copper extends TreasureCard {
		@Override
		public int getValue() {
			return DominionConstants.COPPER_WORTH;
		}
		@Override
		public int getCost() {
			return DominionConstants.ZERO;
		}
	}
	public class Silver extends TreasureCard {
		@Override
		public int getValue() {
			return DominionConstants.SILVER_WORTH;
		}
		@Override
		public int getCost() {
			return DominionConstants.THREE;
		}
	}
	public class Gold extends TreasureCard {
		@Override
		public int getValue() {
			return DominionConstants.GOLD_WORTH;
		}
		@Override
		public int getCost() {
			return DominionConstants.SIX;
		}
	}
	public class ProvinceCard extends VictoryCard {
		@Override
		public int getPoints() {
			return DominionConstants.PROVINCE_POINTS;
		}
		@Override
		public int getCost() {
			return DominionConstants.EIGHT;
		}
	}
	public class EstateCard extends VictoryCard {
		@Override
		public int getPoints() {
			return DominionConstants.ESTATE_POINTS;
		}
		@Override
		public int getCost() {
			return DominionConstants.TWO;
		}
	}
	public class DuchyCard extends VictoryCard {
		@Override
		public int getPoints() {
			return DominionConstants.DUCHY_POINTS;
		}
		@Override
		public int getCost() {
			return DominionConstants.FIVE;
		}
	}
	public class Gardens extends VictoryCard {
		public int getCost() {
			return DominionConstants.FOUR;
		}
	}
	public class Curse extends VictoryCard {
		@Override
		public int getPoints() {
			return DominionConstants.CURSE_POINTS;
		}
		@Override
		public int getCost() {
			return 0;
		}
	}
}
