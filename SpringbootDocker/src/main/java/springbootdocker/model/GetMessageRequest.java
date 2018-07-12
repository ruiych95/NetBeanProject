package springbootdocker.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetMessageRequest 
{
	private String message;

	public String getMessage() 
	{
		return message;
	}
	@JsonProperty("message")
	public void setMessage(String message) 
	{
		this.message = message;
	}
	
	@Override
	public String toString() 
	{
		return "{" +
				"\"" + "message" + "\"" + ":" + "\"" + message + "\"" +
				"}";
	}
}
