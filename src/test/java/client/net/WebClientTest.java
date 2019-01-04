package client.net;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.websocket.DeploymentException;

import org.junit.Test;

import client.view.AppletFrame;
import shared.GameCommand;

public class WebClientTest
{
	
	private WebClient connect()
	{
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
		
		return toServer;
	}

	@Test
	public void testWebClient() {
		
		WebClient toServer = connect();
		assertTrue("Failed to connect to server", toServer != null);
	}

	@Test
	public void testSendCommand(GameCommand UP) {
		
		/*WebClient toServer = connect();
		
		toServer.
		
		
		if(toServer==null)
			fail("Failed to connect to server");
		
		toServer.sendCommand(GameCommand.UP);*/
		
		AppletFrame af = new AppletFrame("Client 1");
		
		int y0 = af.getPongBoard().getPaddle().getY();
		
		af.getWebClient().sendCommand(UP);
		
		int loops = 0;
		
		int y1 = y0;
		while(y0 == y1 && loops < 10)
		{
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			y1 = af.getPongBoard().getPaddle().getY();
			loops++;
		}
		
		
		assertTrue("Failed to connect to server", y1!=y0);
		
		
	}

}
