package springbootdocker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import springbootdocker.model.GetMessageRequest;
import springbootdocker.service.AppService;

@RestController
public class AppController 
{
	@Autowired
	AppService appService;
	
	@RequestMapping(value="/getMessage", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Object getMessage(@RequestBody GetMessageRequest getMessageRequest)
	{
		return appService.getMessage(getMessageRequest);
	}
}
