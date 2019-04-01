package com.rflpazini.badabin.service;

import java.util.Collections;
import java.util.StringJoiner;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class QueueService implements MessageListener {
  private static final Logger LOGGER = LoggerFactory.getLogger(QueueService.class);

  @Autowired private JmsTemplate jmsTemplate;

  private int counter = 0;

  public int completedJobs() {
    return counter;
  }

  public void send(String destination, String message) {
    LOGGER.info("sending message='{}' to destination='{}'", message, destination);
    jmsTemplate.convertAndSend(destination, message);
  }

  public int pendingJobs(String queueName) {
    return jmsTemplate.browse(queueName, (s, qb) -> Collections.list(qb.getEnumeration()).size());
  }

  public boolean isUp() {
    var connection = jmsTemplate.getConnectionFactory();
    try {
      connection.createConnection().close();
      return true;
    } catch (JMSException e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public void onMessage(Message message) {
    if (message instanceof ActiveMQTextMessage) {
      ActiveMQTextMessage textMessage = (ActiveMQTextMessage) message;
      try {
        LOGGER.info(formatMessageLog("Processing task", textMessage));
        Thread.sleep(1000);
        LOGGER.info(formatMessageLog("Completed task ", textMessage));
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (JMSException e) {
        e.printStackTrace();
      }
      counter++;
    } else {
      LOGGER.error("Message is not a text message " + message.toString());
    }
  }

  private String formatMessageLog(String customMessage, ActiveMQTextMessage queueMessage)
      throws JMSException {
    return new StringJoiner(":").add(customMessage).add(queueMessage.getText()).toString();
  }
}
