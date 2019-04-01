package com.rflpazini.badabin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListenerConfigurer;
import org.springframework.jms.config.JmsListenerEndpointRegistrar;
import org.springframework.jms.config.SimpleJmsListenerEndpoint;
import org.springframework.stereotype.Component;

@Component
public class QueueListener implements JmsListenerConfigurer {

  @Autowired private QueueService queueService;

  @Value("${queue.name}")
  private String queueName;

  @Value("${worker.name}")
  private String workerName;

  @Value("${worker.enabled}")
  private boolean workerEnabled;

  @Override
  public void configureJmsListeners(JmsListenerEndpointRegistrar registrar) {
    SimpleJmsListenerEndpoint endpoint = new SimpleJmsListenerEndpoint();
    endpoint.setId(workerName);
    endpoint.setDestination(queueName);
    endpoint.setMessageListener(queueService);
    registrar.registerEndpoint(endpoint);
  }
}
