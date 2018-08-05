package com.ljheee.tk.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 1、加入依赖
 * 2、spring-xxx,xml中开启aop
 * 3、编写MonitorAop
 * <p>
 * 目标：service 下所有方法执行时间统计。
 * 该切面就是一个POJO，可以在该切面中进行切入点及通知定义
 */
@Aspect
@Component
public class MonitorAop {

    private static final Logger logger = Logger.getLogger("MonitorAop");


    /**
     * http://zhuchengzzcc.iteye.com/blog/1504014
     * 环绕通知
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("within(com.ljheee.tk.service.*) && execution(public * *(..))")
    public Object monitor(ProceedingJoinPoint pjp) throws Throwable {//ProceedingJoinPoint：用于环绕通知
        String targetClassName = pjp.getTarget().getClass().getSimpleName();
        String methodName = pjp.getSignature().getName();
        String methodFullName = targetClassName + "." + methodName;

        Object[] args = pjp.getArgs();// 方法参数

        Object result = null;
        try {
            logger.setLevel(Level.INFO);
            logger.info("methodFullName=" + methodFullName);
            long begin = System.currentTimeMillis();
            result = pjp.proceed();

            long delay = System.currentTimeMillis() - begin;
            logger.info("delay=" + delay);

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            logger.info("methodFullName=" + methodFullName + ",result=" + result);
        }

        return result;
    }
}
