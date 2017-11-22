package org.prashant.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.prashant.messenger.model.ErrorMessage;

//we will disable this exception by removing @Provider

//@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable>{

	@Override
	public Response toResponse(Throwable e) {
		// TODO Auto-generated method stub
		ErrorMessage errorMessage=new ErrorMessage(e.getMessage(), 404, "No doc available");
		return Response.status(0, "Generic Error").entity(errorMessage).build();
		
	}

}
