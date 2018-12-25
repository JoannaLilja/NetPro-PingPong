package client.view;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JFrame;
import javax.websocket.DeploymentException;

import client.net.WebClient;
import shared.Variables;

public class AppletFrame extends JFrame
{
		
	public AppletFrame(String title)
	{
		
		super(title);
		
		PongBoard pongBoard = new PongBoard();
		WebClient toServer = null;
		
		try {
			toServer = new WebClient(
					new URI("ws://127.0.0.1:8080/ping-pong/pong"),
					new ViewUpdateHandler(pongBoard)
					);
		} catch (URISyntaxException ignore) {
			// Don't need to do anything.
		} catch (DeploymentException | IOException e) {
			System.out.println("Failed to connect to server. Please try again later.");
			System.exit(0);
		} 
		
	    this.add(pongBoard);
	    this.setSize(Variables.BOARD_WIDTH+22,Variables.BOARD_HEIGHT+56);
	    this.setLocation((int)Variables.X_POSITION,(int)Variables.Y_POSITION);
	    this.setVisible(true);

	    pongBoard.init(toServer);
		
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	pongBoard.close();
		    	System.exit(0);
		    }
		});
	}

}
