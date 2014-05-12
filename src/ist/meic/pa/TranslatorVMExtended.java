package ist.meic.pa;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CodeConverter;
import javassist.CtBehavior;
import javassist.CtClass;
import javassist.NotFoundException;
import javassist.Translator;
import javassist.expr.Cast;
import javassist.expr.ExprEditor;
import javassist.expr.FieldAccess;
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
						exceptionHandlerInstrumentation());
				m.instrument(
						castInstrumentation());
				m.instrument(
						fieldInstrumentation());
			}
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private ExprEditor exceptionHandlerInstrumentation() {
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
	
	private ExprEditor castInstrumentation() {
		return new ExprEditor() {
			public void edit(Cast c) throws CannotCompileException {
				int line = c.getLineNumber();
				String filename = c.getFileName();
				String methodname = c.where().getLongName();
				String template = null;

				template = Util.generateCastTemplate(filename, methodname, line);
				c.replace(template);

			}
		};
	}

	
	private ExprEditor fieldInstrumentation() {
		return new ExprEditor() {
			public void edit(FieldAccess f) throws CannotCompileException {
				int line = f.getLineNumber();
				String filename = f.getFileName();
				String fieldname = f.getFieldName();
				String methodname = f.where().getLongName();
				String template = null;

				if(f.isReader())
					template = Util.generateFieldReadTemplate(filename, fieldname, methodname, line);
				else if(f.isWriter())
					template = Util.generateFieldWriteTemplate(filename, fieldname, methodname, line);
				f.replace(template);

			}
		};
	}
	
	@Override
	public void start(ClassPool arg0) throws NotFoundException,
			CannotCompileException {
		super.start(arg0);
		
	}

}
