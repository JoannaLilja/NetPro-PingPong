package client.net;

import static org.junit.Assert.*;

import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

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
		toServer.sendCommand(GameCommand.QUIT);
	}

	@Test
	public void testSendCommand() {
		
		
		AppletFrame af = new AppletFrame("Client 1");
		AppletFrame af2 = new AppletFrame("Client 2");
		
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
		
		af.closeFrame();
		af2.closeFrame();

		assertTrue("Command had no effect", y1!=y0);
		
	}
	
	@Test
	public void testSameState() {
		
		
		AppletFrame p1 = new AppletFrame("Player 1");
		AppletFrame p2 = new AppletFrame("Player 2");
		
		try {
			Thread.sleep(5500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		double p1y = p1.getPongBoard().getBall().getY();
		double p2y = p2.getPongBoard().getBall().getY();
		
		p1.closeFrame();
		p2.closeFrame();
		
		assertTrue("Players don't have same state", p1y == p2y);
		
	}
	
	@Test
	public void testPlayerDisconnect() {
		AppletFrame p1 = new AppletFrame("Player 1");
		AppletFrame p2 = new AppletFrame("Player 2");
		
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		p1.closeFrame();
		
		AppletFrame p3 = new AppletFrame("Player 3");
		
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		double p2y = p2.getPongBoard().getBall().getY();
		double p3y = p3.getPongBoard().getBall().getY();
		
		p2.closeFrame();
		p3.closeFrame();
		
		assertTrue("Didn't match player 2 and player 3.", p2y == p3y);
		
	}

}
