
public class Thread {
	private String process;
	private int arrivalTime;
	private int serviceTime;
	
	// Constructor
	public Thread(String process, int arrivalTime, int serviceTime){
		this.process = process;
		this.arrivalTime = arrivalTime;
		this.serviceTime = serviceTime;
	}
	
	// Getter for process name
	public String GetProcessName(){
		return process;
	}
	
	// Getter for arrival time
	public int GetArrivalTime(){
		return arrivalTime;
	}
	
	// Getter for service time
	public int GetServiceTime(){
		return serviceTime;
	}
	
	// Return a string with the information of a thread
	public String toString(){
		String result = process + " " + Integer.toString(arrivalTime) + " " + Integer.toString(serviceTime);
		return result;
	}
}
