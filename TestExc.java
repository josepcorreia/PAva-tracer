import ist.meic.pa.Trace;

import java.util.*;

class throwableObj extends Throwable {
	public String toString(){
		return "I'm a throwable!";
	}
}

class Test {

	public Object identity(Object o) {
        return o;
    }

    public void test() {
        Object o = new String("MyObj");
        Throwable to = new throwableObj();

        identity(o);

        Trace.print(o);

	    try {
	    	throw to;
	        
        
	    } catch(Throwable ex) {
        	identity(ex);
        }
	    Trace.print(to);
        
    }
}

public class TestExc {
    public static void main(String args[]) {
        (new Test()).test();
    }
}