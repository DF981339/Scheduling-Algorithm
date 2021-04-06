import java.util.*;

public class SRT {
	int countTime;
	int totalTime;
	int shortestTime;
	
	// Constructor
	public SRT(ArrayList<Thread> threadAList){
		
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
		ArrayList<KVpair> processNext = new ArrayList<>();
		
		// Start
		KVpair pair = new KVpair(a.get(0).GetProcessName(), a.get(0).GetServiceTime());
		processNext.add(pair);
		a.remove(0);
		sb.append(processNext.get(0).getID());
		
		while(countTime < totalTime){
			
			// Add 1 unit of time to check if there is needed to move on to next process
			countTime += 1;
			
			// Check if a process is needed to pass into queue
			if(!a.isEmpty() && countTime == a.get(0).GetArrivalTime()){
				KVpair pr = new KVpair(a.get(0).GetProcessName(), a.get(0).GetServiceTime());
				processNext.add(pr);
				a.remove(0);
			}
			
			// The one that was processed need to -1 unit of time
			int timeLeft = processNext.get(0).getTime() - 1;
			KVpair pr = new KVpair(processNext.get(0).getID(), timeLeft);
			processNext.add(0, pr);
			processNext.remove(1);
			
			// Check if this process's service time is equal to zero
			// If time is zero, remove from the process queue
			for(int x = 0; x < processNext.size(); x++){
				if(processNext.get(x).getTime() <= 0){
					processNext.remove(x);
				}
			}
			
			// Find the shortest remaining time in the queue
			// If there is only one process in the array list, this process has the shortest time
			if(!processNext.isEmpty()){
				shortestTime = processNext.get(0).getTime();
				for (int i = 0; i < processNext.size(); i++) {
					if(processNext.get(i).getTime() < shortestTime){
						shortestTime = processNext.get(i).getTime();
					}
				}
			}
			else{
				break;
			}
			
			// Use the shortest time to find the next process
			int index = 0;
			for (int i = 0; i < processNext.size(); i++) {
				// Find the index of the process with the shortest time
				if(shortestTime == processNext.get(i).getTime()){
					index = i;
					break;
				}
			}
			
			// Put the process with shortest time to the beginning of the process queue
			// Then remove from the current position
			KVpair p = new KVpair(processNext.get(index).getID(), processNext.get(index).getTime());
			processNext.add(0, p);;
			processNext.remove(index+1);
			
			// Next process
			sb.append(",");
			sb.append(processNext.get(0).getID());
		}
		System.out.println(sb.toString());
	}
}
