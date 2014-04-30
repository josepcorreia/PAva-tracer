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
		
			CtMethod method = cc.getDeclaredMethod("foo");
			MethodInfo minfo = method.getMethodInfo();
			CodeAttribute ca = minfo.getCodeAttribute();
			CodeIterator ci = ca.iterator() ;
			while (ci.hasNext()) {
			    int index = ci.next();
			    int op = ci.byteAt(index);
			    System.err.println(Mnemonic.OPCODE[op]);
			}
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadBytecode e) {
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
