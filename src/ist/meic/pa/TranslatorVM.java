package ist.meic.pa;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.Translator;
import javassist.bytecode.BadBytecode;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.CodeIterator;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.Mnemonic;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

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
			if(cc.getName().equals("ist.meic.pa.Trace"))
				return;
			CtMethod[] methods = cc.getDeclaredMethods();
			for(CtMethod m : methods){
				m.instrument(
						new ExprEditor() {
							public void edit(MethodCall m)
									throws CannotCompileException
									{
								int line = m.getLineNumber();
								String template = "{  if($args.length > 0) { ist.meic.pa.Trace.addObject($args[0]); " +
										"ist.meic.pa.Trace.addInfo($args[0], \"->\" +"+ line+");} " +
										"$_ = $proceed($$); " +
										"if($_ != null){ ist.meic.pa.Trace.addObject($_); " +
										"ist.meic.pa.Trace.addInfo($_, \"<-\"+"+line+");}}";
								
								m.replace(template);
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
