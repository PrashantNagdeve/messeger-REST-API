package org.prashant.messenger.resources;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.prashant.messenger.model.Comment;
import org.prashant.messenger.service.CommentService;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {

	private CommentService cs=new CommentService();
	
	
	//works
	@GET 
	public List<Comment> getAllComments(@PathParam("messageId")Long msgId)
	{
		return cs.getAllComments(msgId);
	}
	
	
	//works
	@GET
	@Path("/{cid}")
	public Comment getComment(@PathParam("messageId")Long msgId,@PathParam("cid")Long cid)
	{
		return cs.getComment(msgId, cid);
	}
	
	
	
	@POST
	public Response addComment(@PathParam("messageId")Long msgId,Comment comment)
	{
		// this is to return status code 201 created
		return Response.status(Status.CREATED).entity(cs.addComment(msgId, comment)).build();
		//return cs.addComment(msgId, comment);
	}
	
	
	@PUT
	@Path("/{cid}")
	public Comment updateComment(@PathParam("messageId")Long msgId,@PathParam("cid") long cid,Comment comment)
	{
		comment.setId(cid);
		return cs.updateComment(msgId,comment);
	}
	
	
	
	@DELETE
	@Path("/{cid}")
	public Comment removeMessage(@PathParam("messageId")long msgId,@PathParam("cid")long cid)
	{
		return cs.removeComment(msgId, cid);
	}
	
}
