package org.prashant.messenger.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.bind.annotation.JsonbTransient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Message {

	private long id;
	private String message;
	private Date created;
	private String author;
	private Map<Long,Comment> comments=new HashMap<Long, Comment>();
	private List<Link> links=new ArrayList<Link>();
	
	public long getId() {
		return id;
	}
	
	public Message(){}
	
	public Message(String message, String author) {
		super();
		
		this.message = message;
		this.created = new Date();
		this.author = author;
	}



	public void setId(long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	//method for Comment
	
	//using xmlTransient because we dont want comment data to show when msg data is showed
	@JsonbTransient
	@XmlTransient
	public Map<Long,Comment> getComments()
	{
		return comments;
	}
	
	public void setComments(Map<Long,Comment> comments)
	{
		this.comments=comments;
	}

	
	//getters and setters for links
	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}
	
	//method to add link
	public void addLink(String url,String rel)
	{
		Link link=new Link();
		link.setLink(url);
		link.setRel(rel);
		links.add(link);
		
	}
	
	
	
}
