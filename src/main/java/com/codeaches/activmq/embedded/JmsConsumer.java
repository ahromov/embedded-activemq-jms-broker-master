package com.codeaches.activmq.embedded;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class JmsConsumer {

  Logger log = LoggerFactory.getLogger(JmsConsumer.class);

  @JmsListener(destination = "${activemq.queue.name}")
  public void receive(String message) throws InterruptedException {
    Thread.sleep(10000);
    log.info("Received message='{}'", message);
  }
}
