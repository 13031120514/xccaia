package com.xccaia.mongo.acitivemq.receiver;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @ Author     ：xccaia
 * @ Date       ：2020-03-08
 * @ Description：
 */
public class JMSTopicConsumer01 {

  public static void main(String[] args) {
    ConnectionFactory connectionFactory =
        new ActiveMQConnectionFactory
            ("tcp://127.0.0.1:61616");
    Connection connection = null;
    try {

      connection = connectionFactory.createConnection();
      connection.start();

      Session session = connection.createSession
          (Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
      //创建目的地
      Destination destination = session.createTopic("myTopic");
      //创建发送者
      MessageConsumer consumer = session.createConsumer(destination);

      TextMessage textMessage = (TextMessage) consumer.receive();
      System.out.println(textMessage.getText());

      session.commit();
      session.close();
    } catch (JMSException e) {
      e.printStackTrace();
    } finally {
      if (connection != null) {
        try {
          connection.close();
        } catch (JMSException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
