package com.xalero.dominion.cards;

import javafx.scene.image.Image;

import com.xalero.dominion.cards.action.ActionCard;
import com.xalero.dominion.cards.treasure.TreasureCard;
import com.xalero.dominion.cards.victory.VictoryCard;
import com.xalero.dominion.model.DominionConstants;
import com.xalero.dominion.model.DominionModel;
import com.xalero.dominion.model.Player;
import com.xalero.dominion.utils.Result;

public class CardFactory {
	private static CardFactory cardFactory = new CardFactory();
	private static final int numBaseKingdomCards = 25;
	private static final String baseCardsPath = "dominion/application/cards/base/";
	
	// Original Dominion Cards
	public static Card createCard(String card) throws Exception {
		// big switch statement for creating a card
		switch (card.toLowerCase()) {
		case "adventurer":
			return adventurer;
		case "cellar":
			return cellar;
		case "bureaucrat":
			return bureaucrat;
		case "chancellor":
			return chancellor;
		case "chapel":
			return chapel;
		case "council room":
			return councilRoom;
		case "feast":
			return feast;
		case "festival":
			return festival;
		case "laboratory":
			return laboratory;
		case "library":
			return library;
		case "market":
			return market;
		case "militia":
			return militia;
		case "mine":
			return mine;
		case "moat":
			return moat;
		case "moneylender":
			return moneylender;
		case "remodel":
			return remodel;
		case "smithy":
			return smithy;
		case "spy":
			return spy;
		case "thief":
			return thief;
		case "throne room":
			return throneRoom;
		case "village":
			return village;
		case "witch":
			return witch;
		case "woodcutter":
			return woodcutter;
		case "workshop":
			return workshop;
		case "copper":
			return copper;
		case "silver":
			return silver;
		case "gold":
			return gold;
		case "estate":
			return estate;
		case "duchy":
			return duchy;
		case "province":
			return province;
		case "gardens":
			return gardens;
		default: throw new Exception();
		}
		
	}
	
	public static Card[] getBaseKingdomCards() {
		Card[] baseCards = new Card[numBaseKingdomCards];
		baseCards[0] = adventurer;
		baseCards[1] = bureaucrat;
		baseCards[2] = cellar;
		baseCards[3] = chancellor;
		baseCards[4] = chapel;
		baseCards[5] = councilRoom;
		baseCards[6] = feast;
		baseCards[7] = festival;
		baseCards[8] = laboratory;
		baseCards[9] = library;
		baseCards[10] = market;
		baseCards[11] = militia;
		baseCards[12] = mine;
		baseCards[13] = moat;
		baseCards[14] = moneylender;
		baseCards[15] = remodel;
		baseCards[16] = smithy;
		baseCards[17] = spy;
		baseCards[18] = thief;
		baseCards[19] = throneRoom;
		baseCards[20] = village;
		baseCards[21] = witch;
		baseCards[22] = woodcutter;
		baseCards[23] = workshop;
		baseCards[24] = gardens;
		
		return baseCards;
	}
		
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

	public final static Card estate = cardFactory.new Estate();
	public final static Card duchy = cardFactory.new Duchy();
	public final static Card province = cardFactory.new Province();
	public final static Card gardens = cardFactory.new Gardens();
	public final static Card curse = cardFactory.new Curse();
	
