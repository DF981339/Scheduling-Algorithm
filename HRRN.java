import java.util.*;

public class HRRN {
	int waitingTime;
	Double responseRatio;
	int countTime;
	int totalTime = 0;
	
	// Constructor
	public HRRN(ArrayList<Thread> threadAList) {
		
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
		ArrayList<KVpair> nextProcess = new ArrayList<>();
		
		// Start
		KVpair pair = new KVpair(a.get(0).GetProcessName(), a.get(0).GetServiceTime(), a.get(0).GetArrivalTime());
		nextProcess.add(pair);
		a.remove(0);
		
		// Add number of iterations of this process to StringBuffer
		for(int i = 0; i < nextProcess.get(0).getTime(); i++){
			sb.append(nextProcess.get(0).getID());
			sb.append(",");
		}
		sb.deleteCharAt(sb.length()-1);
		
		// Add A's service time to total time
		countTime += nextProcess.get(0).getTime();
		
		// Remove A from the queue
		nextProcess.remove(0);
		
		while(countTime < totalTime){
			
			// Check if a process is needed to pass into queue
			while(!a.isEmpty() && a.get(0).GetArrivalTime() <= countTime){
				KVpair p = new KVpair(a.get(0).GetProcessName(), a.get(0).GetServiceTime(), a.get(0).GetArrivalTime());
				nextProcess.add(p);
				a.remove(0);
			}
			
			// When there is only one process being scheduled
			if(nextProcess.size() == 1){
				
				// Add number of iterations of this process to StringBuffer
				for(int i = 0; i < nextProcess.get(0).getTime(); i++){
					sb.append(",");
					sb.append(nextProcess.get(0).getID());
				}
				
				// Add this process's service time to total time
				countTime += nextProcess.get(0).getTime();
				
				// Remove this process from process queue
				nextProcess.remove(0);
				
			}
			// When there are more than one processes being scheduled
			else{
				// Map for storing process name corresponding to it's ratio
				Map<Double, String> ratioCompare = new HashMap<>();
				
				// Get the ratio for each process that is scheduled to determine who is next
				for (Iterator<KVpair> i = nextProcess.iterator(); i.hasNext();) {
				    KVpair item = i.next();
				    
				    int arrival = item.getArrivalTime();
				    int service = item.getTime();
				    int waiting = getWaitingTime(countTime, arrival);
				    
				    Double ratio = getResponseRatio(waiting,service);
				    ratioCompare.put(ratio, item.getID());
				}				
				
				// Compare ratio
				String s = (String) getKeyWithMaxValue(ratioCompare);
				
				// Find the index of this process
				int index = 0;
				for (int i = 0; i < nextProcess.size(); i++) {
					if(s == nextProcess.get(i).getID()){
						index = i;
						break;
					}
				}
				
				// Add number of iterations of this process to StringBuffer
				for(int i = 0; i < nextProcess.get(index).getTime(); i++){
					sb.append(",");
					sb.append(s);
				}
				
				//  Add time to total time and remove
				countTime += nextProcess.get(index).getTime();
				nextProcess.remove(index);
			}
		}
		System.out.println(sb.toString());
	}

	private int getWaitingTime(int countTime, int arrivalTime){
		waitingTime = countTime - arrivalTime;
		return waitingTime;
	}
	
	// Get the ratio to compare
	private Double getResponseRatio(int waitingTime, int serviceTime){
		responseRatio = ((double)waitingTime + (double)serviceTime) / (double)serviceTime;
		return responseRatio;
	}
	
	private static Object getKeyWithMaxValue(Map<Double, String> map) {
	    if (map.isEmpty()){
	        return null;
	    }
	    int length = map.size();
	    Collection<Double> c = map.keySet();
	    Object[] obj = c.toArray();
	    Arrays.sort(obj);
	    return map.get(obj[length-1]);
	}
}
