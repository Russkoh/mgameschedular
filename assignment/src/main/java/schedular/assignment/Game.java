package schedular.assignment;

public class Game {

	private String name;
	private int noOfPlayers;
	
	public Game(){
		
	}
	
	public Game(String name, int noOfPlayers){
		this.name = name;
		this.noOfPlayers = noOfPlayers;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getNoOfPlayers(){
		return this.noOfPlayers;
	}
	
	public void setNoOfPlayers(int noOfPlayers){
		this.noOfPlayers = noOfPlayers;
	}
}
