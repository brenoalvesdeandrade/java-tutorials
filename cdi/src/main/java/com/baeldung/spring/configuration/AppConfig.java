package com.nklkarthi.spring.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.nklkarthi.spring.aspect.SpringTestAspect;
import com.nklkarthi.spring.service.SpringSuperService;

@Configuration
@EnableAspectJAutoProxy
public class AppConfig {
    @Bean
    public SpringSuperService springSuperService() {
        return new SpringSuperService();
    }

    @Bean
    public SpringTestAspect springTestAspect() {
        return new SpringTestAspect();
    }

    @Bean
    public List<String> getAccumulator() {
        return new ArrayList<String>();
    }
}
