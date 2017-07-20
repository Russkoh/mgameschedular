package schedular.assignment; 

import static org.junit.Assert.*;

import org.junit.Test;

public class Testing {

	String tn = "Tennis";
	String rg = "Roger";
	Service service = new Service();
	@Test
	public void addingGameInSystem() {
		
		Game g = new Game(tn, 2);
		assertEquals(service.createGame(g), "Game has been created");
	}
	
	@Test
	public void addingGameWithoutNameInSystem() {
		
		Game g = new Game(null, 4);
		assertEquals(service.createGame(g), "Game name should not be empty");
	}
	
	@Test
	public void addingGameWithNoPlayers() {
		
		Game g = new Game("Ping Pong", 0);
		assertEquals(service.createGame(g), "Number of players should not be zero");
	}
	
	@Test
	public void addingDuplicateGames() {
		Game g = new Game(tn, 2);
		Game gr = new Game(tn, 2);
		service.createGame(gr);
		assertEquals(service.createGame(g), "The game already exists");
	}
	
	@Test
	public void addingNullGame() {
		
		Game g = null;
		assertEquals(service.createGame(g), "The game object passed is null");
	}
	
	@Test
	public void addingAPlayer() {
		
		
		Game tennis = new Game(tn, 2);
		Game badminton = new Game("Badminton", 4);
		Game football = new Game("football", 30);
		service.createGame(tennis);
		
		Game[] games = {tennis, badminton, football};
		Player p = new Player("Tom", games);
		
		assertEquals(service.createPlayer(p), "Player has been created");
	}
	
	@Test
	public void addingPlayerhatDoesNotPlayAnyGames() {
		
		Game[] games = new Game[0];
		Player p = new Player(tn, games);
		
		assertEquals(service.createPlayer(p), "Player does not play any games listed in the system");
	}
	
	@Test
	public void addingAPlayerWithoutName() {
		Game tennis = new Game(tn, 2);
		service.createGame(tennis);
		Game[] games = {tennis};
		Player p = new Player(null, games);
		assertEquals(service.createPlayer(p), "Player name cannot be empty");
	}
	
	@Test
	public void addingADuplicatePlayer(){
		Game tennis = new Game(tn, 2);
		service.createGame(tennis);
		Game[] games = {tennis};
		Player roger = new Player(rg, games);
		Player p = new Player(rg, games);
		service.createPlayer(roger);
		assertEquals(service.createPlayer(p), "Player is already added");
	}
	
	@Test
	public void addingNullPlayer(){
	
		Player p = null;
		assertEquals(service.createPlayer(p), "Player object passed is null");
	}
	
	@Test
	public void addingADay(){
		Game tennis = new Game(tn, 2);
		Game basketball = new Game("Basketball", 20);
		service.createGame(tennis);
		service.createGame(basketball);
		Game[] games = {tennis, basketball};
		Day d = new Day("Day One", games);
		assertEquals(service.createDay(d), "Day has been created");
	}
	
	@Test
	public void addingADayWithoutAValidGame(){
		Game tennis = new Game(tn, 2);
		Game basketball = new Game("Basketball", 20);
		
		Game[] games = {tennis, basketball};
		Day d = new Day("Day One", games);
		assertEquals(service.createDay(d), "Day does not contain games that are in the system");
	}
	
	@Test
	public void addingADayWithoutAName(){
		Game tennis = new Game(tn, 2);
		service.createGame(tennis);
		Game basketball = new Game("Basketball", 20);
		service.createGame(basketball);
		Game[] games = {tennis, basketball};
		
		Day d = new Day(null, games);
		assertEquals(service.createDay(d), "Day name cannot be empty");
	}
	
	@Test
	public void addingADuplicateDay(){
		Game tennis = new Game(tn, 2);
		service.createGame(tennis);
		Game basketball = new Game("Basketball", 20);
		service.createGame(basketball);
		Game[] games = {tennis, basketball};
		
		Day dr = new Day("Day One", games);
		service.createDay(dr);
		Day d = new Day("Day One", games);
		assertEquals(service.createDay(d), "Day already exists");
	}
	
	@Test
	public void addingANullDay(){
		
		Day d = null;
		assertEquals(service.createDay(d), "Day object is null");
	}
	
	@Test
	public void gettingAReportOfAnExistingGame(){
		StringBuilder testBuffer  = new StringBuilder();
		Game tennis = new Game(tn, 2);
		Game[] games = {tennis};
		service.createGame(tennis);
		Day dr = new Day("Day One", games);
		service.createDay(dr);
		Player roger = new Player(rg, games);
		service.createPlayer(roger);
		
		String gameName = tn;
		testBuffer.append("\n *** Game-Wise Report ***");
		testBuffer.append("\n\nGame: " + gameName + " \n\nDates of " + gameName + " : \n");
		testBuffer.append("\n" + "Day One" + "\n");
		testBuffer.append("Player: "+rg+ " ");
		testBuffer.append("\n");
		testBuffer.append("\n\n*** End of Game Wise Report ***");
		assertEquals(testBuffer.toString(),service.getGameWiseReport(tn));
	}
	
	@Test
	public void gettingAReportOfANonexistantGame(){
		
		String gameNotFound= "Game is not found";
		
		assertEquals(gameNotFound,service.getGameWiseReport(tn));
	}

	@Test
	public void gettingReportOfGameWithoutName(){
		
		String searchIsEmpty= "Search field is empty";
		
		assertEquals(searchIsEmpty,service.getGameWiseReport(null));
	}
	
	@Test
	public void gettingAReportOfAnExistingPlayer(){
		StringBuilder testBuffer  = new StringBuilder();
		Game tennis = new Game(tn, 2);

		service.createGame(tennis);
		
		Game[] games = {tennis};
		Player p = new Player("Tom", games);
		service.createPlayer(p);
		Day dr = new Day("Day One", games);
		service.createDay(dr);
		Player roger = new Player(rg, games);
		service.createPlayer(roger);
		
		String playerName = rg;
		testBuffer.append("\n *** Player-Wise Report ***");
		testBuffer.append("\n\nPlayer: " + playerName + " \n\nGames " + playerName + " plays: \n\n");
		testBuffer.append("Game: " + "Tennis" + " ");
		testBuffer.append(" Date: ");
		testBuffer.append(dr.getName() + "\n");
		testBuffer.append("\n\n*** End of Player-Wise Report ***");
		assertEquals(testBuffer.toString(),service.getPlayerWiseReport(playerName));
	}
	
	@Test
	public void gettingAReportOfANonexistantPlayer(){
		
		String playerName = "James";
		String playerNotFound= "Player is not found";
		
		assertEquals(playerNotFound,service.getPlayerWiseReport(playerName));
	}
	
	@Test
	public void gettingAReportOfAPlayerWithBlankName(){
		
		String playerName = null;
		String playerNameEmpty= "Player name is blank";
		
		assertEquals(playerNameEmpty,service.getPlayerWiseReport(playerName));
	}
	
	
	
	@Test
	public void gettingAReportOfANonexistantDay(){
		
		String dayName = "Day Two";
		String dayNotFound= "Day is not found";
		
		assertEquals(dayNotFound,service.getDayWiseReport(dayName));
	}
	
	@Test
	public void gettingReportOfNullDay(){
		
		String searchIsEmpty= "Search field is empty";
		
		assertEquals(searchIsEmpty,service.getDayWiseReport(null));
	}
	
}
