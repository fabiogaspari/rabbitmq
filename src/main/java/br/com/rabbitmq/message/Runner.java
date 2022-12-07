package br.com.rabbitmq.message;

import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

  private final Receiver receiver;

  public Runner(Receiver receiver, RabbitTemplate rabbitTemplate) {
    this.receiver = receiver;
  }

  @Override
  public void run(String... args) throws Exception {
    System.out.println("Listening message...");
    receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
  }

}
