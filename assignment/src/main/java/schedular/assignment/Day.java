package schedular.assignment;

public class Day {

	private String name;
	private Game[] games;
	
	public Day(){
		//Default constructor
		
		
	}
	
	public Day(String name, Game[] games){
		this.name = name;
		this.games = games;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public Game[] getGames(){
		return this.games;
	}
	
	public void setGames(Game[] games){
		this.games = games;
	}
}
