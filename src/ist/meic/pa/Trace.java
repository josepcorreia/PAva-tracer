package ist.meic.pa;

import java.util.ArrayList;
import java.util.IdentityHashMap;

public class Trace {
	private static IdentityHashMap<Object, ArrayList<String>> info = new IdentityHashMap<Object, ArrayList<String>>();

	public static void addObject(Object o){
		if(!info.containsKey(o)){
			info.put(o, new ArrayList<String>());
		}
	}

	public static void addInfo(Object o, String s){
		// TODO Descobrir o bug que substitui o objecto actual pelo novo
		System.err.println("Antes " + info);
		info.get(o).add(s);
		System.err.println("Depois" + info);
	}

	public static void print(Object o){
		if(!info.containsKey(o))
			System.err.println("Tracing for " + o.toString() + " is nonexistent!");		
		else{
			System.err.println("Tracing for " + o.toString());
			for(String s: info.get(o)){
				System.err.println(s);
			}
		}
	}	
}
