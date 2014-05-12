package ist.meic.pa;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtBehavior;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.Translator;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import javassist.expr.NewExpr;

public class TranslatorVM implements Translator {

	@Override
	public void onLoad(ClassPool arg0, String arg1) throws NotFoundException,
	CannotCompileException {

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
				m.instrument(
						instantiationInstrumentation());
			}
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return
	 */
	private ExprEditor instantiationInstrumentation() {
		return new ExprEditor() {
			public void edit(NewExpr ne)
					throws CannotCompileException
					{
				
				int line = ne.getLineNumber();
				String fileName = ne.getFileName();
				String constructorName = null;
				
				try {
					constructorName = ne.getConstructor().getLongName();
				} catch (NotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String template = null;

				template = Util.generateTemplate(fileName, constructorName, line);
				ne.replace(template);
					}
		};
	}

	/**
	 * @return
	 */
	private ExprEditor methodCallInstrumentation() {
		return new ExprEditor() {
			public void edit(MethodCall m)
					throws CannotCompileException
					{
				int line = m.getLineNumber();
				try {
					String filename = m.getFileName();
					String methodname = m.getMethod().getLongName();
					String template = null;

					template = Util.generateTemplate(filename, methodname, line);
					m.replace(template);
				} catch (NotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

					}
		};
	}

	@Override
	public void start(ClassPool arg0) throws NotFoundException,
	CannotCompileException {
		// TODO Auto-generated method stub

	}

}