	public class Adventurer extends ActionCard {
		public Adventurer() {
			super();
			id = 1;
			cardImage = new Image(baseCardsPath + "adventurer.jpg", true);
		}
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
		@Override
		public Result playCard(DominionModel dominionModel, long playerId) {
			return null;
		}
		@Override
		public String toString() {
			return "Adventurer";
		}
	}
	public class Bureaucrat extends ActionCard {
		public Bureaucrat() {
			super();
			id = 2;
			cardImage = new Image(baseCardsPath + "bureaucrat.jpg", true);
		}
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
		@Override
		public Result playCard(DominionModel dominionModel, long playerId) {
			return null;
		}
		@Override 
		public String toString() {
			return "Bureaucrat";
		}
	}
	public class Cellar extends ActionCard {
		public Cellar() {
			super();
			id = 3;
			cardImage = new Image(baseCardsPath + "cellar.jpg", true);
		}
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
		@Override
		public Result playCard(DominionModel dominionModel, long playerId) {
			return null;
		}
		@Override 
		public String toString() {
			return "Cellar";
		}
	}
	public class Chancellor extends ActionCard {
		public Chancellor() {
			super();
			id = 4;
			cardImage = new Image(baseCardsPath + "chancellor.jpg", true);
		}
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
		@Override
		public Result playCard(DominionModel dominionModel, long playerId) {
			return null;
		}
		@Override
		public String toString() {
			return "Chancellor";
		}
	}
	public class Chapel extends ActionCard {
		public Chapel() {
			super();
			id = 5;
			cardImage = new Image(baseCardsPath + "chapel.jpg", true);
		}
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
		@Override
		public Result playCard(DominionModel dominionModel, long playerId) {
			return null;
		}
		@Override
		public String toString() {
			return "Chapel";
		}
	}
	public class CouncilRoom extends ActionCard {
		public CouncilRoom() {
			super();
			id = 6;
			cardImage = new Image(baseCardsPath + "council_room.jpg", true);
		}
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
		@Override
		public Result playCard(DominionModel dominionModel, long playerId) {
			return null;
		}
		@Override
		public String toString() {
			return "Council Room";
		}
	}
	public class Feast extends ActionCard {
		public Feast() {
			super();
			id = 7;
			cardImage = new Image(baseCardsPath + "feast.jpg", true);
		}
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
		@Override
		public Result playCard(DominionModel dominionModel, long playerId) {
			return null;
		}
		@Override
		public String toString() {
			return "Feast";
		}
	}
	public class Festival extends ActionCard {
		public Festival() {
			super();
			id = 8;
			cardImage = new Image(baseCardsPath + "festival.jpg", true);
		}
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
		@Override
		public Result playCard(DominionModel dominionModel, long playerId) {
			return null;
		}
		@Override
		public String toString() {
			return "Festival";
		}
	}
	public class Laboratory extends ActionCard {
		public Laboratory() {
			super();
			id = 9;
			cardImage = new Image(baseCardsPath + "laboratory.jpg", true);
		}
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
		@Override
		public Result playCard(DominionModel dominionModel, long playerId) {
			return null;
		}
		@Override
		public String toString() {
			return "Laboratory";
		}
	}
	public class Library extends ActionCard {
		public Library() {
			super();
			id = 10;
			cardImage = new Image(baseCardsPath + "library.jpg", true);
		}
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
		@Override
		public Result playCard(DominionModel dominionModel, long playerId) {
			return null;
		}
		@Override
		public String toString() {
			return "Library";
		}
	}
	public class Market extends ActionCard {
		public Market() {
			super();
			id = 11;
			cardImage = new Image(baseCardsPath + "market.jpg", true);
		}
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
		@Override
		public Result playCard(DominionModel dominionModel, long playerId) {
			Player player = dominionModel.getPlayerById(playerId);
			Result result = player.canPlayAction(this);
			if (!result.isSuccess()) {
				return result;
			}
			
			player.removeAction();
			player.addAction();
			player.drawCard();
			player.addBuy();
			player.addMoney();
			player.addToDiscardFromHand(this);
			dominionModel.notifyObservers();
			
			return new Result(true, "Played Market");
		}
		@Override
		public String toString() {
			return "Market";
		}
	}
	public class Militia extends ActionCard {
		public Militia() {
			super();
			id = 12;
			cardImage = new Image(baseCardsPath + "militia.jpg", true);
		}
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
		@Override
		public Result playCard(DominionModel dominionModel, long playerId) {
			return null;
		}
		@Override
		public String toString() {
			return "Militia";
		}
	}
	public class Mine extends ActionCard {
		public Mine() {
			super();
			id = 13;
			cardImage = new Image(baseCardsPath + "mine.jpg", true);
		}
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
		@Override
		public Result playCard(DominionModel dominionModel, long playerId) {
			return null;
		}
		@Override
		public String toString() {
			return "Mine";
		}
	}
	public class Moat extends ActionCard {
		public Moat() {
			super();
			id = 14;
			cardImage = new Image(baseCardsPath + "moat.jpg", true);
		}
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
		@Override
		public Result playCard(DominionModel dominionModel, long playerId) {
			return null;
		}
		@Override
		public String toString() {
			return "Moat";
		}
	}
	public class Moneylender extends ActionCard {
		public Moneylender() {
			super();
			id = 15;
			cardImage = new Image(baseCardsPath + "moneylender.jpg", true);
		}
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
		@Override
		public Result playCard(DominionModel dominionModel, long playerId) {
			return null;
		}
		@Override
		public String toString() {
			return "Moneylender";
		}
	}
	public class Remodel extends ActionCard {
		public Remodel() {
			super();
			id = 16;
			cardImage = new Image(baseCardsPath + "remodel.jpg", true);
		}
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
		@Override
		public Result playCard(DominionModel dominionModel, long playerId) {
			return null;
		}
		@Override
		public String toString() {
			return "Remodel";
		}
	}
	public class Smithy extends ActionCard {
		public Smithy() {
			super();
			id = 17;
			cardImage = new Image(baseCardsPath + "smithy.jpg", true);
		}
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
		@Override
		public Result playCard(DominionModel dominionModel, long playerId) {
			return null;
		}
		@Override
		public String toString() {
			return "Smithy";
		}
	}
	public class Spy extends ActionCard {
		public Spy() {
			super();
			id = 18;
			cardImage = new Image(baseCardsPath + "spy.jpg", true);
		}
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
		@Override
		public Result playCard(DominionModel dominionModel, long playerId) {
			return null;
		}
		@Override
		public String toString() {
			return "Spy";
		}
	}
	public class Thief extends ActionCard {
		public Thief() {
			super();
			id = 19;
			cardImage = new Image(baseCardsPath + "thief.jpg", true);
		}
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
		@Override
		public Result playCard(DominionModel dominionModel, long playerId) {
			return null;
		}
		@Override
		public String toString() {
			return "Thief";
		}
	}
	public class ThroneRoom extends ActionCard {
		public ThroneRoom() {
			super();
			id = 20;
			cardImage = new Image(baseCardsPath + "throne_room.jpg", true);
		}
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
		@Override
		public Result playCard(DominionModel dominionModel, long playerId) {
			return null;
		}
		@Override
		public String toString() {
			return "Throne Room";
		}
	}
	public class Village extends ActionCard {
		public Village() {
			super();
			id = 21;
			cardImage = new Image(baseCardsPath + "village.jpg", true);
		}
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
		@Override
		public Result playCard(DominionModel dominionModel, long playerId) {
			Player player = dominionModel.getPlayerById(playerId);
			Result result = player.canPlayAction(this);
			if (!result.isSuccess()) {
				return result;
			}
			
			player.removeAction();
			player.addActions(2);
			player.drawCard();
			player.addToDiscardFromHand(this);
			dominionModel.notifyObservers();
			
			return new Result(true, "Played Village");
		}
		@Override
		public String toString() {
			return "Village";
		}
	}
	public class Witch extends ActionCard {
		public Witch() {
			super();
			id = 22;
			cardImage = new Image(baseCardsPath + "witch.jpg", true);
		}
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
		@Override
		public Result playCard(DominionModel dominionModel, long playerId) {
			return null;
		}
		@Override
		public String toString() {
			return "Witch";
		}
	}
	public class Woodcutter extends ActionCard {
		public Woodcutter() {
			super();
			id = 23;
			cardImage = new Image(baseCardsPath + "woodcutter.jpg", true);
		}
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
		@Override
		public Result playCard(DominionModel dominionModel, long playerId) {
			return null;
		}
		@Override
		public String toString() {
			return "Woodcutter";
		}
	}
	public class Workshop extends ActionCard {
		public Workshop() {
			super();
			id = 24;
			cardImage = new Image(baseCardsPath + "workshop.jpg", true);
		}
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
		@Override
		public Result playCard(DominionModel dominionModel, long playerId) {
			return null;
		}
		@Override
		public String toString() {
			return "Workshop";
		}
	}
	public class Copper extends TreasureCard {
		public Copper() {
			super();
			id = 25;
			cardImage = new Image(baseCardsPath + "copper.jpg", true);
		}
		@Override
		public int getValue() {
			return DominionConstants.COPPER_WORTH;
		}
		@Override
		public int getCost() {
			return DominionConstants.ZERO;
		}
		@Override
		public String toString() {
			return "Copper";
		}
	}
	public class Silver extends TreasureCard {
		public Silver() {
			super();
			id = 26;
			cardImage = new Image(baseCardsPath + "silver.jpg", true);
		}
		@Override
		public int getValue() {
			return DominionConstants.SILVER_WORTH;
		}
		@Override
		public int getCost() {
			return DominionConstants.THREE;
		}
		@Override
		public String toString() {
			return "Silver";
		}
	}
	public class Gold extends TreasureCard {
		public Gold() {
			super();
			id = 27;
			cardImage = new Image(baseCardsPath + "gold.jpg", true);
		}
		@Override
		public int getValue() {
			return DominionConstants.GOLD_WORTH;
		}
		@Override
		public int getCost() {
			return DominionConstants.SIX;
		}
		@Override
		public String toString() {
			return "Gold";
		}
	}
	public class Province extends VictoryCard {
		public Province() {
			super();
			id = 28;
			cardImage = new Image(baseCardsPath + "province.jpg", true);
		}
		@Override
		public int getPoints() {
			return DominionConstants.PROVINCE_POINTS;
		}
		@Override
		public int getCost() {
			return DominionConstants.EIGHT;
		}
		@Override
		public String toString() {
			return "Province";
		}
	}
	public class Estate extends VictoryCard {
		public Estate() {
			super();
			id = 29;
			cardImage = new Image(baseCardsPath + "estate.jpg", true);
		}
		@Override
		public int getPoints() {
			return DominionConstants.ESTATE_POINTS;
		}
		@Override
		public int getCost() {
			return DominionConstants.TWO;
		}
		@Override
		public String toString() {
			return "Estate";
		}
	}
	public class Duchy extends VictoryCard {
		public Duchy() {
			super();
			id = 30;
			cardImage = new Image(baseCardsPath + "duchy.jpg", true);
		}
		@Override
		public int getPoints() {
			return DominionConstants.DUCHY_POINTS;
		}
		@Override
		public int getCost() {
			return DominionConstants.FIVE;
		}
		@Override
		public String toString() {
			return "Duchy";
		}
	}
	public class Gardens extends VictoryCard {
		public Gardens() {
			super();
			id = 31;
			cardImage = new Image(baseCardsPath + "gardens.jpg", true);
		}
		public int getCost() {
			return DominionConstants.FOUR;
		}
		@Override
		public String toString() {
			return "Gardens";
		}
	}
	public class Curse extends VictoryCard {
		public Curse() {
			super();
			id = 32;
			cardImage = new Image(baseCardsPath + "curse.jpg", true);
		}
		@Override
		public int getPoints() {
			return DominionConstants.CURSE_POINTS;
		}
		@Override
		public int getCost() {
			return 0;
		}
		@Override
		public String toString() {
			return "Curse";
		}
	}
}
