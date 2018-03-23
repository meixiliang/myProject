package com.mxl.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import com.mxl.activemq.listener.AMQMessageListener;
/**
 * 消息消费类
 * @author Administrator
 *
 */
public class Receiver {
	
	private static final String TOPICS_NAME = "topics_1";
	private static final String QUEUE_NAME = "queue_1";
	public static void main(String[] args) {
		// ConnectionFactory ：连接工厂，JMS 用它创建连接
		ConnectionFactory connectionFactory;
		// Connection ：JMS 客户端到JMS Provider 的连接
		Connection connection = null;
		// Session： 一个发送或接收消息的线程
		Session session;
		// Destination ：消息的目的地;消息发送给谁.
		Destination destination;
		// 消费者，消息接收者
		MessageConsumer consumer;
		connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD, "tcp://localhost:61616");
		try {
			// 构造从工厂得到连接对象
			connection = connectionFactory.createConnection();
			// 启动
			connection.start();
			// 获取操作连接
			session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
			/**
			 * 队列模式session.createQueue("mxl");
			 * 主题模式session.createTopic("mxl");主题模式需要提前预定主题，不然无法收到消息。
			 */
			destination = session.createTopic(TOPICS_NAME);
			consumer = session.createConsumer(destination);
			consumer.setMessageListener(new AMQMessageListener());
//			while (true) {
//				// 设置接收者接收消息的时间，为了便于测试，这里谁定为100s
//				TextMessage message = (TextMessage) consumer.receive(500000);
//				if (null != message) {
//					System.out.println("收到消息" + message.getText());
//				} else {
//					break;
//				}
//			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			//使用监听器是异步的，不能关闭连接，不然无法收到消息。
//			try {
//				if (null != connection)
//					connection.close();
//			} catch (Throwable ignore) {
//			}
		}
	}
}
