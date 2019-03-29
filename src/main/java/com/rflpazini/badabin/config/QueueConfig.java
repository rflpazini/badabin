package com.rflpazini.badabin.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;


@Configuration
@EnableJms
public class QueueConfig {
  @Value("${spring.activemq.broker-url}")
  private String brokerUrl;

  @Value("${spring.activemq.user}")
  private String userName;

  @Value("${spring.activemq.password}")
  private String userPass;
  @Bean
  public ActiveMQConnectionFactory activeMQConnectionFactory() {
    ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
    activeMQConnectionFactory.setBrokerURL(brokerUrl);
    activeMQConnectionFactory.setUserName(userName);
    activeMQConnectionFactory.setPassword(userPass);

    return activeMQConnectionFactory;
  }

  @Bean
  public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
    factory.setConnectionFactory(activeMQConnectionFactory());
    factory.setConcurrency("1");
    return factory;
  }

  @Bean
  public CachingConnectionFactory cachingConnectionFactory() {
    return new CachingConnectionFactory(activeMQConnectionFactory());
  }

  @Bean
  public JmsTemplate jmsTemplate() {
    return new JmsTemplate(cachingConnectionFactory());
  }

  @Bean
  public QueueService queue() {
    return new QueueService();
  }
}
