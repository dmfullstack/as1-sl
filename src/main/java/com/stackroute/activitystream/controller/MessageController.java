package com.stackroute.activitystream.controller;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stackroute.activitystream.config.HibernateUtil;
import com.stackroute.activitystream.model.Message;
import com.stackroute.activitystream.repository.MessageRepository;

/*Annotate the class with @Controller annotation.@Controller annotation is used to mark 
 * any POJO class as a controller so that Spring can recognize this class as a Controller*/
@Controller
public class MessageController {

	/*
	 * From the problem statement, we can understand that the application
	 * requires us to implement two functionalities. They are as following:
	 * 
	 * 1. display the list of existing messages from the database. Each message
	 *    should contain senderName, message, and timestamp 
	 * 2. send a message which should contain the senderName, message, and timestamp.
	 * 
	 */
	
	
	/*Define a handler method to read the existing messages from the database and add it to
	the ModelMap which is an implementation of Map for use when building model data for use 
	with views. it should map to the default URL i.e. "/" */
	@RequestMapping("/")
	public String index(ModelMap model) {
		
		MessageRepository messageRepository=new MessageRepository();
		model.addAttribute("messages", messageRepository.getAllMessages());
		return "index";
	}

	
	
	/*Define a handler method which will read the senderName and message from request parameters and
	 * save the message in message table in database. Please note that the timestamp should always
	 * be auto populated with system time and should not be accepted from the user. Also, after 
	 * saving the message, it should show the same along with existing messages. Hence, reading 
	 * messages has to be done here again and the retrieved messages object should be sent back to the 
	 * view using ModelMap
	 * This handler method should map to the URL "/sendMessage". 
	*/
	@PostMapping("/sendMessage")
	public String sendMessage(@RequestParam("sender") String sender, @RequestParam("message") String messageContent,
			ModelMap model) {
		
		MessageRepository messageRepository=new MessageRepository();
		if (sender == null || sender.isEmpty() || messageContent == null || messageContent.isEmpty()) {
			model.addAttribute("errorMsg", "SenderName or Message content can't be empty");
			model.addAttribute("messages", messageRepository.getAllMessages());
			return "index";
		}

		Message message = new Message();
		message.setSenderName(sender);
		message.setMessage(messageContent);
		message.setPostedDate();

		messageRepository.sendMessage(message);
		
		model.addAttribute("messages", messageRepository.getAllMessages());
		return "redirect:/";
	}

}
