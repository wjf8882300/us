package com.tonggu.provider.us.common.config;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ExecutorConfig {
	
	private static final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 2;
	
	private static final String PREFIX_THREAD_NAME = "acd-async-service-";

	@Bean
	Executor asyncServiceExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(CORE_POOL_SIZE);
        executor.setQueueCapacity(99999);
        executor.setKeepAliveSeconds(300);
        executor.setThreadNamePrefix(PREFIX_THREAD_NAME);

        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();
        
        return executor;
	}
	
}
