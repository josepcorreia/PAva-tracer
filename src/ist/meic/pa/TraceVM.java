package ist.meic.pa;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;

public class TraceVM {
	public static void main(String[] args){
		
		ClassPool pool = ClassPool.getDefault();
		CtClass cc = null;
		try {
			if(args[0] != null) {				
				cc = pool.get(args[0]);
			}
			System.out.println(cc);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
