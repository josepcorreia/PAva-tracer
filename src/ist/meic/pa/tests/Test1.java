package ist.meic.pa.tests;

import ist.meic.pa.Trace;

class TestC1 {
	public Object foo() {
		return new String("Foo");
	}
	public Object bar() {
		return foo();
	}
	public Object baz() {
		return bar();
	}
	public void test() {
		Trace.print(foo());
		Trace.print(bar());
		Trace.print(baz());
	}
}

public class Test1 {
	
	public static void main(String args[]) {
		(new TestC1()).test();
	}
}
