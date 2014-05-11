package ist.meic.pa;

import java.util.ArrayList;
import java.util.IdentityHashMap;

import javassist.CtClass;

public class Trace {
	private static IdentityHashMap<Object, ArrayList<String>> info = new IdentityHashMap<Object, ArrayList<String>>();

	public static void addInfo(Object o, String s){
		if(info.containsKey(o)){
			info.get(o).add(s);
		}
		else{
			info.put(o, new ArrayList<String>());
			info.get(o).add(s);
		}
	}

	public static void print(Object o){
		if(!info.containsKey(o))
			Util.printString("Tracing for " + o.toString() + " is nonexistent!");		
		else{
			Util.printString("Tracing for " + o.toString());
			for(String s: info.get(o)){
				System.err.println(s);
			}
		}
	}
}
