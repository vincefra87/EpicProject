package it.epic.projects.MobileSubscriber.configuration;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author Vincenzo Francavilla
 * 
 * Class that managed concurrent task
 * 
 * */
@Configuration
@EnableAsync
public class AsyncConfiguration {
	
	@Bean(name= "taskExecutor")
	public Executor taskExecutor() {
		final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setThreadNamePrefix("MobileSubscriber-");
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(2);
		executor.setQueueCapacity(100);
		executor.initialize();
		return executor;
	}
}