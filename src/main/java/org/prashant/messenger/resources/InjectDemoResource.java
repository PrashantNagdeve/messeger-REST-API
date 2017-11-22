package org.prashant.messenger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

//this class wont be use in our project
//this is just for information about different parameters


@Path("/injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {

	// to access
	//use url 
	//http://localhost:8080/messenger/webapi/injectdemo/annotations;param="Prashant Nagdeve is working on rest apt"
	@GET
	@Path("/annotations")
	public String greet(@MatrixParam("param") String param,@HeaderParam("authID") String hparam,
			            @CookieParam("name") String cookie)
	{
		
		return "Matrix param : "+param+"   authID : "+hparam;}
	
	@GET
	@Context
	@Path("/context")
	public String getParamsUsingContext(@Context UriInfo uriinfo,@Context HttpHeaders httpheader)
	{
		return "Path : "+uriinfo.getAbsolutePath().toString()+"   MediaType : "+httpheader.getMediaType().toString();
	}
	
	
}
