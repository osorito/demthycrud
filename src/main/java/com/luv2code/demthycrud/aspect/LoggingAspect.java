package com.luv2code.demthycrud.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;




@Aspect
@Component
public class LoggingAspect extends AOPExpressions{

	@Around("forServicePackage() || forControllerPackage() || forDAOPackage() || forEntityPackage() || getter()")
	public Object aroundMeasureTime(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable
	{
		//print out method we are advising on 
		String method = theProceedingJoinPoint.getSignature().toShortString();
		System.out.println("\n=====>>> Executing @Around on method: " + method);
		
		//get begin time stamp
		long begin = System.currentTimeMillis();
		
		// now, lets execute the method
		Object result = null;
		try {
			result = theProceedingJoinPoint.proceed();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			throw e;
		}
		
		//get end timestamp
		long end = System.currentTimeMillis();
		
		
		//compute duration and display it
		long duration = end - begin;
		System.out.println("\n=====>>> Duration: " + duration / 1000.0 + " seconds, method: " + method);
		return result;
	}	
	
	
	@AfterThrowing(
			pointcut = "forServicePackage() || forControllerPackage() || forDAOPackage() || forEntityPackage()",
			throwing = "theExc"
			)
	private void afterThrowing(JoinPoint theJoinPoint, Throwable theExc)
	{
		// print out which method we are advising on
		
			String method = theJoinPoint.getSignature().toShortString();
			System.out.println("\n=====>>> Executing @AfterThrowing on method: " + method);
		// log the exception
			System.out.println("\n=====>>> The exception is: " + theExc);
	}
	
	
	
	
	@Before("getter()")
	public void beforeAddAccountAdvice(JoinPoint theJoinPoint)
	{
		System.out.println("\n=====>> Executing @Before advice ");
		
		
		// display the method signature
		MethodSignature methodSignature = (MethodSignature)theJoinPoint.getSignature();
		System.out.println("Method: " + methodSignature);
		//display method arguments
		
		// get args
		Object[] args = theJoinPoint.getArgs();
		//loop the args
		for(Object tempArg:args)
		{
			System.out.println(tempArg);

		}
	}
	
	
}
