package org.prashant.messenger.service;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.prashant.messenger.database.DatabaseClass;
import org.prashant.messenger.model.Comment;
import org.prashant.messenger.model.Message;;
public class MessageService {

	private Map<Long,Message> messages=DatabaseClass.getMessages();
	
	
	public MessageService()
	{
		Message m1=new Message("How are you?", "Prashant");
		m1.setId(1);
		Message m2=new Message("I am good.","Nagdeve");
		m2.setId(2);
		Message m3=new Message("So which courses have you taken?","Prashant");
		m3.setId(3);
		Message m4=new Message("OODD,ALDA,SE and AI","Nagdeve");
		m4.setId(4);
		//setting comments for messages
		/*
		Comment c1=new Comment(1,"This was a good post", "Prashant");
		Comment c2=new Comment(2,"Very good", "Nagdeve");
		Comment c3=new Comment(3,"I did not like this at all", "Victor");
		
		Map<Long,Comment> comments1=new HashMap<>();
		comments1.put((long) (comments1.size()+1),c1);
		comments1.put((long) (comments1.size()+1),c2);
		comments1.put((long) (comments1.size()+1),c3);
		m1.setComments(comments1);
				
		Map<Long,Comment> comments2=new HashMap<>();
		comments2.put((long) (comments2.size()+1),new Comment(1,"Tihs is not that good", "Prashant"));
		comments2.put((long) (comments2.size()+1),new Comment(1,"I dont see it", "Victor"));
		comments2.put((long) (comments2.size()+1),new Comment(1,"Did you check secret file", "John"));
		
		m2.setComments(comments2);
		*/
		
		//ends
		messages.put(1L, m1);
		messages.put(2L, m2);
		messages.put(3L, m3);
		messages.put(4L, m4);
	}
	
	
	public List<Message> getAllMessages()
	{   
		return new ArrayList<Message>(messages.values());
	}
    
	public List<Message> getAllMessagesForYear(int year)
	{
		List<Message> msgForYear=new ArrayList<Message>();
		
		Calendar cal =Calendar.getInstance();
		
		for(Message m:messages.values())
		{
			cal.setTime(m.getCreated());
			if(cal.get(Calendar.YEAR)==year)
				msgForYear.add(m);
		}
		
		
		return msgForYear;
	}
	
	public List<Message> getAllMessagesPaginated(int start,int size)
	{
		ArrayList<Message> list=new ArrayList<Message>(messages.values());
		
		if(start+size >= list.size())
			return list;
		
		return list.subList(start,start+size);
	}
	
	public Message getMessage(long id)
	{
		return messages.get(id);
	}
	
	public Message addMessage(Message message)
	{
		message.setId(messages.size()+1);
		messages.put(message.getId(), message);
		return message;
	}
	
	
	public Message updateMessage(Message message)
	{
		if(message.getId()<=0)
			return null;
		
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message removeMessage(long id)
	{
		Message temp=messages.get(id);
		messages.remove(id);
		return temp;
	}


	
}
