package com.luv2code.demthycrud.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AOPExpressions {
	
	@Pointcut("execution(* com.luv2code.demthycrud.service.*.*(..))")
	public void forServicePackage() {}
	
	
	@Pointcut("execution(* com.luv2code.demthycrud.controller.*.*(..))")
	public void forControllerPackage() {}
	
	@Pointcut("execution(* com.luv2code.demthycrud.dao.*.*(..))")
	public void forDAOPackage() {}
	
	@Pointcut("execution(* com.luv2code.demthycrud.entity.*.*(..))")
	public void forEntityPackage() {}
	
	@Pointcut("execution(* com.luv2code.demthycrud.*.*.*(..))")
	public void getter() {}
	
}
