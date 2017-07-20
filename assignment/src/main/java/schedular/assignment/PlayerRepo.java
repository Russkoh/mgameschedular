package schedular.assignment;

public class PlayerRepo implements IPlayerRepo{
	
	private Player[] players = new Player[0];

	public String save(Player p){
		adder();
		players[players.length - 1] = p;
		return "Player has been created";
	}
	
	public Player findOne(String name){
		for(int i = 0; i < players.length; i++){
			if(players[i].getName() == name){
				return players[i];
			}
		}
		return null;
	}
	public Player[] findAll(){
		return this.players;
	}
	
	void adder(){

		Player[] copyOfPlayers = new Player[players.length + 1];
		
		for(int i = 0; i<players.length; i++){
			copyOfPlayers[i] = players[i];
		}
		players = copyOfPlayers;
		
	}

}
