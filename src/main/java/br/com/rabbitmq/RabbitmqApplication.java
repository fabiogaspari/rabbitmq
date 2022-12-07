package br.com.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import br.com.rabbitmq.message.Receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class RabbitmqApplication {

	Logger logger = LoggerFactory.getLogger(RabbitmqApplication.class);

	public static final String topicExchangeName = "ex-spring-laravel";

	public static final String queueName = "spring-laravel";
  
	@Bean
	Queue queue() {
	  return new Queue(queueName, true);
	}
	
	@Bean
	TopicExchange exchange() {
	  return new TopicExchange(topicExchangeName);
	}
  
	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
		MessageListenerAdapter listenerAdapter) {
	 
	  SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
	  
	  container.setConnectionFactory(connectionFactory);
	  container.setQueueNames(queueName);
	  container.setMessageListener(listenerAdapter);
	  
	  return container;
	}
  
	@Bean
	MessageListenerAdapter listenerAdapter(Receiver receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}

	public static void main(String[] args) throws InterruptedException {
	  SpringApplication.run(RabbitmqApplication.class, args);
	}
}