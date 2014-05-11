package ist.meic.pa;

public class Util {
	
	public static String generateTemplate(String filename, String methodname,
			int line) {
		return "{ ist.meic.pa.Util.processArguments($args, \"" + filename + "\",\"" + methodname + "\",\"" + line + "\");"
				+"$_ = $proceed($$);"
				+ "ist.meic.pa.Util.processReturn($_,\""+ filename +"\",\"" + methodname + "\",\"" + line + "\");}";
	}
	
	public static void printString(String s) {
		System.err.println(s);
	}
	
	public static void processArguments(Object[] args, String filename, String methodname, String line){
		 if(args.length > 0) { 
			for(int i=0; i < args.length; i++){ 
				if(!args[i].getClass().isPrimitive()){
					ist.meic.pa.Trace.addInfo(args[i], "  -> " + methodname + " on " + filename + ":" + line);
				}
			}
		 }
	}
	public static void processReturn(Object ret, String filename, String methodname, String line){
		if(ret != null){ 
			ist.meic.pa.Trace.addInfo(ret, "  <- " + methodname +" on " + filename + ":" + line);
		}
	}
	public static void processReturn(boolean ret, String filename, String methodname, String line){
		// DO NOTHING
	}
	public static void processReturn(int ret, String filename, String methodname, String line){
		// DO NOTHING
	}
	public static void processReturn(double ret, String filename, String methodname, String line){
		// DO NOTHING
	}
	public static void processReturn(float ret, String filename, String methodname, String line){
		// DO NOTHING
	}
	public static void processReturn(short ret, String filename, String methodname, String line){
		// DO NOTHING
	}
	public static void processReturn(long ret, String filename, String methodname, String line){
		// DO NOTHING
	}
	public static void processReturn(char ret, String filename, String methodname, String line){
		// DO NOTHING
	}
	public static void processReturn(byte ret, String filename, String methodname, String line){
		// DO NOTHING
	}
}

