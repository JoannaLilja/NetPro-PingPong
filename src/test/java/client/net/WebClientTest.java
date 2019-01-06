package client.net;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.websocket.DeploymentException;

import org.junit.Test;

import client.view.AppletFrame;
import client.view.ViewUpdateHandler;
import shared.GameCommand;

public class WebClientTest
{
	
	private WebClient connect()
	{
		WebClient toServer = null;

		try {
			toServer = new WebClient(
					new URI("ws://127.0.0.1:8080/ping-pong/pong"),
					new ViewUpdateHandler(null)
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
	public void testSendCommand() {
		
		
		/*WebClient toServer = connect();
		
		toServer.
		
		
		if(toServer==null)
			fail("Failed to connect to server");
		
		toServer.sendCommand(GameCommand.UP);*/
		
		AppletFrame af = new AppletFrame("Client 1");
		new AppletFrame("Client 2");
		
		int y0 = af.getPongBoard().getPaddle().getY();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		assertTrue("Webclient is null...", af.getWebClient() != null);
		af.getWebClient().sendCommand(GameCommand.UP);
		
		int loops = 0;
		
		int y1 = y0;
		while(y0 == y1 && loops < 10)
		{
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			y1 = af.getPongBoard().getPaddle().getY();
			loops++;
		}
		
		
		assertTrue("Command had no effect", y1!=y0);
		
		
	}

}
