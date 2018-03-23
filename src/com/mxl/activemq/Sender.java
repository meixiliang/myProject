package com.mxl.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
/**
 * 消息生产类
 * @author Administrator
 *
 */
public class Sender {
	private static final int SEND_NUMBER = 10000;

	public static void main(String[] args) {
		// Jms用ConnectionFactory创建连接
		ConnectionFactory connectionFactory;
		// Jms客户端到JMS Provide 的连接
		Connection connection = null;
		// 一个发送或者接收消息的线程
		Session session;
		// 消息的目的地
		Destination destination;
		// 消息发送者
		MessageProducer producer;
		//创建ActiveMQ连接工厂
		connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD, "tcp://localhost:61616");
		try {
			//获取连接对象
			connection = connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			//地址
			destination = session.createQueue("mxl");
			// 得到消息生成者【发送者】
			producer = session.createProducer(destination);
			//设置不持久化
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			// 构造消息，此处写死，项目就是参数，或者方法获取  
            sendMessage(session, producer);  
            session.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	 public static void sendMessage(Session session, MessageProducer producer)  
	            throws Exception {  
	        for (int i = 1; i <= SEND_NUMBER; i++) {  
	            TextMessage message = session.createTextMessage("ActiveMq 发送的消息"  
	                    + i);  
	            // 发送消息到目的地方  
	            System.out.println("发送消息：" + "ActiveMq 发送的消息" + i);  
	            producer.send(message);  
	        }  
	    }
}
