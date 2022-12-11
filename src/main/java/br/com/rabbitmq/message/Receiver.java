package br.com.rabbitmq.message;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

  @RabbitListener(queues = "laravel-spring")
  public void receiveMessage(String message) throws InterruptedException {
    System.out.println("Received <" + message + ">");
  }

}