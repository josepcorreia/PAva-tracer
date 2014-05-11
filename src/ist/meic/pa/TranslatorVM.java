package ist.meic.pa;

import javassist.CannotCompileException;
import javassist.ClassPool;
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
			CtField[] fields = cc.getDeclaredFields();
			for(CtField f : fields) {
				System.err.println(f);
			}

			CtMethod[] methods = cc.getDeclaredMethods();
			for(CtMethod m : methods){
				m.instrument(
						new ExprEditor() {
							public void edit(MethodCall m)
									throws CannotCompileException
									{
								int line = m.getLineNumber();
								try {
									String filename = m.getFileName();
									String methodname = m.getMethod().getLongName();
									String template = null;

									template ="{ist.meic.pa.Util.processArguments($args, \"" + filename + "\",\"" + methodname + "\",\"" + line + "\");"
											+"$_ = $proceed($$);"
											+ "ist.meic.pa.Util.processReturn($_,\""+ filename +"\",\"" + methodname + "\",\"" + line + "\");}";
									m.replace(template);
								} catch (NotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

									}
						});
				m.instrument(
						new ExprEditor() {
							public void edit(NewExpr ne)
									throws CannotCompileException
									{
								int line = ne.getLineNumber();

								String filename = ne.getFileName();
								String methodname = null;
								try {
									methodname = ne.getConstructor().getLongName();
								} catch (NotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								String template = null;

								template ="{ist.meic.pa.Util.processArguments($args, \"" + filename + "\",\"" + methodname + "\",\"" + line + "\");"
										+"$_ = $proceed($$);"
										+ "ist.meic.pa.Util.processReturn($_,\""+ filename +"\",\"" + methodname + "\",\"" + line + "\");}";
								ne.replace(template);

									}
						});
			}
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
