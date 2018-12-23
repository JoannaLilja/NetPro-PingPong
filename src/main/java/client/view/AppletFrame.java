package client.view;

import javax.swing.JFrame;

import shared.Variables;

public class AppletFrame extends JFrame
{
		
	public AppletFrame(String title)
	{
		
		super(title);
		
		PongBoard pongBoard = new PongBoard();
	    this.add(pongBoard);
	    this.setSize(Variables.BOARD_WIDTH+22,Variables.BOARD_HEIGHT+56);
	    this.setLocation((int)Variables.X_POSITION,(int)Variables.Y_POSITION);
	    this.setVisible(true);

	    pongBoard.init();
		
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	// TODO do your stuff in here
		    }
		});
	}

}
