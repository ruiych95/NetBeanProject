package springbootdocker.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AppModel 
{
	private String response;

	public String getMessage() 
	{
		return response;
	}
	@JsonProperty("response")
	public void setMessage(String message) 
	{
		this.response = message;
	}
	
	@Override
	public String toString() 
	{
		return "{" +
				"\"" + "response" + "\"" + ":" + "\"" + response + "\"" +
				"}";
	}
}
