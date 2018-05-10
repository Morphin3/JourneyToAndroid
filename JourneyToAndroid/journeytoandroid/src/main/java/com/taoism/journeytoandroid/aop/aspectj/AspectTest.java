package com.taoism.journeytoandroid.aop.aspectj;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Date: 2018-05-04
 * Time: 15:24
 * Author: chenfei
 * -----------------------------
 * MISSION
 */
@Aspect
public class AspectTest {

    @Before("execution(* android.app.Activity.on**(..))")
    public void onActivityMethodExecutionBefore(JoinPoint joinPoint) throws Throwable {
        String key = joinPoint.getSignature().toString();
        Log.d("AOP", "onActivityMethodExecutionBefore:" + key);
    }


    @Before("call(* android.app.Activity.on**(..))")
    public void onActivityMethodCallBefore(JoinPoint joinPoint) {
        String key = joinPoint.getSignature().toString();
        Log.d("AOP", "onActivityMethodCallBefore:" + key);
    }


//    call(* *..*(..))表示任意类的任意方法，被调用的 JPoint。

    @Before("execution(* *..testAOP(..))")
    public void beforeMethodTestAOPExecution(JoinPoint joinPoint) {
        String key = joinPoint.getSignature().toString();
        Log.d("AOP", "beforeMethodTestAOPExecution:" + key);
    }

    @Before("call(* *..testAOP(..))")
    public void beforeMethodTestAOPCall(JoinPoint joinPoint) {
        String key = joinPoint.getSignature().toString();
        Log.d("AOP", "beforeMethodTestAOPCall:" + key);
    }


    //    @Before("execution(* *..e(..))")
//    @Before("execution(* android..*.Log.e(..))")
//    @Before("execution(* android.util..Log+.e(..))")
//    @Before("execution(* android.util..Log.e(..))")
    @Before("call(* android.util.Log.e(..))")
    public void beforeLogE(JoinPoint joinPoint) {
        String key = joinPoint.getSignature().toString();
        Log.d("AOP", "before Log.E():" + key);

        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 1 && args[1].getClass() == String.class) {
            Log.d("AOP", "获取到的参数是" + args[0] + "," + args[1]);
        }
    }


//    @Pointcut("call(static * *(..))")
//    public void anyStaticOperation() {}
//
//    @Around("anyStaticOperation()")
//    public void aroundStaticMethods(ProceedingJoinPoint jp) throws Throwable {
//        Log.d("AOP", "before Log.E():");
//        jp.proceed();
//    }
}
