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
 * ��Ϣ������
 * @author Administrator
 *
 */
public class Sender {
	private static final int SEND_NUMBER = 10000;

	public static void main(String[] args) {
		// Jms��ConnectionFactory��������
		ConnectionFactory connectionFactory;
		// Jms�ͻ��˵�JMS Provide ������
		Connection connection = null;
		// һ�����ͻ��߽�����Ϣ���߳�
		Session session;
		// ��Ϣ��Ŀ�ĵ�
		Destination destination;
		// ��Ϣ������
		MessageProducer producer;
		//����ActiveMQ���ӹ���
		connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD, "tcp://localhost:61616");
		try {
			//��ȡ���Ӷ���
			connection = connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			//��ַ
			destination = session.createQueue("mxl");
			// �õ���Ϣ�����ߡ������ߡ�
			producer = session.createProducer(destination);
			//���ò��־û�
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			// ������Ϣ���˴�д������Ŀ���ǲ��������߷�����ȡ  
            sendMessage(session, producer);  
            session.commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	 public static void sendMessage(Session session, MessageProducer producer)  
	            throws Exception {  
	        for (int i = 1; i <= SEND_NUMBER; i++) {  
	            TextMessage message = session.createTextMessage("ActiveMq ���͵���Ϣ"  
	                    + i);  
	            // ������Ϣ��Ŀ�ĵط�  
	            System.out.println("������Ϣ��" + "ActiveMq ���͵���Ϣ" + i);  
	            producer.send(message);  
	        }  
	    }
}
