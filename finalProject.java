import java.io.*;
import java.util.*;

public class finalProject {

	public static void main(String[] args) throws IOException {
		
		// Store threads into array list
		ArrayList<Thread> threadAList = new ArrayList<>(5);
		
		// Read file content through command line argument
		File f = new File(args[0]);
		FileReader fr = new FileReader(args[0]);
		BufferedReader br = new BufferedReader(fr);
		
		// Add information into array list
		String s = br.readLine();
		while(s != null){
			StringTokenizer st = new StringTokenizer(s, ",");
			String process = st.nextToken();
			int arrivalTime = Integer.parseInt(st.nextToken());
			int serviceTime = Integer.parseInt(st.nextToken());
			
			Thread th = new Thread(process, arrivalTime, serviceTime);
			threadAList.add(th);

			s = br.readLine();
		}
		br.close();
		
		// Somehow the data loss while using the same array list, so I clone it
		ArrayList<Thread> threadAList1 = new ArrayList<Thread>(threadAList);
		ArrayList<Thread> threadAList2 = new ArrayList<Thread>(threadAList);
		ArrayList<Thread> threadAList3 = new ArrayList<Thread>(threadAList);
		ArrayList<Thread> threadAList4 = new ArrayList<Thread>(threadAList);
		
		System.out.println("File Name: " + f.getName());
		System.out.println();
		
		// FCFS
		System.out.println("FCFS");
		FCFS fcfs = new FCFS(threadAList1);
		
		// RR
		System.out.println("RR");
		RR rr = new RR(threadAList2);
		
		// HRRN
		System.out.println("HRRN");
		HRRN hrrn = new HRRN(threadAList3);
		
		// SRT
		System.out.println("SRT");
		SRT srt = new SRT(threadAList4);
	}

}
