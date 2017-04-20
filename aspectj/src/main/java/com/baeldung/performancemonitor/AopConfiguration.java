package com.nklkarthi.performancemonitor;

import java.time.LocalDate;
import java.time.Month;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.interceptor.PerformanceMonitorInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AopConfiguration {
    
    @Pointcut("execution(public String com.nklkarthi.performancemonitor.PersonService.getFullName(..))")
    public void monitor() { }
    
    @Pointcut("execution(public int com.nklkarthi.performancemonitor.PersonService.getAge(..))")
    public void myMonitor() { }
    
    @Bean
    public PerformanceMonitorInterceptor performanceMonitorInterceptor() {
        return new PerformanceMonitorInterceptor(true);
    }

    @Bean
    public Advisor performanceMonitorAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("com.nklkarthi.performancemonitor.AopConfiguration.monitor()");
        return new DefaultPointcutAdvisor(pointcut, performanceMonitorInterceptor());
    }
    
    @Bean
    public Person person(){
        return new Person("John","Smith", LocalDate.of(1980, Month.JANUARY, 12));
    }
    
    @Bean
    public PersonService personService(){
        return new PersonService();
    }
    
    @Bean
    public MyPerformanceMonitorInterceptor myPerformanceMonitorInterceptor() {
        return new MyPerformanceMonitorInterceptor(true);
    }
    
    @Bean
    public Advisor myPerformanceMonitorAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("com.nklkarthi.performancemonitor.AopConfiguration.myMonitor()");
        return new DefaultPointcutAdvisor(pointcut, myPerformanceMonitorInterceptor());
    }
    
}
