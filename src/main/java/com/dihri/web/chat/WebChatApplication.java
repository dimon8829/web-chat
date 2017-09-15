package com.dihri.web.chat;

import com.dihri.web.chat.service.ChatServiceImpl;
import com.dihri.web.chat.service.WebSocketServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
public class WebChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebChatApplication.class, args);
	}


	@Bean(name = ChatServiceImpl.READ_MESSAGE_EXECUTOR)
	public Executor readMessageExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(4);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix(ChatServiceImpl.READ_MESSAGE_EXECUTOR +"-");
		executor.initialize();
		return executor;
	}

	@Bean(name = WebSocketServiceImpl.WEB_SOCKET_EXECUTOR)
	public Executor webSocketExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(4);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix(WebSocketServiceImpl.WEB_SOCKET_EXECUTOR +"-");
		executor.initialize();
		return executor;
	}


}
