import java.util.*;

public class RR {
	int quantum = 4;
	int countTime;
	int totalTime = 0;
	
	public RR(ArrayList<Thread> threadAList){
		
		// Use string buffer to collect all process
		StringBuffer sb = new StringBuffer();
		
		// Get the total time
		for(Thread th: threadAList){
			totalTime += th.GetServiceTime();
		}
		
		// Clone the threadAList
		ArrayList<Thread> a = threadAList;
		
		// Count time
		countTime = 0;
		
		// To store the rest of the process
		ArrayList<KVpair> processesLeft = new ArrayList<>();
		
		KVpair pair = new KVpair(a.get(0).GetProcessName(), a.get(0).GetServiceTime());
		processesLeft.add(pair);
		a.remove(0);
		
		while(countTime < totalTime){
			
			// No more time left for this process
			if(processesLeft.get(0).getTime() <= quantum){
				
				// Add number of iterations of this process to StringBuffer
				for(int i = 0; i < processesLeft.get(0).getTime(); i++){
					sb.append(",");
					sb.append(processesLeft.get(0).getID());
				}
				
				// Add this process's service time to total time
				countTime += processesLeft.get(0).getTime();
				
				// Find if other process needs to get into the queue or not
				// If other process pass into queue, then remove from array list
				while(!a.isEmpty() && a.get(0).GetArrivalTime() <= countTime){
					KVpair p = new KVpair(a.get(0).GetProcessName(), a.get(0).GetServiceTime());
					processesLeft.add(p);
					a.remove(0);
				}
				
				// Remove the this process so it can move on to next process
				processesLeft.remove(0);
				
			}
			// Still have service time for this process
			else{
				// Add number of iterations of this process to StringBuffer
				for(int i = 0; i < quantum; i++){
					sb.append(",");
					sb.append(processesLeft.get(0).getID());
				}
				
				// Add time quantum to total time
				countTime += quantum;
				
				// Find if other process needs to get into the queue or not
				// If other process pass into queue, then remove from array list
				while(!a.isEmpty() && a.get(0).GetArrivalTime() <= countTime){
					KVpair p = new KVpair(a.get(0).GetProcessName(), a.get(0).GetServiceTime());
					processesLeft.add(p);
					a.remove(0);
				}
				
				// Calculate the rest of time of this process
				int timeLeft = processesLeft.get(0).getTime() - quantum;
				
				// Make this process with the remaining time into a pair
				KVpair pr = new KVpair(processesLeft.get(0).getID(), timeLeft);
				
				// Adding this pair to the queue
				processesLeft.add(pr);
				
				// Remove the this process so it can move on to next process
				processesLeft.remove(0);
			}
		}
		sb.deleteCharAt(0);
		System.out.println(sb.toString());
	}
}
