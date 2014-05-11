import ist.meic.pa.Trace;


class Test {
	Object o = new String("Batatas");
	
	public void test() {
        Trace.print(o);
    }
	
	private String foo() {
		return new String("Coiso");
	}
	
	{
	Trace.print(o);
	}
}

public class Test3 {

	 public static void main(String args[]) {
	        (new Test()).test();
	    }
}
