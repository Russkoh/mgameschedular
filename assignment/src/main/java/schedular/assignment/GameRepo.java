package schedular.assignment;

public class GameRepo {

	private Game[] games = new Game[0];
	
	public String save(Game g){
		adder();
		games[games.length - 1] = g;
		return "Game has been created";
	}
	
	public Game findOne(String name){
		for(int i = 0; i < games.length; i++){
			if(games[i].getName() == name){
				return games[i];
			}
		}
		return null;
	}
	public Game[] findAll(){
		return this.games;
	}
	
	void adder(){

		Game[] copyOfGames = new Game[games.length + 1];
		
		for(int i = 0; i<games.length; i++){
			copyOfGames[i] = games[i];
		}
		games = copyOfGames;
		
	}
	
}
