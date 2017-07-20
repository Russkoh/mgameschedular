package schedular.assignment;


public class Main {
	
	private Main(){
		//Default Cosntructor
	}
	
	public static void main(String[] args){
	Service service = new Service();
	
	//String buff = service.getGameWiseReport("Basketball");

	//String buff = service.getPlayerWiseReport("Roger");
	
	String buff = service.getDayWiseReport("Day One");
	
	System.out.print(buff);
	}


}