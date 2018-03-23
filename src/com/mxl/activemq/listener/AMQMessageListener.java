package com.mxl.activemq.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class AMQMessageListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		try {  
            System.out.println("收到的消息："+((TextMessage)message).getText());  
        } catch (JMSException e) {
        	System.out.println(e);
            e.printStackTrace();  
        }  
	}


}
