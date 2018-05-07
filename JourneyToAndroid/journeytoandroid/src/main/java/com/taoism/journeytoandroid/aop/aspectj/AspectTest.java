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


}
