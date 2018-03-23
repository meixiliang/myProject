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
 * ��Ϣ������
 * @author Administrator
 *
 */
public class Receiver {
	
	private static final String TOPICS_NAME = "topics_1";
	private static final String QUEUE_NAME = "queue_1";
	public static void main(String[] args) {
		// ConnectionFactory �����ӹ�����JMS ������������
		ConnectionFactory connectionFactory;
		// Connection ��JMS �ͻ��˵�JMS Provider ������
		Connection connection = null;
		// Session�� һ�����ͻ������Ϣ���߳�
		Session session;
		// Destination ����Ϣ��Ŀ�ĵ�;��Ϣ���͸�˭.
		Destination destination;
		// �����ߣ���Ϣ������
		MessageConsumer consumer;
		connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD, "tcp://localhost:61616");
		try {
			// ����ӹ����õ����Ӷ���
			connection = connectionFactory.createConnection();
			// ����
			connection.start();
			// ��ȡ��������
			session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
			/**
			 * ����ģʽsession.createQueue("mxl");
			 * ����ģʽsession.createTopic("mxl");����ģʽ��Ҫ��ǰԤ�����⣬��Ȼ�޷��յ���Ϣ��
			 */
			destination = session.createTopic(TOPICS_NAME);
			consumer = session.createConsumer(destination);
			consumer.setMessageListener(new AMQMessageListener());
//			while (true) {
//				// ���ý����߽�����Ϣ��ʱ�䣬Ϊ�˱��ڲ��ԣ�����˭��Ϊ100s
//				TextMessage message = (TextMessage) consumer.receive(500000);
//				if (null != message) {
//					System.out.println("�յ���Ϣ" + message.getText());
//				} else {
//					break;
//				}
//			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			//ʹ�ü��������첽�ģ����ܹر����ӣ���Ȼ�޷��յ���Ϣ��
//			try {
//				if (null != connection)
//					connection.close();
//			} catch (Throwable ignore) {
//			}
		}
	}
}
