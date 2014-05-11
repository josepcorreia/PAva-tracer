package ist.meic.pa;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.Loader;
import javassist.NotFoundException;
import javassist.Translator;

public class TraceVMExtended {
	public static void main(String[] args){
		
		ClassPool cp = ClassPool.getDefault();
		Loader l = new Loader(cp);
		Translator te = new TranslatorVMExtended();
		try {
			l.addTranslator(cp, te);
		} catch (NotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (CannotCompileException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String[] restArgs = new String[args.length-1];
		System.arraycopy(args, 1, restArgs, 0, restArgs.length);
		try {
			l.run(args[0], restArgs);
			} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
