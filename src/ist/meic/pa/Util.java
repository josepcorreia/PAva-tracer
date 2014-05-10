package ist.meic.pa;

import java.util.HashSet;
import java.util.Set;

public class Util {
	public static void processArguments(Object[] args, String filename, String methodname, String line){
		 if(args.length > 0) { 
			for(int i=0; i < args.length; i++){ 
				if(!args[i].getClass().isPrimitive()){
					ist.meic.pa.Trace.addObject(args[i]);
					ist.meic.pa.Trace.addInfo(args[i], "-> " + methodname + " on " + filename + ":" + line);
				}
			}
		 }
	}
	public static void processReturn(Object ret, String filename, String methodname, String line){
		if(ret != null){ 
			ist.meic.pa.Trace.addObject(ret); 
			ist.meic.pa.Trace.addInfo(ret, "<- " + methodname +" on " + filename + ":" + line);
		}
	}
	public static void processReturn(boolean ret, String filename, String methodname, String line){
			ist.meic.pa.Trace.addObject(ret); 
			ist.meic.pa.Trace.addInfo(ret, "<- " + methodname +" on " + filename + ":" + line);
	}
}

