package br.com.rabbitmq.message;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

  private CountDownLatch latch = new CountDownLatch(1);

  Logger logger = LoggerFactory.getLogger(Receiver.class);

  @RabbitListener(queues = "spring-laravel")
  public void receiveMessage(String message) throws InterruptedException {
    logger.info(message.toString());
    System.out.println("Received <" + message + ">");
    latch.countDown();
  }

  public CountDownLatch getLatch() {
    return latch;
  }

}