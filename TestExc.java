import ist.meic.pa.Trace;

import java.util.*;

class ThrowableObj extends Throwable {
	String s = "lol";
	Object oo;
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
        ThrowableObj to = new ThrowableObj();

        identity(o);
        o = new String(to.s);
        to.oo = o;

        Trace.print(o);
        

	    Throwable t = (Throwable)to;
        try {
	    	throw to;
	        
        
	    } catch(Throwable ex) {
        	identity(ex);
        }
	    Trace.print(to);
	    Trace.print(t);
        
    }
}

public class TestExc {
    public static void main(String args[]) {
        (new Test()).test();
    }
}