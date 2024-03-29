package ist.meic.pa;

import java.util.ArrayList;
import java.util.HashMap;

public class Trace {
	private static HashMap<Object, ArrayList<String>> info = new HashMap<Object, ArrayList<String>>();
	
	public static void addObject(Object o, String s){
		info.put(o, new ArrayList<String>());
	}
	
	public static void addInfo(Object o, String s){
		info.get(o).add(s);
	}
	
	public static void print(Object o){
		if(!info.containsKey(o))
			System.err.println("Tracing for " + o.toString() + " is nonexistent!");		
		else{
			for(String s: info.get(o)){
				System.err.println(s);
			}
		}
	}	
}
