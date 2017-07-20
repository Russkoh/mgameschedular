package schedular.assignment; 
import static org.junit.Assert.*;

import org.junit.Test;

public class Testing {

	Service service = new Service();
	@Test
	public void test_adding_game_in_system() {
		
		Game g = new Game("Tennis", 2);
		assertEquals(service.createGame(g), "Game has been created");
	}
	
	@Test
	public void test_adding_game_without_name_in_system() {
		
		Game g = new Game(null, 4);
		assertEquals(service.createGame(g), "Game name should not be empty");
	}
	
	@Test
	public void test_adding_game_with_no_players() {
		
		Game g = new Game("Ping Pong", 0);
		assertEquals(service.createGame(g), "Number of players should not be zero");
	}
	
	@Test
	public void test_adding_duplicate_games() {
		Game g = new Game("Tennis", 2);
		Game gr = new Game("Tennis", 2);
		service.createGame(gr);
		assertEquals(service.createGame(g), "The game already exists");
	}
	
	@Test
	public void test_adding_null_game() {
		
		Game g = null;
		assertEquals(service.createGame(g), "The game object passed is null");
	}
	
	@Test
	public void test_adding_a_player() {
		
		
		Game tennis = new Game("Tennis", 2);
		Game badminton = new Game("Badminton", 4);
		Game football = new Game("football", 30);
		service.createGame(tennis);
		
		Game[] games = {tennis, badminton, football};
		Player p = new Player("Tom", games);
		
		assertEquals(service.createPlayer(p), "Player has been created");
	}
	
	@Test
	public void test_adding_player_that_does_not_play_any_games() {
		
		Game[] games = new Game[0];
		Player p = new Player("Tennis", games);
		
		assertEquals(service.createPlayer(p), "Player does not play any games listed in the system");
	}
	
	@Test
	public void test_adding_a_player_without_a_name() {
		Game tennis = new Game("Tennis", 2);
		service.createGame(tennis);
		Game[] games = {tennis};
		Player p = new Player(null, games);
		assertEquals(service.createPlayer(p), "Player name cannot be empty");
	}
	
	@Test
	public void test_adding_a_duplicate_player(){
		Game tennis = new Game("Tennis", 2);
		service.createGame(tennis);
		Game[] games = {tennis};
		Player roger = new Player("Roger", games);
		Player p = new Player("Roger", games);
		service.createPlayer(roger);
		assertEquals(service.createPlayer(p), "Player is already added");
	}
	
	@Test
	public void test_adding_a_null_player(){
	
		Player p = null;
		assertEquals(service.createPlayer(p), "Player object passed is null");
	}
	
	@Test
	public void test_adding_a_day(){
		Game tennis = new Game("Tennis", 2);
		Game basketball = new Game("Basketball", 20);
		service.createGame(tennis);
		service.createGame(basketball);
		Game[] games = {tennis, basketball};
		Day d = new Day("Day One", games);
		assertEquals(service.createDay(d), "Day has been created");
	}
	
	@Test
	public void test_adding_a_day_without_a_valid_game(){
		Game tennis = new Game("Tennis", 2);
		Game basketball = new Game("Basketball", 20);
		
		Game[] games = {tennis, basketball};
		Day d = new Day("Day One", games);
		assertEquals(service.createDay(d), "Day does not contain games that are in the system");
	}
	
	@Test
	public void test_adding_a_day_without_a_name(){
		Game tennis = new Game("Tennis", 2);
		service.createGame(tennis);
		Game basketball = new Game("Basketball", 20);
		service.createGame(basketball);
		Game[] games = {tennis, basketball};
		
		Day d = new Day(null, games);
		assertEquals(service.createDay(d), "Day name cannot be empty");
	}
	
