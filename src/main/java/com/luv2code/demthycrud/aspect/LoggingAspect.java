package com.luv2code.demthycrud.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingAspect extends AOPExpressions{

	@Around("forServicePackage() || forControllerPackage() || forDAOPackage() || forEntityPackage()")
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
			//log the exception
			System.out.println(e.getMessage());
			//result = "Major accident! But no worries, your private AOP helicopeter is on the way! ";
			//give user a custom message
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
			pointcut = "forServicePackage() || forControllerPackage())",
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
	
}
