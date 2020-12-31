package com.gs.lambda.email.lambda.functions;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.gs.lambda.email.lambda.service.EmailService;

@Component
public class EmailFunction implements Function<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

	//@Autowired
	//private EmailService emailService;
	
	@Autowired
	private AmazonSNS snsClient;
	
	@Override
	public APIGatewayProxyResponseEvent apply(APIGatewayProxyRequestEvent apiGatewayProxyRequestEvent) {
		System.out.println(apiGatewayProxyRequestEvent);
		APIGatewayProxyResponseEvent apiGatewayProxyResponseEvent = new APIGatewayProxyResponseEvent();
		apiGatewayProxyResponseEvent.setStatusCode(200);
		apiGatewayProxyResponseEvent.setBody("Received " + apiGatewayProxyRequestEvent.getBody());
		publishMsg(apiGatewayProxyRequestEvent.getBody());
		return apiGatewayProxyResponseEvent;
	}

	public void publishMsg(String message) {
 
		final PublishRequest publishRequest = new PublishRequest("arn:aws:sns:us-east-1:568805750472:monmouth-paper-flower-email-topic", message);
		final PublishResult publishResponse = snsClient.publish(publishRequest);

		System.out.println("Message successfully published " + publishResponse.getMessageId() );

	}
	
}
