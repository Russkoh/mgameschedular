package schedular.assignment;

public class DayRepo implements IDayRepo {
	
	private Day[] days = new Day[0];

	public String save(Day d){
		adder();
		days[days.length - 1] = d;
		return "Day has been created";
	}
	
	public Day findOne(String name){
		for(int i = 0; i < days.length; i++){
			if(days[i].getName() == name){
				return days[i];
			}
		}
		return null;
	}
	public Day[] findAll(){
		return this.days;
	}
	
	void adder(){

		Day[] copyOfDays = new Day[days.length + 1];
		
		for(int i = 0; i<days.length; i++){
			copyOfDays[i] = days[i];
		}
		days = copyOfDays;
		
	}
	
}
