package com.lecheng.hello.fzgjj.AspectJ;
import android.content.Intent;

import com.blankj.utilcode.util.ActivityUtils;
import com.lecheng.hello.fzgjj.BlankActivity;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by 不听话的好孩子 on 2018/4/23.
 */

@Aspect
public class AspextJHandler {
    @Pointcut("execution(@com.lecheng.hello.fzgjj.AspectJ.CheckIfCanVisit * *(..))")
    public void methodCheckIfCanVisit(){}

//    @AfterReturning(pointcut = "methodCheckIfCanVisit()",returning = "result")
//    public void ifCanVisit(String result) {
//        System.out.println("ifCanVisit----"+result);
//        if(result.equals("{\"禁止\":\"\"}")&&!ActivityUtils.getTopActivity().getClass().getSimpleName().equals("BlankActivity")){
//            ActivityUtils.getTopActivity().startActivity(new Intent(ActivityUtils.getTopActivity(), BlankActivity.class));
//            ActivityUtils.finishAllActivities();
//        }
//    }
    @Around("methodCheckIfCanVisit()")
    public Object ifCanVisit2(ProceedingJoinPoint joinPoint) {
        String result=null;
        try {
            result = (String) joinPoint.proceed();
            if(result.equals("{\"禁止\":\"\"}")&&!ActivityUtils.getTopActivity().getClass().getSimpleName().equals("BlankActivity")){
                ActivityUtils.getTopActivity().startActivity(new Intent(ActivityUtils.getTopActivity(), BlankActivity.class));
                ActivityUtils.finishAllActivities();
                return null;
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }

}
