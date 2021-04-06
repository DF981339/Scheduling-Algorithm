
public class KVpair {
	private String s;
	private int i1;
	private int i2;
	
	// Constructor
	KVpair(String str, int t1){
		s = str;
		i1 = t1;
	}
	
	KVpair(String str, int t1, int t2){
		s = str;
		i1 = t1;
		i2 = t2;
	}
	
	// Getter
	public String getID() { return s; }
	public int getTime() { return i1; }
	public int getArrivalTime() { return i2; }
}
