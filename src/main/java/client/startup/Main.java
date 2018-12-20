package client.startup;

import java.net.URI;
import java.net.URISyntaxException;

import client.net.WebClient;

public class Main {

	public static void main(String[] args) {
		try {
			WebClient client = new WebClient(new URI("ws://127.0.0.1:8080/ping-pong/pong"));
			client.sendMessage("Hello from client");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

}
