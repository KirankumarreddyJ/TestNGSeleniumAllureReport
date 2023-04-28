package listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;
import org.testng.internal.annotations.DisabledRetryAnalyzer;

public class AnnotationTransfer implements IAnnotationTransformer {
	//Overriding the transform method to set the RetryAnalyzer
		public void transform(ITestAnnotation testAnnotation, Class testClass, 
				Constructor testConstructor, Method testMethod) {
			testAnnotation.setRetryAnalyzer(RetryAnalyzer.class);
//			 Class<? extends IRetryAnalyzer> retry = testAnnotation.getRetryAnalyzerClass();
//			if (retry.getClass().getName().equals(DisabledRetryAnalyzer.class.getName()))
//				testAnnotation.setRetryAnalyzer(RetryAnalyzer.class);
		}
}
