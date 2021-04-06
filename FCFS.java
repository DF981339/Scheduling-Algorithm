import java.util.ArrayList;

public class FCFS {
	int totalTime = 0;
	int countTime;
	
	// Constructor
	public FCFS(ArrayList<Thread> threadAList) {
		
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
			if(processNext.get(0).getTime() <= 0){
				processNext.remove(0);
			}
			
			// Check if the queue is empty
			if(processNext.isEmpty()){
				break;
			}
			
			// Next process
			sb.append(",");
			sb.append(processNext.get(0).getID());
		}
		System.out.println(sb.toString());
	}
}
