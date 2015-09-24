package com.anhao.jms;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Resource
	JmsTemplate jmsTemplate;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/send", method = RequestMethod.GET)
	public String send(Locale locale, Model model) {
		/**
		 * send mapMessage
		 */
		Map map = new HashMap();
		map.put("Name", "welcome to china great wall.");	
		
		jmsTemplate.convertAndSend(map);
		model.addAttribute("send", "sucess");
		return "home";
	}
	
	/**
	 * receive jms message
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/receive", method = RequestMethod.GET)
	public String test(Locale locale, Model model) {
		Message message = jmsTemplate.receive();
	
		if (message instanceof MapMessage) {
			final MapMessage mapMessage = (MapMessage) message;

			try {
				logger.info(mapMessage.getString("Name"));
				model.addAttribute("content", mapMessage.getString("Name"));
			} catch (JMSException e) {
				// do something
			}
		}
		
		return "receive";
	}
	
}
