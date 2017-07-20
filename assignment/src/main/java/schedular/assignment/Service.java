package schedular.assignment;

public class Service implements ISchedularService{
	
	GameRepo gameReport = new GameRepo();
	PlayerRepo playerReport = new PlayerRepo();
	DayRepo dayReport = new DayRepo();
	
	/*Service(){
		
		Game tennis = new Game("Tennis", 2);
		Game basketball = new Game("Basketball", 20);
		
		createGame(basketball);
		createGame(tennis);
		
		Game[] timGames = {tennis};
		Game[] bball = {basketball};
		Player tim = new Player("Tim", timGames);
		Player roger = new Player("Roger", gameReport.findAll());
		createPlayer(tim);
		createPlayer(roger);
		
		Day d = new Day("Day One", timGames);
		
		Day d2 = new Day("Day Two", bball);
		createDay(d);
		createDay(d2);
	}*/
	
	public String createGame(Game g){
		if(g == null){
			return "The game object passed is null";
		}
		
		else if(g.getName() == null){
			return "Game name should not be empty";
		}
		else if(g.getNoOfPlayers() == 0){
			return "Number of players should not be zero";
		}
		else if(gameReport.findOne(g.getName())!= null){
			return "The game already exists";
		}
		else{
			return gameReport.save(g);
		}
	}
	
	public String createPlayer(Player p){
		
			if(p == null){
				return "Player object passed is null";
			}
			
			else if(p.getName() == null){
				return "Player name cannot be empty";
			}
			else if(playerGameChecker(p,gameReport) ){
				return "Player does not play any games listed in the system";
			}
			else if(playerReport.findOne(p.getName())!= null){
				return "Player is already added";
			}
			else{
				return playerReport.save(p);
			}
		
	}
	public String createDay(Day d){
		if(d == null){
			return "Day object is null";
		}
		
		else if(d.getName() == null){
			return "Day name cannot be empty";
		}
		else if(dayGameChecker(d,gameReport) ){
			return "Day does not contain games that are in the system";
		}
		else if(dayReport.findOne(d.getName())!= null){
			return "Day already exists";
		}
		else{
			return dayReport.save(d);
		}
	}
	
	public String getGameWiseReport(String name){
		if(name == null){
			return "Search field is empty"; 
		}
		else if(gameChecker(name,gameReport)){
			return "Game is not found";
		}
		else{
			return gameWiseReport(name);
		}
	}
	
	public String getPlayerWiseReport(String name){
		if(name == null){
			return "Player name is blank"; 
		}
		else if(playerChecker(name,gameReport)){
			return "Player is not found";
		}
		else{
			return playerWiseReport(name);
		}
	}
	
	public String getDayWiseReport(String name){
		if(name == null){
			return "Search field is empty"; 
		}
		else if(dayChecker(name,gameReport)){
			return "Day is not found";
		}
		else{
			return dayWiseReport(name);
		}
	}
	
	public String gameWiseReport(String name){
		
		
		
		StringBuffer buff = new StringBuffer();
		buff.append("\n *** Game-Wise Report ***");
		buff.append("\n\nGame: " + name + " \n\nDates of " + name + " : \n");
		for(int i = 0; i< dayReport.findAll().length;i++){
			for(int l = 0; l<dayReport.findAll()[i].getGames().length; l++ ){
				
			
				if(dayReport.findAll()[i].getGames()[l].getName() == name){
					buff.append("\n" + dayReport.findAll()[i].getName() + "\n");
					
				
					for(int j = 0;j<playerReport.findAll().length;j++){	
						for(int k = 0; k<playerReport.findAll()[j].getGames().length; k++)
						
							if(playerReport.findAll()[j].getGames()[k].getName() == name){	
								buff.append("Player: ");
								buff.append(playerReport.findAll()[j].getName() + " ");
							}
					}
					buff.append("\n");
				}
			}
		}
		buff.append("\n\n*** End of Game Wise Report ***");
		return buff.toString();
	}
		
	

	public String playerWiseReport(String playerName){
		
		StringBuffer buff = new StringBuffer();
		buff.append("\n *** Player-Wise Report ***");
		buff.append("\n\nPlayer: " + playerName + " \n\nGames " + playerName + " plays: \n\n");
		for(int i = 0; i< playerReport.findAll().length;i++){
			if(playerReport.findAll()[i].getName() == playerName){
				for(int j = 0; j< playerReport.findAll()[i].getGames().length;j++){
					buff.append("Game: " + playerReport.findAll()[i].getGames()[j].getName() + " ");
					
					for(int k = 0; k< dayReport.findAll().length; k++){
						for(int l = 0; l< dayReport.findAll()[k].getGames().length; l++){
							if(playerReport.findAll()[i].getGames()[j].getName() == dayReport.findAll()[k].getGames()[l].getName()){
								buff.append(" Date: ");
								
								buff.append(dayReport.findAll()[k].getName() + "\n");
							}
						}
					}
				}
			}
		}
		buff.append("\n\n*** End of Player-Wise Report ***");
		return buff.toString();
		
	}

	public String dayWiseReport(String dayName){
		
		StringBuffer buff = new StringBuffer();
		buff.append("\n *** Day-Wise Report ***");
		buff.append("\n\nDay: " + dayName + " \n\nGames on " + dayName + ": \n");
		for(int j = 0; j< dayReport.findAll().length; j++){
			if(dayReport.findAll()[j].getName() ==  dayName){
				for(int i = 0; i< dayReport.findAll()[j].getGames().length; i++){
					buff.append(dayReport.findAll()[j].getGames()[i].getName() + "\n");
					
					for(int k = 0 ; k < playerReport.findAll().length; k++){
						for(int l = 0; l < playerReport.findAll()[k].getGames().length; l++){
							if(playerReport.findAll()[k].getGames()[l].getName() == dayReport.findAll()[j].getGames()[i].getName())
								buff.append("Player: " + playerReport.findAll()[k].getName());
						}
						
					}
					buff.append("\n\n");	
				
				}
			}
		}
		buff.append("** End of Day-Wise Report*** ");
		return buff.toString();
	}

	boolean playerGameChecker(Player p, GameRepo gameReport){
		for(int i = 0; i< p.getGames().length; i++){
			for(int j = 0; j< gameReport.findAll().length; j++){
				if(p.getGames()[i] == gameReport.findAll()[j]){
					return false;
				}
			}
		}
		return true;
	}
	
	boolean dayGameChecker(Day d, GameRepo gameReport){
		for(int i = 0; i< d.getGames().length; i++){
			for(int j = 0; j< gameReport.findAll().length; j++){
				if(d.getGames()[i] == gameReport.findAll()[j]){
					return false;
				}
			}
		}
		return true;
	}
	
	boolean gameChecker(String g, GameRepo gameReport){
		for(int j = 0; j< gameReport.findAll().length; j++){
			if(gameReport.findAll()[j].getName() == g){
				return false;
			}
		}
		return true;
	}
	
	boolean playerChecker(String g, GameRepo gameReport){
		for(int j = 0; j< playerReport.findAll().length; j++){
			if(playerReport.findAll()[j].getName() == g){
				return false;
			}
		}
		return true;
	}
	boolean dayChecker(String g, GameRepo gameReport){
		for(int j = 0; j< dayReport.findAll().length; j++){
			if(dayReport.findAll()[j].getName() == g){
				return false;
			}
		}
		return true;
	}
	
}
