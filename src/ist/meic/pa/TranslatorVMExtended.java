package ist.meic.pa;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtBehavior;
import javassist.CtClass;
import javassist.NotFoundException;
import javassist.Translator;
import javassist.expr.ExprEditor;
import javassist.expr.Handler;
import javassist.expr.MethodCall;

/**
 * Print normal Translator info (by calling the superclass' methods) plus the extensions
 */
public class TranslatorVMExtended extends TranslatorVM implements Translator {

	@Override
	public void onLoad(ClassPool arg0, String arg1) throws NotFoundException,
			CannotCompileException {
		super.onLoad(arg0, arg1);
		
		CtClass cc = null;
		try {
			if(arg1 != null) {				
				cc = arg0.get(arg1);
			}
			if(cc.getName().equals("ist.meic.pa.Trace") || cc.getName().equals("ist.meic.pa.Util") )
				return;

			CtBehavior[] behaviors = cc.getDeclaredBehaviors();
			for(CtBehavior m : behaviors){
				m.instrument(
						methodCallInstrumentation());
			}
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private ExprEditor methodCallInstrumentation() {
		return new ExprEditor() {
			public void edit(Handler h) throws CannotCompileException {
				int line = h.getLineNumber();
				String filename = h.getFileName();
				String methodname = h.where().getLongName();
				String template = null;

				template = Util.generateExceptionTemplate(filename, methodname, line);
				h.insertBefore(template);

			}
		};
	}
	
	
	@Override
	public void start(ClassPool arg0) throws NotFoundException,
			CannotCompileException {
		super.start(arg0);
		
	}

}
