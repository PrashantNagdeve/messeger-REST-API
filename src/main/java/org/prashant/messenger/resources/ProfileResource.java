package org.prashant.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.prashant.messenger.service.ProfileService;
import org.prashant.messenger.model.Profile;
@Path("/profiles")
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource {

	private ProfileService ps=new ProfileService();
	
	@GET
	public List<Profile> getProfiles()
	{
		return ps.getAllProfiles();
	}
	
	
	@GET
	@Path("/{profileName}")
	public Profile getProfile(@PathParam("profileName") String profileName)
	{
		return ps.getProfile(profileName);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Profile addProfile(Profile profile)
	{
		return ps.addProfile(profile);
	}
	
	@PUT
	@Path("/{profileName}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Profile updateProfile(@PathParam("profileName") String pname,Profile profile)
	{
		profile.setProfileName(pname);
		ps.updateProfile(profile);
		return profile;
	}
	
	@DELETE
	@Path("/{pname}")
	public Profile removeProfile(@PathParam("pname") String pname)
	{
		return ps.removeProfile(pname);
	}
	
	
}
