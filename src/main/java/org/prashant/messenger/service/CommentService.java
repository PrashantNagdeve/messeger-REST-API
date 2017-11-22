package org.prashant.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.prashant.messenger.database.DatabaseClass;
import org.prashant.messenger.model.Comment;
import org.prashant.messenger.model.ErrorMessage;
import org.prashant.messenger.model.Message;

public class CommentService {

	
	
	public CommentService()
	{
		
	}
	
	
	private Map<Long,Message> messages=DatabaseClass.getMessages();
	
	
	
	public List<Comment> getAllComments(Long msgId)
	{
		Map<Long,Comment>comments=messages.get(msgId).getComments();
		return new ArrayList<Comment>(comments.values());
	}
	
	public Comment getComment(Long msgId,Long cid)
	{
		Message message=messages.get(msgId);
		if(message==null)
		{
			//we will throw an excpetion which jersey knows
			//build a response
			ErrorMessage errorMessage=new ErrorMessage("NO message found", 404, "Refer to message API");
			Response response=Response.status(Status.NOT_FOUND).entity(errorMessage).build();
			
			throw new WebApplicationException(response);
		}
		Map<Long,Comment> comments=message.getComments();
		Comment comment= comments.get(cid);
		if(comment==null)
		{
			ErrorMessage errorMessage=new ErrorMessage("NO comment found", 404, "Refer to comment API");
			Response response=Response.status(Status.NOT_FOUND).entity(errorMessage).build();
			
			throw new NotFoundException(response);
		}
		return comment;
	}
	
	//does not work
	public Comment addComment(Long msgId,Comment comment)
	{
		
		Map<Long, Comment> comments=messages.get(msgId).getComments();
		comment.setId(comments.size()+1);
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	
	public Comment updateComment(long msgId,Comment comment)
	{
		if(comment.getId()<=0)
			return null;
		Message message=messages.get(msgId);
		Map<Long,Comment> comments=message.getComments();
		comments.put(comment.getId(),comment);
		return comment;
	}
	
	//this does not work
	public Comment removeComment(long msgId,long cid)
	{
		Map<Long,Comment> comments=messages.get(msgId).getComments();
		return comments.remove(cid);
	}
	
	
}
