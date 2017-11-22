package org.prashant.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.prashant.messenger.model.ErrorMessage;

//provider registers the class in jaxrs so that jax rs knows that there exists an exception mapper


@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException>{

	@Override
	public Response toResponse(DataNotFoundException e) {
		// TODO Auto-generated method stub
		//here we can write a proper response to error
		//we will create a custom error message
		ErrorMessage errorMessage=new ErrorMessage(e.getMessage(), 404, "https://en.wikipedia.org/wiki/HTTP_404");
		return Response.status(Status.NOT_FOUND).entity(errorMessage).build();
		
	}

	

}
