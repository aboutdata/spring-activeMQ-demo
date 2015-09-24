package com.anhao.jms;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Component
/**
 * MessageListener when send message. trigger this
 * config root-context.xml
 * @author Administrator
 *
 */
public class MessageReceiver implements MessageListener {

	Logger logger = LoggerFactory.getLogger(MessageReceiver.class);

	public void onMessage(final Message message) {

		if (message instanceof MapMessage) {

			final MapMessage mapMessage = (MapMessage) message;

			try {
				logger.info(mapMessage.getString("Name"));
			
			} catch (JMSException e) {
				// do something
			}
		}
	}
}