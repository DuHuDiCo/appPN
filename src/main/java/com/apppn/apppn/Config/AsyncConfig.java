package com.apppn.apppn.Config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class AsyncConfig extends AsyncConfigurerSupport{

    @Bean(name = "fileTaskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10); // Número de hilos principales
        executor.setMaxPoolSize(50); // Número máximo de hilos
        executor.setQueueCapacity(100); // Capacidad de cola para tareas
        executor.setThreadNamePrefix("FileTask-");
        executor.initialize();
        return executor;
    }

}
