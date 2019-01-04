package client.net;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.websocket.DeploymentException;

import org.junit.Test;

public class WebClientTest {

	@Test
	public void testWebClient() {
		WebClient toServer = null;
		
		try {
			toServer = new WebClient(
					new URI("ws://127.0.0.1:8080/ping-pong/pong"),
					null
					);
		} catch (URISyntaxException ignore) {
		} catch (DeploymentException | IOException e) {
			toServer = null;
		}
		
		assertTrue("Failed to connect to server", toServer != null);
	}

	@Test
	public void testSendCommand() {
		fail("Not yet implemented");	
	}

}
