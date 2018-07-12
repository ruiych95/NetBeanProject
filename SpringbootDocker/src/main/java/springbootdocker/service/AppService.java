package springbootdocker.service;

import org.springframework.stereotype.Service;

import springbootdocker.model.AppModel;
import springbootdocker.model.GetMessageRequest;

@Service
public class AppService 
{
	public Object getMessage(GetMessageRequest getMessageRequest)
	{
		AppModel appModel = new AppModel();
		appModel.setMessage(getMessageRequest.getMessage());
		return appModel;
	}
}
