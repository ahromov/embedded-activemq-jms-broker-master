package com.codeaches.activmq.embedded;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class JmsConfig {

  @Value("${activemq.broker.url}")
  String brokerUrl;

  /* Active MQ Embedded Broker configuration */
  @Bean
  public BrokerService broker() throws Exception {
    BrokerService broker = new BrokerService();
    broker.setPersistent(false);
    broker.setUseJmx(true);
    broker.addConnector(brokerUrl);
    return broker;
  }

  /* Producer configuration */
  @Bean
  public JmsTemplate jmsTemplate() {
    return new JmsTemplate(new ActiveMQConnectionFactory(brokerUrl));
  }

  /* Consumer configuration */
  @Bean
  public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
    factory.setConnectionFactory(new ActiveMQConnectionFactory(brokerUrl));
    return factory;
  }
}
