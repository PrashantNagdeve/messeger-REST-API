package org.prashant.messenger.model;

import java.util.Date;

public class Comment {
  private long id;
  private String message;
  private Date date;
  private String author;

  
  
  
  
  
  
  
  
  public Comment()
  {
	  
  }
  
  public Comment(long id, String message, String author) {
	super();
	this.id = id;
	this.message = message;
	this.date = new Date();
	this.author = author;
}
  
public long getId() {
	return id;
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
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
public String getAuthor() {
	return author;
}
public void setAuthor(String author) {
	this.author = author;
}
  
  
  
}
