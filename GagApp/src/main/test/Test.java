package main.test;
import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;

import cm.uds.fuchsia.gag.model.configuration.Configuration;
import cm.uds.fuchsia.gag.model.specification.DecompositionRule;
import cm.uds.fuchsia.gag.model.specification.Equation;
import cm.uds.fuchsia.gag.model.specification.FunctionDeclaration;
import cm.uds.fuchsia.gag.model.specification.FunctionExpression;
import cm.uds.fuchsia.gag.model.specification.GAG;
import cm.uds.fuchsia.gag.model.specification.Guard;
import cm.uds.fuchsia.gag.model.specification.IdExpression;
import cm.uds.fuchsia.gag.model.specification.Parameter;
import cm.uds.fuchsia.gag.model.specification.SemanticRule;
import cm.uds.fuchsia.gag.model.specification.Service;
import cm.uds.fuchsia.gag.ui.component.ComponentIHM;
import cm.uds.fuchsia.gag.specification.aspect.GAGAspect;
import groovy.lang.*;

public class Test {

static String classPath ="E:\\PhD Recherche\\Implementation\\workspace-java\\GagApp\\bin";
	
	public static void launchComponent(String componentName, String gagfile) {
		
	}
	
	public static void main (String[] args) {
		
		System.out.println("Hello world");
		
		Binding b =new Binding();
		b.setVariable("obj",5);
		GroovyShell gsh= new GroovyShell(b);
		GroovyClassLoader cl=gsh.getClassLoader();
		cl.addClasspath(classPath);
		//System.out.println(gsh.evaluate("main.test.Test.getFive(obj)"));
		GAG g =new GAG();
		g.setName("My GAG");
		Service S1= new Service();
		S1.setName("S1");
		Service S2= new Service();
		S2.setName("S2");
		S1.setAxiom(true);
		
		S1.getInputParameters().add(new Parameter("a"));
		S1.getInputParameters().add(new Parameter("b"));
		S1.getOutputParameters().add(new Parameter("c"));
		DecompositionRule s1r= new DecompositionRule();
		s1r.getSubServices().add(S2);
		s1r.getSubServices().add(S1);
		Guard guardSR1= new Guard();
		guardSR1.setLocation("Util.test");
		guardSR1.setMethod("g");
		s1r.setGuard(guardSR1);
		s1r.setName("R1");
		SemanticRule sem= new SemanticRule();
		FunctionDeclaration f1 = new FunctionDeclaration();
		f1.setName("f1");
		f1.setLocation("Util.test");
		f1.setMethod("f1");
		sem.getFunctionDeclarations().add(f1);
		s1r.setSemantic(sem);
		Equation eq1 = new Equation();
		IdExpression eq1l = new IdExpression();
		eq1l.setParameterName("a");
		eq1l.setServiceName("S1");
		IdExpression eq1r = new IdExpression();
		eq1r.setParameterName("b");
		eq1r.setServiceName("S2");
		eq1.setLeftpart(eq1l);
		eq1.setRightpart(eq1r);
		sem.getEquations().add(eq1);
		S1.getRules().add(s1r);
		g.getServices().add(S1);
		g.getServices().add(S2);
		FunctionExpression funcExpr = new FunctionExpression();
		funcExpr.setFunction(f1);
		funcExpr.getIdExpressions().add(eq1r);
		funcExpr.getIdExpressions().add(eq1l);
		Equation eq2 =new Equation();
		eq2.setLeftpart(eq1l);
		eq2.setRightpart(funcExpr);
		sem.getEquations().add(eq2);
		//new GAGAspect(g).run();
		JAXBContext ctx;
		 String currentPath;
		try {
			currentPath = new java.io.File(".").getCanonicalPath();

			 System.out.println("Current dir:" + currentPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ctx = JAXBContext.newInstance(GAG.class,Configuration.class,IdExpression.class, FunctionExpression.class);
			
			Marshaller msh = ctx.createMarshaller();
			Unmarshaller umsh = ctx.createUnmarshaller();
			/*
			msh.marshal(g, new File("C:\\Users\\TAGUEU\\Desktop\\file.xml"));
			//GAG mygag= (GAG) umsh.unmarshal(new File("C:\\Users\\TAGUEU\\Desktop\\file.xml"));
			//msh.marshal(mygag, new File("C:\\Users\\TAGUEU\\Desktop\\file1.xml"));
			*/
			GAG mygag= (GAG) umsh.unmarshal(new File("gag-specification\\gag.xml"));
			GAGAspect gasp=new GAGAspect(mygag);
			ComponentIHM window = new ComponentIHM();
			window.setVisible(true);
			window.disposeTheGraph(new GAGAspect(mygag));
			window.setTitle("My Component");
			//gasp.runWithExternalOuputInterface(window.getGraphLayout());
		} catch (JAXBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	}
	
	public static int getFive(int fiveObject) {
		
		return fiveObject;
	}
}