	@Test
	public void test_adding_a_duplicate_day(){
		Game tennis = new Game("Tennis", 2);
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
	public void test_adding_a_null_day(){
		
		Day d = null;
		assertEquals(service.createDay(d), "Day object is null");
	}
	
	@Test
	public void test_getting_a_report_of_an_existing_game(){
		StringBuffer testBuffer  = new StringBuffer();
		Game tennis = new Game("Tennis", 2);
		Game[] games = {tennis};
		service.createGame(tennis);
		Day dr = new Day("Day One", games);
		service.createDay(dr);
		Player roger = new Player("Roger", games);
		service.createPlayer(roger);
		
		String gameName = "Tennis";
		testBuffer.append("\n *** Game-Wise Report ***");
		testBuffer.append("\n\nGame: " + gameName + " \n\nDates of " + gameName + " : \n");
		testBuffer.append("\n" + "Day One" + "\n");
		testBuffer.append("Player: "+"Roger"+ " ");
		testBuffer.append("\n");
		testBuffer.append("\n\n*** End of Game Wise Report ***");
		assertEquals(testBuffer.toString(),service.getGameWiseReport("Tennis"));
	}
	
	@Test
	public void test_getting_a_report_of_a_nonexistant_game(){
		

		String gameName = "Tennis";
		String gameNotFound= "Game is not found";
		
		assertEquals(gameNotFound,service.getGameWiseReport(gameName));
	}

	@Test
	public void test_getting_a_report_from_a_game_without_a_name(){
		
		String searchIsEmpty= "Search field is empty";
		
		assertEquals(searchIsEmpty,service.getGameWiseReport(null));
	}
	
	@Test
	public void test_getting_a_report_of_an_existing_player(){
		StringBuffer testBuffer  = new StringBuffer();
		Game tennis = new Game("Tennis", 2);

		service.createGame(tennis);
		
		Game[] games = {tennis};
		Player p = new Player("Tom", games);
		service.createPlayer(p);
		Day dr = new Day("Day One", games);
		service.createDay(dr);
		Player roger = new Player("Roger", games);
		service.createPlayer(roger);
		
		String playerName = "Roger";
		testBuffer.append("\n *** Player-Wise Report ***");
		testBuffer.append("\n\nPlayer: " + playerName + " \n\nGames " + playerName + " plays: \n\n");
		testBuffer.append("Game: " + "Tennis" + " ");
		testBuffer.append(" Date: ");
		testBuffer.append(dr.getName() + "\n");
		testBuffer.append("\n\n*** End of Player-Wise Report ***");
		assertEquals(testBuffer.toString(),service.getPlayerWiseReport(playerName));
	}
	
	@Test
	public void test_getting_a_report_of_a_nonexistant_player(){
		
		String playerName = "James";
		String playerNotFound= "Player is not found";
		
		assertEquals(playerNotFound,service.getPlayerWiseReport(playerName));
	}
	
	@Test
	public void test_getting_a_report_of_an_empty_player(){
		
		String playerName = null;
		String playerNameEmpty= "Player name is blank";
		
		assertEquals(playerNameEmpty,service.getPlayerWiseReport(playerName));
	}
	
	@Test
	public void test_getting_a_report_of_an_existing_day(){
		StringBuffer testBuffer  = new StringBuffer();
		Game tennis = new Game("Tennis", 2);
		Game[] games = {tennis};
		service.createGame(tennis);
		Day dr = new Day("Day One", games);
		service.createDay(dr);
		Player roger = new Player("Roger", games);
		service.createPlayer(roger);
		
		String dayName = "Day One";
		testBuffer.append("\n *** Day-Wise Report ***");
		testBuffer.append("\n\nDay: " + dayName + " \n\nGames on " + dayName + ": \n");
		testBuffer.append(tennis.getName() + "\n");
		testBuffer.append("Player: " + roger.getName());
		testBuffer.append("\n\n");
		testBuffer.append("** End of Day-Wise Report*** ");
		assertEquals(testBuffer.toString(),service.getDayWiseReport(dayName));
	}
	
	@Test
	public void test_getting_a_report_of_a_nonexistant_day(){
		
		String dayName = "Day Two";
		String dayNotFound= "Day is not found";
		
		assertEquals(dayNotFound,service.getDayWiseReport(dayName));
	}
	
	@Test
	public void test_getting_a_report_from_a_day_without_a_name(){
		
		String searchIsEmpty= "Search field is empty";
		
		assertEquals(searchIsEmpty,service.getDayWiseReport(null));
	}
	
}
