package com.zoudys.rest.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.zoudys.messenger.model.Message;

public class RestApiClient {

	public static void main(String[] args) {

		// Client is an interface
		Client client = ClientBuilder.newClient();
		
		WebTarget baseTarget = client.target("http://localhost:8080/Advanced-jaxrs-06/webapi");
		WebTarget messagesTarget = baseTarget.path("messages");
		WebTarget singleTarget = messagesTarget.path("{messageId}");
		
		Message message1 = singleTarget
				.resolveTemplate("messageId", "1")
				.request(MediaType.APPLICATION_JSON)
				.get(Message.class);

		Message message2 = singleTarget
				.resolveTemplate("messageId", "2")
				.request(MediaType.APPLICATION_JSON)
				.get(Message.class);

		
		//Message message = response.readEntity(Message.class);
		System.out.println(message1.getMessage());
		System.out.println(message2.getMessage());
		
		System.out.println(message1);
		
		// POST message from client side
		
		Message newMessage = new Message(3L, "Application jaxrs support tuto", "Nzouda");
		Response postResponse = messagesTarget
		.request()
		.post(Entity.json(newMessage));
		
		System.out.println(postResponse);
		// read fast to check
		Message createdMessage = postResponse.readEntity(Message.class);
		System.out.println(createdMessage.getMessage());

	}

}
