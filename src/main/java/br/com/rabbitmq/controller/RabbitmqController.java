package br.com.rabbitmq.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rabbitmq.RabbitmqApplication;

@RestController
@RequestMapping("/")
public class RabbitmqController {

  private final RabbitTemplate rabbitTemplate;

  public RabbitmqController(RabbitTemplate rabbitTemplate) {
      this.rabbitTemplate = rabbitTemplate;
  }
  
  @GetMapping("/send/{message}")
  public ResponseEntity<String> send(@PathVariable String message) {
      rabbitTemplate.convertAndSend(RabbitmqApplication.topicExchangeName, "foo.bar.baz", "Hello from Java RabbitMQ!");
      
      return new ResponseEntity<String> (
          "Sucesso",
          HttpStatus.OK
      );
  
    }
}
