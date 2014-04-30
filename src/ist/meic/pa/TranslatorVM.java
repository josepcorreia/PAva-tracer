package ist.meic.pa;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import javassist.Translator;

public class TranslatorVM implements Translator {

	@Override
	public void onLoad(ClassPool arg0, String arg1) throws NotFoundException,
			CannotCompileException {
		// TODO Auto-generated method stub
		System.err.println("A carregar a classe: "+ arg1);

		CtClass cc = null;
		try {
			if(arg1 != null) {				
				cc = arg0.get(arg1);
			}
			System.out.println(cc);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void start(ClassPool arg0) throws NotFoundException,
			CannotCompileException {
		// TODO Auto-generated method stub
		
	}

}
