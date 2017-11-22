package org.prashant.messenger.resources;



import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.prashant.messenger.bean.MessageFilterBean;
import org.prashant.messenger.exception.DataNotFoundException;
import org.prashant.messenger.model.Message;
import org.prashant.messenger.service.MessageService;

//tell jersey to look for methods in this class hence we use @Path
//so that jersey will look for methods to handle

//class level path annotation
//we can add @Produces and @Consumes on class level so that every method will
//consume and produce same format

//this class now can produce xml as well as html
//we can use @Produces(value={MediaType.TEXT_XML,MediaType.APPLICATION_JSON})
@Path("/messages")
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {
	MessageService ms=new MessageService();

	//as soon as jersey finds '/messages' , it enters into this class
	//Now how jersey know to execute particular method ?
	//use get
	
	//after using get Jersey got the message but how does it send it to server and
	// how it decides the format
	// we use @Produces which tells jersey about the return content
	
	//Another part is we need to add @XmlRootElement to convert this list to xml and this will
	//be added in class Message
	
	
	
	
	//earliar method was accepting 3 query params 
	//But now we have created separate bean class to hadle the same thing
	
	/*@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessages(@QueryParam("year") int year,@QueryParam("start") int start,@QueryParam("size") int size){
		if(year>0)
		  return ms.getAllMessagesForYear(year);
		if(start>=0 && size>0)
		 return ms.getAllMessagesPaginated(start,size);
		return ms.getAllMessages();
	}*/
	
	//does not work
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Message> getXMLMessages(@BeanParam MessageFilterBean bean){
		//Identical to above method
		System.out.println("Returning XML messages");
		if(bean.getYear()>0)
			  return ms.getAllMessagesForYear(bean.getYear());
			if(bean.getStart()>=0 && bean.getSize()>0)
			 return ms.getAllMessagesPaginated(bean.getStart(),bean.getSize());
			return ms.getAllMessages();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getJSONMessages(@BeanParam MessageFilterBean bean){
		//Identical to above method
		System.out.println("Returning JSON messages");
		if(bean.getYear()>0)
			  return ms.getAllMessagesForYear(bean.getYear());
			if(bean.getStart()>=0 && bean.getSize()>0)
			 return ms.getAllMessagesPaginated(bean.getStart(),bean.getSize());
			return ms.getAllMessages();
	}
	
	
	
	
	
	// to convert to json , jersey does not provide any method like for XML
	//hence we have to add external jar for conversion
	//uncommented some part in pom.xml
	
	
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long id,@Context UriInfo uriInfo)
	{
		Message msg= ms.getMessage(id);
		if(msg==null)
		{
			throw new DataNotFoundException("No message found for id "+id);
		}
		
		//adding links
		
		//one way
		//String commentsurl=uriInfo.getBaseUriBuilder().path(MessageResource.class).path(Long.toString(msg.getId())).path("comments").build().toString();
		
		msg.addLink(getSelfLink(uriInfo,msg), "self");
		msg.addLink(getAllMessagesUrl(uriInfo,msg),"All messages");
		msg.addLink(getCommentsUrl(uriInfo,msg),"Comments");
		///finished adding links
		return msg;
	}
	
	private String getSelfLink(UriInfo uriInfo, Message msg) {
		// TODO Auto-generated method stub
		String self=uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(Long.toString(msg.getId()))
				.build().toString();
		return self;
	}



	private String getAllMessagesUrl(UriInfo uriInfo, Message msg) {
		// TODO Auto-generated method stub
		String allmsg=uriInfo.getBaseUriBuilder().path(MessageResource.class).build().toString();
		return allmsg;
	}



	private String getCommentsUrl(UriInfo uriInfo,Message msg) {
		
		// TODO Auto-generated method stub
		URI uri=uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(MessageResource.class,"getCommentResource").resolveTemplate("messageId", msg.getId())
				.build();
		//resolveTemplate is used to insert the value of message ID
		return uri.toString();
	}



	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addMessage(Message message,@Context UriInfo uriinfo) throws URISyntaxException
	{
		//as Jersey sees the argument it converts json from body into Message object
		//to show path for msg with id
		//we will use Uri builder which starts with absolute path and adds the path we specify
		System.out.println(uriinfo.getAbsolutePath());
		Message newMsg=ms.addMessage(message);
		
		URI uri=uriinfo.getAbsolutePathBuilder().path(String.valueOf(newMsg.getId())).build();
		//return Response.created(new URI(uriinfo.getAbsolutePath()+""+newMsg.getId())).entity(newMsg).build();
		return Response.created(uri).entity(newMsg).build();
		
	}
	
	
	@PUT
	@Path("/{messageId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Message updateMessage(@PathParam("messageId") long id,Message message)
	{
		message.setId(id);
		ms.updateMessage(message);
		return message;
	}
	
	@DELETE
	@Path("/{messageId}")
	public Message removeMessage(@PathParam("messageId") long messageId)
	{
		return ms.removeMessage(messageId);
	}
	
	//Now we will implement our sub resource for messages
	//we dont need to specify any get post put or delete as control will go
	// to another class
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource()
	{
		//jersey will go and check if there exists any method which can be called 
		//control will go inside CommentResource
		return new CommentResource();
	}
	
	
	
	
}
